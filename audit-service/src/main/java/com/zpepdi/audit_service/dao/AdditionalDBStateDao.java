package com.zpepdi.audit_service.dao;

import com.zpepdi.audit_service.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface AdditionalDBStateDao {

    void auditOperation0(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation1(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation3(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation4(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation5(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation6(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation7(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation7Type0Other(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation7Type1(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation9(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation10(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation11(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation12(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation13(@Param("user") User user, @Param("map") Map<String, Object> map);

    void auditOperation15(@Param("user") User user, @Param("map") Map<String, Object> map);

}
