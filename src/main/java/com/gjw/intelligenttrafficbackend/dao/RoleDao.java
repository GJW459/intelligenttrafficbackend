package com.gjw.intelligenttrafficbackend.dao;

import com.gjw.intelligenttrafficbackend.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 郭经伟
 * @since 2021-04-19
 */
public interface RoleDao extends BaseMapper<Role> {

    Role findPermissionsByRoleId(@Param("id") Integer roleId);

}
