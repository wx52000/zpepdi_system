package com.zpepdi.eureka_client.service.impl;

import com.zpepdi.eureka_client.entity.Reason;
import com.zpepdi.eureka_client.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zpepdi.eureka_client.dao.UserDao;
import com.zpepdi.eureka_client.dao.VolumeDao;
import com.zpepdi.eureka_client.dao.VolumeUserDao;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.entity.Volume;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.VolumeService;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Transactional
public class VolumeServiceImpl implements VolumeService {
    private VolumeDao volumeDao;
    private VolumeUserDao volumeUserDao;
    private UserDao userDao;
    @Autowired
    public void  setVolumeDao(VolumeDao volumeDao){
        this.volumeDao = volumeDao;
    }
    @Autowired
    public void  setVolumeUserDao(VolumeUserDao volumeUserDao){
        this.volumeUserDao = volumeUserDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
      this.userDao = userDao;
    }
    @Override
    public void addFormPro(Project project) {

    }

    @Override
    public Map queryById(Integer id) {
        return volumeDao.queryById(id);
    }

  @Override
  public List<Map<String, String>> queryByProjectId(Project project) {
        Map map = new HashMap();
        map.put("id", project.getId());

        if (project.getPickerDate() != null) {
          map.put("maxDate", project.getPickerDate().get(1));
          map.put("minDate", project.getPickerDate().get(0));
        }
        return volumeDao.queryByProjectId(map);

  }

  @Override
  public List<Map<String, String>> queryByDate(String date) {
    return volumeDao.queryByDate(date);
  }

  @Override
    public void upd(Volume volume) {
        volumeDao.upd(volume);
        if(volume.getDesigner() != null ){
            volume.setPower(4);
            volumeUserDao.del(volume);
            volumeUserDao.addDesigner(volume);
        }
        if(volume.getChecker() != null ){
            volume.setPower(5);
            volumeUserDao.del(volume);
            volumeUserDao.addChecker(volume);
        }
    }

    @Override
    public void add(Volume volume) {
        Date date = new Date();
        volume.setCreatorDate(date.getTime());
        volumeDao.add(volume);
        volumeUserDao.addDesigner(volume);
        volumeUserDao.addChecker(volume);
    }

  @Override
  public List<Map> queryVolume(String user, String volume) {
    return volumeDao.queryVolume(user,volume);
  }

  @Override
  public Result queryByNumber(Volume volume) {
      User user = userDao.queryById(volume.getId());
      volume.setName(user.getName());
    return Result.ok(volumeDao.queryByNumber(volume));
  }

  @Override
  public List<Map<String, String>> personalVolume(Map<String, String> map) {
    User user = userDao.queryById(Integer.valueOf(map.get("id")));
    map.put("name",user.getName());
    return volumeDao.personalVolume(map);
  }

    @Override
    public Result setReason( Map<String, String> map) {
        volumeDao.setReason(map);
        return Result.ok();
    }

    @Override
    public Result setWorkday(Integer userId, Map<String, String> map) {
        String []s = {"待送出版","正在出版",	"代送业主", "已完成", "院交出"};
        Map<String,Object> map1 = volumeDao.queryUsableWorkday(Integer.valueOf(map.get("id")));
        map1.put("used",Double.parseDouble(map1.get("used").toString()) - Double.parseDouble(map1.get("workday").toString()));
        if ((Double.parseDouble(map1.get("amount").toString()) - Double.parseDouble(map1.get("used").toString()))
                > Double.parseDouble(map.get("workday"))) {
            map.put("type", "工时设置");
            new Thread(() -> volumeDao.setWorkdayLog(userId, map)).start();
            map.put("state", "0");
            for (String s1 : s) {
                if (s1.equals(map1.get("state").toString())) {
                    map.put("state", "1");
                }
            }
            map.put("date", DateUtils.getDateMonth());
            volumeDao.setWorkday(map);
            map1.put("used",Double.parseDouble(map1.get("used").toString()) + Double.parseDouble(map.get("workday")));
            return Result.ok(map1);
        }else return Result.build(566,"可用工时不足，请重新输入");
    }

    @Override
    public Result setWorkdayHigh(Integer userId, Map<String, String> map) {
        Map<String,Object> map1 = volumeDao.queryUsableWorkday(Integer.valueOf(map.get("id")));
        if ((Double.parseDouble(map1.get("amount").toString())- Double.parseDouble(map1.get("used").toString()))
                > Double.parseDouble(map.get("workday"))) {
            map.put("type", "工时提前发放或比例修改");
            new Thread(() -> volumeDao.setWorkdayLog(userId, map)).start();
            map.put("state","0");
            if (map.get("workday") != null && !map.get("workday").equals("")) {
                map.put("state","2");
            }
            volumeDao.setWorkdayHigh(map);
            volumeDao.setWorkdayGrant(map);
            return Result.ok(map1);
        }else  return Result.build(566,"可用工时不足，请重新输入");
    }

    @Override
    public Result queryVolumeWorkday(Map<String, String> map) {
        return Result.ok(volumeDao.queryVolumeWorkday(map));
    }

    @Override
    public Result queryVolumeWorkdayLog(Map<String, String> map) {
        return Result.ok(volumeDao.queryVolumeWorkdayLog(map));
    }

    @Override
    public Result queryBackupWorkdayLog(Map<String, String> map) {
        return Result.ok(volumeDao.queryBackupWorkdayLog(map));
    }

    @Override
    public Result setWorkdayState(String date, Integer old, Integer now) {
        volumeDao.setWorkdayState(date,old,now);
        return Result.ok();
    }
}
