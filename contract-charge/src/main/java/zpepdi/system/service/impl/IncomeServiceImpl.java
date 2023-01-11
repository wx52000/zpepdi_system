package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.IncomeDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.IncomeService;

import java.util.Map;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeDao incomeDao;
    @Override
    public Result setIncome(Integer userId, Map<String, Object> map) {
        incomeDao.setIncome(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryByContractId(Map<String, Object> map) {
        return Result.ok(incomeDao.queryByContractId(map));
    }
}
