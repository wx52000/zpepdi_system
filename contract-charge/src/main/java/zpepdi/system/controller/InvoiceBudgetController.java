package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.InvoiceBudgetService;

import java.util.Map;

@RestController
@RequestMapping("budget")
public class InvoiceBudgetController {
    @Autowired
    private InvoiceBudgetService budgetService;

    @RequestMapping("setBudgetMonth")
    public Result setBudgetMonth(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return budgetService.setBudgetMonth(userId,map);
    }

    @RequestMapping("setBudgetYear")
    public Result setBudgetYear(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return budgetService.setBudgetYear(userId,map);
    }

    @RequestMapping("setBudgetQuarter")
    public Result setBudgetQuarter(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return budgetService.setBudgetQuarter(userId,map);
    }

    @RequestMapping("queryByContractId")
    public Result queryByContractId(@RequestBody Map<String,Object> map){
        return budgetService.queryByContractId(map);
    }

    @RequestMapping("queryNowById")
    public Result queryNowById(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return budgetService.queryNowById(userId,map);
    }

    @RequestMapping("queryAllMonthByContractId")
    public Result queryAllMonthByContractId(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return budgetService.queryAllMonthByContractId(userId,map);
    }
}
