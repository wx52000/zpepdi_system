package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ContractUserDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractUserService;

import java.util.Map;

@Service
public class ContractUserServiceImpl implements ContractUserService {
    @Autowired
    private ContractUserDao contractUserDao;

    @Override
    public Result addContractUser(Integer userId, Map<String, Object> map) {
        contractUserDao.delContractUserByRole(map);
        contractUserDao.addContractUser(userId,map);
        return Result.ok();
    }

    @Override
    public Result delContractUser(Integer id) {
        contractUserDao.delContractUser(id);
        return Result.ok();
    }

    @Override
    public Result queryByRole(Map<String, Object> map) {
        return Result.ok(contractUserDao.queryByRole(map));
    }
}
