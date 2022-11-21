package com.zpepdi.eureka_client.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpepdi.eureka_client.dao.appraise.*;
import com.zpepdi.eureka_client.entity.User;
import com.zpepdi.eureka_client.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.NewsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class NewsServiceImpl implements NewsService {
  private NewsDao newsDao;
  @Autowired
  private UserDao userDao;
  @Autowired
  ProjectWorkdayDao projectWorkdayDao;
  @Autowired
  private ProjectDao projectDao;
  @Autowired
  private DepartmentDao departmentDao;
  @Autowired
  public void setNewsDao(NewsDao newsDao){
    this.newsDao = newsDao;
  }

  @Override
  public Result newsCount(Integer id) {
    return Result.ok(newsDao.count(id));
  }

  @Override
  public Result queryCountByType(Integer id) {
    return Result.ok(newsDao.queryCountByType(id));
  }

  @Override
  public Result queryByType(Integer id,Integer type) {
    List<Map<String,Object>> mapList = new ArrayList<>();
    switch (type){
      case 0:
        mapList = newsDao.queryByAdmin(id);
        break;
      case 1:
        mapList = newsDao.queryByAdmin1(id);
        break;
      case 3:
        mapList = newsDao.queryByHeadman(id);
        break;
      case 4:
        mapList = newsDao.queryByHeadman2(id);
        break;
      case 5:
        mapList = newsDao.queryByGeneral1(id);
        break;
      case 6:
        mapList = newsDao.queryByGeneral2(id);
        break;
      case 7:
        mapList.addAll(newsDao.queryByAdmin2(id));
        mapList.addAll(newsDao.queryByAdmin3(id));
        break;
      case 9:
        mapList = newsDao.scientificGeneral(id);
        break;
      case 10:
        mapList = newsDao.scientificHeadman(id);
        break;
      default:
        break;
    }
    return Result.ok(mapList);
  }

  @Override
  public Result  query(Integer id) {
    List<Map<String,Object>> maps = new ArrayList<>();
    List<Callable<List<Map<String,Object>>>> callables = new ArrayList<>();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(3,12,
            1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(12));
    List<Future<List<Map<String,Object>>>> futures;
    //主任工时分配审核
    callables.add(()-> newsDao.queryByAdmin(id));
    //主任工时申请审核
    callables.add(() -> newsDao.queryByAdmin1(id));
    callables.add(() -> newsDao.queryByAdmin2(id));
    callables.add(() -> newsDao.queryByAdmin3(id));
//    callables.add(() -> newsDao.queryByGeneral(id));
    callables.add(() -> newsDao.queryByGeneral1(id));
    callables.add(() -> newsDao.queryByGeneral2(id));
    //组长审核
    callables.add(() -> newsDao.queryByHeadman(id));
    callables.add(() -> newsDao.queryByHeadman2(id));
    callables.add(() -> newsDao.scientificGeneral(id));
    callables.add(() -> newsDao.scientificHeadman(id));

    callables.add(() -> newsDao.resultByGeneral(id));

    callables.add(() -> newsDao.resultByGeneral1(id));

//    callables.add(() -> newsDao.resultByPrincipal(id));

    callables.add(() -> newsDao.resultByPrincipal1(id));

    callables.add(() -> newsDao.resultByPrincipal2(id));
    try {
      futures = executor.invokeAll(callables);
      for (Future<List<Map<String,Object>>> future : futures){
        maps.addAll(future.get());
      }
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }finally {
      executor.shutdown();
    }
    return Result.ok(maps);
  }

  @Override
  public Result queryLog(Integer id, Integer index) {
    PageHelper.startPage(index,30);
    PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(newsDao.queryLog(id));
    return Result.ok(pageInfo);
  }

  @Override
  @Transactional
  public Result check(Integer id, List<Map<String, Object>> list, Integer check) {
    boolean limit = false;
    if (check == 1) {
      AtomicBoolean except = new AtomicBoolean(false);
      for (int i = 0; i < list.size(); i++) {
        Map<String,Object> map = list.get(i);
        String type =  map.get("type").toString();
        if (type.equals("5")) {
          if (!DateUtils.getDateMonth(
                  new Date().getTime() - (3600L *24* projectDao.declareDay()*1000))
                  .equals(list.get(i).get("submit_date").toString())){
            list.get(i).put("submit_date", DateUtils.getDateMonth());
          }
        }
        if (type.equals("7")){
          if (map.get("typeNote").toString().equals("0")){
            Object object = map.get("workday");
            if (object == null || object.equals("")) {
              except.set(true);
            }
          }else if (map.get("typeNote").toString().equals("1")){
            Map<String,Object> surplus = departmentDao.querySurplus(id, Integer.valueOf(map.get("did").toString()));
            if (Double.parseDouble(surplus.get("surplus").toString()) <
                    Double.parseDouble(map.get("workday").toString())){
              return Result.build(587,"无产值项目申请工时数大于当前专业可用工时");
            }
          }
        }
        if (type.equals("8")){
              int workday;
              JSONArray list1 = JSONArray.parseArray(JSONArray.toJSONString(map.get("list")));
              workday = list1.stream()
                      .mapToInt(item -> {
                        Object o = JSONObject.parseObject(item.toString()).get("workday");
                        if (o != null && o != "") {
                          return Integer.parseInt(o.toString());
                        }else {
                          except.set(true);
                          return 0;
                        }
                      }).sum();
              if(!except.get()) {
                newsDao.setProjectWorkday(Integer.valueOf(map.get("projectId").toString()), workday);
                newsDao.setProjectBackup(Integer.parseInt(map.get("projectId").toString()), workday);
              }
        }
      }
      if (except.get()){
        return Result.build(587,"存在项目工时未赋值");
      }
    }
    if (list.size() >0 ) {
      newsDao.check(list, check, id);
      new Thread(() ->{
        list.forEach(item ->{
          String type = item.get("type").toString();
          if (type.equals("0") || type.equals("1")){
            newsDao.checkLog0(item,check,id);
            newsDao.checkLog0List(item,check,id);
          }else if (type.equals("3")){
            newsDao.checkLog3(item,check,id);
          }else if (type.equals("4")){
            newsDao.checkLog4(item,check,id);
            newsDao.checkLog4List(item,check,id);
          }else if (type.equals("7")){
            newsDao.checkLog7(item,check,id);
          }else{
            newsDao.checkLog0(item,check,id);
            newsDao.checkLog5List(item,check,id);
          }
        });
      }).start();
    }
    if (limit){
     return Result.build(333,"部分备用申请因发放工时超出设总备用上限，审核失败");
    }
    return Result.ok();
  }

  @Override
  public Result querySelfCheck(Integer id) {
    return Result.ok(newsDao.adminQueryApply(id));
  }

  @Override
  public Result queryExtend(Integer userId, Map<String, Object> map) {
    Integer type = Integer.valueOf(map.get("type").toString());
    Object object = null;
    switch (type){
      case 0:
        object = newsDao.queryInformation0(map);
        break;
      case 1:
        object = newsDao.queryInformation1(map);
        break;
      case 3:
        object = newsDao.queryInformation3(map);
        break;
      case 4:
        object = newsDao.queryInformation4(map);
        break;
      case 5:
        object = newsDao.queryInformation5(userId,map);
        break;
      case 7:
        object = newsDao.queryInformation7(map);
        break;
      case 8:
        object = newsDao.queryInformation8(map);
        break;
      default:
        break;
    }
    return Result.ok(object);
  }

  @Override
  public Result withdrawOverAll(Map<String, Object> map) {
    Integer type = Integer.valueOf(map.get("type").toString());
    boolean re = true;
    switch (type){
      case 0:
        if (!newsDao.isWithdrawOverAll0(map)){
          newsDao.withdrawOverAll0(map);
        }else {
          re = false;
        }
        break;
      case 3:
        if (!newsDao.isWithdrawOverAll3(map)){
          newsDao.withdrawOverAll3(map);
        }else {
          re = false;
        }
        break;
      case 5:
        String nowSubmitDate = newsDao.querySubmitDateNow();
        if (nowSubmitDate.equals(map.get("submit_date").toString())){
          newsDao.withdrawOver5(map);
        }else {
          re = false;
        }
        break;
      case 7:
        if (!newsDao.isWithdrawOverAll7Or8(map)){
          newsDao.withdrawOverAll7(map);
        }else {
          re = false;
        }
        break;
      case 8:
        if (!newsDao.isWithdrawOverAll7Or8(map)){
          newsDao.withdrawOverAll8(map);
        }else {
          re = false;
        }
        break;
      default:
        break;
    }
    if (re) {
      return Result.ok();
    }else {
      return Result.build(843,"当前项目已不可撤回");
    }
  }
}
