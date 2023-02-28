package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.result.Result;
import zpepdi.system.service.Statistics2Service;
import zpepdi.system.service.StatisticsService;

import java.util.Map;

@RestController
@RequestMapping("statistics2")
public class Statistics2Controller {
    @Autowired
    private Statistics2Service statistics2Service;

    @RequestMapping("queryPlan2")
    public Result queryPlan(@RequestBody Map<String,String> map){
        Result result = statistics2Service.queryPlan2(map.get("year"),map.get("start"),map.get("end"));
        return result;
    }



}
