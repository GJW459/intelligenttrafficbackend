package com.gjw.intelligenttrafficbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 账户名
     */
    private String username;
    private String password;
    private String salt;

    /**
     * 是否禁用
     */
    private Integer status;

    /**
     * 更新时间
     * JsonFormat 后端=>前端 日期格式化
     * DateTimeFormat 前端=>后端 日期格式化
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    /**
     * 一个用户会有多个角色
     */
    @TableField(exist = false)
    private List<Role> roles;

}
