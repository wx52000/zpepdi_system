package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.ProjectService;

import java.util.Map;

@RestController
@RequestMapping("project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("query")
    public Result query(){
        return projectService.query();
    }

    @RequestMapping("queryById")
    public Result queryById(@UserId Integer userId, @RequestHeader String id){
        return projectService.queryById(id);
    }

    @RequestMapping("setLedger")
    public Result setLedger(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return projectService.setLedger(userId,map);
    }

    @RequestMapping("queryLedger")
    public Result queryLedger(@UserId Integer userId, @RequestHeader String id){
        return projectService.queryLedger(id);
    }
}
