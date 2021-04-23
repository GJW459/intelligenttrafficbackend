package com.gjw.mybatisplusgenerator.service.impl;

import com.gjw.mybatisplusgenerator.entity.User;
import com.gjw.mybatisplusgenerator.mapper.UserDao;
import com.gjw.mybatisplusgenerator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 郭经伟
 * @since 2021-04-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
