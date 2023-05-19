package zpepdi.system.dao.fd;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InvoiceDao {

    void addRelativeBudget(Map<String,Object> map);
    //存储过程查询
    List<Map<String,Object>> queryByContractId(String id);

    List<Map<String,Object>> queryBaseByContractId(String id);

    List<Map<String,Object>> queryReceiveByInvoiceId(String id);
}
