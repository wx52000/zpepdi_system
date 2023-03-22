package com.zpepdi.qj_airhammer.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zpepdi.qj_airhammer.entity.Excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<Excel> {
    List<Excel> list = new ArrayList<>();


    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public ExcelListener() {}
    @Override
    public void invoke(Excel data, AnalysisContext context) {
        list.add(data);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {


    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    public List<Excel> getLists() {

        return list;
    }
    public void setLists(List<Excel> lists) {

        this.list = list;
    }

}
