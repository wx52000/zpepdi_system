package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.result.Result;
import zpepdi.system.service.SingleService;

@RestController
@RequestMapping("single")
public class SingleController {
    @Autowired
    private SingleService singleService;

    @RequestMapping("query")
    public Result query(){
        return singleService.query();
    }
}
