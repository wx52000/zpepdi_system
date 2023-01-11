package zpepdi.system.dao.fd;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Repository
public interface SingleDao {

    List<Map<String,Object>> query();

}
