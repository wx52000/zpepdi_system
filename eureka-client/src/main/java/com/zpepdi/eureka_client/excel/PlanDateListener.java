package com.zpepdi.eureka_client.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpepdi.eureka_client.entity.ExcelProject;
import com.zpepdi.eureka_client.entity.Project;
import com.zpepdi.eureka_client.service.ProjectService;
import com.zpepdi.eureka_client.service.VolumeService;
import io.jsonwebtoken.lang.Collections;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanDateListener extends AnalysisEventListener<Map<Integer,Object>> {
    private final VolumeService volumeService;

    public PlanDateListener(VolumeService volumeService) {
        this.volumeService = volumeService;
    }
    List<Map<String,Object>> notHave = new ArrayList<>();

    List<Map<String,Object>> list = new ArrayList<>();

    @Override
    public void invoke(Map<Integer, Object> data, AnalysisContext context) {

        HashMap<String,Object> map = new HashMap<>();
        map.put("number", data.get(0));
        if (data.get(1) != null && !data.get(1).equals("")) {
            String date = data.get(1).toString();
            date = date.replaceAll("/", "-");
            date = date.replaceAll("[年,月,日]", "-");
            date = date.replaceAll("\\.", "-");
            map.put("date", date);
        }else {
            map.put("date", null);
        }
        list.add(map);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        volumeService.setPlanDate(list);
        notHave = volumeService.queryNotHave(JSONObject.toJSONString(list));
    }

    public List<Map<String,Object>> getNotHave() {
        return notHave;
    }
}
