package zpepdi.system.service;


import zpepdi.system.result.Result;

import java.util.Map;

public interface ContractSplitService {

    Result setSplit(Integer userId, Map<String,Object> map);

    Result  setContractZCBSplit(Map<String,Object> map);
}
