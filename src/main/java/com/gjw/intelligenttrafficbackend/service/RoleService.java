package com.gjw.intelligenttrafficbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gjw.intelligenttrafficbackend.entity.Permission;
import com.gjw.intelligenttrafficbackend.entity.Role;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 郭经伟
 * @since 2021-04-19
 */
public interface RoleService extends IService<Role> {

    List<Permission> findPermissionByRoleId(Integer id);
    /**
     * 获取所有角色信息
     * @return
     */
    List<Role> getAllRoles();
    /**
     * 根据roleId查询Role
     * @param roleId
     * @return
     */
    Role getRoleById(Integer roleId);

    /**
     * 编辑一个Role
     * @param role
     * @return
     */
    int updateOneRole(Role role);

    /**
     * 增加一个Role
     * @param role
     * @return
     */
    int saveOneRole(Role role);

    /**
     * 删除一个Role
     * @param roleId
     * @return
     */
    int deleteOneRole(Integer roleId);

}
