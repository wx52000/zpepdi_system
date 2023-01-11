package zpepdi.system.dao.fd;

import org.springframework.stereotype.Repository;
import zpepdi.system.entity.Contract;

import java.util.List;
import java.util.Map;

@Repository
public interface ContractDao {

    List<Contract> query();

    Contract queryById(String id);

    List<Map<String,Object>> contractReceive(String id);


    List<Map<String,Object>> queryPlan(Integer year);


}