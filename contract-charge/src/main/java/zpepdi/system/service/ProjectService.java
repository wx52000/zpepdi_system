package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.Map;

public interface ProjectService {

    Result addRelateContract(Integer userId,Map<String,Object> map);

    Result delRelateContract(Map<String,Object> map);
    Result insertSingle(Integer userId,Map<String,Object> map);
    Result query();

    Result queryById(String id);

    Result setContractZCBSplit(Map<String,Object> map);

    Result setLedger(Integer userId, Map<String,Object> map);

    Result queryLedger(String id);

    Result querySearch(String search);
}
