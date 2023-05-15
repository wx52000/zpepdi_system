package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zpepdi.system.result.Result;

import java.util.List;
import java.util.Map;

@Repository
public interface ContractUserDao {

    void addContractUser(@Param("userId") Integer userId, @Param("map") Map<String,Object> map);


    void delContractUserByRole(Map<String,Object> map);

    void delContractUser(Integer id);

    List<Map<String,Object>> queryByRole(Map<String,Object> map);
}
