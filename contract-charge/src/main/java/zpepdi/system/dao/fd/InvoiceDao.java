package zpepdi.system.dao.fd;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InvoiceDao {

    List<Map<String,Object>> queryByContractId(String id);
}
