package com.zpepdi.qj_heating.controller;

import com.zpepdi.qj_heating.Utils.FileZipUtil;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.ThicknessSerice;
import org.springframework.beans.factory.annotation.Autowired;
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
