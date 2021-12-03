package com.zpepdi.eureka_client.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkdayByProjectExcelListener extends AnalysisEventListener<Map<String,String>> {

    private List<String> list = new ArrayList<>();

    @Override
    public void invoke(Map<String, String> data, AnalysisContext context) {

        list.add(data.get("2"));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<String> getList() {
        return list;
    }
}
