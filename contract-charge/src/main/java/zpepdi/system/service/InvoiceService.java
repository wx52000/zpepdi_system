package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.List;
import java.util.Map;

public interface InvoiceService {

    Result queryByContractId(String id);
}
