package com.zpepdi.eureka_client.service;

import com.alibaba.fastjson.JSON;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.tools.DateUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ScientificSystemService {

    default String getPath(){
        String path = "D:\\excel\\";
        return path + DateUtils.getDateMonth() + "工时";
    }


    void createScientificProduce(String date);

    List<Map<String,Object>> querySumWorkdayByDep(Integer id,String date);

    List<Map<String,Object>> queryDownDepartment();

    Result fdDataTransmit();

    Result creatDepartmentExcel();

    HttpServletResponse  down(HttpServletResponse response) throws Exception;

    Result produceDataTransmit(Map<String,Object> map);
}
