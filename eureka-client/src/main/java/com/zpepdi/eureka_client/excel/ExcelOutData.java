package com.zpepdi.eureka_client.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelOutData {

  /**
   * @param list
   * @param type
   * @return
   *  type == 0   主任
   *  type == 1   设总
   *  type == 2   主设人
   */
  public static List<List<String>> userAll(List<Map<String,Object>> list, Integer type){
    List<List<String>> lists = new ArrayList<>();
    list.stream().forEach((Map<String,Object> o) ->{
      List<String> list1 = new ArrayList<>();
      list1.add(o.get("username").toString());
      list1.add(o.get("name").toString());
      list1.add(o.get("tec").toString());
      list1.add(o.get("workday").toString());
      list1.add(o.get("volume_workday").toString());
      list1.add(o.get("task_workday").toString());
      if (type == 0) {
        list1.add(o.get("manage").toString());
      }
      list1.add(o.get("advance_workday").toString());
      lists.add(list1);
    });
    return lists;
  }


  public static List<List<String>> dataTec(List<Map<String,String>> list){
    List<List<String>> lists = new ArrayList<>();
    list.stream().forEach((Map<String,String> o) ->{
      List<String> list1 = new ArrayList<>();
      list1.add(o.get("tec"));
      list1.add(o.get("count"));
      list1.add(o.get("have"));
      list1.add(o.get("backup"));
      list1.add(o.get("used"));
      lists.add(list1);
    });
    return lists;
  }



  public static List<List<String>> dataVolume(List<Map<String,String>> list,Integer mode){
    List<List<String>> lists = new ArrayList<>();
    list.stream().forEach((Map<String,String> o) ->{
      List<String> list1 = new ArrayList<>();
      if (mode == 1){
        list1.add(o.get("pNumber"));
        list1.add(o.get("pName"));
      }
      list1.add(o.get("number"));
      list1.add(o.get("name"));
      list1.add(o.get("dep"));
      list1.add(o.get("tec"));
      list1.add(o.get("state"));
      list1.add(o.get("workday"));
      list1.add(o.get("principal"));
      list1.add(o.get("designer"));
      list1.add(o.get("checker"));
      list1.add(o.get("actual_principal"));
      list1.add(o.get("workday"));
      list1.add(o.get("workday_state"));
      list1.add(o.get("planned_start_date"));
      list1.add(o.get("start_date"));
      list1.add(o.get("planned_shot_date"));
      list1.add(o.get("shot_date"));
      list1.add(o.get("proofreading_date"));
      list1.add(o.get("planned_publication_date"));
      list1.add(o.get("actual_publication_date"));
      list1.add(o.get("complete_time"));
      lists.add(list1);
    });
    return lists;
  }

  public static List<List<String>> everyone(List<Map<String,String>> list,Integer mode){
    List<List<String>> lists = new ArrayList<>();
    list.stream().forEach((Map<String,String> o) ->{
      List<String> list1 = new ArrayList<>();
      if (mode == 1){
      list1.add(o.get("username"));
      list1.add(o.get("name"));
      }else if (mode == 2){
        list1.add(o.get("number"));
        list1.add(o.get("name"));
      }
      list1.add(o.get("amount_p"));
      list1.add(o.get("amount_d"));
      list1.add(o.get("amount_c"));
      lists.add(list1);
    });
    return lists;
  }

  public static List<List<String>> personal(List<Map<String,Object>> list){
    Map<String,String> map = new HashMap<>();
    map.put("0","未发放");
    map.put("1","已发放");
    map.put("2","部分发放");
    List<List<String>> lists = new ArrayList<>();
    list.stream().forEach((Map<String,Object> o) ->{
      List<String> list1 = new ArrayList<>();
      list1.add(o.get("principal").toString());
      list1.add(o.get("workday").toString());
      list1.add(map.get(o.get("type").toString()));
      list1.add(o.get("number").toString());
      lists.add(list1);
    });
    return lists;
  }
}
