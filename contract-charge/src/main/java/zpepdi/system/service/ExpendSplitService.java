package zpepdi.system.service;

import org.apache.ibatis.annotations.Param;
import zpepdi.system.result.Result;

import java.util.List;
import java.util.Map;

public interface ExpendSplitService {

    Result setSplitContract(@Param("userId")Integer userId, @Param("map") Map<String,Object> map);

    Result delSplitContract(int id);

    Result queryById(String id);
}
