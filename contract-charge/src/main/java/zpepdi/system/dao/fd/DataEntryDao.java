package zpepdi.system.dao.fd;

import java.util.List;
import java.util.Map;

public interface DataEntryDao {

//    插入合同数据
    void insertContractData(List<Map<String,Object>> list);

    void insertProjectData(List<Map<String,Object>> list);

    void insertMarketProjectData(List<Map<String,Object>> list);
}
