package com.gjw.intelligenttrafficbackend.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gjw.intelligenttrafficbackend.dao.UserDao;
import com.gjw.intelligenttrafficbackend.entity.User;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 * 自定义MD5凭证
 **/
public class CustomMD5CredentialMatcher extends SimpleCredentialsMatcher {

    @Resource
    private UserDao userDao;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        /**
         * 获得用户输入的账号和密码
         */
        String username = (String) usernamePasswordToken.getPrincipal();
        String inPassword = String.valueOf(usernamePasswordToken.getPassword());

        /**
         * 数据库中的密码
         */
        String dbPassword = (String) info.getCredentials();

        /**
         * 查询数据库中的盐
         */

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("salt");
        userQueryWrapper.eq("username", username);
        String salt = userDao.selectOne(userQueryWrapper).getSalt();
        Md5Hash md5Hash = new Md5Hash(inPassword, salt, 1024);
        String md5Password = md5Hash.toHex();
        return Objects.equals(md5Password, dbPassword);
    }
}
