package com.gjw.intelligenttrafficbackend.controller;
import com.gjw.intelligenttrafficbackend.entity.Permission;
import com.gjw.intelligenttrafficbackend.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {


    @Resource
    private PermissionService permissionService;

    @RequiresRoles("admin")
    @GetMapping("list")
    public R getAllPermissions(){
        List<Permission> permissions = permissionService.list();
        if (!CollectionUtils.isEmpty(permissions)){
            return R.ok().messageAndCode(ResultCode.FIND_ALL_PERMISSIONS_SUCCESS).data("permissions",permissions);
        }else{
            return R.error().messageAndCode(ResultCode.FIND_ALL_PERMISSIONS_FAIL);
        }
    }

    @RequiresRoles("admin")
    @GetMapping("getPermissionById")
    public R getPermissionById(Integer permissionId){
        Permission permission = permissionService.getById(permissionId);
        if (permission!=null){
            return R.ok().messageAndCode(ResultCode.FIND_ONE_PERMISSIONS_SUCCESS).data("permission",permission);
        }else {
            return R.error().messageAndCode(ResultCode.FIND_ONE_PERMISSIONS_FAIL);
        }
    }


    /**
     * 编辑一个Permission
     * @param permission
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/updateOnePermission")
    public R updateOnePermission(@RequestBody Permission permission){

        boolean flag = permissionService.updateById(permission);
        if (flag){
            return R.ok().messageAndCode(ResultCode.UPDATE_ONE_ROLE_SUCCESS);
        }else {
            return R.error().messageAndCode(ResultCode.UPDATE_ONE_ROLE_FAIL);
        }
    }

    /**
     * 增加一个Permission
     * @param permission
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("/saveOnePermission")
    public R saveOnePermission(@RequestBody Permission permission){
        boolean flag = permissionService.save(permission);
        if (flag){
            return R.ok().messageAndCode(ResultCode.INSERT_ONE_PERMISSION_SUCCESS);
        }else {
            return R.error().messageAndCode(ResultCode.INSERT_ONE_PERMISSION_FAIL);
        }
    }

    /**
     * 删除一个Permission
     * @param permissionId
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("/deleteOnePermission")
    public R deleteOnePermission(Integer permissionId){
        boolean flag = permissionService.removeById(permissionId);
        if (flag){
            return R.ok().messageAndCode(ResultCode.DELETE_ONE_PERMISSION_SUCCESS);
        }else {
            return R.error().messageAndCode(ResultCode.DELETE_ONE_PERMISSION_FAIL);
        }
    }

}

