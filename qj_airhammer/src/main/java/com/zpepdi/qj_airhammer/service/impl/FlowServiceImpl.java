package com.zpepdi.qj_airhammer.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.SaveFormat;
import com.itextpdf.text.pdf.PdfDocument;
import com.zpepdi.qj_airhammer.capacity.Water;
import com.zpepdi.qj_airhammer.dao.FlowDao;
import com.zpepdi.qj_airhammer.entity.Flow;
import com.zpepdi.qj_airhammer.result.Result;
import com.zpepdi.qj_airhammer.service.FlowService;
//import com.zpepdi.qj_airhammer.utils.FileUtils;
import com.zpepdi.qj_airhammer.utils.FileUtils1;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class FlowServiceImpl implements FlowService {

    @Autowired
    private FlowDao flowDao;

    @Override
    public Result compute(Map<String, Object> map) {

        double x=1;
        String x1=map.get("X").toString();
        if(!x1.equals(""))
        {
            x= Double.valueOf(map.get("X").toString());
        }
            Water water=new Water();
            String p=map.get("P").toString();
            double mpa=0;
            if(!map.get("Mpa").equals("")){
                mpa=Double.valueOf(map.get("Mpa").toString());
            }

            if(p.equals("MPa")){
                mpa=mpa;
            }
            else if(p.equals("kg/cm^2")){
                mpa=mpa* 0.0980665;
            }
            else if(p.equals("bar")){
                mpa=mpa* 0.1;
            }
            else if(p.equals("kPa")){
                mpa=mpa* 0.001;
            }
            else if(p.equals("psi-1b/in^2")){
                mpa=mpa* 0.006894757;
            }
            else if(p.equals("atm")){
                mpa=mpa* 0.1013325;
            }
            String t=map.get("T").toString();

        double temp=0;
        if(!map.get("Temperature").equals("")){
            temp=Double.valueOf(map.get("Temperature").toString());
        }
            if(t.equals("℃")){
                temp=temp;
            }
            else if(t.equals("℉")){
                temp=(temp-32)* 5.0 / 9.0;
            }
            else if(t.equals("K 开尔文")){
                temp=temp- 273.15;
            }
            else if(t.equals("R 兰金")){
                temp=(temp-491.67)* 5.0 / 9.0;
            }
             String meduim=water.m_type_PT(mpa,temp);
            String vO=map.get("V").toString();
        double volume=0;
        if(!map.get("Volume").equals("")){
            volume=Double.valueOf(map.get("Volume").toString());
        }

            String w=map.get("W").toString();

        double weight=0;
        if(!map.get("Weight").equals("")){
            weight=Double.valueOf(map.get("Weight").toString());
        }
            if(w.equals("t/h")){
                weight=weight*1000/3600;
            }
        double Out=0;
        if(!map.get("Out").equals("")){
            Out=Double.valueOf(map.get("Out").toString());
        }
        double Width=0;
        if(!map.get("Width").equals("")){
            Width=Double.valueOf(map.get("Width").toString());
        }
        double in=0;
        if(!map.get("In").equals("")){
            in=Double.valueOf(map.get("In").toString());
        }

        double Q=0;
        double ap=0;
        if(Out!=0&&Width!=0){
            in=Out/1000-2*Width/1000;
            ap=Math.PI*Math.pow((in/2),2);
            map.put("In",in*1000);
        }
        else{
            ap=Math.PI*Math.pow((in/2),2);
        }
//        if(in!=0){
//            ap=Math.PI*Math.pow((in/2),2);
//        }
//        else{
//
//
//        }

            double v=water.v_pt(mpa,temp,x);
            if(weight!=0){
               Q=weight*v;
                map.put("Volume",Q);
            }
            else{
                Q=volume;
                weight=volume/v;
                map.put("Weight",weight);
            }
            double result=Q/ap;

            map.put("Actual",result);
            map.put("X",x);
            map.put("Medium",meduim);
            return Result.ok(map);
    }
    @Override
    public Result judge(Map<String, Object> map) {
     Water water = new Water();
        String p=map.get("P").toString();
        double mpa=0;
        if(!map.get("Mpa").equals("")){
            mpa=Double.valueOf(map.get("Mpa").toString());
        }

        if(p.equals("MPa")){
            mpa=Double.valueOf(mpa);
        }
        else if(p.equals("kg/cm^2")){
            mpa=Double.valueOf(mpa)* 0.0980665;
        }
        else if(p.equals("bar")){
            mpa=Double.valueOf(mpa)* 0.1;
        }
        else if(p.equals("kPa")){
            mpa=Double.valueOf(mpa)* 0.001;
        }
        else if(p.equals("psi-1b/in^2")){
            mpa=Double.valueOf(mpa)* 0.006894757;
        }
        else if(p.equals("atm")){
            mpa=Double.valueOf(mpa)* 0.1013325;
        }
        String t=map.get("T").toString();
        double temp=0;
        if(!map.get("Temperature").equals("")){
            temp=Double.valueOf(map.get("Temperature").toString());
        }
        if(t.equals("℃")){
            temp=Double.valueOf(temp);
        }
        else if(t.equals("℉")){
            temp=(Double.valueOf(temp)-32)* 5.0 / 9.0;
        }
        else if(t.equals("K 开尔文")){
            temp=Double.valueOf(temp)- 273.15;
        }
        else if(t.equals("R 兰金")){
            temp=(Double.valueOf(temp)-491.67)* 5.0 / 9.0;
        }
        double Tempresult=water.Ts(mpa);
        double abs = 0;
        double btemp=Math.abs(temp-Tempresult)/Tempresult;
//        System.out.println(btemp);
        if(btemp<0.005&&btemp>=0){
            abs=1;
        }
        else{
            abs=0;
        }
        map.put("abs",abs);
        return Result.ok(map);
    }
    @Override
    public HttpServletResponse export(HttpServletResponse response, String json) throws IOException {
        File tempFile = File.createTempFile("tmp", ".txt");
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(tempFile));
        List<Flow> flowlist = JSONArray.parseArray(json, Flow.class);
        List<Map<String, Object>> flow = flowlist.stream()
                .map(p -> {
                    Map<String, Object> map = new HashMap<>();
                    if(p.getGName()==null){
                        map.put("GName", "管道名无数据");
                    }
                    else if(p.getGName().equals("")){
                        map.put("GName", "管道名无数据");
                    }
                    else{
                        map.put("GName", p.getGName());
                    }
                    if(p.getName()==null){
                        map.put("Name", "管道类别无数据");
                    }
                    else if(p.getName().equals("")){
                        map.put("Name", "管道类别无数据");
                    }
                    else{
                        map.put("Name", p.getName());
                    }

                    if(p.getMpa()==null){
                        map.put("Mpa", "压力无数据");
                    }
                    else{
                        map.put("Mpa", p.getMpa()+"("+p.getP()+")");
                    }
                    if(p.getTemperature()==null){
                        map.put("Temperature", "温度无数据");
                    }
                    else{
                        map.put("Temperature", p.getTemperature()+"("+p.getT()+")");
                    }
//                  map.put("Temperature", p.getTemperature()+p.getT());
                    if(p.getMedium1().equals("Over the range")){
                        map.put("Medium", "汽/水");
                    }
                    else if(p.getMedium1()==null){
                        map.put("Medium", "介质暂无数据");
                    }
                    else{
                        map.put("Medium", p.getMedium1());
                    }
                    if(p.getOut()==null){
                        map.put("Out", "外径无数据");
                    }
                    else{
                        map.put("Out", p.getOut()+"(mm)");
                    }
//                    map.put("Out", p.getOut()+"mm");
                    if(p.getWidth()==null){
                        map.put("Width","壁厚无数据");
                    }
                    else{
                        map.put("Width",  p.getWidth()+"(mm)");
                    }

                    if(p.getIn()==null){
                        map.put("In","内径无数据");
                    }
                    else{
                        map.put("In",  p.getIn()+"(mm)");
                    }

//                    map.put("Width", p.getWidth()+"mm");
                    if(p.getVolume()==null){
                        map.put("Volume", "体积流量无数据");
                    }
                    else{
                        map.put("Volume",  p.getVolume()+"("+p.getV()+")");
                    }
//                    map.put("Volume", p.getVolume()+p.getV());
                    if(p.getWeight()==null){
                        map.put("Weight", "质量流量无数据");
                    }
                    else{
                        map.put("Weight",  p.getWeight()+"("+p.getW()+")");
                    }
//                    map.put("Weight", p.getWeight()+p.getW());
                    if(p.getActual()==null){
                        map.put("Actual", "流速无数据");
                    }
                    else{
                        map.put("Actual",  p.getActual()+"(m/s)");
                    }
                    return map;
                }).collect(Collectors.toList());

        for (int i = 0; i < flow.size(); i++) {
            bw.write(i+1+" ");
            bw.write(flow.get(i).get("GName").toString()+" ");
            bw.write(flow.get(i).get("Name").toString()+" ");
            bw.write(flow.get(i).get("Mpa").toString()+" ");
            bw.write(flow.get(i).get("Temperature").toString()+" ");
            bw.write(flow.get(i).get("Medium").toString()+" ");
            bw.write(flow.get(i).get("Out").toString()+" ");
            bw.write(flow.get(i).get("Width").toString()+" ");
            bw.write(flow.get(i).get("In").toString()+" ");
            bw.write(flow.get(i).get("Volume").toString()+" ");
            bw.write(flow.get(i).get("Weight").toString()+" ");
            bw.write(flow.get(i).get("Actual").toString());
            bw.newLine();
            bw.flush();}
        bw.close();
        FileOutputStream fos = null;

        try {
            String fileName="D://商品分析统计.txt";
            response.setContentType("text/plain;UTF-8");
            OutputStream os = response.getOutputStream();
            //这里进行设置了一个文件名，其实也可以不要设置了，
            //在前端进行下载的时候需要重新给定一个文件名进行下载
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-disposition","attachment;filename=="+ URLEncoder.encode(fileName,"UTF-8"));
//            os.write(tempFile.getBytes());
            File file = new File(tempFile.toURI());
            byte[] bytes = FileUtils.readFileToByteArray(file);
            os.write(bytes);
            os.flush();
            os.close();
            tempFile.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

//    public HttpServletResponse export(HttpServletResponse response, String json) throws IOException {
//        InputStream in = this.getClass().getClassLoader().getResourceAsStream(
//                "template/计算书模板.docx");
////        FileInputStream in = new FileInputStream("src\\main\\java\\com\\zpepdi\\qj_airhammer\\doc\\Test.docx");//载入文档
//        XWPFDocument xwpf = new XWPFDocument(in);//得到word文档的信息
//        Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格
//        List<XWPFTable> tables = xwpf.getTables();
//        XWPFTable table1 = tables.get(1);//获取第一个表格
//        // XWPFTableRow table1 = tables.get(0).getRow(0);
//        List<Flow> flowlist = JSONArray.parseArray(json, Flow.class);
//        System.out.println(flowlist);
//        List<Map<String, Object>> flow = flowlist.stream()
//                .map(p -> {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("GName", p.getGName());
//                    map.put("Name", p.getName());
//                    if(p.getMpa()==null){
//                        map.put("Mpa", "");
//                    }
//                    else{
//                        map.put("Mpa", p.getMpa()+p.getP());
//                    }
//                    if(p.getTemperature()==null){
//                        map.put("Temperature", "");
//                    }
//                    else{
//                        map.put("Temperature", p.getTemperature()+p.getT());
//                    }
////                  map.put("Temperature", p.getTemperature()+p.getT());
//                    if(p.getMedium().equals("Over the range")){
//                        map.put("Medium", "汽/水");
//                    }
//                    else{
//                        map.put("Medium", p.getMedium());
//                    }
//                    if(p.getOut()==null){
//                        map.put("Out", "");
//                    }
//                    else{
//                        map.put("Out", p.getOut()+"mm");
//                    }
////                    map.put("Out", p.getOut()+"mm");
//                    if(p.getWidth()==null){
//                        map.put("Width", "");
//                    }
//                    else{
//                        map.put("Width",  p.getWidth()+"mm");
//                    }
////                    map.put("Width", p.getWidth()+"mm");
//                    if(p.getVolume()==null){
//                        map.put("Volume", "");
//                    }
//                    else{
//                        map.put("Volume",  p.getVolume()+p.getV());
//                    }
////                    map.put("Volume", p.getVolume()+p.getV());
//                    if(p.getWeight()==null){
//                        map.put("Weight", "");
//                    }
//                    else{
//                        map.put("Weight",  p.getWeight()+p.getW());
//                    }
////                    map.put("Weight", p.getWeight()+p.getW());
//                    map.put("Actual", p.getActual()+"m/s");
//                    return map;
//                }).collect(Collectors.toList());
//
//        if (flow != null && flow.size()>0){
//            for(int i=0;i<flow.size();i++){
//                XWPFTableRow row = table1.insertNewTableRow(1+i);//为第2+i行向下添加一个新行
//                row.createCell().setText(String.valueOf(i));//添加第三个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("GName")));//添加第三个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Name")));//添加第三个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Mpa")));//添加第四个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Temperature")));//添加第五个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Medium")));//添加第五个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Out")));//添加第五个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Width")));//添加第五个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Volume")));//添加第五个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Weight")));//添加第五个列
//                row.createCell().setText(String.valueOf(flow.get(i).get("Actual")));//添加第五个列
//            }
//        }
//        String fileName="D://商品分析统计.docx";
//        File newFile = new File(fileName);
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(newFile);
//            xwpf.write(fos);
//            response.setContentType("application/pdf;charset=utf-8");
//            OutputStream os = response.getOutputStream();
//            //这里进行设置了一个文件名，其实也可以不要设置了，
//            //在前端进行下载的时候需要重新给定一个文件名进行下载
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
//            response.setHeader("Content-disposition","attachment;filename=="+ URLEncoder.encode(fileName,"UTF-8"));
//            FileInputStream inputStream = new FileInputStream(fileName);
//            FileUtils.wordTopdfByAspose(inputStream,os);
//            os.flush();
//            os.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fos != null) {
//                    fos.flush();
//                }
//                if (fos != null) {
//                    fos.close();
//                }
//                xwpf.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        newFile.delete();
//        return response;
//    }


    @Override
    public HttpServletResponse export1(HttpServletResponse response, String json) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(
                "template/计算书模板.docx");
//        FileInputStream in = new FileInputStream("src\\main\\java\\com\\zpepdi\\qj_airhammer\\doc\\Test.docx");//载入文档
        XWPFDocument xwpf = new XWPFDocument(in);//得到word文档的信息
        Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格
        List<XWPFTable> tables = xwpf.getTables();
        XWPFTable table1 = tables.get(1);//获取第一个表格
        // XWPFTableRow table1 = tables.get(0).getRow(0);
        List<Flow> flowlist = JSONArray.parseArray(json, Flow.class);
        System.out.println(flowlist);
        List<Map<String, Object>> flow = flowlist.stream()
                .map(p -> {
                    Map<String, Object> map = new HashMap<>();
                    if(p.getGName()==null){
                        map.put("GName", "管道名无数据");
                    }
                    else{
                        map.put("GName", p.getGName());
                    }
                    if(p.getName()==null){
                        map.put("Name", "管道类别无数据");
                    }
                    else if(p.getName().equals("")){
                        map.put("Name", "管道类别无数据");
                    }
                    else{
                        map.put("Name", p.getName());
                    }

                    if(p.getMpa()==null){
                        map.put("Mpa", "压力无数据");
                    }
                    else{
                        map.put("Mpa", p.getMpa()+"("+p.getP()+")");
                    }
                    if(p.getTemperature()==null){
                        map.put("Temperature", "温度无数据");
                    }
                    else{
                        map.put("Temperature", p.getTemperature()+"("+p.getT()+")");
                    }
//                  map.put("Temperature", p.getTemperature()+p.getT());
                    if(p.getMedium1().equals("Over the range")){
                        map.put("Medium", "汽/水");
                    }
                    else if(p.getMedium1()==null){
                        map.put("Medium", "介质暂无数据");
                    }
                    else{
                        map.put("Medium", p.getMedium1());
                    }
                    if(p.getOut()==null){
                        map.put("Out", "外径无数据");
                    }
                    else{
                        map.put("Out", p.getOut()+"(mm)");
                    }
//                    map.put("Out", p.getOut()+"mm");
                    if(p.getWidth()==null){
                        map.put("Width","壁厚无数据");
                    }
                    else{
                        map.put("Width",  p.getWidth()+"(mm)");
                    }

                    if(p.getIn()==null){
                        map.put("In","内径无数据");
                    }
                    else{
                        map.put("In",  p.getIn()+"(mm)");
                    }

//                    map.put("Width", p.getWidth()+"mm");
                    if(p.getVolume()==null){
                        map.put("Volume", "体积流量无数据");
                    }
                    else{
                        map.put("Volume",  p.getVolume()+"("+p.getV()+")");
                    }
//                    map.put("Volume", p.getVolume()+p.getV());
                    if(p.getWeight()==null){
                        map.put("Weight", "质量流量无数据");
                    }
                    else{
                        map.put("Weight",  p.getWeight()+"("+p.getW()+")");
                    }
//                    map.put("Weight", p.getWeight()+p.getW());
                    if(p.getActual()==null){
                        map.put("Actual", "流速无数据");
                    }
                    else{
                        map.put("Actual",  p.getActual()+"(m/s)");
                    }
                    return map;
                }).collect(Collectors.toList());

        if (flow != null && flow.size()>0){
            for(int i=0;i<flow.size();i++){
                XWPFTableRow row = table1.insertNewTableRow(1+i);//为第2+i行向下添加一个新行
                row.createCell().setText(String.valueOf(i+1));//添加第三个列
                row.createCell().setText(String.valueOf(flow.get(i).get("GName")));//添加第三个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Name")));//添加第三个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Mpa")));//添加第四个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Temperature")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Medium")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Out")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Width")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Volume")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Weight")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Actual")));//添加第五个列
            }
        }
        String fileName="D://商品分析统计.docx";
        File newFile = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(newFile);
            xwpf.write(fos);
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document;charset=utf-8");
            OutputStream os = response.getOutputStream();
            //这里进行设置了一个文件名，其实也可以不要设置了，
            //在前端进行下载的时候需要重新给定一个文件名进行下载
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-disposition","attachment;filename=="+ URLEncoder.encode(fileName,"UTF-8"));
//            FileInputStream inputStream = new FileInputStream(fileName);
//            FileUtils1.wordTopdfByAspose(inputStream,os);
            xwpf.write(os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                }
                if (fos != null) {
                    fos.close();
                }
                xwpf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public HttpServletResponse export2(HttpServletResponse response, String json) throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(
                "template/计算书模板.docx");
//        FileInputStream in = new FileInputStream("src\\main\\java\\com\\zpepdi\\qj_airhammer\\doc\\Test.docx");//载入文档
        XWPFDocument xwpf = new XWPFDocument(in);//得到word文档的信息
        Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格
        List<XWPFTable> tables = xwpf.getTables();
        XWPFTable table1 = tables.get(1);//获取第一个表格
        // XWPFTableRow table1 = tables.get(0).getRow(0);
        List<Flow> flowlist = JSONArray.parseArray(json, Flow.class);
        System.out.println(flowlist);
        List<Map<String, Object>> flow = flowlist.stream()
                .map(p -> {
                    Map<String, Object> map = new HashMap<>();
                    if(p.getGName()==null){
                        map.put("GName", "管道名无数据");
                    }
                    else{
                        map.put("GName", p.getGName());
                    }
                    if(p.getName()==null){
                        map.put("Name", "管道类别无数据");
                    }
                    else if(p.getName().equals("")){
                        map.put("Name", "管道类别无数据");
                    }
                    else{
                        map.put("Name", p.getName());
                    }

                    if(p.getMpa()==null){
                        map.put("Mpa", "压力无数据");
                    }
                    else{
                        map.put("Mpa", p.getMpa()+"("+p.getP()+")");
                    }
                    if(p.getTemperature()==null){
                        map.put("Temperature", "温度无数据");
                    }
                    else{
                        map.put("Temperature", p.getTemperature()+"("+p.getT()+")");
                    }
//                  map.put("Temperature", p.getTemperature()+p.getT());
                    if(p.getMedium1().equals("Over the range")){
                        map.put("Medium", "汽/水");
                    }
                    else if(p.getMedium1()==null){
                        map.put("Medium", "介质暂无数据");
                    }
                    else{
                        map.put("Medium", p.getMedium1());
                    }
                    if(p.getOut()==null){
                        map.put("Out", "外径无数据");
                    }
                    else{
                        map.put("Out", p.getOut()+"(mm)");
                    }
//                    map.put("Out", p.getOut()+"mm");
                    if(p.getWidth()==null){
                        map.put("Width","壁厚无数据");
                    }
                    else{
                        map.put("Width",  p.getWidth()+"(mm)");
                    }

                    if(p.getIn()==null){
                        map.put("In","内径无数据");
                    }
                    else{
                        map.put("In",  p.getIn()+"(mm)");
                    }

//                    map.put("Width", p.getWidth()+"mm");
                    if(p.getVolume()==null){
                        map.put("Volume", "体积流量无数据");
                    }
                    else{
                        map.put("Volume",  p.getVolume()+"("+p.getV()+")");
                    }
//                    map.put("Volume", p.getVolume()+p.getV());
                    if(p.getWeight()==null){
                        map.put("Weight", "质量流量无数据");
                    }
                    else{
                        map.put("Weight",  p.getWeight()+"("+p.getW()+")");
                    }
//                    map.put("Weight", p.getWeight()+p.getW());
                    if(p.getActual()==null){
                        map.put("Actual", "流速无数据");
                    }
                    else{
                        map.put("Actual",  p.getActual()+"(m/s)");
                    }
                    return map;
                }).collect(Collectors.toList());

        if (flow != null && flow.size()>0){
            for(int i=0;i<flow.size();i++){
                XWPFTableRow row = table1.insertNewTableRow(1+i);//为第2+i行向下添加一个新行
                row.createCell().setText(String.valueOf(i+1));//添加第三个列
                row.createCell().setText(String.valueOf(flow.get(i).get("GName")));//添加第三个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Name")));//添加第三个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Mpa")));//添加第四个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Temperature")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Medium")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Out")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Width")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Volume")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Weight")));//添加第五个列
                row.createCell().setText(String.valueOf(flow.get(i).get("Actual")));//添加第五个列
            }
        }
        String fileName="D://商品分析统计.docx";
        File newFile = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(newFile);
            xwpf.write(fos);
            response.setContentType("application/pdf;charset=utf-8");
            OutputStream os = response.getOutputStream();
            //这里进行设置了一个文件名，其实也可以不要设置了，
            //在前端进行下载的时候需要重新给定一个文件名进行下载
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-disposition","attachment;filename=="+ URLEncoder.encode(fileName,"UTF-8"));
            FileInputStream inputStream = new FileInputStream(fileName);
            FileUtils1.wordTopdfByAspose(inputStream,os);
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                }
                if (fos != null) {
                    fos.close();
                }
                xwpf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        newFile.delete();
        return response;
    }



    @Override
    public Result save(Integer userId, Map<String, Object> map) throws UnsupportedEncodingException {
//      String json=map.get("datalist").toString();

//        json=java.net.URLDecoder.decode(json,"UTF-8");
//        System.out.println(json);
//        JSONObject jsonObject = JSON.parseObject(json);
// 获取对应的数据【返回值为String类型】 参数为对应key名称
        String str = map.get("datalist").toString();

// 转换为集合

      String personal=map.get("pname").toString();
        List<Flow> flowlist = JSON.parseArray(str, Flow.class);

        List<Map<String, Object>> flow = flowlist.stream()
                .map(p -> {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("GName", p.getGName());
                    map1.put("Name", p.getName());
                    map1.put("Mpa", p.getMpa());
                    map1.put("P", p.getP());
                    map1.put("Temperature", p.getTemperature());
                    map1.put("T", p.getT());
                    map1.put("Medium", p.getMedium());
                    map1.put("Out", p.getOut());
                    map1.put("Width", p.getWidth());
                    map1.put("Volume", p.getVolume());
                    map1.put("Weight", p.getWeight());
                    map1.put("Actual", p.getActual());
                    map1.put("Conclusion", p.getConclusion());
                    map1.put("Speed", p.getSpeed());
                    map1.put("Medium1", p.getMedium1());
                    map1.put("V", p.getV());
                    map1.put("W", p.getW());
                    map1.put("X", p.getX());
                    return map1;
                }).collect(Collectors.toList());
        System.out.println(userId);
        System.out.println(personal);
        System.out.println(flow.get(1).get("GName"));
        flowDao.doInsert(userId,personal,flow);
        return null;
    }

    @Override
    public HttpServletResponse upload(HttpServletResponse response,Integer userId,MultipartFile file, HttpServletRequest request) throws Exception {

        File log = new File("D:/AAA/BBB/CCC/", userId.toString()+".txt");
        String originalFileName = file.getOriginalFilename();
        //取文件名下标，给文件重命名的时候使用
        String suffix = originalFileName.substring(originalFileName.indexOf("."));
        //取一个随机id给文件重命名使用
        String uuid = UUID.randomUUID().toString();
        //你的接收的文件新的名字
        String filename = uuid+suffix;
        String uri = request.getSession().getServletContext().getRealPath("/");
        System.out.println(uri);
        File newfile = new File(uri+ "/" + filename);
        //将接收的到的 multipartFile 类型的文件 转为 file
        file.transferTo(newfile);
//        //获取接收到的并存在项目本地的文件
        String filepath=newfile.getAbsolutePath();
        FileInputStream fin = new FileInputStream(filepath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        int i=0;
        String P = null;
        String T= null;
        String V = null;
        String W= null;
        File tempFile1 = File.createTempFile("temp", ".txt");
        List<Map<String, Object>> flow=new ArrayList<>();
        while((strTmp = buffReader.readLine())!=null){
            StringBuilder sb=new StringBuilder();
            Map map = new HashMap();
            try{
                String[] s=strTmp.split(" ");
                if(s[1].equals("管道名无数据")){
                    s[1]="";
                }
//            else{
//                String targetStr = s[0].substring(s[0].indexOf("(") + 1, s[0].indexOf(")"));
//            }
                if(s[2].equals("管道类别无数据")){
                    s[2]="";
                }
                if(s[3].equals("压力无数据")){
                    s[3]="";
                }
                else{
                    P = s[3].substring(s[3].indexOf("(") + 1, s[3].indexOf(")"));
                    s[3] = s[3].substring(0, s[3].indexOf("("));
                    if(!isNumeric(s[3])){
                        sb.append("压力输入有误；");
                    }
                }
                if(s[4].equals("温度无数据")){
                    s[4]="";
                }
                else{
                    T= s[4].substring(s[4].indexOf("(") + 1, s[4].indexOf(")"));
                    s[4] = s[4].substring(0, s[4].indexOf("("));
                    if(!isNumeric(s[4])){
                        sb.append("温度输入有误；");
                    }
                }
//            if(s[4]=="汽/水"){
//                s[4]="";
//            }
                if(s[6].equals("外径无数据")){
                    s[6]="";
                }
                else{
                    s[6] = s[6].substring(0, s[6].indexOf("("));
                    if(!isNumeric(s[6])){
                        sb.append("外径输入有误；");
                    }
                }
                if(s[7].equals("壁厚无数据")){
                    s[7]="";
                }  else{
                    s[7] = s[7].substring(0, s[7].indexOf("("));
                    if(!isNumeric(s[7])){
                        sb.append("壁厚输入有误；");
                    }
                }
                if(s[8].equals("内径无数据")){
                    s[8]="";
                }  else{
                    s[8] = s[8].substring(0, s[8].indexOf("("));
                    if(!isNumeric(s[8])){
                        sb.append("内径输入有误；");
                    }
                }
                if(s[9].equals("体积流量无数据")){
                    s[9]="";
                }else{
                    V=s[9].substring(s[9].indexOf("(") + 1, s[9].indexOf(")"));
                    s[9] = s[9].substring(0, s[9].indexOf("("));
                    if(!isNumeric(s[9])){
                        sb.append("体积流量输入有误；");
                    }
                }
                if(s[10].equals("质量流量无数据")){
                    s[10]="";
                }else{
                    W=s[10].substring(s[10].indexOf("(") + 1, s[10].indexOf(")"));
                    s[10] = s[10].substring(0, s[10].indexOf("("));
                    if(!isNumeric(s[10])){
                        sb.append("质量流量输入有误；");
                    }
                }
                if(s[11].equals("流速无数据")){
                    s[11]="";
                }else{
                    s[11] = s[11].substring(0, s[11].indexOf("("));
                    if(!isNumeric(s[11])){
                        sb.append("流速输入有误；");
                    }
                }
                if(!s[3].equals("")||!s[4].equals("")||!s[10].equals("")){
                    if(!s[9].equals("")){
                        sb.append("输入压力温度质量流量之后请勿输入体积流量；");
                    }
                }
                else if(!s[9].equals("")){
                    if(!s[3].equals("")||!s[4].equals("")||!s[10].equals("")){
                        sb.append("输入体积流量之后请勿输入压力温度质量流量；");
                    }
                }
                if(!s[7].equals("")||!s[6].equals("")){
                    if(!s[8].equals("")){
                        sb.append("输入外径壁厚之后请勿输入内径；");
                    }
                }
                else if(!s[8].equals("")){
                    if(!s[7].equals("")||!s[5].equals("")){
                        sb.append("输入内径之后请勿输入外径壁厚；");
                    }
                }
                map.put("GName",s[1]);
                map.put("Name",s[2]);
                map.put("Mpa",s[3]);
                map.put("P",P);
                map.put("Temperature",s[4]);
                map.put("T",T);
                map.put("Medium",s[5]);
                map.put("Medium1",s[5]);
                map.put("Out",s[6]);
                map.put("Width",s[7]);
                map.put("Volume",s[9]);
                map.put("Weight",s[10]);
                map.put("In",s[8]);
                if(s[9].equals("")){
                    map.put("V","m^3/s");
                }
if(s[10].equals("")){
    map.put("W","kg/s");
}

                map.put("Actual",s[11]);
            }catch (Exception e){
                sb.append(e.toString());

            }finally {
                if(sb!=null && sb.length()>0){
                    i++;
                    map.put("error",sb);
                }
                flow.add(map);
            }
        }
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

        BufferedWriter bw1=new BufferedWriter(new FileWriter(log,true));
        BufferedWriter bw2=new BufferedWriter(new FileWriter(tempFile1));
        if(i==0){
            StringBuilder str=new StringBuilder();
            String data = JSONObject.toJSONString(flow);
            String type="1";
            Object bySelect=flowDao.selectTemporary(userId,type);
            if(org.springframework.util.StringUtils.isEmpty(bySelect)){
                flowDao.insertTemporary(userId,data,type);
            }
            else{
                flowDao.updateTemporary(userId, data,type);
            }
//            fileinput(log, date, dateFormat, bw1, bw2,str);
            str.append(dateFormat.format(date)+"\r\n");
            str.append("导入数据成功"+"\r\n");
            str.append("-----------------------------------------------------------"+"\r\n");
            System.out.println(log.getAbsolutePath());
            System.out.println(log.getPath());
            insert(log.getPath(),0,str.toString());
//            System.out.println("+++++++");
//            write(tempFile1, bw1,str);
            return null;
        }
        else{
            StringBuilder str=new StringBuilder();
            File tempFile = File.createTempFile("tmp", ".txt");
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(tempFile));
            for (int j = 0; j < flow.size(); j++) {
                int k=j+1;
                if(flow.get(j).containsKey("error")){
                    bw.write("第"+k+"行错误原因：");
                    bw.write(flow.get(j).get("error").toString());
                }
                else{
                    bw.write("第"+k+"行格式正确：");
                }
                bw.newLine();
                bw.flush();}
                bw.close();
//            fileinput(log, date, dateFormat, bw1, bw2,str);
            str.append(dateFormat.format(date)+"\r\n");
            str.append("导入数据失败"+"\r\n");
            FileInputStream finput3 = new FileInputStream(tempFile);
            InputStreamReader reader3 = new InputStreamReader(finput3);
            BufferedReader buffReader3 = new BufferedReader(reader3);
            String strTmp3 = "";
            while((strTmp3 = buffReader3.readLine())!=null){
                str.append(strTmp3+"\r\n");
            }
            str.append("-----------------------------------------------------------"+"\r\n");
//            write(tempFile1, bw1,str);
            insert(log.getPath(),0,str.toString());
            FileOutputStream fos = null;
            try {
                String fileName=tempFile.getName();
                System.out.println(filename);
                response.setContentType("text/plain");
                OutputStream os = response.getOutputStream();
                //这里进行设置了一个文件名，其实也可以不要设置了，
                //在前端进行下载的时候需要重新给定一个文件名进行下载
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                response.setHeader("Content-disposition","attachment;filename=="+ URLEncoder.encode(fileName,"UTF-8"));
//            os.write(tempFile.getBytes());
                File file1 = new File(tempFile.toURI());

                byte[] bytes = FileUtils.readFileToByteArray(file1);
                os.write(bytes);
                os.flush();
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return response;
        }



//
//
//
//        InputStream inputStream = file.getInputStream();

    }

    private void fileinput(File log, Date date, SimpleDateFormat dateFormat, BufferedWriter bw1, BufferedWriter bw2,StringBuilder str) throws IOException {
        FileInputStream finput = new FileInputStream(log);
        InputStreamReader reader1 = new InputStreamReader(finput);
        BufferedReader buffReader1 = new BufferedReader(reader1);
        String strTmp1 = "";
        while((strTmp1 = buffReader1.readLine())!=null){
            System.out.println(strTmp1);
         str.append(strTmp1+"\r\n");
        }
        buffReader1.close();
        bw2.flush();
        bw2.close();
        bw1.write(dateFormat.format(date));
        bw1.newLine();
    }
    public  void insert(String fileName, long pos,
                              String insertContent) throws IOException {
        RandomAccessFile raf = null;
        //创建一个临时文件来保存插入点后的数据
        File tmp = File.createTempFile("tmp", null);
        FileOutputStream tmpOut = null;
        FileInputStream tmpIn = null;
        tmp.deleteOnExit();
        try {
            raf = new RandomAccessFile(fileName, "rw");
            tmpOut = new FileOutputStream(tmp);
            tmpIn = new FileInputStream(tmp);
            raf.seek(pos);
            //--------下面代码将插入点后的内容读入临时文件中保存---------
            byte[] bbuf = new byte[64];
            //用于保存实际读取的字节数
            int hasRead = 0;
            //使用循环方式读取插入点后的数据
            while ((hasRead = raf.read(bbuf)) > 0) {
                //将读取的数据写入临时文件
                tmpOut.write(bbuf, 0, hasRead);
            }
            //----------下面代码插入内容----------
            //把文件记录指针重新定位到pos位置
            raf.seek(pos);
            //追加需要插入的内容
            raf.write(insertContent.getBytes());
            //追加临时文件中的内容
            while ((hasRead = tmpIn.read(bbuf)) > 0) {
                raf.write(bbuf, 0, hasRead);
            }
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
    }
    private void write(File tempFile1, BufferedWriter bw1,StringBuilder str) throws IOException {
        str.append("-----------------------------------------------------------"+"\r\n");
//        FileInputStream finput2 = new FileInputStream(tempFile1);
//        InputStreamReader reader2 = new InputStreamReader(finput2);
//        BufferedReader buffReader2 = new BufferedReader(reader2);
//        String strTmp2 = "";
//        while((strTmp2 = buffReader2.readLine())!=null){
//            System.out.println(strTmp2);
//            bw1.write(strTmp2);
//            bw1.newLine();
//        }
//        bw1.flush();
//        bw1.close();
        bw1.write(str.toString());
        bw1.flush();
        bw1.close();
    }

    @Override
    public Result mpa(Map<String, Object> map) {
        String p=map.get("P").toString();
        double mpa=0;
        if(!map.get("Mpa").equals("")){
            mpa=Double.valueOf(map.get("Mpa").toString());
        }
        if(p.equals("MPa")){
            mpa=Double.valueOf(mpa);
        }
        else if(p.equals("kg/cm^2")){
            mpa=Double.valueOf(mpa)* 0.0980665;
        }
        else if(p.equals("bar")){
            mpa=Double.valueOf(mpa)* 0.1;
        }
        else if(p.equals("kPa")){
            mpa=Double.valueOf(mpa)* 0.001;
        }
        else if(p.equals("psi-1b/in^2")){
            mpa=Double.valueOf(mpa)* 0.006894757;
        }
        else if(p.equals("atm")){
            mpa=Double.valueOf(mpa)* 0.1013325;
        }
        map.put("Mpa",mpa);
        return Result.ok(map);
    }


    @Override
    public Result temperature(Map<String, Object> map) {
        String t=map.get("T").toString();
        double temp=0;
        if(!map.get("Temperature").equals("")){
            temp=Double.valueOf(map.get("Temperature").toString());
        }
        if(t.equals("℃")){
            temp=Double.valueOf(temp);
        }
        else if(t.equals("℉")){
            temp=(Double.valueOf(temp)-32)* 5.0 / 9.0;
        }
        else if(t.equals("K 开尔文")){
            temp=Double.valueOf(temp)- 273.15;
        }
        else if(t.equals("R 兰金")){
            temp=(Double.valueOf(temp)-491.67)* 5.0 / 9.0;
        }
        map.put("Temperature",temp);
        return Result.ok(map);
    }
    public boolean isNumeric(String str) {
        String string = new String(str);
        return string.matches("[+-]?(\\d*\\.\\d+|\\d+\\.\\d*|\\d+)([eE][+-]?\\d+)?");
        /*
        以下对正则进行解释:
        [+-]?                 -> 正或负符号出现与否
        (\\d*\\.\\d+|\\d+\\.\\d*|\\d+)    ->要么是小数(小数的整数和小数部分必须有一个有值），
                                            要么是整数，且必须有一项，避免出现""或者"+"
             \\d*             -> 小数的整数部分是否出现，如-.34 或 +3.34或32.e10均符合
             \\.\\d+          -> 如果出现小数点，那么小数点后面必须有数字；否则一起不出现
        ([eE][\\+\\-]?\\d+)?  -> 如果存在指数部分，那么e或E肯定出现，+或-可以不出现，
                                紧接着必须跟着整数；或者整个部分都不出现
        */

    }
}
