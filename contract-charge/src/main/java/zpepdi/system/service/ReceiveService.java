package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.Map;

public interface ReceiveService {

    Result addReceive(Integer userId, Map<String,Object> map);
}
