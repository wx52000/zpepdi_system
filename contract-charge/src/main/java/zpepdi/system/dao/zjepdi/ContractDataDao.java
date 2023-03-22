package zpepdi.system.dao.zjepdi;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContractDataDao {

    List<Map<String,Object>> queryList();

    List<Map<String,Object>> queryListBySearch(String search);
 }
