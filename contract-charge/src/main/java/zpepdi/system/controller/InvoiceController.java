package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.result.Result;
import zpepdi.system.service.InvoiceService;

import java.util.Map;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping("addRelativeBudget")
    public Result addRelativeBudget(@RequestBody Map<String,Object> map){
        return invoiceService.addRelativeBudget(map);
    }
    @RequestMapping("queryByContractId")
    public Result queryByContractId(@RequestHeader String id){
        return invoiceService.queryByContractId(id);
    }

    @RequestMapping("queryBaseByContractId")
    public Result queryBaseByContractId(@RequestHeader String id){
        return invoiceService.queryBaseByContractId(id);
    }
}
