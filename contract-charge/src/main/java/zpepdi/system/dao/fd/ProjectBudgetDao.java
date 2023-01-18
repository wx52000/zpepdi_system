package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectBudgetDao {

    void setBudgetMonth(@Param("userId")Integer userId,@Param("map") Map<String,Object> map);

    void setBudgetYear(@Param("userId")Integer userId,@Param("map") Map<String,Object> map);

    Map<String,Object> queryByProjectId(Map<String,Object> map);

    List<Map<String,Object>> queryMonthByProjectId(Map<String,Object> map);
}
