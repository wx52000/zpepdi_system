package com.zpepdi.eureka_client.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserListListener extends AnalysisEventListener<Map<Integer,Object>> {
    private List<String> list = new ArrayList<>();

    @Override
    public void invoke(Map<Integer, Object> data, AnalysisContext context) {
        list.add(data.get(0).toString());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<String> getList() {
        return list;
    }
}
