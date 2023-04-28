package com.zpepdi.audit_service.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AuditDataDao {

    void addAuditData(Map<String,Object> map);


}
