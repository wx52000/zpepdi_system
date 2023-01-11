package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.IncomeBudgeDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.IncomeBudgetService;

import java.util.Map;

@Service
public class IncomeBudgetServiceImpl implements IncomeBudgetService {
    @Autowired
    private IncomeBudgeDao incomeBudgeDao;
    @Override
    public Result setBudgetMonth(Integer userId, Map<String, Object> map) {
        incomeBudgeDao.setBudgetMonth(userId,map);
        return Result.ok();
    }

    @Override
    public Result setBudgetYear(Integer userId, Map<String, Object> map) {
        incomeBudgeDao.setBudgetYear(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryByContractId(Map<String, Object> map) {
        return Result.ok(incomeBudgeDao.queryByContractId(map));
    }

    @Override
    public Result queryMonthByContractId(Map<String, Object> map) {
        return Result.ok(incomeBudgeDao.queryMonthByContractId(map));
    }
}
