package com.zpepdi.utils.service.impl;

import com.zpepdi.utils.dao.SaveDao;
import com.zpepdi.utils.result.Result;
import com.zpepdi.utils.service.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class SaveServiceImpl implements SaveService {
    @Autowired
    private SaveDao saveDao;
    @Override
    public Result save(Integer userId, Map<String, Object> map){
        String datalist=map.get("datalist").toString();
        String type=map.get("type").toString();
        String nodeId=map.get("nodeId").toString();
        Object bySelect=saveDao.doSelect(userId,datalist,type,nodeId);
        if(StringUtils.isEmpty(bySelect)){
    saveDao.doInsert(userId,datalist,type,nodeId);
}
else{
    saveDao.doUpdate(userId,datalist,type,nodeId);
}

//        else{
//            waterDao.doUpdate(userId,map);
//        }
        return null;


//        return null;
    }

    @Override
    public Result temporary(Integer userId, Map<String, Object> map){
        String data=map.get("datalist").toString();
        String type=map.get("type").toString();

        Object bySelect=saveDao.selectTemporary(userId,type);
        if(StringUtils.isEmpty(bySelect)){
            saveDao.insertTemporary(userId,data,type);
        }
        else{
            saveDao.updateTemporary(userId, data,type);
        }
        return null;


//        return null;
    }

    public Result tree(Integer userId, Map<String, Object> map){
        String data=map.get("tree").toString();
        String type=map.get("type").toString();
        String startid=map.get("startid").toString();
        Object bySelect=saveDao.selectTree(userId,type);
        if(StringUtils.isEmpty(bySelect)){
            saveDao.insertTree(userId,data,type,startid);
        }
        else{
            saveDao.updateTree(userId,data,type,startid);
        }
        return null;


//        return null;
    }



    public Result folder(Integer userId, Map<String, Object> map){
        String parentid=map.get("id").toString();
        String nodeid=map.get("nodeId").toString();
        String name=map.get("name").toString();
        String type=map.get("type").toString();
        String restype=map.get("restype").toString();
        saveDao.insertfolder(userId,parentid,name,type,restype,nodeid);


        return null;
    }

    public Result delete(Integer userId, List<Map<String,Object>> list){
        saveDao.delete(userId,list);


        return null;
    }

    public Result updatefolder(Integer userId, Map<String, Object> map){
        String parentid=map.get("id").toString();
        String nodeid=map.get("nodeId").toString();
        String name=map.get("name").toString();
        String type=map.get("type").toString();
        String restype=map.get("restype").toString();
        saveDao.updatefolder(userId,parentid,name,type,restype,nodeid);


        return null;
    }
}
