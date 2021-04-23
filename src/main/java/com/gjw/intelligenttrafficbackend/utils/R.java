package com.gjw.intelligenttrafficbackend.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 **/
@Data
public class R {


    /**
     * 是否返回成功
     */
    private Boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * code
     */
    private Integer code;

    /**
     * 返回的状态码和消息
     */
    private ResultCode codeAndMessage;
    /**
     * 返回的数据
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    // 构造器方法私有
    private R() {

    }

    /**
     * 成功静态方法
     *
     * @return
     */
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        return r;
    }

    public R messageAndCode(ResultCode messageAndCode) {
        this.setCodeAndMessage(messageAndCode);
        this.setCode(messageAndCode.getCode());
        this.setMessage(messageAndCode.getMessage());
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
