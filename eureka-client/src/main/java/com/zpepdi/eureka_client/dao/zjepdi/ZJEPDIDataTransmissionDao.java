package com.zpepdi.eureka_client.dao.zjepdi;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ZJEPDIDataTransmissionDao {

    List<Map<String,Object>> queryFromZJEPDI(String name);

    List<Map<String,Object>> queryIncomeInformation();
}
