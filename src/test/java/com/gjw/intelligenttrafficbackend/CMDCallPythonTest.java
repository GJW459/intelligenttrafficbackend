package com.gjw.intelligenttrafficbackend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 郭经伟
 * @Date 2021/4/16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CMDCallPythonTest {


    @Test
    public void test1() {



        Runtime runtime = Runtime.getRuntime();
        try {
            // 需要执行的cmd命令
            String cmd="cmd /c cd C:\\Users\\14478\\Desktop\\yolov3_deepsort&&conda activate tracker-gpu&&python object_tracker.py --video ./data/video/test1.mp4 --output ./data/video/results1.avi";
            // 执行cmd命令
            Process process = runtime.exec(cmd);
            //关闭流释放资源
            if (process!=null){
                process.getOutputStream().close();
            }
            InputStream in=process.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            String tmp=null;
            while ((tmp=br.readLine())!=null){

            }

        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("执行结束");
    }

}
