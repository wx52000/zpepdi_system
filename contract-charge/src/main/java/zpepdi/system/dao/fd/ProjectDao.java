package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectDao {

    void addRelateContract(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    void updateData(Map<String,Object> map);
    void delRelateContract(Map<String,Object> map);
    void insertSingle(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);
    List<Map<String,Object>> query();

    Map<String,Object> queryById(String id);

    List<Map<String,Object>> queryContractByProjectId(String id);

    void setLedger(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);

    void delLeader(Integer id);

    List<Map<String,Object>> queryLedger(String id);

    List<Map<String,Object>> querySearch(String search);
}
