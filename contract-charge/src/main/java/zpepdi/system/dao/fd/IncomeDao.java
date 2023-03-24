package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IncomeDao {

    void setIncome(@Param("userId")Integer userId,
                   @Param("map") Map<String,Object> map);

    List<Map<String,Object>> queryByContractId(Map<String,Object> map);

    List<Map<String,Object>> query(Map<String,Object> map);
}
