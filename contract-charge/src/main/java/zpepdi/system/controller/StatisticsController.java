package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.result.Result;
import zpepdi.system.service.StatisticsService;

@RestController
@RequestMapping("statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("queryPlan")
    public Result queryPlan(@RequestHeader("year")String year){
        return statisticsService.queryPlan(year);
    }


}
