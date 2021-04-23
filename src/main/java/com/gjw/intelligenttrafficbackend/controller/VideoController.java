package com.gjw.intelligenttrafficbackend.controller;

import com.gjw.intelligenttrafficbackend.entity.BreakRules;
import com.gjw.intelligenttrafficbackend.entity.Video;
import com.gjw.intelligenttrafficbackend.service.BreakRulesService;
import com.gjw.intelligenttrafficbackend.service.VideoService;
import com.gjw.intelligenttrafficbackend.utils.CallPythonScript;
import com.gjw.intelligenttrafficbackend.utils.FileUtils;
import com.gjw.intelligenttrafficbackend.utils.R;
import com.gjw.intelligenttrafficbackend.utils.ResultCode;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 郭经伟
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    /**
     * 源视频保存位置
     */
    private static final String SOURCE_VIDEO_PATH = "C:\\毕设项目\\source\\";

    /**
     * 目标视频保存位置
     */
    private static final String TARGET_VIDEO_PATH = "C:\\毕设项目\\target\\";

    private static final String TXT_FILE_PATH = "C:\\Users\\14478\\Desktop\\yolov3_deepsort\\weiguixinxi.txt";

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Resource
    private VideoService videoService;

    @Resource
    private BreakRulesService breakRulesService;

    /**
     * 处理上传的视频 交给python脚本训练
     * 步骤:
     * 1.上传视频到后端 后端接受视频
     * 2.后端保存视频到指定位置
     * 3.后端将这个视频的位置送给python算法处调用
     * 4.python算法将处理后的视频输出到指定位置
     * 5.将处理后的视频路径插入数据库
     * 6.读取生成的交通违章.txt 插入到数据库中
     *
     * @param file 上传的视频
     * @return
     */
    @RequiresRoles("admin")
    @PostMapping("videoHandle")
    public R videoHandle(MultipartFile file) {
        //步骤1. 上传视频到后端 后端接受视频
        String filename = file.getOriginalFilename();
        /**
         * 文件类型非视频格式 直接返回失败
         */
        if (!(filename.endsWith(".mp4") || filename.endsWith(".avi"))) {
            return R.error().messageAndCode(ResultCode.WRONG_FILE_TYPE);
        }
        // 将上传的文件保存到指定位置
        String format = sdf.format(new Date());
        //初始化文件
        File folder = new File(SOURCE_VIDEO_PATH);
        if (!folder.exists()) {
            //如果文件不存在 则创建文件夹
            folder.mkdirs();
        }
        // 20211001_test1.mp4
        String sourceVideoSaveName = format + "_" + filename;
        //步骤2. 后端保存视频到指定位置
        try {
            //保存到本地
            file.transferTo(new File(folder, sourceVideoSaveName));
        } catch (IOException e) {
            e.printStackTrace();
            return R.error().messageAndCode(ResultCode.SAVE_FAIL);

        }
        //步骤3. 后端将这个视频的位置送给python算法处调用
        //步骤4. python算法将处理后的视频输出到指定位置
        // 20211001_result_test1.avi
        String realFileName = FileUtils.getFileNameNoEx(filename);
        String targetVideoSaveName = format + "_" + realFileName + ".avi";
        String videoPath = SOURCE_VIDEO_PATH + sourceVideoSaveName;
        String outPath = TARGET_VIDEO_PATH + targetVideoSaveName;
        File outputFolder = new File(TARGET_VIDEO_PATH);
        if (!outputFolder.exists()) {
            //如果文件不存在 则创建文件夹
            outputFolder.mkdirs();
        }
        new Thread(() -> {
            // 开启一个子线程去处理视频
            CallPythonScript.CallPython(videoPath, outPath);
            //步骤5. 将处理后的视频路径插入数据库
            Video video = new Video(null, targetVideoSaveName, outPath, LocalDateTime.now(), LocalDateTime.now());
            videoService.save(video);
            //步骤6. 读取生成的交通违章.txt 插入到数据库中
            List<BreakRules> breakRulesList = FileUtils.readBreakRulesByTxt(TXT_FILE_PATH);
            breakRulesList.stream().distinct().forEach(breakRules -> {
                breakRules.setCreateTime(LocalDateTime.now());
                breakRules.setUpdateTime(LocalDateTime.now());
                breakRules.setVideoId(video.getId());
                breakRulesService.save(breakRules);
            });
        }).start();
        return R.ok().messageAndCode(ResultCode.HANDLING_THE_VIDEO);
    }
    @GetMapping("list")
    public R listAllVideo() {
        List<Video> videos = videoService.list();
        if (!CollectionUtils.isEmpty(videos)) {
            return R.ok().messageAndCode(ResultCode.FIND_ALL_VIDEO_SUCCESS).data("videos", videos);
        } else {
            return R.error().messageAndCode(ResultCode.FIND_ALL_VIDEO_FAIL);
        }
    }

}

