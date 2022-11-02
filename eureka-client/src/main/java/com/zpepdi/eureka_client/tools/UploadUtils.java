package com.zpepdi.eureka_client.tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class UploadUtils {

    public static Map<String,Object> upload(MultipartFile multipartFile, Map<String,Object> map){
        if (multipartFile == null){
            return null;
        }
        //获取文件的原名称 getOriginalFilename
        String OriginalFilename = multipartFile.getOriginalFilename();
        //获取时间戳和文件的扩展名，拼接成一个全新的文件名； 用时间戳来命名是为了避免文件名冲突
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        //定义文件存放路径
        String filePath = "D:\\zpepdi_system\\scientificFile\\" + map.get("projectId").toString() + "\\";
        //新建一个目录（文件夹）
        File dest = new File(filePath + fileName);
        //判断filePath目录是否存在，如不存在，就新建一个
        if (!dest.getParentFile().canExecute()){
            dest.getParentFile().mkdirs(); //新建一个目录
        }
        try {
            //文件输出
            multipartFile.transferTo(dest);
        }
        catch ( Exception e) {
            e.printStackTrace();
            //拷贝失败要有提示
            return null;
        }
        map.put("name", OriginalFilename);
        map.put("path", filePath + fileName);
        return map;
    }
}
