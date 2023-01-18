package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.Map;

public interface ExpendService {

    Result setExpend(Integer userId, Map<String,Object> map);

    Result queryById(String id);
}
