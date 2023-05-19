package com.zpepdi.qj_heating.controller;

import com.zpepdi.qj_heating.Utils.ExcelUtils;
import com.zpepdi.qj_heating.Utils.FileUtil;
import com.zpepdi.qj_heating.Utils.FileZipUtil;
import com.zpepdi.qj_heating.dao.ThicknessDao;
import com.zpepdi.qj_heating.entity.UserUnitcy;
import com.zpepdi.qj_heating.entity.Userpiping;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.ThicknessSerice;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Thickness")
@CrossOrigin(origins = "*")
public class ThicknessController {

    @Autowired
    private ThicknessSerice thicknessSerice;
    @Autowired
    private ThicknessDao thicknessDao;


    //删除管道
    @RequestMapping("delgd")
    public Result delgd(@RequestBody Userpiping userpiping){
        Integer id = userpiping.getId();
        return thicknessSerice.delgd(id);
    }
    //删除分组
    @RequestMapping("delfenzu")
    public Result delfenzu(@RequestBody Userpiping userpiping){
        return thicknessSerice.delfenzu(userpiping);
    }
    //更改分组名称
    @RequestMapping("rename2")
    public Result rename2(@RequestBody Userpiping userpiping){
        return thicknessSerice.rename2(userpiping);
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
    //更新分组排序
    @RequestMapping("upfenzusort")
    public Result upfenzusort(@RequestBody Userpiping userpiping){
        return thicknessSerice.upfenzusort(userpiping);
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
        List<Userpiping> querypipinglist = thicknessSerice.querypiping(userpiping);
        return Result.ok(querypipinglist);
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
    @RequestMapping("querycailiao5310")
    public Result querycailiao5310(){
        Result result = thicknessSerice.querycailiao5310();
        return result;
    }
    @RequestMapping("querycailiao3087")
    public Result querycailiao3087(){
        Result result = thicknessSerice.querycailiao3087();
        return result;
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> downloadWordFile(@RequestBody Map<String,Object> map) throws Exception {
        ApplicationHome applicationHome = new ApplicationHome(new FileUtil().getClass());
        // 保存目录位置根据项目需求可随意更改
        String absolutePath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        String path=absolutePath+"\\src\\main\\resources";
        //先删除计算书
        try {
            new ClassPathResource("管道规格计算书.docx").getInputStream();
            File file = new File(path,"管道规格计算书.docx");
            file.delete();
        }catch (Exception e){
            System.out.println("暂无计算书");
        }

        //创建计算书
        if(map!=null){
            Userpiping userpiping = new Userpiping();
            List<Userpiping> querypiping = new ArrayList<Userpiping>();
            String username = (String) map.get("username");
            ArrayList xzidlist = (ArrayList)map.get("xzidlist");
            if(username!=null){
                userpiping.setUsername(username);
                querypiping = thicknessSerice.querypiping(userpiping);
            }else if(xzidlist.size()>0){
                for(Object id: xzidlist){
                    Userpiping byidquerypiping = thicknessDao.byidquerypiping((Integer) id);
                    querypiping.add(byidquerypiping);
                }
            }
            FileUtil.createjisuanFile2(querypiping);
        }
        // 读取计算书
        InputStream inputStream = new FileInputStream(path + "\\管道规格计算书.docx");
        byte[] bytes = IOUtils.toByteArray(inputStream);
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "管道规格计算书");
        inputStream.close();
        // 返回ResponseEntity对象
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

}
