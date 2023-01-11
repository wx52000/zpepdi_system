package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NodeDao {

    List<Map<String,Object>> queryByContractId(String contractId);
    void setNode(@Param("userId") Integer userId, @Param("map")Map<String,Object> map);
}
