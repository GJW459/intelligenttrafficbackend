package com.gjw.intelligenttrafficbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 郭经伟
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("break_rules")
public class BreakRules implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 违章id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车牌
     */
    private String licensePlate;

    /**
     * 违章原因
     */
    private String reason;

    /**
     * 对应video id
     */
    private Integer videoId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BreakRules that = (BreakRules) o;
        return (Objects.equals(licensePlate, that.licensePlate) &&
                Objects.equals(reason, that.reason)) ||
                (Objects.equals(licensePlate, that.licensePlate) && !Objects.equals(reason, that.reason));
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, reason);
    }
}
