package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.IncomeBudgetService;

import java.util.Map;

@RestController
@RequestMapping("incomeBudget")
public class IncomeBudgetController {
    @Autowired
    private IncomeBudgetService incomeBudgetService;

    @RequestMapping("setBudgetMonth")
    public Result setBudgetMonth(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return incomeBudgetService.setBudgetMonth(userId,map);
    }

    @RequestMapping("setBudgetYear")
    public Result setBudgetYear(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return incomeBudgetService.setBudgetYear(userId,map);
    }

    @RequestMapping("queryByContractId")
    public Result queryByContractId(@RequestBody Map<String,Object> map){
        return incomeBudgetService.queryByContractId(map);
    }

    @RequestMapping("queryMonthByContractId")
    public Result queryMonthByContractId(@RequestBody Map<String,Object> map){
        return incomeBudgetService.queryMonthByContractId(map);
    }
}
