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
import java.util.Map;

@RestController
@RequestMapping("Thickness")
@CrossOrigin(origins = "*")
public class ThicknessController {

    @Autowired
    private ThicknessSerice thicknessSerice;


    @RequestMapping("delgd")
    public Result delgd(@RequestBody Userpiping userpiping){
        Integer id = userpiping.getId();
        return thicknessSerice.delgd(id);
    }

    @RequestMapping("upgdname")
    public Result upgdname(@RequestBody Userpiping userpiping){
        Integer id = userpiping.getId();
        String gdname = userpiping.getGdname();
        return thicknessSerice.upgdname(id,gdname);
    }
    @RequestMapping("savepiping")
    public Result savepiping(@RequestBody Userpiping userpiping){
        Result savepiping = thicknessSerice.savepiping(userpiping);
        return Result.ok(userpiping.getId());
    }
    @RequestMapping("querypiping")
    public Result querypiping(@RequestBody Userpiping userpiping){
        String username = userpiping.getUsername();
        String name = userpiping.getName();
        String defstr1 = userpiping.getDefstr1();
        Result querypiping = thicknessSerice.querypiping(username, name,defstr1);
        return querypiping;
    }
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
