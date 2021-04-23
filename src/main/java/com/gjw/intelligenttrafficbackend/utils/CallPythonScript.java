package com.gjw.intelligenttrafficbackend.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 郭经伟
 * @Date 2021/4/22
 * 调用Python脚本
 **/
public class CallPythonScript {


    /**
     * @param videoPath 输入的视频路径
     * @param outPath   输出的路径
     */
    public static void CallPython(String videoPath, String outPath) {
        Runtime runtime = Runtime.getRuntime();
        try {
            // 需要执行的cmd命令
            String cmd = "cmd /c " +
                    "cd C:\\Users\\14478\\Desktop\\yolov3_deepsort&&" +
                    "conda activate tracker-gpu" +
                    "&&python 提取背景.py --video " + videoPath +
                    "&&python 车道线标定.py" +
                    "&&python object_tracker.py --video " + videoPath + " --output " + outPath;
            // 执行cmd命令
            Process process = runtime.exec(cmd);
            //关闭流释放资源
            if (process != null) {
                process.getOutputStream().close();
            }
            InputStream in = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String tmp = null;
            while ((tmp = br.readLine()) != null) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("执行结束");
    }

    public static void main(String[] args) {
        CallPythonScript.CallPython("data/video/test1.mp4","./data/video/results7.avi");
    }

}
