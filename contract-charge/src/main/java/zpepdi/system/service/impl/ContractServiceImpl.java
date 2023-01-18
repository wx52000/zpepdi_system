package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import zpepdi.system.dao.fd.ContractDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractService;

import java.util.List;
import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;

    @Override
    public Result query(Integer income) {
        return Result.ok(contractDao.query(income));
    }

    @Override
    public Result queryChildren(String id) {
        return Result.ok(contractDao.queryChildren(id));
    }

    @Override
    public Result addChildren(Map<String, Object> map) {
        contractDao.addChildren(map);
        return Result.ok();
    }

    @Override
    public Result delChildren(String id) {
        contractDao.delChildren(id);
        return Result.ok();
    }

    @Override
    public Result queryBlur(String search) {
        return Result.ok(contractDao.queryBlur(search));
    }

    @Override
    public Result queryById( String id) {
        return Result.ok(contractDao.queryById(id));
    }

    @Override
    public Result contractReceive(String id) {
        return Result.ok(contractDao.contractReceive(id));
    }

    @Override
    public Result queryConfirm(Integer userId) {
        return Result.ok(contractDao.queryConfirm(userId));
    }

    @Override
    public Result setConfirm(Integer userId, List<Map<String, Object>> list) {
        contractDao.setConfirm(userId,list);
        return Result.ok();
    }


}
