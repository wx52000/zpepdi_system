package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.Map;

public interface ProjectBudgetService {

    Result setBudgetMonth(Integer userId, Map<String,Object> map);

    Result setBudgetYear(Integer userId, Map<String,Object> map);

    Result setQuarter(Integer userId, Map<String,Object> map);

    Result queryNowById(Map<String,Object> map);

    Result queryByProjectId(Map<String,Object> map);
}
