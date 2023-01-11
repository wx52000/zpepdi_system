package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.Map;

public interface ProjectService {

    Result query();

    Result queryById(String id);

    Result setLedger(Integer userId, Map<String,Object> map);

    Result queryLedger(String id);
}
