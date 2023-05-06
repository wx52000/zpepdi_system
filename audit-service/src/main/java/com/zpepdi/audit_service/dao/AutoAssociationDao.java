package com.zpepdi.audit_service.dao;

import com.zpepdi.audit_service.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AutoAssociationDao {

    List<Map<String,Object>> queryBySelf(User user);
}
