package zpepdi.system.service;


import zpepdi.system.result.Result;

import java.util.Map;

public interface InvoiceBudgetService {

    Result setBudgetMonth(Integer userId, Map<String,Object> map);

    Result setBudgetYear(Integer userId, Map<String,Object> map);

    Result setBudgetQuarter(Integer userId, Map<String,Object> map);

    Result queryByContractId(Map<String,Object> map);

    Result queryNowById(Integer userId, Map<String,Object> map);

    Result queryAllMonthByContractId(Integer userId, Map<String,Object> map);
}
