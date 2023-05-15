package zpepdi.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.ContractUserService;

import java.util.Map;

@RestController
@RequestMapping("contractUser")
public class ContractUserController {

    @Autowired
    private ContractUserService contractUserService;

    @RequestMapping("addContractUser")
    public Result addContractUser(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return contractUserService.addContractUser(userId, map);
    }

    @RequestMapping("delContractUser")
    public Result delContractUser(@UserId Integer userId, @RequestHeader("id") Integer id){
        return contractUserService.delContractUser(id);
    }

    @RequestMapping("queryByRole")
    public Result queryByRole(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return contractUserService.queryByRole( map);
    }

}
