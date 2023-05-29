package com.zpepdi.utils.controller;

import com.zpepdi.utils.annotation.UserId;
import com.zpepdi.utils.result.Result;
import com.zpepdi.utils.service.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("save")
public class SaveController {
    @Autowired
    private SaveService saveService;
    @RequestMapping("compute")
    public Result Save(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return saveService.save(userId,map);
    }

    @RequestMapping("temporary")
    public Result Temporary(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return saveService.temporary(userId,map);
    }

    @RequestMapping("tree")
    public Result tree(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return saveService.tree(userId,map);
    }


    @RequestMapping("folder")
    public Result folder(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return saveService.folder(userId,map);
    }

    @RequestMapping("delete")
    public Result delete(@UserId Integer userId, @RequestBody List<Map<String,Object>> list) {
        System.out.println(Arrays.toString(list.toArray()));
        return saveService.delete(userId,list);
    }
//重命名文件夹
    @RequestMapping("updatefolder")
    public Result updatefolder(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return saveService.updatefolder(userId,map);
    }






}
