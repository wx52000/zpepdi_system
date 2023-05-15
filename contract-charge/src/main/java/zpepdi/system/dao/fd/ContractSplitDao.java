package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ContractSplitDao {

    void setSplit(@Param("userId")Integer userId, @Param("map")Map<String,Object> map);

    void setContractZCBSplit(Map<String,Object> map);
}
