package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpendDao {

    void setExpend(@Param("userId")Integer userId,
                         @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryById(String id);
}
