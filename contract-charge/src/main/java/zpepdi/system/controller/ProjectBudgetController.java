package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.ProjectBudgetService;

import java.util.Map;

@RestController
@RequestMapping("projectBudget")
public class ProjectBudgetController {
    @Autowired
    private ProjectBudgetService projectBudgetService;

    @RequestMapping("setBudgetMonth")
    public Result setBudgetMonth(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectBudgetService.setBudgetMonth(userId,map);
    }

    @RequestMapping("setBudgetYear")
    public Result setBudgetYear(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectBudgetService.setBudgetYear(userId,map);
    }

    @RequestMapping("setQuarter")
    public Result setQuarter(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectBudgetService.setQuarter(userId,map);
    }

    @RequestMapping("queryNowById")
    public Result queryNowById(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectBudgetService.queryNowById(map);
    }

    @RequestMapping("queryByProjectId")
    public Result queryByProjectId(@RequestBody Map<String,Object> map){
        return projectBudgetService.queryByProjectId(map);
    }
}
