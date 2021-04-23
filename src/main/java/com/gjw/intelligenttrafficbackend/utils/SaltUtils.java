package com.gjw.intelligenttrafficbackend.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author 郭经伟
 * @Date 2021/4/12
 * 生成随机盐
 **/
@Component
public class SaltUtils {

    /**
     * 生成盐
     * @param n 生成盐的位数
     * @return
     */
    public static String getSalt(int n){

        char[] chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<n;i++){

            sb.append(chars[new Random().nextInt(chars.length)]);

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = SaltUtils.getSalt(8);
        System.out.println(salt);
    }

}
