package com.zpepdi.qj_airhammer.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zpepdi.qj_airhammer.Listener.ExcelListener;
import com.zpepdi.qj_airhammer.entity.AirHammer;
import com.zpepdi.qj_airhammer.entity.Excel;
import com.zpepdi.qj_airhammer.service.AirHammerService;
import jxl.write.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;

@Service
public class AirHammerServiceImpl implements AirHammerService {


    @Override
    public HttpServletResponse compute(HttpServletResponse response, AirHammer airHammer, MultipartFile file) throws IOException, WriteException {
        BigDecimal num1 = new BigDecimal("1.05");
        BigDecimal num2 = new BigDecimal("1000");
        BigDecimal num3 = new BigDecimal("3600");
        InputStream inputStream = file.getInputStream();
        ExcelListener analysisEventListener = new ExcelListener();
        EasyExcel.read(inputStream,
                Excel.class,
                analysisEventListener).sheet().headRowNumber(0).doRead();
        List<Excel> list = analysisEventListener.getLists();
        BigDecimal big2 = (num2.multiply(num3)).multiply(airHammer.getTime());
//        BigDecimal big3=big1.divide(big2,3,ROUND_HALF_UP);
//        String a =big3.setScale(0, ROUND_HALF_UP).toString();
        int b = 0;
        String str1 = "管道号";
        for (int i =1; i < list.size(); i++) {
                    BigDecimal L = new BigDecimal(list.get(i).getLength());
                    BigDecimal big1 = (airHammer.getFlow().multiply(L)).multiply(num1);
                    BigDecimal big3 = big1.divide(big2, 3, ROUND_HALF_UP);
                    String a = big3.setScale(0, ROUND_HALF_UP).toString();
                    b = Integer.parseInt(a) * 2;
                    list.get(i).setF(String.valueOf(big3));
                    list.get(i).setFz(a);
                    list.get(i).setTf(String.valueOf(b));
        }
        String fileName =  "气锤计算" + System.currentTimeMillis() + ".xls";
        HSSFWorkbook workbook =  new HSSFWorkbook();
        HSSFRow row =null;
        //创建一个表，是指一个xsl文件可以创建三个表其中的一个表的表名，设置为user info
        HSSFSheet sheet = workbook.createSheet("计算数据");
        for (int i =0 ;i<list.size();i++){
            row = sheet.createRow(i); //重新找到这一行数据的行数，也就是在前端的基础上再另起一行
            Excel excel =list.get(i); //得到集合遍历的每一行数据
            row.createCell(0).setCellValue(excel.getNumber()); //将值给到单元格
            row.createCell(1).setCellValue(excel.getPoint()); //unitVO是一个实体类对象，获取get方法进行赋值
            row.createCell(2).setCellValue(excel.getLength());
            row.createCell(3).setCellValue(excel.getDirection());
            row.createCell(4).setCellValue(excel.getF());
            row.createCell(5).setCellValue(excel.getFz());
            row.createCell(6).setCellValue(excel.getTf());
        }
        response.setContentType("application/vnd.ms-excel;charset:utf-8");
        OutputStream os = response.getOutputStream();
        //这里进行设置了一个文件名，其实也可以不要设置了，
        //在前端进行下载的时候需要重新给定一个文件名进行下载
        response.setHeader("Content-disposition","attachment;filename=="+URLEncoder.encode(fileName,"UTF-8"));
        workbook.write(os);
        os.flush();
        os.close();

        return response;
        }

        public static  BigDecimal BR(BigDecimal Mpa,BigDecimal Temperature){
        BigDecimal a=new BigDecimal("1.0");
        return a;
        }
}




//    public static void main(String[] args) throws IOException {
//        AirHammerServiceImpl air =new AirHammerServiceImpl();
//        AirHammer airHammer=new AirHammer();
//        airHammer.setFmax(BigDecimal.valueOf(169.7060793));
//        airHammer.setMpa(BigDecimal.valueOf(6.705));
//        airHammer.setTemperature(BigDecimal.valueOf(628));
//        airHammer.setFlow(BigDecimal.valueOf(812375));
//        airHammer.setTime(BigDecimal.valueOf(0.15));
//        airHammer.setV(BigDecimal.valueOf(0.06024282));
//        airHammer.setC(BigDecimal.valueOf(716.2322928));
//        airHammer.setLcr(BigDecimal.valueOf(107.4348439));
//        String filename="C:\\Users\\ZPEPDI-3D\\Desktop\\气锤计算.xls";
//        BigDecimal num1=new BigDecimal("1.05");
//        BigDecimal num2=new BigDecimal("1000");
//        BigDecimal num3=new BigDecimal("3600");
////        InputStream inputStream = file.getInputStream();
//        AirHammerServiceImpl airHammerServiceImpl=new AirHammerServiceImpl();
//        ExcelListener analysisEventListener=new ExcelListener();
//        EasyExcel.read(filename, Excel.class, analysisEventListener).sheet().headRowNumber(1).doRead();
//
//        List<Excel> list=analysisEventListener.getLists();
////        BigDecimal f=new BigDecimal("");
////        BigDecimal length=new BigDecimal("");
////        BigDecimal fz=new BigDecimal("");
////        BigDecimal tf=new BigDecimal("");
////        BigDecimal big1=new BigDecimal("");
////        BigDecimal big1=(airHammer.getFlow().multiply(length))).multiply(num1);
//        BigDecimal big2=(num2.multiply(num3)).multiply(airHammer.getTime());
////        BigDecimal big3=big1.divide(big2,3,ROUND_HALF_UP);
////        String a =big3.setScale(0, ROUND_HALF_UP).toString();
//        int b=0;
//        String str1="长度L";
//        String str2="左支";
//        for(int i=1;i<list.size();i++){
//            if(str1.equals(list.get(i).getLength())){
//                i=i+1;
//            }
//            else {
//                if(str2.equals(list.get(i).getNumber())){
//                    i=i+1;
//                }
//                else{
//                    BigDecimal L=new BigDecimal(list.get(i).getLength());
//                    BigDecimal big1=(airHammer.getFlow().multiply(L)).multiply(num1);
//                    BigDecimal big3=big1.divide(big2,3,ROUND_HALF_UP);
//                    String a =big3.setScale(0, ROUND_HALF_UP).toString();
//                    b=Integer.parseInt(a)*2;
//                    list.get(i).setF(String.valueOf(big3));
//                    list.get(i).setFz(a);
//                    list.get(i).setTf(String.valueOf(b));
//                }
//
//            }
//
//        }
//        String fileName =  "C:\\Users\\ZPEPDI-3D\\Desktop\\simpleWrite" + System.currentTimeMillis() + ".xls";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, Excel.class).sheet("模板").doWrite(list);
//
//
//
//
//    }




