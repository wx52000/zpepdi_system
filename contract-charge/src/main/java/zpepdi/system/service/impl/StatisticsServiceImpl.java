package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.StatisticsDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public Result queryPlan(String year) {
        return Result.ok(statisticsDao.queryPlan(year));
    }
}
