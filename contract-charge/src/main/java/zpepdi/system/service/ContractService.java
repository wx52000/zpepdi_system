package zpepdi.system.service;

import zpepdi.system.entity.Contract;
import zpepdi.system.result.Result;

import java.util.List;
import java.util.Map;

public interface ContractService {

    Result query(Integer income);

    Result queryChildren(String id);

    Result addChildren(Map<String,Object> map);
    Result delChildren(String id);

    Result queryBlur(String search);
    Result queryById(String id);

    Result contractReceive(String id);

    Result queryConfirm(Integer userId);

    Result setConfirm(Integer userId, List<Map<String,Object>> list);
}
