package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.IncomeService;

import java.util.Map;

@RestController
@RequestMapping("income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @RequestMapping("setIncome")
    public Result setIncome(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return incomeService.setIncome(userId,map);
    }

    @RequestMapping("queryByContractId")
    public Result queryByContractId(@RequestBody Map<String,Object> map){
        return incomeService.queryByContractId(map);
    }
}
