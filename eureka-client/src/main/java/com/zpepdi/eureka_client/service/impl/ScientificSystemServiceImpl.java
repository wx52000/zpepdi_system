package com.zpepdi.eureka_client.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zpepdi.eureka_client.dao.appraise.ScientificSystemDao;
import com.zpepdi.eureka_client.dao.zjepdi.ScientificWorkdayTimeDao;
import com.zpepdi.eureka_client.excel.DepartmentProduceProcess;
import com.zpepdi.eureka_client.result.Result;
import com.zpepdi.eureka_client.service.ScientificSystemService;
import com.zpepdi.eureka_client.tools.DateUtils;
import com.zpepdi.eureka_client.tools.Download;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ScientificSystemServiceImpl implements ScientificSystemService {
    @Autowired
    private ScientificSystemDao scientificSystemDao;
    @Autowired
    private ScientificWorkdayTimeDao scientificWorkdayTimeDao;

    private double getRatio(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String queryDate = "";
        try {
            Date date1 = simpleDateFormat.parse(date);
            queryDate = DateUtils.getDateMonth(date1.getTime()-100);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String url = "http://10.136.238.48:8866/queryPara";
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        HttpEntity<JSONObject> httpEntity =
                new HttpEntity<JSONObject>(headers);
        String text = restTemplate.postForEntity(url, httpEntity, String.class).getBody();
        JSONObject object = JSONObject.parseObject(text);
        double max = 10;
        if (object.get("code") != null && object.get("code").toString().equals("0")) {
            JSONObject jsonObject = object.getJSONObject("data");
            max = jsonObject.getDoubleValue("produceLimit");
        }
        double produceSum = scientificSystemDao.querySumWorkday(date);
        int staffCount = scientificSystemDao.queryScientificStaffCount();
        double scientificMax = max * staffCount;
        double actualRatio = scientificMax/ produceSum;
        return (Math.random()* 0.3 + 0.7) * actualRatio;
    }

    @Override
    public void createScientificProduce(String date) {
        double ratio = getRatio(date);
        Map<String,Object> map = new HashMap<>();
        map.put("date", date);
        map.put("scientific_date", DateUtils.getDateMonth());
        map.put("ratio",ratio);
        scientificSystemDao.setWorkday(map);
    }

    @Override
    public List<Map<String,Object>> querySumWorkdayByDep(Integer id,String date) {

        return scientificSystemDao.querySumWorkdayByDep(id,date).get(1);
    }

    @Override
    public List<Map<String,Object>> queryDownDepartment() {
        return scientificSystemDao.queryDownDepartment();
    }

    @Override
    public Result creatDepartmentExcel() {
        DepartmentProduceProcess departmentProduceProcess =
                new DepartmentProduceProcess(this);
        departmentProduceProcess.creatDepartmentFolder();
        return Result.ok();
    }

    @Override
    public Result fdDataTransmit(){
        if (!scientificSystemDao.queryIsCreate(DateUtils.getDateMonth())){
            creatDepartmentExcel();
        }
        try {
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                String url = "http://10.136.238.48:8866/produceData/fdDataTransmit";
                headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
                JSONObject paramMap = new JSONObject();
                paramMap.put("data",scientificSystemDao.queryProduce(DateUtils.getDateMonth()));
                HttpEntity<JSONObject> httpEntity =
                        new HttpEntity<JSONObject>(paramMap, headers);
                restTemplate.postForEntity(url, httpEntity, String.class);
            return Result.ok();
        }catch (Exception e){
            return Result.build(787, e.getMessage());

        }
    }

    @Override
    public HttpServletResponse down(HttpServletResponse response) throws Exception {
//        createScientificProduce(DateUtils.getDateMonth(new Date().getTime() - 2592000));
        String name= DateUtils.getDateMonth() + "工时.zip";
        Download.zip("D:\\excel\\" + name,this.getPath());
        Download.downloadFile(response,"D://excel/" + name,name);
        return response;
    }

    @Override
    public Result produceDataTransmit(Map<String, Object> map) {
        List<Map<String,Object>> list = new ArrayList<>();
        if (map.get("department") != null){
            String date = map.get("date").toString();
            if (!map.get("department").toString().equals("发电工程公司")){
                double limit = Double.parseDouble(map.get("limit").toString());
                int num = Integer.parseInt(map.get("num").toString());
                String endDate = date + "-25";
                String startDate = "";
                Date date1 = null;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
                try {
                    date1 = simpleDateFormat.parse(date);
                    startDate = DateUtils.getDateMonth(date1.getTime()-100) + "-25";
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                map.put("startDate",startDate);
                map.put("endDate",endDate);
                double sum = scientificWorkdayTimeDao.queryDepartmentSum(map);
                if ( sum/ num > limit){
                    return Result.build(757,"生产工时平均值超出管理员设置上限，请联系调整");
                }
                list = scientificWorkdayTimeDao.queryDepartment(map);
            }else {
                list = scientificSystemDao.queryProduce(date);
            }
        }
        return Result.ok(list);
    }
}
