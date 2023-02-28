package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.Statistics2Dao;
import zpepdi.system.dao.fd.StatisticsDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.Statistics2Service;
import zpepdi.system.service.StatisticsService;
import zpepdi.system.tools.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class Statistics2ServiceImpl implements Statistics2Service {

    @Autowired
    private Statistics2Dao statistics2Dao;

    @Override
    public Result queryPlan2(String year,String start,String end) {
        String year2 = year;
        if(start.equals("-10")){
            year2 = String.valueOf(new Integer(year) +1);
        };
        return Result.ok(statistics2Dao.queryPlan2(year,year2,start,end));
    }


}
