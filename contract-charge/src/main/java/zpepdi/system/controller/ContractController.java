package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractService;

@RestController
@RequestMapping("contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @RequestMapping("query")
    public Result query(){
        return contractService.query();
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader("id") String id){
        return contractService.queryById(id);
    }

    @RequestMapping("contractReceive")
    public Result contractReceive(@RequestHeader("number") String number){
        return contractService.contractReceive(number);
    }
}
