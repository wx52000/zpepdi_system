package com.zpepdi.qj_heating.controller;

import com.zpepdi.qj_heating.Utils.ExcelUtils;
import com.zpepdi.qj_heating.Utils.FileUtil;
import com.zpepdi.qj_heating.result.Result;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("dynamic")
@CrossOrigin(origins = "*")
public class dynamicsMax {

    @RequestMapping("dynamiclujing")
    public Result dynamiclujing(@RequestBody Map<String,String> map ){
        //得到文件夹路径
        String lujing = map.get("lujing");
        //荷载文件数据
        Map<String,List<List<String>>> sumdata = new LinkedHashMap<>();
        Map<String,List<String>> newsumdata = new LinkedHashMap<>();
        //位移文件数据
        Map<String,List<List<String>>> sumdata2 = new LinkedHashMap<>();
        Map<String,List<String>> newsumdata2 = new LinkedHashMap<>();
        //得到荷载文件
        List<File> allFile = getAllFile(lujing);
        //得到位移文件
        List<File> allFile2 = getAllFile2(lujing);

        //解析荷载文件
        for (File file :allFile) {
            Map<String, String> contentDocx = FileUtil.getContentDocx(file);
            for (String key : contentDocx.keySet()) {
                //doc内容
                if(key.equals("content")){
                    String[] contentDocxsplit = contentDocx.get(key).split("\r\n");
                    //判断是否到了需要的数据
                    int state = 0;
                    for(String line:contentDocxsplit){
                        if(line.length()>5 && !line.substring(0,5).equals("     ")){
                            if(state == 1){
                                //得到有用的数据集合
                                List<String> list = new ArrayList<>();
                                String[] s = line.split(" ");
                                for (int i = 0; i < s.length; i++) {
                                    if(!s[i].equals("")){
                                        list.add(s[i]);
                                    }
                                }
                                //如果map里面没有这个key，代表是第一次，则创建列表并添加到map中
                                if(sumdata.get(list.get(0)) == null){
                                    List<List<String>> da = new ArrayList<>();
                                    for(int i=1;i<7;i++){
                                        List<String> temp = new ArrayList();
                                        temp.add(list.get(i));
                                        da.add(temp);
                                    }
                                    sumdata.put(list.get(0),da);
                                }else {
                                    //如果不是一次
                                    List<List<String>> lists = sumdata.get(list.get(0));
                                    for(int i=0;i<6;i++){
                                        lists.get(i).add(list.get(i+1));
                                    }
                                }
                            }
                            if(line.length()>6 && line.substring(0,6).equals("CAESAR")){
                                state = 1;
                            }

                        }
                    }
                }
            }
        }

        //解析位移文件
        for (File file :allFile2) {
            Map<String, String> contentDocx = FileUtil.getContentDocx(file);
            for (String key : contentDocx.keySet()) {
                //doc内容
                if(key.equals("content")){
                    String[] contentDocxsplit = contentDocx.get(key).split("\r\n");
                    //判断是否到了需要的数据
                    int state = 0;
                    for(String line:contentDocxsplit){
                        if(line.length()>5 && !line.substring(0,5).equals("     ")){
                            if(state == 1){
                                //得到有用的数据集合
                                List<String> list = new ArrayList<>();
                                String[] s = line.split(" ");
                                for (int i = 0; i < s.length; i++) {
                                    if(!s[i].equals("")){
                                        list.add(s[i]);
                                    }
                                }
                                //如果map里面没有这个key，代表是第一次，则创建列表并添加到map中
                                if(sumdata2.get(list.get(0)) == null){
                                    List<List<String>> da = new ArrayList<>();
                                    for(int i=1;i<4;i++){
                                        List<String> temp = new ArrayList();
                                        temp.add(list.get(i));
                                        da.add(temp);
                                    }
                                    sumdata2.put(list.get(0),da);
                                }else {
                                    //如果不是一次
                                    List<List<String>> lists = sumdata2.get(list.get(0));
                                    for(int i=0;i<3;i++){
                                        lists.get(i).add(list.get(i+1));
                                    }
                                }
                            }
                            if(line.length()>6 && line.substring(0,6).equals("CAESAR")){
                                state = 1;
                            }

                        }
                    }
                }
            }
        }
        //处理荷载map
        for (String key : sumdata.keySet()){
            List<List<String>> lists = sumdata.get(key);
            List<String> newlist = new ArrayList<>();
            for(List<String> list:lists){
                int max = 0;
                for(String s:list){
                    int i = Integer.parseInt(s);
                    if(Math.abs(i)>max){
                        max = Math.abs(i);
                    }
                }
                newlist.add(String.valueOf(max));
            }
            newsumdata.put(key,newlist);
        }
        //处理位移map
        for (String key : sumdata2.keySet()){
            List<List<String>> lists = sumdata2.get(key);
            List<String> newlist = new ArrayList<>();
            for(List<String> list:lists){
                Double max = 0.0;
                for(String s:list){
                    Double i = Double.parseDouble(s);
                    if(Math.abs(i)>max){
                        max = Math.abs(i);
                    }
                }
                newlist.add(String.valueOf(max));
            }
            newsumdata2.put(key,newlist);
        }

        try {
            //生成荷载doc文件
            FileUtil.createFile(lujing,newsumdata);
            //生成位移doc文件
            FileUtil.createFile2(lujing,newsumdata2);
            //excel文件路径
            File file = getAllFile3(lujing);
            //读取并创建excel文件
            ExcelUtils.readExcel(lujing,file,newsumdata,newsumdata2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok("文件正常生成成功");
    }




    /**
     * 获取指定文件夹下所有荷载文件
     *
     * @param dirFilePath 文件夹路径
     * @return
     */
    public List<File> getAllFile(String dirFilePath) {
        if (dirFilePath == null || dirFilePath.equals(""))
            return null;
        return getAllFile(new File(dirFilePath));
    }
    /**
     * 获取指定文件夹下所有位移文件
     *
     * @param dirFilePath 文件夹路径
     * @return
     */
    public List<File> getAllFile2(String dirFilePath) {
        if (dirFilePath == null || dirFilePath.equals(""))
            return null;
        return getAllFile2(new File(dirFilePath));
    }
    /**
     * 获取指定文件夹下excel文件
     *
     * @param dirFilePath 文件夹路径
     * @return
     */
    public File getAllFile3(String dirFilePath) {
        if (dirFilePath == null || dirFilePath.equals(""))
            return null;
        return getAllFile3(new File(dirFilePath));
    }

    /**
     * 获取指定文件夹下所有荷载文件
     *
     * @param dirFile 文件夹
     * @return
     */
    public List<File> getAllFile(File dirFile) {
        // 如果文件夹不存在或着不是文件夹，则返回 null
        if (Objects.isNull(dirFile) || !dirFile.exists() || dirFile.isFile())
            return null;

        File[] childrenFiles = dirFile.listFiles();
        if (Objects.isNull(childrenFiles) || childrenFiles.length == 0)
            return null;

        List<File> files = new ArrayList<>();
        for (File childFile : childrenFiles) {
            // 如果是文件，直接添加到结果集合
            if (childFile.isFile()) {
                String filename = childFile.getName();
                String[] split = filename.split("\\.");
                //文件后缀
                String type = split[split.length-1];
                //文件名前两位
                String start = split[0].substring(0,2);
                //文件名后两位
                String end = split[0].substring(split[0].length() - 2);
                if(type.equals("docx") && start.equals("动态") && end.equals("荷载")){
                    files.add(childFile);
                }
            }
            else {
                // 如果是文件夹，则将其内部文件添加进结果集合
                List<File> cFiles = getAllFile(childFile);
                if (Objects.isNull(cFiles) || cFiles.isEmpty()) continue;
                files.addAll(cFiles);
            }
        }
        return files;
    }

    /**
     * 获取指定文件夹下所有位移文件
     *
     * @param dirFile 文件夹
     * @return
     */
    public List<File> getAllFile2(File dirFile) {
        // 如果文件夹不存在或着不是文件夹，则返回 null
        if (Objects.isNull(dirFile) || !dirFile.exists() || dirFile.isFile())
            return null;

        File[] childrenFiles = dirFile.listFiles();
        if (Objects.isNull(childrenFiles) || childrenFiles.length == 0)
            return null;

        List<File> files = new ArrayList<>();
        for (File childFile : childrenFiles) {
            // 如果是文件，直接添加到结果集合
            if (childFile.isFile()) {
                String filename = childFile.getName();
                String[] split = filename.split("\\.");
                //文件后缀
                String type = split[split.length-1];
                //文件名前两位
                String start = split[0].substring(0,2);
                //文件名后两位
                String end = split[0].substring(split[0].length() - 2);
                if(type.equals("docx") && start.equals("动态") && end.equals("位移")){
                    files.add(childFile);
                }
            }
            else {
                // 如果是文件夹，则将其内部文件添加进结果集合
                List<File> cFiles = getAllFile2(childFile);
                if (Objects.isNull(cFiles) || cFiles.isEmpty()) continue;
                files.addAll(cFiles);
            }
        }
        return files;
    }

    /**
     * 获取指定文件夹下excel文件
     *
     * @param dirFile 文件夹
     * @return
     */
    public File getAllFile3(File dirFile) {
        // 如果文件夹不存在或着不是文件夹，则返回 null
        if (Objects.isNull(dirFile) || !dirFile.exists() || dirFile.isFile())
            return null;

        File[] childrenFiles = dirFile.listFiles();
        if (Objects.isNull(childrenFiles) || childrenFiles.length == 0)
            return null;

        List<File> files = new ArrayList<>();
        for (File childFile : childrenFiles) {
            // 如果是文件
            if (childFile.isFile()) {
                String filename = childFile.getName();
                String[] split = filename.split("\\.");
                //文件后缀
                String type = split[split.length-1];
                if(type.equals("xlsx")){
                    files.add(childFile);
                    return childFile;
                }
            }
        }
        return null;
    }

}
