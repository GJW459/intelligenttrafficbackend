package com.gjw.intelligenttrafficbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gjw.intelligenttrafficbackend.entity.Role;
import com.gjw.intelligenttrafficbackend.entity.User;
import com.gjw.intelligenttrafficbackend.utils.R;

import java.util.List;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 **/
public interface UserService extends IService<User> {

    R register(User user);
    R login(User user);
    List<Role> findRolesByUsername(String username);

    List<User> findAllUsers();
}
