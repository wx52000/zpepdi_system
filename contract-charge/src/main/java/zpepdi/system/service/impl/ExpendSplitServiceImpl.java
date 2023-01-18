package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ExpendSplitDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ExpendSplitService;

import java.util.Map;

@Service
public class ExpendSplitServiceImpl implements ExpendSplitService {
    @Autowired
    private ExpendSplitDao expendSplitDao;

    @Override
    public Result setSplitContract(Integer userId, Map<String, Object> map) {
        expendSplitDao.setSplitContract(userId,map);
        return Result.ok(map);
    }

    @Override
    public Result delSplitContract(int id) {
        expendSplitDao.delSplitContract(id);
        return Result.ok();
    }

    @Override
    public Result queryById(String id) {
        return Result.ok(expendSplitDao.queryById(id));
    }
}
