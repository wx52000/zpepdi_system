package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.ExpendService;

import java.util.Map;

@RestController
@RequestMapping("expend")
public class ExpendController {
    @Autowired
    private ExpendService expendService;

    @RequestMapping("setExpend")
    public Result setExpend(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return expendService.setExpend(userId,map);
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader("id")String id){
        return expendService.queryById(id);
    }
}
