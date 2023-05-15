package zpepdi.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractSplitService;

import java.util.Map;

@RestController
@RequestMapping("contractSplit")
public class ContractSplitController {
    @Autowired
    private ContractSplitService contractSplitService;

    @RequestMapping("setSplit")
    public Result setSplit(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return contractSplitService.setSplit(userId,map);
    }


    @RequestMapping("setContractZCBSplit")
    public Result setContractZCBSplit(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return contractSplitService.setContractZCBSplit(map);
    }

}
