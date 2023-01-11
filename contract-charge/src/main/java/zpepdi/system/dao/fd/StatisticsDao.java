package zpepdi.system.dao.fd;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StatisticsDao {

    //    合同收费计划表
    List<Map<String,Object>> queryPlan(String year);
}
