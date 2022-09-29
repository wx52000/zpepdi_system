package com.zpepdi.eureka_client.tools;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.zpepdi.eureka_client.config.ScheduledConfig.setDay;

public class AppraiseDate {

  public static Map appDate() {
    Map<Object, Integer> map = new HashMap();
    Calendar calendar = Calendar.getInstance();
    if (calendar.get(Calendar.DAY_OF_MONTH) <= 15){
      calendar.add(Calendar.MONTH,-1);
    }
    //获取当前年份
    map.put("year", calendar.get(Calendar.YEAR));
    map.put("month",calendar.get(Calendar.MONTH)+1);
    return map;
  }

  public static Boolean quarter(Integer month) {
    Integer nowMonth;
    Integer nowDay;
    Calendar calendar = Calendar.getInstance();
    nowMonth = calendar.get(Calendar.MONTH)+1;
    nowDay = calendar.get(Calendar.DAY_OF_MONTH);
    if (nowMonth < month)
      nowMonth = nowMonth + 12;
    if (Math.abs(nowMonth - month) < 3){
      return  true;
    }else if (Math.abs(nowMonth - month) == 3){
      if (nowDay >= 25)
        return false;
      else
        return true;
    }else
      return  false;
  }

  public static Integer year(Integer month) {
    Integer year;
    Integer nowMonth;
    Calendar calendar = Calendar.getInstance();
    nowMonth = calendar.get(Calendar.MONTH) + 1;
    year = calendar.get(Calendar.YEAR);
    if (nowMonth <= 3) {
      if (month > 3)
        return --year;
      else
        return year;
    } else if (nowMonth <= 6) {
      if (month > 6)
        return --year;
      else
        return year;
    } else if (nowMonth <= 9) {
      if (month > 9)
        return --year;
      else
        return year;
    } else
      return year;
  }
}
