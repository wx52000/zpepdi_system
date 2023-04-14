package com.zpepdi.qj_heating.service.Impl;

import com.zpepdi.qj_heating.dao.ThicknessDao;
import com.zpepdi.qj_heating.result.Result;
import com.zpepdi.qj_heating.service.ThicknessSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ThicknessServiceImpl implements ThicknessSerice {

    @Autowired
    private ThicknessDao thicknessDao;


    @Override
    public Result queryRank() {
        return Result.ok(thicknessDao.queryRank());
    }

    @Override
    public Result queryjiezhi() {
        return Result.ok(thicknessDao.queryjiezhi());
    }
    @Override
    public Result queryyingli(Map<String, String> map) {
        String name = map.get("name");
        if(name.split(":").length==3){
            String product = name.split(":")[1];
            String rank = name.split(":")[2];
            String wd = map.get("wendu");
            double wendu = Double.parseDouble(wd);
            int wen = (int) wendu;
            double wen2 = 0.0;
            if(wendu>=250 && wendu<=660){
                wen = (wen/10)*10;
                wen2 = Double.parseDouble(String.format("%.2f", wendu%10).toString());
                String queryyingli = thicknessDao.queryyingli(wen, product, rank);
                String queryyingli2 = thicknessDao.queryyingli(wen+10, product, rank);
                if(queryyingli!=null&&queryyingli2!=null){
                    Double yingli = Double.parseDouble(queryyingli);
                    Double yingli2 = Double.parseDouble(queryyingli2);
                    double wen21 = ((yingli2- yingli)/10)* wen2;
                    Double yl = yingli+wen21;
                    return Result.ok(yl.toString());
                }else if(queryyingli!=null){
                    Double v = Double.parseDouble(queryyingli);
                    return Result.ok(v.toString());
                }else if(queryyingli2!=null){
                    Double v = Double.parseDouble(queryyingli2);
                    return Result.ok(v.toString());
                }else {
                    return null;
                }
            }else if (wendu >= 200 && wendu < 250){
                wen2 = wendu - 200;
                String queryyingli = thicknessDao.queryyingli(200, product, rank);
                String queryyingli2 = thicknessDao.queryyingli(250, product, rank);
                if(queryyingli!=null&&queryyingli2!=null){
                    Double yingli = Double.parseDouble(queryyingli);
                    Double yingli2 = Double.parseDouble(queryyingli2);
                    double wen21 = ((yingli2- yingli)/50)* wen2;
                    Double yl = yingli+wen21;
                    return Result.ok(yl.toString());
                }else if(queryyingli!=null){
                    Double v = Double.parseDouble(queryyingli);
                    return Result.ok(v.toString());
                }else if(queryyingli2!=null){
                    queryyingli = thicknessDao.queryyingli(20, product, rank);
                    Double yingli = Double.parseDouble(queryyingli);
                    Double yingli2 = Double.parseDouble(queryyingli2);
                    double wen21 = ((yingli2- yingli)/230)* (wen2+180);
                    Double yl = yingli+wen21;
                    return Result.ok(yl.toString());
                }else {
                    return null;
                }
            }else if (wendu >= 20 && wendu < 200){
                wen2 = wendu - 20;
                String queryyingli = thicknessDao.queryyingli(20, product, rank);
                String queryyingli2 = thicknessDao.queryyingli(200, product, rank);
                if(queryyingli!=null&&queryyingli2!=null){
                    Double yingli = Double.parseDouble(queryyingli);
                    Double yingli2 = Double.parseDouble(queryyingli2);
                    double wen21 = ((yingli2- yingli)/180)* wen2;
                    Double yl = yingli+wen21;
                    return Result.ok(yl.toString());
                }else if(queryyingli!=null){
                    queryyingli2 = thicknessDao.queryyingli(250, product, rank);
                    Double yingli = Double.parseDouble(queryyingli);
                    Double yingli2 = Double.parseDouble(queryyingli2);
                    double wen21 = ((yingli2- yingli)/230)* wen2;
                    Double yl = yingli+wen21;
                    return Result.ok(yl.toString());
                }else if(queryyingli2!=null){
                    Double v = Double.parseDouble(queryyingli2);
                    return Result.ok(v.toString());
                }else {
                    return null;
                }
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public Result queryyingliY() {
        return Result.ok(thicknessDao.queryyingliY());
    }

}
