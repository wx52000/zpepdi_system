package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.ReceiveService;

import java.util.Map;

@RestController
@RequestMapping("receive")
public class ReceiveController {

    @Autowired
    private ReceiveService receiveService;

    @RequestMapping("addReceive")
    public Result addReceive(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return receiveService.addReceive(userId,map);
    }
}
