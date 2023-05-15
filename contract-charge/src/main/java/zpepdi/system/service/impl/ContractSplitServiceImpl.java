package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ContractSplitDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractSplitService;

import java.util.Map;

@Service
public class ContractSplitServiceImpl implements ContractSplitService {
    @Autowired
    private ContractSplitDao contractSplitDao;
    @Override
    public Result setSplit(Integer userId, Map<String, Object> map) {
        contractSplitDao.setSplit(userId,map);
        return Result.ok();
    }

    @Override
    public Result setContractZCBSplit(Map<String, Object> map) {
        contractSplitDao.setContractZCBSplit(map);
        return Result.ok();
    }
}
