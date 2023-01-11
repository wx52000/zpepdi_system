package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.InvoiceBudgetDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.InvoiceBudgetService;

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
    public Result queryByContractId(Map<String, Object> map) {
        return Result.ok(budgetDao.queryByContractId(map));
    }
}
