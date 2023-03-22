package com.zpepdi.qj_heating.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zpepdi.qj_heating.dao.TabletestDao;
import com.zpepdi.qj_heating.entity.TableList;
import com.zpepdi.qj_heating.opcvTest.JsonBean;
import com.zpepdi.qj_heating.opcvTest.Table;
import com.zpepdi.qj_heating.opcvTest.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("create")
@CrossOrigin(origins = "*")
@DS("slave_1")
public class TabletestController {

    @Autowired
    private TabletestDao tabletestDao;


    /**
     * 创建数据库表
     *
     * @return 是否创建成功
     */
    @PostMapping("/createTable")
    public ResponseEntity<String> createTableTest() {
        for(int i = 1;i<=1;i++){
            String name = "image"+i+".png";
            TableList gettable = gettable(name);

//            List<String> btList = gettable.getBtList();
//            btList.add("普朗特数");
//            List<String> l2 = new ArrayList<>();
//            for(String bt : btList){
//                l2.add("相对湿度"+bt.substring(0,2));
//            }
//            gettable.setBtList(btList);

            try {
                // 创建数据库表
                tabletestDao.createTable(gettable);
                //增加数据
                tabletestDao.insertTable(gettable);

            } catch (Exception e) {
                System.out.println(e);
//                return ResponseEntity.status(500).body("创建数据库表失败");
            }
//            return ResponseEntity.ok("创建数据库表成功");
        }
        return ResponseEntity.ok("成功");
    }

    public TableList gettable(String name){
        String result = Table.table(name);
        System.out.println(result);
        TableList tableList = new TableList();
        JsonBean jsonBean = GsonUtils.fromJson(result, JsonBean.class);
        List<JsonBean.B> b = jsonBean.getTables_result();
        for(JsonBean.B i : b){
            List<JsonBean.B.header> header = i.getHeader();
            List<JsonBean.B.body> body = i.getBody();
            String hsum = "";
            for(JsonBean.B.header h: header){
                hsum+=h.getWords();
            }
            //设置表名
            tableList.settName(hsum);
            System.out.println(hsum);
            System.out.println("---------------");
            String row_start = "0";
            String col_start = "";
            int bt1 = 1;
            ArrayList<String> arr = new ArrayList<String>();
            List<String> arrlist = new ArrayList<>();
            List<List<String>> arrlist2 = new ArrayList<>();
            int s = 1;
            for(JsonBean.B.body bo: body){
                //如果不是同一行
                if(!row_start.equals(bo.getRow_start())){
                    bt1 = 2;
                    row_start = bo.getRow_start();
                    System.out.println();
                }
                //内容不为空
                if(!bo.getWords().isEmpty()){
                    //如果没标题
                    if(bt1 == 1){
                        arr.add(bo.getWords().trim().replace("\n", "").replace("-","_").replace("/","_").replace(" ","").replace("(","_").replace(")","_").replace(".","_").replace("%",""));
                        continue;
                    }
                    //将数据加入到list
                    int arrl = arr.size();

                    arrlist.add(bo.getWords().replace("\n", ""));
                    if(s%arrl == 0){
                        if(!arrlist.isEmpty()){
                            arrlist2.add(arrlist);
                            arrlist = new ArrayList<>();
                        }
                    }
                    s++;
                    System.out.print(bo.getWords().replace("\n", "")+"\t");
                }else {
                    arrlist.add(" ");
                    s++;
                    System.out.print("\t");
                }
            }
            if(!arr.isEmpty()){
                tableList.setBtList(arr);

            }
            if(!arrlist2.isEmpty()){
                tableList.setBodyList(arrlist2);

            }
        }

        return tableList;
    }
}

