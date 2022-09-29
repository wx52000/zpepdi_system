package com.zpepdi.eureka_client.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.merge.AbstractMergeStrategy;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.alibaba.excel.write.merge.OnceAbsoluteMergeStrategy;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.zpepdi.eureka_client.service.ScientificSystemService;
import com.zpepdi.eureka_client.tools.DateUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class DepartmentProduceProcess {

    private final ScientificSystemService scientificSystemService;

    public DepartmentProduceProcess(ScientificSystemService scientificSystemService) {
        this.scientificSystemService = scientificSystemService;
        downDepartments = this.scientificSystemService.queryDownDepartment();
    }

    List<Map<String,Object>> downDepartments = new ArrayList<>();

    public void creatDepartmentFolder(){
        downDepartments.forEach(item -> {
            List<Map<String,Object>> list =
                    scientificSystemService.querySumWorkdayByDep(
                            Integer.valueOf(item.get("did").toString()),
                            DateUtils.getDateMonth());
            List<List<String>> headers = new ArrayList<>();
            String departmentName = item.get("name").toString();
            List<String> head = new ArrayList<>();
            head.add(departmentName);
            head.add("序号");
            head.add("序号");
            headers.add(new ArrayList<>(head));
            head.clear();
            head.add(departmentName);
            head.add("工程编号");
            head.add("工程编号");
            headers.add(new ArrayList<>(head));
            head.clear();
            head.add(departmentName);
            head.add("工程名称");
            head.add("工程名称");
            headers.add(new ArrayList<>(head));
            head.clear();
            head.add(departmentName);
            head.add("阶段");
            head.add("阶段");
            headers.add(new ArrayList<>(head));
            head.clear();
            List<String> tecList = new ArrayList<>();
            if (list != null && list.size() > 0){
                Map<String,Object > map = list.get(0);

                Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()){
                    String key = iterator.next().getKey();
                    if (!key.equals("name")&& !key.equals("number") && !key.equals("stage")){
                        head.add(DateUtils.getDateMonth());
                        head.add("各专业耗工");
                        tecList.add(key);
                        head.add(key);
                        headers.add(new ArrayList<>(head));
                        head.clear();
                    }
                }
            }
            head.add(DateUtils.getDateMonth());
            head.add("合计耗工");
            head.add("合计耗工");
            headers.add(new ArrayList<>(head));
            List<List<String>> listList = data(list,tecList);
            OnceAbsoluteMergeStrategy onceAbsoluteMergeStrategy =
                    new OnceAbsoluteMergeStrategy(listList.size()+2, listList.size()+2,
                            0,5+tecList.size());
//            String folder = DateUtils.getDateMonth() + "工时";
            Path path = Paths.get(scientificSystemService.getPath());
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileName = path +"\\" +departmentName + DateUtils.getDateMonth() +".xlsx";
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(contentWriteCellStyle, contentWriteCellStyle);
            EasyExcel.write(fileName)
                    // 这里放入动态头
                    .head(headers).sheet("Sheet1")
                    .registerWriteHandler(onceAbsoluteMergeStrategy)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .doWrite(listList);
        });
    }

    private List<List<String>> data(List<Map<String,Object>> list,List<String> tecList){

        List<List<String>> lists = new ArrayList<>();
        if (list == null){
            return lists;
        }
        int n = 5;
        DecimalFormat df = new DecimalFormat("#.##");
        for (AtomicInteger i = new AtomicInteger(); i.get() < list.size(); i.getAndIncrement()) {
            Map<String,Object> map = list.get(i.get());
            List<String>  strings = new ArrayList<>();
            strings.add(String.valueOf(i.get() +1));
            strings.add(map.get("number").toString());
            strings.add(map.get("name").toString());
            strings.add(map.get("stage").toString());
            AtomicReference<Double> sum = new AtomicReference<>((double) 0);
            tecList.forEach(item ->{
                i.getAndIncrement();
                String value = df.format(map.get(item));
                strings.add(value);
                sum.updateAndGet(v -> new Double(v + Double.parseDouble(value)));
            });
            strings.add(df.format(sum.get()));
            lists.add(strings);
        }
        List<String> footer = new ArrayList<>();
        for (int i = 0; i < n; i++){
            footer.add("制表人：        室主任：");
        }
        lists.add(footer);
        return lists;
    }

}
