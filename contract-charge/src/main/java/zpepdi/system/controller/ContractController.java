package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @RequestMapping("insertSingle")
    public Result insertSingle(@UserId Integer id, @RequestBody Map<String,Object> map){
        return contractService.insertSingle(id, map);
    }
    @RequestMapping("query")
    public Result query(@RequestHeader("income") Integer income){
        return contractService.query(income);
    }

    @RequestMapping("queryChildren")
    public Result queryChildren(@RequestHeader("id") String id){
        return contractService.queryChildren(id);
    }

    @RequestMapping("addChildren")
    public Result addChildren(@RequestBody Map<String,Object> map){
        return contractService.addChildren(map);
    }

    @RequestMapping("delChildren")
    public Result delChildren(@RequestHeader("id") String id){
        return contractService.delChildren(id);
    }

    @RequestMapping("queryBlur")
    public Result queryBlur(@RequestHeader("search") String search){
        return contractService.queryBlur(search);
    }


    @RequestMapping("queryById")
    public Result queryById(@RequestHeader("id") String id){
        return contractService.queryById(id);
    }

    @RequestMapping("contractReceive")
    public Result contractReceive(@RequestHeader("number") String number){
        return contractService.contractReceive(number);
    }

    @RequestMapping("queryConfirm")
    public Result queryConfirm(@UserId Integer userId){
        return contractService.queryConfirm(userId);
    }

    @RequestMapping("setConfirm")
    public Result setConfirm(@UserId Integer userId,@RequestBody List<Map<String,Object>> list){
        return contractService.setConfirm(userId,list);
    }
}
