package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.Map;

public interface ProjectService {

    Result addRelateContract(Integer userId,Map<String,Object> map);

    Result delRelateContract(Map<String,Object> map);
    Result insertSingle(Integer userId,Map<String,Object> map);
    Result query();

    Result queryById(String id);


    Result setLedger(Integer userId, Map<String,Object> map);

    Result delLedger(Integer id);

    Result queryLedger(String id);

    Result querySearchLocal(String search);

    Result querySearch(String search);
}
