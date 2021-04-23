package com.gjw.intelligenttrafficbackend.controller;


import com.gjw.intelligenttrafficbackend.entity.Role;
import com.gjw.intelligenttrafficbackend.service.RoleService;
import com.gjw.intelligenttrafficbackend.utils.R;
import com.gjw.intelligenttrafficbackend.utils.ResultCode;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 郭经伟
 * @since 2021-04-19
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 获取所有角色信息
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("list")
    public R getAllRoles(){
        List<Role> roles = roleService.getAllRoles();
        if (!CollectionUtils.isEmpty(roles)){
            // roles不为空
            return R.ok().messageAndCode(ResultCode.FIND_ALL_ROLES_SUCCESS).data("roles",roles);
        }else {
            return R.error().messageAndCode(ResultCode.FIND_ALL_ROLES_FAIL);

        }
    }

    /**
     * 根据roleId查询Role
     * @param roleId
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("/getRoleById")
    public R getRoleById(Integer roleId){
        Role role = roleService.getRoleById(roleId);
        if (role!=null){
            return R.ok().messageAndCode(ResultCode.FIND_ONE_ROLE_SUCCESS).data("role",role);
        }else
        {
            return R.error().messageAndCode(ResultCode.FIND_ONE_ROLE_FAIL);
        }
    }

    /**
     * 编辑一个Role
     * @param role
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/updateOneRole")
    public R updateOneRole(@RequestBody Role role){

        int count = roleService.updateOneRole(role);
        if (count>0){
            //更新成功
            return R.ok().messageAndCode(ResultCode.UPDATE_ONE_ROLE_SUCCESS);
        }
        else {
            //更新失败
            return R.error().messageAndCode(ResultCode.UPDATE_ONE_ROLE_FAIL);

        }
    }

    /**
     * 增加一个Role
     * @param role
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/saveOneRole")
    public R saveOneRole(@RequestBody Role role){
        int count = roleService.saveOneRole(role);
        if (count>0){
            return R.ok().messageAndCode(ResultCode.INSERT_ONE_ROLE_SUCCESS);
        }else
        {
            return R.error().messageAndCode(ResultCode.INSERT_ONE_ROLE_FAIL);
        }
    }

    /**
     * 删除一个Role
     * @param roleId
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("/deleteOneRole")
    public R deleteOneRole(Integer roleId){
        int count = roleService.deleteOneRole(roleId);
        if (count>0){
            return R.ok().messageAndCode(ResultCode.DELETE_ONE_ROLE_SUCCESS);
        }else{
            return R.error().messageAndCode(ResultCode.DELETE_ONE_ROLE_FAIL);
        }
    }

}

