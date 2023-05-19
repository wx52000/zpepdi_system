package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ReceiveDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ReceiveService;

import java.util.Map;

@Service
public class ReceiveServiceImpl implements ReceiveService {
    @Autowired
    private ReceiveDao receiveDao;

    @Override
    public Result addReceive(Integer userId, Map<String, Object> map) {
        receiveDao.addReceive(userId,map);
        return Result.ok(map);
    }
}
