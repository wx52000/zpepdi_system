package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ProjectDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ProjectService;

import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;
    @Override
    public Result query() {
        return Result.ok(projectDao.query());
    }

    @Override
    public Result queryById(String id) {
        return Result.ok(projectDao.queryById(id));
    }

    @Override
    public Result setLedger(Integer userId, Map<String, Object> map) {
        projectDao.setLedger(userId,map);
        return Result.ok(map.get("id"));
    }

    @Override
    public Result queryLedger(String id) {
        return Result.ok(projectDao.queryLedger(id));
    }
}
