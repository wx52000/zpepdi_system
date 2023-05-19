package zpepdi.system.service.impl;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ProjectBudgetDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ProjectBudgetService;

import java.util.Calendar;
import java.util.Map;

@Service
public class ProjectBudgetServiceImpl implements ProjectBudgetService {
    @Autowired
    private ProjectBudgetDao projectBudgetDao;

    @Override
    public Result setBudgetMonth(Integer userId, Map<String, Object> map) {
        projectBudgetDao.setBudgetMonth(userId,map);
        return Result.ok();
    }

    @Override
    public Result setBudgetYear(Integer userId, Map<String, Object> map) {
        projectBudgetDao.setBudgetYear(userId,map);
        return Result.ok();
    }

    @Override
    public Result setQuarter(Integer userId, Map<String, Object> map) {
        projectBudgetDao.setQuarter(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryNowById(Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int quarter = (int) Math.ceil(month/3);
        calendar.add(Calendar.MONTH,1);
        int nextMonthYear = calendar.get(Calendar.YEAR);
        int nextMonth = calendar.get(Calendar.MONTH)+1;
        map.put("year",year);
        map.put("month", month);
        map.put("quarter", quarter);
        map.put("nextMonth", nextMonth);
        map.put("nextMonthYear", nextMonthYear);
        return Result.ok(projectBudgetDao.queryNowById(map));
    }

    @Override
    public Result queryByProjectId(Map<String, Object> map) {
        return Result.ok(projectBudgetDao.queryByProjectId(map));
    }
}
