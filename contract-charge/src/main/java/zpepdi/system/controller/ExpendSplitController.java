package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.ExpendSplitService;

import java.util.Map;

@RestController
@RequestMapping("expendSplit")
public class ExpendSplitController {
    @Autowired
    private ExpendSplitService expendSplitService;

    @RequestMapping("setSplitContract")
    public Result setSplitContract(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return expendSplitService.setSplitContract(userId,map);
    }

    @RequestMapping("delSplitContract")
    public Result delSplitContract(@RequestHeader("id")Integer id){
        return expendSplitService.delSplitContract(id);
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader("id")String id){
        return expendSplitService.queryById(id);
    }
}
