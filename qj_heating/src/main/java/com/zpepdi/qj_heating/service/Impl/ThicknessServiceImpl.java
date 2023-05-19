package com.zpepdi.qj_heating.service.Impl;

import com.zpepdi.qj_heating.dao.ThicknessDao;
import com.zpepdi.qj_heating.entity.Userpiping;
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

    /**
     * 修改管道名称
     * @param id
     * @param gdname
     * @return
     */
    @Override
    public Result upgdname(Integer id, String gdname) {
        return Result.ok(thicknessDao.upgdname(id,gdname));
    }

    /**
     * 修改管道排序
     * @param id
     * @param defstr2
     * @return
     */
    @Override
    public Result upsort(Integer id, Integer defstr2) {
        return Result.ok(thicknessDao.upsort(id,defstr2));
    }

    /**
     * 修改分组排序
     * @param userpiping
     * @return
     */
    @Override
    public Result upfenzusort(Userpiping userpiping) {
        return Result.ok(thicknessDao.upfenzusort(userpiping));
    }
    /**
     * 保存管道
     * @param userpiping
     * @return
     */
    @Override
    public Result savepiping(Userpiping userpiping) {
        int savepiping = thicknessDao.savepiping(userpiping);
        String maxsort = querymaxsort(userpiping);
        Integer defstr2;
        if(maxsort == null){
            defstr2 = 1;
        }else {
            defstr2 = Integer.parseInt(maxsort)+1;
        }
        userpiping.setDefstr2(defstr2);
        upgd(userpiping);
        Userpiping userpiping1 = new Userpiping();
        userpiping1.setId(userpiping.getId());
        userpiping1.setDefstr2(defstr2);
        return Result.ok(userpiping1);
    }
 /**
     * 保存管道文件
     * @param userpiping
     * @return
     */
    @Override
    public Result filesave(Userpiping userpiping) {
        String defstr3 = userpiping.getDefstr3();
        //直接查询id更优，若后续需要优化可修改
        List<Userpiping> querypiping = querypiping(userpiping);
        for(Userpiping gd:querypiping){
            Integer id = gd.getId();
            thicknessDao.upfilename(id,defstr3);
        }
        return Result.ok(defstr3);
    }

    /**
     * 保存管道文件（另存为）
     * @param userpiping
     * @return
     */
    @Override
    public Result filesave2(Userpiping userpiping) {
        //新管道名称
        String defstr3 = userpiping.getDefstr3();
        List<Userpiping> querypiping = querypiping(userpiping);
        for(Userpiping gd:querypiping){
            thicknessDao.updefstr4(gd.getId(),"false");
        }
        for(Userpiping gd:querypiping){
            gd.setDefstr2(null);
            gd.setDefstr3(defstr3);
            gd.setDefstr4("true");
            savepiping(gd);
        }
        return Result.ok();
    }  /**
     * 打开文件
     * @param userpiping
     * @return
     */
    @Override
    public Result dakaifile(Userpiping userpiping) {

        List<Userpiping> querypiping = querypiping(userpiping);
        for(Userpiping gd:querypiping){
            thicknessDao.updefstr4(gd.getId(),"false");
        }
        List<Userpiping> byfilequerypiping = thicknessDao.byfilequerypiping(userpiping.getUsername(), userpiping.getName(), userpiping.getDefstr3());
        for(Userpiping gd:byfilequerypiping){
            thicknessDao.updefstr4(gd.getId(),"true");
        }
        return Result.ok();
    }

    /**
     * 查询最大排序号
     * @param userpiping
     * @return
     */
    public String querymaxsort(Userpiping userpiping) {
        return thicknessDao.querymaxsort(userpiping);
    }
   /**
     * 查询用户文件列表
     * @param
     * @return
     */
   @Override
    public Result queryfilenamelist(Userpiping userpiping) {
        return Result.ok(thicknessDao.queryfilenamelist(userpiping));
    }


    /**
     * 修改管道
     * @param userpiping
     * @return
     */
    public Result upgd(Userpiping userpiping) {
        return Result.ok(thicknessDao.upgd(userpiping));
    }

    /**
     * 查询用户管道(区分外径还是内径)
     * @param
     * @return
     */
    @Override
    public List<Userpiping> querypiping(Userpiping userpiping) {
        return thicknessDao.querypiping(userpiping);
    }

    /**
     * 根据id查询管道
     * @param id
     * @return
     */
    @Override
    public Result byidquerypiping(Integer id) {
        return Result.ok(thicknessDao.byidquerypiping(id));
    }

    /**
     * 删除管道
     * @param id
     * @return
     */
    @Override
    public Result delgd(Integer id) {
        return Result.ok(thicknessDao.delgd(id));
    }

    /**
     * 删除分组
     * @param userpiping
     * @return
     */
    @Override
    public Result delfenzu(Userpiping userpiping) {
        return Result.ok(thicknessDao.delfenzu(userpiping));
    }
    @Override
    public Result rename2(Userpiping userpiping) {
        return Result.ok(thicknessDao.rename2(userpiping));
    }
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
        if(name.split(":").length==5){
            String product = name.split(":")[1];
            String rank = name.split(":")[2];
            String wd = map.get("wendu");
            double wendu = 0.0;
            if(wd != null){
                wendu = Double.parseDouble(wd);
            }

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

    @Override
    public Result querycailiao3087() {
        return Result.ok(thicknessDao.querycailiao3087());
    }

    @Override
    public Result querycailiao5310() {
        return Result.ok(thicknessDao.querycailiao5310());
    }
}
