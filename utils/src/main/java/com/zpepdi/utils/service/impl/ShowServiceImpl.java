package com.zpepdi.utils.service.impl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpepdi.utils.dao.SaveDao;
import com.zpepdi.utils.dao.ShowDao;
import com.zpepdi.utils.entity.File;
import com.zpepdi.utils.result.Result;
import com.zpepdi.utils.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowDao showDao;
    @Override
    public Result show(Integer userId, Map<String, Object> map){
        String nodeId=map.get("nodeId").toString();
        String type=map.get("type").toString();
//        Map<String,Object> map1=showDao.show(userId,personal,type,id);
        return Result.ok(showDao.show(userId,nodeId,type));
    }

    public Result temporary(Integer userId, Map<String, Object> map){
        String type=map.get("type").toString();
//        Map<String,Object> map1=showDao.show(userId,personal,type,id);
        return Result.ok(showDao.temporary(userId,type));
    }

    @Override
    public Result choice(Integer userId, Map<String, Object> map){

        String type=map.get("type").toString();
//        Map<String,Object> map1=showDao.show(userId,type);
        return Result.ok(showDao.choice(userId,type));
    }

    public Result tree(Integer userId, Map<String, Object> map){

        String type=map.get("type").toString();
//        Map<String,Object> map1=showDao.show(userId,type);
        return Result.ok(showDao.tree(userId,type));
    }



    public Result folder(Integer userId, Map<String, Object> map){
        String parentid=map.get("parentid").toString();
        String type=map.get("type").toString();
//        Map<String,Object> map1=showDao.show(userId,type);
        return Result.ok(showDao.folder(userId,type,parentid));
    }

    public Result folderlist(Integer userId,  Map<String, Object> map){
        String parentid=map.get("parentid").toString();
        String type=map.get("type").toString();
//        Map<String,Object> map1=showDao.show(userId,type);
        return Result.ok(showDao.folderlist(type,parentid,userId));
    }

    public Result copyInfo(Integer userId, Map<String, Object> map){
        String nodeId=map.get("nodeId").toString();
        String type=map.get("type").toString();
        String parentid=map.get("parentid").toString();
        String nowid=map.get("nowid").toString();
//        Map<String,Object> map1=showDao.show(userId,type);


        showDao.copyInfo(type,nodeId,userId,parentid,nowid);
        return null;
    }

    public Result copyFile(Integer userId, Map<String, Object> map){
        String str=map.get("list").toString();
        System.out.println(str);
        List<File> flowlist = JSON.parseArray(str, File.class);
        String type=map.get("type").toString();
        int startid= Integer.parseInt(map.get("startid").toString());
        for(int i=0;i<flowlist.size();i++){
            String beforeid=flowlist.get(i).getBeforeid();
            String parentid=flowlist.get(i).getParentid();
            List<Map<String,Object>> list =showDao.fileinfo(beforeid,userId,type);
            System.out.println(parentid);
            System.out.println(list);
            System.out.println("++++++++++++++++++++++++++=");
            for(int j=0;j<list.size();j++){
                startid=startid-1;
                list.get(j).put("newid",startid);
            }
            showDao.copyfile(type,list,parentid,beforeid,userId);
        }
        return Result.ok(startid);
    }
}
