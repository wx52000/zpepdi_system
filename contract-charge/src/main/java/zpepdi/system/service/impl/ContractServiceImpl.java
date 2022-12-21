package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ContractDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;

    @Override
    public Result query() {
        return Result.ok(contractDao.query());
    }

    @Override
    public Result queryById(String id) {
        return Result.ok(contractDao.queryById(id));
    }


}
