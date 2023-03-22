package zpepdi.system.dao.zjepdi;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectDataDao {

//    查询阶段项目
    List<Map<String,Object>> queryList();

    List<Map<String,Object>> queryListBySearch(String search);

    List<Map<String,Object>> queryMarketList();
}
