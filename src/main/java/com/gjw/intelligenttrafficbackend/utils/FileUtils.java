package com.gjw.intelligenttrafficbackend.utils;

import com.gjw.intelligenttrafficbackend.entity.BreakRules;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郭经伟
 * @Date 2021/4/23
 * 文件读取
 **/
public class FileUtils {
    /**
     * 通过文本文件读取交通违规信息
     *
     * @param filePath
     * @return
     */
    public static List<BreakRules> readBreakRulesByTxt(String filePath) {

        List<String> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            InputStreamReader input = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader bf = new BufferedReader(input);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                list.add(str);
            }
            bf.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<BreakRules> breakRulesList = new ArrayList<>();
        for (int i = 0; i < list.size(); i = i + 2) {
            BreakRules breakRules = new BreakRules();
            breakRules.setLicensePlate(list.get(i));
            breakRules.setReason(list.get(i + 1));
            breakRulesList.add(breakRules);
        }
        return breakRulesList;
    }

    /**
     * java文件操作 获得不带扩展名的文件名
     *
     * @param fileName
     * @return
     */
    public static String getFileNameNoEx(String fileName) {

        if ((fileName != null) && (fileName.length() > 0)) {
            // 当字符串不为空 并且不为空串时执行
            // 获得最后一个.的位置索引
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(0, dot);
            }
        }
        return fileName;
    }

    public static void main(String[] args) {

        String fileName = "C:\\Users\\14478\\Desktop\\yolov3_deepsort\\weiguixinxi.txt";
        List<BreakRules> breakRulesList = FileUtils.readBreakRulesByTxt(fileName);
        System.out.println("=================");
        breakRulesList.stream().distinct().forEach(System.out::println);
        String str = "20210423_result.avi";
        String fileNameNoEx = FileUtils.getFileNameNoEx(str);
        System.out.println(fileNameNoEx);
    }
}
