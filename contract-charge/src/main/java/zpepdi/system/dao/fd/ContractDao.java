package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zpepdi.system.entity.Contract;

import java.util.List;
import java.util.Map;

@Repository
public interface ContractDao {

    void insertSingle(@Param("userId") Integer userId,@Param("map") Map<String,Object> map);
    List<Map<String,Object>> query(Integer income);

    List<Map<String,Object>> queryParent(Map<String,Object> map);

    List<Map<String,Object>> queryChildren(String id);

    List<Map<String,Object>> queryZCBChildren(String id);

    List<Map<String,Object>> queryRelativeProject(String id);

    void setType(Map<String,Object> map);

    void setContractDate(Map<String,Object> map);

    void setEndTime(Map<String,Object> map);

    void addChildren(Map<String,Object> map);

    void setChildren(Map<String,Object> map);
    void delChildren(String id);

    List<Map<String,Object>> queryBlur(String search);

    Map<String,Object> queryById(String id);


    List<Map<String,Object>> contractReceive(String id);


    List<Map<String,Object>> queryPlan(Integer year);

    List<Map<String,Object>> queryConfirm(Integer userId);

    void setConfirm(@Param("userId")Integer userId,@Param("list") List<Map<String,Object>> list);
}