package zpepdi.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zpepdi.system.annotation.UserId;
import zpepdi.system.result.Result;
import zpepdi.system.service.NodeService;

import javax.xml.soap.Node;
import java.util.Map;

@RestController
@RequestMapping("node")
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @RequestMapping("queryByContractId")
    public Result queryByContractId(@RequestHeader("id") String id){
        return nodeService.queryByContractId(id);
    }

    @RequestMapping("setNode")
    public Result setNode(@UserId Integer userId, @RequestBody Map<String,Object> map){
        return nodeService.setNode(userId,map);
    }


}
