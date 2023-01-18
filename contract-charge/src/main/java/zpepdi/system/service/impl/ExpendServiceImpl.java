package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ExpendDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ExpendService;

import java.util.Map;

@Service
public class ExpendServiceImpl implements ExpendService {
    @Autowired
    private ExpendDao expendDao;

    @Override
    public Result setExpend(Integer userId, Map<String, Object> map) {
        expendDao.setExpend(userId,map);
        return Result.ok(map);
    }

    @Override
    public Result queryById(String id) {
        return Result.ok(expendDao.queryById(id));
    }
}
