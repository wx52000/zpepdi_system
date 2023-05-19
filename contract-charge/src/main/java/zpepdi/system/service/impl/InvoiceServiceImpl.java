package zpepdi.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zpepdi.system.dao.fd.InvoiceDao;
import zpepdi.system.result.Result;
import zpepdi.system.service.InvoiceService;

import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDao invoiceDao;


    @Override
    public Result addRelativeBudget(Map<String, Object> map) {
        invoiceDao.addRelativeBudget(map);
        return Result.ok();
    }

    @Override
    public Result queryByContractId(String id) {
        return Result.ok(invoiceDao.queryByContractId(id).get(1));
    }

    @Override
    public Result queryBaseByContractId(String id) {
        return Result.ok(invoiceDao.queryBaseByContractId(id));
    }


}
