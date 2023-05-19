package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.List;
import java.util.Map;

public interface InvoiceService {

    Result addRelativeBudget(Map<String,Object> map);
    Result queryByContractId(String id);

    Result queryBaseByContractId(String id);

}
