package com.gjw.intelligenttrafficbackend.controller;

import com.gjw.intelligenttrafficbackend.entity.User;
import com.gjw.intelligenttrafficbackend.service.UserService;
import com.gjw.intelligenttrafficbackend.utils.R;
import com.gjw.intelligenttrafficbackend.utils.ResultCode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 * <p>
 * springboot vue shiro前后端分离
 **/

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;

    //    @RequiresRoles(value = {"admin","user"})
//    @RequiresPermissions("admin:usermanage:login")
    @PostMapping("login")
    public R login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequiresPermissions("admin:usermanage:register")
    @RequiresRoles(value = {"admin"})
    @PostMapping("register")
    public R register(@RequestBody User user) {
        return userService.register(user);
    }


    @RequiresRoles("admin")
    @GetMapping("list")
    public R getAllUsers() {

        List<User> users = userService.list();
        if (!CollectionUtils.isEmpty(users)) {
            return R.ok().messageAndCode(ResultCode.FIND_ALL_USERS_SUCCESS).data("users", users);
        } else {
            return R.error().messageAndCode(ResultCode.FIND_ALL_USERS_FAIL);
        }

    }

    @RequiresRoles("admin")
    @GetMapping("getUserById")
    public R getUserById(@RequestParam("userId") Integer userId) {
        User user = userService.getById(userId);
        if (user != null) {
            return R.ok().messageAndCode(ResultCode.FIND_ONE_USER_SUCCESS).data("user", user);
        } else {
            return R.error().messageAndCode(ResultCode.FIND_ONE_USER_FAIL);
        }
    }

    /**
     * 编辑一个User
     *
     * @param user
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/updateOneUser")
    public R updateOneRole(@RequestBody User user) {

        boolean flag = userService.updateById(user);
        if (flag) {
            return R.ok().messageAndCode(ResultCode.UPDATE_ONE_USER_SUCCESS);
        } else {
            return R.error().messageAndCode(ResultCode.UPDATE_ONE_USER_FAIL);
        }
    }


    /**
     * 删除一个User
     *
     * @param userId
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("/deleteOneUser")
    public R deleteOneUser(Integer userId) {
        boolean flag = userService.removeById(userId);
        if (flag) {
            return R.ok().messageAndCode(ResultCode.DELETE_ONE_USER_SUCCESS);
        } else {
            return R.error().messageAndCode(ResultCode.DELETE_ONE_USER_FAIL);
        }
    }
}
