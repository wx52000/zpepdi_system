package com.zpepdi.utils.controller;

import com.zpepdi.utils.annotation.UserId;
import com.zpepdi.utils.result.Result;
import com.zpepdi.utils.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @RequestMapping("info")
    public Result Show(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return showService.show(userId,map);
    }


    @RequestMapping("temporary")
    public Result temporary(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return showService.temporary(userId,map);
    }

    @RequestMapping("choice")
    public Result Choice(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return showService.choice(userId,map);
    }

    @RequestMapping("tree")
    public Result tree(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return showService.tree(userId,map);
    }


    @RequestMapping("folder")
    public Result folder(@UserId Integer userId, @RequestBody Map<String, Object> map) {
        return showService.folder(userId,map);
    }

    @RequestMapping("folderlist")
    public Result folderlist(@UserId Integer userId,@RequestBody Map<String, Object> map) {
        return showService.folderlist(userId,map);
    }

    @RequestMapping("copy")
    public Result copyInfo(@UserId Integer userId,@RequestBody Map<String, Object> map) {
        return showService.copyInfo(userId,map);
    }

    @RequestMapping("copyfile")
    public Result copyFile(@UserId Integer userId,@RequestBody Map<String, Object> map) {
        return showService.copyFile(userId,map);
    }


}
