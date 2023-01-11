package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.result.Result;
import zpepdi.system.service.InvoiceService;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping("queryByContractId")
    public Result queryByContractId(@RequestHeader String id){
        return invoiceService.queryByContractId(id);
    }
}
