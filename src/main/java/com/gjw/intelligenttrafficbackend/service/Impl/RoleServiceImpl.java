package com.gjw.intelligenttrafficbackend.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gjw.intelligenttrafficbackend.dao.RoleDao;
import com.gjw.intelligenttrafficbackend.entity.Permission;
import com.gjw.intelligenttrafficbackend.entity.Role;
import com.gjw.intelligenttrafficbackend.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 郭经伟
 * @since 2021-04-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public List<Permission> findPermissionByRoleId(Integer id) {
        return roleDao.findPermissionsByRoleId(id).getPermissions();
    }

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleDao.selectList(null);
        return roles;
    }

    @Override
    public Role getRoleById(Integer roleId) {
        Role role = roleDao.selectOne(new QueryWrapper<Role>().eq("id", roleId));
        return role;
    }

    @Transactional
    @Override
    public int updateOneRole(Role role) {
        int count = roleDao.updateById(role);
        return count;
    }

    @Transactional
    @Override
    public int saveOneRole(Role role) {
        return roleDao.insert(role);
    }

    @Transactional
    @Override
    public int deleteOneRole(Integer roleId) {
        return roleDao.deleteById(roleId);
    }
}
