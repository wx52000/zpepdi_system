package zpepdi.system.service;


import zpepdi.system.result.Result;

import java.util.Map;

public interface IncomeService {

    Result setIncome(Integer userId,
                     Map<String,Object> map);

    Result queryByContractId(Map<String,Object> map);
}
