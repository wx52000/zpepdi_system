package zpepdi.system.service;

import zpepdi.system.entity.Contract;
import zpepdi.system.result.Result;

import java.util.List;

public interface ContractService {

    Result query();

    Result queryById(String id);

}
