package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zpepdi.system.dao.fd.ProjectDao;
import zpepdi.system.dao.zjepdi.ProjectDataDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ProjectService;

import java.util.Map;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectDataDao projectDataDao;

    @Override
    @Transactional
    public Result addRelateContract(Integer userId, Map<String, Object> map) {
        projectDao.addRelateContract(userId,map);
        projectDao.setContractZCBSplit(map);
        return Result.ok();
    }

    @Override
    public Result delRelateContract( Map<String, Object> map) {
        projectDao.delRelateContract(map);
        return Result.ok();
    }

    @Override
    public Result query() {
        return Result.ok(projectDao.query());
    }

    @Override
    public Result insertSingle(Integer userId, Map<String, Object> map) {
        if (map.get("id") == null || map.get("id").toString().equals("")){
            map.put("id", UUID.randomUUID().toString());
        }
        projectDao.insertSingle(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryById(String id) {
        return Result.ok(projectDao.queryById(id));
    }

    @Override
    public Result setContractZCBSplit(Map<String, Object> map) {
        projectDao.setContractZCBSplit(map);
        return Result.ok();
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

    @Override
    public Result querySearch(String search) {
        return Result.ok(projectDataDao.queryListBySearch(search));
    }
}
