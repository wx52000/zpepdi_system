package zpepdi.system.service;

import zpepdi.system.result.Result;

import java.util.Map;

public interface ContractUserService {

    Result addContractUser(Integer userId,Map<String,Object> map);

    Result delContractUser(Integer id);

    Result queryByRole(Map<String,Object> map);
}
