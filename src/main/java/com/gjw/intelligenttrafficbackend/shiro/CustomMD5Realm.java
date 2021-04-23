package com.gjw.intelligenttrafficbackend.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gjw.intelligenttrafficbackend.dao.UserDao;
import com.gjw.intelligenttrafficbackend.entity.Permission;
import com.gjw.intelligenttrafficbackend.entity.Role;
import com.gjw.intelligenttrafficbackend.entity.User;
import com.gjw.intelligenttrafficbackend.service.RoleService;
import com.gjw.intelligenttrafficbackend.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 **/
public class CustomMD5Realm extends AuthorizingRealm {



    @Resource
    private UserDao userDao;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 获得身份信息 =username =>需要得到userId
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("调用授权验证："+primaryPrincipal);
        // 根据主身份获得角色和权限信息
        List<Role> roles = userService.findRolesByUsername(primaryPrincipal);
        if (!CollectionUtils.isEmpty(roles)){
            //集合不为空
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            roles.forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                List<Permission> permissions = roleService.findPermissionByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(permissions)){
                    //查询的permission不为空
                    permissions.forEach(permission -> simpleAuthorizationInfo.addStringPermission(permission.getAction()));
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        Integer count = userDao.selectCount(new QueryWrapper<User>().eq("username", username));
        if (count==1){
            //数据库中存在这个用户
            //数据库中的密码
            String dbPassword = userDao.selectOne(new QueryWrapper<User>().eq("username", username)).getPassword();
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, dbPassword, this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
