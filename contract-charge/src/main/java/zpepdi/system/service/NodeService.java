package zpepdi.system.service;


import zpepdi.system.result.Result;

import java.util.Map;

public interface NodeService {

    Result queryByContractId(String contractId);
    Result setNode(Integer userId, Map<String,Object> map);
}
