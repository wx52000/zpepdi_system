package zpepdi.system.dao.fd;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface Statistics2Dao {

    //    合同收费计划表
    List<Map<String,Object>> queryPlan2(@Param("year") String year,@Param("year2") String year2, @Param("start")String start, @Param("end")String end);

}
