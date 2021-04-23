package com.gjw.intelligenttrafficbackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gjw.intelligenttrafficbackend.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 **/
public interface UserDao extends BaseMapper<User> {

    User findRolesByUserName(@Param("username") String username);
    List<User> findAllUsers();
}
