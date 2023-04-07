package com.zpepdi.eureka_client.dao.zjepdi;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ZJEPDIDataTransmissionDao {

    List<Map<String,Object>> queryFromZJEPDI(String number);

    Map<String,Object> queryProjectFromZJEPDI(String number);

    List<Map<String,Object>> queryIncomeInformation();

    List<Map<String,Object>> queryCheckerList(String processInstanceId);

    Map<String,Object> queryProjectVal(String number);

    List<Map<String,Object>> queryTecList(String number);

    //    合同数据 检索 模糊查询
    List<Map<String,Object>> queryContractListBySearch(String search);

    //项目数据检索 模糊查询
    List<Map<String,Object>> proListBySearch(String search);




}
