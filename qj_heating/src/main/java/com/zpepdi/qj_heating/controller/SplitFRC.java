package com.zpepdi.qj_heating.controller;


import com.zpepdi.qj_heating.Utils.FileZipUtil;
import com.zpepdi.qj_heating.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("SplitFRC")
@CrossOrigin(origins = "*")
public class SplitFRC {

    @RequestMapping("savedif")
    public Result savedif(@RequestBody Map<String,String> map){
        String path  = map.get("path");
        String str  = map.get("str");
        //下载目录，确保不重复
        String directoryPath = "C:\\ceshiFRCquebaobucongfu";
        File file2 = new File(directoryPath);
        if (!file2.exists()){
            //创建文件夹
            if (file2.mkdirs()){
                System.out.println("创建" + directoryPath + "成功");
            } else {
                System.out.println("创建" + directoryPath + "失败");
            }
        }
        path = directoryPath+"\\"+path;
        File file = new File(path);
        //返回true 表示创建文件成功
        //false 表示文件已经存在
        try {
            if (file.createNewFile()) {
                System.out.println("文件创建成功");
            } else {
                System.out.println("文件已经存在不需要重复创建");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 使用FileWriter写文件
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @GetMapping("/download")
    public void download(String filename, HttpServletResponse response) throws IOException {
        FileZipUtil.exportZip(response,"C:\\ceshiFRCquebaobucongfu\\",filename,".zip");
    }

    @RequestMapping("delete")
    public void delete(){
        File file = new File("C:\\ceshiFRCquebaobucongfu");
        deleteFile(file);
    }

    private static void deleteFile(File file) {
        if (!file.exists()) {
            System.out.println("文件不存在");
        } else {
            if (file.isDirectory()) {
                System.out.println("这是一个文件夹");
                File[] files = file.listFiles();
                Arrays.stream(files).forEach(item -> {
                    if (item.isDirectory()) {
                        deleteFile(item);
                    }
                    try {
                        Files.delete(item.toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
