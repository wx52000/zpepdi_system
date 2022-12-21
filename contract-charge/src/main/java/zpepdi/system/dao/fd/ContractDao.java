package zpepdi.system.dao.fd;

import org.springframework.stereotype.Repository;
import zpepdi.system.entity.Contract;

import java.util.List;

@Repository
public interface ContractDao {

    List<Contract> query();

    List<Contract> queryById(String id);


}