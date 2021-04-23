package com.gjw.intelligenttrafficbackend.utils;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 **/
public enum ResultCode  {

    LOGIN_SUCCESS("登录成功",200000),
    HAS_ACCOUNT("此用户已存在",200001),
    REGISTER_SUCCESS("注册成功",200002),
    REGISTER_FAIL("注册失败",200003),
    PASSWORD_IS_WRONG("密码错误",200004),
    USERNAME_IS_WRONG("用户不存在",200005),
    FIND_ALL_ROLES_SUCCESS("查询所有角色成功",200006),
    FIND_ALL_ROLES_FAIL("查询所有角色失败",200007),
    FIND_ONE_ROLE_SUCCESS("查询角色成功",200008),
    FIND_ONE_ROLE_FAIL("查询角色失败",200009),
    UPDATE_ONE_ROLE_SUCCESS("修改一个角色成功",200010),
    UPDATE_ONE_ROLE_FAIL("修改一个角色失败",200011),
    INSERT_ONE_ROLE_SUCCESS("增加一个角色成功",200012),
    INSERT_ONE_ROLE_FAIL("增加一个角色失败",200013),
    DELETE_ONE_ROLE_SUCCESS("删除一个角色成功",200014),
    DELETE_ONE_ROLE_FAIL("删除一个角色失败",200015),
    FIND_ALL_PERMISSIONS_SUCCESS("查询所有权限成功",200016),
    FIND_ALL_PERMISSIONS_FAIL("查询所有权限失败",200017),
    FIND_ONE_PERMISSIONS_SUCCESS("查询权限成功",200018),
    FIND_ONE_PERMISSIONS_FAIL("查询权限失败",200019),
    UPDATE_ONE_PERMISSION_SUCCESS("修改一个权限成功",200020),
    UPDATE_ONE_PERMISSION_FAIL("修改一个权限失败",200021),
    INSERT_ONE_PERMISSION_SUCCESS("增加一个权限成功",200022),
    INSERT_ONE_PERMISSION_FAIL("增加一个权限失败",200023),
    DELETE_ONE_PERMISSION_SUCCESS("删除一个权限成功",200024),
    DELETE_ONE_PERMISSION_FAIL("删除一个权限失败",200025),
    FIND_ALL_USERS_SUCCESS("查询所有用户成功",200026),
    FIND_ALL_USERS_FAIL("查询所有用户失败",200027),
    FIND_ONE_USER_SUCCESS("查询用户成功",200028),
    FIND_ONE_USER_FAIL("查询用户失败",200029),
    UPDATE_ONE_USER_SUCCESS("修改一个用户成功",200030),
    UPDATE_ONE_USER_FAIL("修改一个用户失败",200031),
    INSERT_ONE_USER_SUCCESS("增加一个用户成功",200032),
    INSERT_ONE_USER_FAIL("增加一个用户失败",200033),
    DELETE_ONE_USER_SUCCESS("删除一个用户成功",200034),
    DELETE_ONE_USER_FAIL("删除一个用户失败",200035),
    WRONG_FILE_TYPE("文件类型不对",200036),
    SAVE_FAIL("保存文件失败",200037),
    SAVE_SUCCESS("保存文件成功",200038),
    HANDLING_THE_VIDEO("正在处理视频请稍后",200039),
    FIND_ALL_BREAK_RULES_SUCCESS("查询所有交通违规成功",200040),
    FIND_ALL_BREAK_RULES_FAIL("查询所有交通违规失败",200041),
    FIND_ALL_VIDEO_SUCCESS("查询所有处理后的交通视频成功",200042),
    FIND_ALL_VIDEO_FAIL("查询所有处理后的交通视频失败",200043);
    private String message;
    private Integer code;

    ResultCode(String message, Integer code){
        this.message=message;
        this.code=code;
    }


    public static String getMessageByCode(Integer code){
        for(ResultCode resultCode: ResultCode.values()){
            if (resultCode.getCode()==code){
                return resultCode.getMessage();
            }
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
