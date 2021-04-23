package com.gjw.intelligenttrafficbackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gjw.intelligenttrafficbackend.dao.UserDao;
import com.gjw.intelligenttrafficbackend.entity.Role;
import com.gjw.intelligenttrafficbackend.entity.User;
import com.gjw.intelligenttrafficbackend.service.UserService;
import com.gjw.intelligenttrafficbackend.utils.R;
import com.gjw.intelligenttrafficbackend.utils.ResultCode;
import com.gjw.intelligenttrafficbackend.utils.SaltUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 **/
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


    @Resource
    private UserDao userDao;

    /**
     * 注册
     * @param user
     * @return
     */
    @Transactional
    @Override
    public R register(User user) {

        /**
         * 先查询是否已经有这个账户 有则注册失败
         * 否则注册成功
         */
        Integer count = userDao.selectCount(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (count == 1) {
            //已经有这个账户了
            return R.error().messageAndCode(ResultCode.HAS_ACCOUNT);
        } else {

            // 插入成功
            try {
                String salt = SaltUtils.getSalt(8);
                Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
                user.setPassword(md5Hash.toHex());
                user.setSalt(salt);
                userDao.insert(user);
                return R.ok().messageAndCode(ResultCode.REGISTER_SUCCESS);
            }catch (Exception e){
                e.printStackTrace();
                return R.error().messageAndCode(ResultCode.REGISTER_FAIL);
            }

        }
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Transactional
    @Override
    public R login(User user) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            subject=SecurityUtils.getSubject();
            String username = (String) subject.getPrincipal();
            if (subject.hasRole("admin")){
                System.out.println("有此角色");
            }
            return R.ok().messageAndCode(ResultCode.LOGIN_SUCCESS)
                    .data("token",subject.getSession().getId())
                    .data("username",username);
        }catch (IncorrectCredentialsException e){

            return R.error().messageAndCode(ResultCode.PASSWORD_IS_WRONG);

        }catch (UnknownAccountException e){
            return R.error().messageAndCode(ResultCode.USERNAME_IS_WRONG);
        }

    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        return userDao.findRolesByUserName(username).getRoles();
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }
}
