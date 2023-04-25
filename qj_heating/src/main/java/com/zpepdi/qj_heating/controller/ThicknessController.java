package com.zpepdi.qj_heating.controller;

import com.zpepdi.qj_heating.Utils.FileZipUtil;
import com.zpepdi.qj_heating.entity.UserUnitcy;
import com.zpepdi.qj_heating.entity.Userpiping;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.ThicknessSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Thickness")
@CrossOrigin(origins = "*")
public class ThicknessController {

    @Autowired
    private ThicknessSerice thicknessSerice;


    //删除管道
    @RequestMapping("delgd")
    public Result delgd(@RequestBody Userpiping userpiping){
        Integer id = userpiping.getId();
        return thicknessSerice.delgd(id);
    }

    //更新管道名称
    @RequestMapping("upgdname")
    public Result upgdname(@RequestBody Userpiping userpiping){
        Integer id = userpiping.getId();
        String gdname = userpiping.getGdname();
        return thicknessSerice.upgdname(id,gdname);
    }
    //更新排序
    @RequestMapping("upsort")
    public Result upsort(@RequestBody Userpiping userpiping){
        Integer id = userpiping.getId();
        Integer defstr2 = userpiping.getDefstr2();
        return thicknessSerice.upsort(id,defstr2);
    }
    //保存管道
    @RequestMapping("savepiping")
    public Result savepiping(@RequestBody Userpiping userpiping){
        Result savepiping = thicknessSerice.savepiping(userpiping);
        return savepiping;
    }
    //文件保存
    @RequestMapping("filesave")
    public Result filesave(@RequestBody Userpiping userpiping){
        Result savepiping = thicknessSerice.filesave(userpiping);
        return savepiping;
    }
    //文件另存为
    @RequestMapping("filesave2")
    public Result filesave2(@RequestBody Userpiping userpiping){
        Result savepiping = thicknessSerice.filesave2(userpiping);
        return savepiping;
    }
    //文件另存为
    @RequestMapping("dakaifile")
    public Result dakaifile(@RequestBody Userpiping userpiping){
        Result savepiping = thicknessSerice.dakaifile(userpiping);
        return savepiping;
    }
    //查询用户文件列表
    @RequestMapping("queryfilenamelist")
    public Result queryfilenamelist(@RequestBody Userpiping userpiping){
        Result savepiping = thicknessSerice.queryfilenamelist(userpiping);
        return savepiping;
    }
    //更新管道
    @RequestMapping("upgd")
    public Result upgd(@RequestBody Userpiping userpiping){
        Result savepiping = thicknessSerice.upgd(userpiping);
        return savepiping;
    }
    //查询管道
    @RequestMapping("querypiping")
    public Result querypiping(@RequestBody Userpiping userpiping){
        String username = userpiping.getUsername();
        String name = userpiping.getName();
        String defstr1 = userpiping.getDefstr1();
        List<Userpiping> querypiping = thicknessSerice.querypiping(username, name,defstr1);
        return Result.ok(querypiping);
    }
    //根据id查询管道
    @RequestMapping("byidquerypiping")
    public Result byidquerypiping(@RequestBody Userpiping userpiping){
        Integer id = userpiping.getId();
        Result querypiping = thicknessSerice.byidquerypiping(id);
        return querypiping;
    }

    @RequestMapping("queryRank")
    public Result queryRank(){
        Result result = thicknessSerice.queryRank();
        return result;
    }
    @RequestMapping("queryjiezhi")
    public Result queryjiezhi(){
        Result result = thicknessSerice.queryjiezhi();
        return result;
    }
    @RequestMapping("queryyingli")
    public Result queryyingli(@RequestBody Map<String, String> map){
        Result result = thicknessSerice.queryyingli(map);
        return result;
    }
    @RequestMapping("queryyingliY")
    public Result queryyingliY(){
        Result result = thicknessSerice.queryyingliY();
        return result;
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        FileZipUtil.exportZip(response,"temp\\newfile\\","文件名",".zip");
    }
}
