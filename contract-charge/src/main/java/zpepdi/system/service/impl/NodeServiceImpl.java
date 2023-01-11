package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.NodeDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.NodeService;

import java.util.Map;

@Service
public class NodeServiceImpl implements NodeService {
    @Autowired
    private NodeDao nodeDao;


    @Override
    public Result queryByContractId(String contractId) {
        return Result.ok(nodeDao.queryByContractId(contractId));
    }

    @Override
    public Result setNode(Integer userId, Map<String, Object> map) {
        nodeDao.setNode(userId,map);
        return Result.ok();
    }
}
