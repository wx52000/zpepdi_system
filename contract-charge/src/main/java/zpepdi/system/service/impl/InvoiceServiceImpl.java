package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.InvoiceDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;
    @Override
    public Result queryByContractId(String id) {
        return Result.ok(invoiceDao.queryByContractId(id).get(1));
    }
}
