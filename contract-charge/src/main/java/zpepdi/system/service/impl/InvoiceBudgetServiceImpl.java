package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.InvoiceBudgetDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.InvoiceBudgetService;

import java.util.Calendar;
import java.util.Map;

@Service
public class InvoiceBudgetServiceImpl implements InvoiceBudgetService {
    @Autowired
    private InvoiceBudgetDao budgetDao;

    @Override
    public Result setBudgetMonth(Integer userId, Map<String, Object> map) {
        int month = Integer.parseInt(map.get("month").toString());
        int quarter = (int)Math.ceil(month/3);
        map.put("quarter",quarter);
        budgetDao.setBudgetMonth(userId,map);
        return Result.ok();
    }

    @Override
    public Result setBudgetYear(Integer userId, Map<String, Object> map) {
        budgetDao.setBudgetYear(userId,map);
        return Result.ok();
    }

    @Override
    public Result setBudgetQuarter(Integer userId, Map<String, Object> map) {
        budgetDao.setQuarter(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryByContractId(Map<String, Object> map) {
        return Result.ok(budgetDao.queryByContractId(map));
    }

    @Override
    public Result queryNowById(Integer userId, Map<String, Object> map) {
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
        return Result.ok(budgetDao.queryNowById(map));
    }

    @Override
    public Result queryAllMonthByContractId(Integer userId, Map<String, Object> map) {
        return Result.ok(budgetDao.queryAllMonthByContractId(map));
    }
}
