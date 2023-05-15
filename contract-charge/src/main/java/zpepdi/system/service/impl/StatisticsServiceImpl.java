package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.StatisticsDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.StatisticsService;
import zpepdi.system.tools.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public Result queryPlan(String year) {
        return Result.ok(statisticsDao.queryPlan(year));
    }

    @Override
    public Result queryZCBMonth(String date) {
        Map<String,Object> map = new HashMap<>();
        Date date1 = DateUtils.stringToDate(date,"yyyy-MM");
        map.put("date", date + "-01");
        map.put("nowMonth",DateUtils.dateToString(date1,"MM"));
//        上个月最后一天时间
//        date1.setTime(date1.getTime()-1000);
//        本年度第一天
        map.put("lastDate", DateUtils.dateToString(date1,"yyyy-01-01"));
//        下个月时间
        date1.setTime(date1.getTime() + 3000000000L);
        int year = DateUtils.getYearOfDate(date1);
        int month = DateUtils.getMonthOfDate(date1);
        map.put("year",year);
        map.put("month",month);
        map.put("endDate",DateUtils.dateToString(date1,"yyyy-MM-01"));
        return Result.ok(statisticsDao.queryZCBMonth(map));
    }

    @Override
    public Result queryZCBYear(Integer year) {
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        map.put("start", year + "-01-01");
        map.put("end", year + "-12-31");
        return Result.ok(statisticsDao.queryZCBYear(map));
    }
}
