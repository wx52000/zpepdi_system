package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.ProjectBudgetDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.ProjectBudgetService;

import java.util.Map;

@Service
public class ProjectBudgetServiceImpl implements ProjectBudgetService {
    @Autowired
    private ProjectBudgetDao projectBudgetDao;

    @Override
    public Result setBudgetMonth(Integer userId, Map<String, Object> map) {
        projectBudgetDao.setBudgetMonth(userId,map);
        return Result.ok();
    }

    @Override
    public Result setBudgetYear(Integer userId, Map<String, Object> map) {
        projectBudgetDao.setBudgetYear(userId,map);
        return Result.ok();
    }

    @Override
    public Result queryByProjectId(Map<String, Object> map) {
        return Result.ok(projectBudgetDao.queryByProjectId(map));
    }
}
