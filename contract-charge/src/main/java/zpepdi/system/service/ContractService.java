package zpepdi.system.service;

import zpepdi.system.entity.Contract;
import zpepdi.system.result.Result;

import java.util.List;
import java.util.Map;

public interface ContractService {

    Result insertSingle(Integer userId,Map<String,Object> map);

    Result addRelativeProject(Integer userId,Map<String,Object> map);

    Result setType(Map<String,Object> map);

    Result setContractDate(Map<String,Object> map);
    Result setEndTime(Map<String,Object> map);
    Result query(Integer income);

    Result queryParent(Map<String,Object> map);

    Result queryChildren(String id);

    Result queryZCBChildren(String id);

    Result queryRelativeProject(String id);

    Result addChildren(Map<String,Object> map);
    Result delChildren(String id);

    Result queryBlur(String search);
    Result queryById(String id);

    Result contractReceive(String id);

    Result queryConfirm(Integer userId);

    Result setConfirm(Integer userId, List<Map<String,Object>> list);

    Result queryParentBySearchFromZpepdi(Integer userId, Map<String,Object> map);
}
