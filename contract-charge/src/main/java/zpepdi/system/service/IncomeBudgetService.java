package zpepdi.system.service;

import org.apache.ibatis.annotations.Param;
import zpepdi.system.result.Result;

import java.util.List;
import java.util.Map;

public interface IncomeBudgetService {

    Result setBudgetMonth(Integer userId,
                           Map<String,Object> map);

    Result setBudgetYear(Integer userId,
                        Map<String,Object> map);

    Result queryByContractId(Map<String,Object> map);

    Result queryMonthByContractId(Map<String,Object> map);
}
