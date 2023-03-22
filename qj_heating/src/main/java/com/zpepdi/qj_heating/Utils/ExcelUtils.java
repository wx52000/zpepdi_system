package com.zpepdi.qj_heating.Utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * 处理excel读入的工具类
 * Created by Liujishuai on 2015/8/5.
 */
public class ExcelUtils {
    /**
     * 要求excel版本在2007以上
     *
     * @param file 文件信息
     * @return
     * @throws Exception
     */
    public static void readExcel(String lujing,File file,Map<String,List<String>> newlistdata,Map<String,List<String>> newlistdata2) throws Exception {
        if(!file.exists()){
            throw new Exception("找不到文件");
        }
        List<List<Object>> list = new ArrayList<>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        // 读取第一张表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);
        String sheetName = xwb.getSheetName(0);
        Map<String,XSSFSheet> sheetMap = new LinkedHashMap<>();
        int numberOfSheets = xwb.getNumberOfSheets();
        for(int i=1;i<numberOfSheets;i++){
            sheetMap.put(xwb.getSheetName(i),xwb.getSheetAt(i));
        }
        XSSFRow row = null;
        XSSFCell cell = null;
        System.out.println(sheet.getLastRowNum());
        for (int i = (sheet.getFirstRowNum()); i <= (sheet.getLastRowNum()); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                list.add(new ArrayList<>());
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = 0; j <= row.getLastCellNum(); j++) {
                Object value = null;
                cell = row.getCell(j);
                if (cell == null) {
                    value = "";
                    linked.add(value);
                    continue;
                }
                switch (cell.getCellType()) {
                    case STRING:
                        //String类型返回String数据
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        //日期数据返回LONG类型的时间戳
                        if ("yyyy\"年\"m\"月\"d\"日\";@".equals(cell.getCellStyle().getDataFormatString())) {
                            //System.out.println(cell.getNumericCellValue()+":日期格式："+cell.getCellStyle().getDataFormatString());
//                            value = DateUtils.getMillis(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())) / 1000;
                        } else {
                            if(Math.abs(cell.getNumericCellValue())>0.00001 && Math.abs(cell.getNumericCellValue())<1){
                                cell.setCellType(NUMERIC);
                                value = String.valueOf(cell.getNumericCellValue());
                            }else {
                                cell.setCellType(STRING);
                                value = cell.getStringCellValue();
                            }
                            if(((String)value).indexOf(".") > -1){
                                cell.setCellType(NUMERIC);
                                value = String.valueOf(cell.getNumericCellValue());
                            }
                        }
                        break;
                    case BOOLEAN:
                        //布尔类型
                        value = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        //空单元格
                        value = "";
                        break;
                    default:
                        value = cell.toString();
                }
                    linked.add(value);
            }
                list.add(linked);
        }

        //将word文件插入到excel中
        List<List<Object>> lists = docInsertXlsx(newlistdata,newlistdata2, list);
        //生成excel文件
        String filename = lujing+"\\应力计算结果.xlsx";
        createExcel(filename,lists,sheetName,sheetMap);
    }

    public static List<List<Object>> docInsertXlsx(Map<String,List<String>> docdata,Map<String,List<String>> docdata2,List<List<Object>> xlsxdata){

        for(String key:docdata.keySet()){
            int init = 33;
            for(int i=init;i<xlsxdata.size();i++){
                if(xlsxdata.get(i) == null || xlsxdata.get(i).size()==0){
                    continue;
                }
                String s = (String) xlsxdata.get(i).get(0);
                if(s != null && !s.equals("")){
                    if(key.equals(s.split(" ")[0])){
                        while (true){
                            i++;
                            if(xlsxdata.get(i).get(1)==null || xlsxdata.get(i).get(1)=="" || ((String)xlsxdata.get(i).get(1)).trim().equals("MAX")){
                                break;
                            }
                        }
                        List<Object> newl = new ArrayList<>();
                        newl.add("");
                        newl.add("MAX(Dynamic)");
                        for(String str:docdata.get(key)){
                            newl.add(str);
                        }
                        xlsxdata.add(i+1,newl);
                        //计算静动max
                        List<Object> newl2 = new ArrayList<>();
                        newl2.add("");
                        newl2.add("MAX(S+D)");
                        for(int k=2;k<8;k++){
                            String max = "0";
                            if(xlsxdata.get(i).get(k)==null||xlsxdata.get(i).get(k).equals("")){
                                max = xlsxdata.get(i + 1).get(k).toString();
                                newl2.add(max);
                                continue;
                            }
                            String s1 = xlsxdata.get(i).get(k).toString().split("/")[0].trim();
                            String s2 = xlsxdata.get(i + 1).get(k).toString();
                            max = Math.abs(Integer.parseInt(s1))>Integer.parseInt(s2)?String.valueOf(Math.abs(Integer.parseInt(s1))):s2;
                            newl2.add(max);
                        }
                        xlsxdata.add(i+2,newl2);
                    }
                }
            }
        }

        for(String key:docdata2.keySet()){
            int init = 33;
            for(int i=init;i<xlsxdata.size();i++){
                if(xlsxdata.get(i) == null || xlsxdata.get(i).size()==0){
                    continue;
                }
                String s = (String) xlsxdata.get(i).get(0);
                if(s != null && !s.equals("")){
                    if(key.equals(s.split(" ")[0])){
                        while (true){
                            i++;
                            if(xlsxdata.get(i).get(1)==null || xlsxdata.get(i).get(1)=="" || ((String)xlsxdata.get(i).get(1)).trim().equals("MAX")){
                                break;
                            }
                        }
                        for(String s2:docdata2.get(key)){
                            xlsxdata.get(i+1).add(s2);
                        }
                        //计算静动max
                        for(int k=8;k<11;k++){
                            String max = "0";
                            if(xlsxdata.get(i).get(k)==null||xlsxdata.get(i).get(k).equals("")){
                                max = xlsxdata.get(i + 1).get(k).toString();
                                xlsxdata.get(i+2).add(max);
                                continue;
                            }
                            String s1 = xlsxdata.get(i).get(k).toString().split("/")[0].trim();
                            String s2 = xlsxdata.get(i + 1).get(k).toString();
                            max = Math.abs(Double.parseDouble(s1))>Double.parseDouble(s2)?String.valueOf(Math.abs(Double.parseDouble(s1))):s2;
                            xlsxdata.get(i+2).add(max);
                        }
                    }
                }
            }
        }
        return xlsxdata;
    }

    /**
     * 要求excel版本在2007以上
     *
     * @param fileInputStream 文件信息
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(FileInputStream fileInputStream) throws Exception {
        List<List<Object>> list = new LinkedList<List<Object>>();
        XSSFWorkbook xwb = new XSSFWorkbook(fileInputStream);
        // 读取第一张表格内容
        XSSFSheet sheet = xwb.getSheetAt(1);
        XSSFRow row = null;
        XSSFCell cell = null;
        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                Object value = null;
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                switch (cell.getCellType()) {
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        if ("yyyy\"年\"m\"月\"d\"日\";@".equals(cell.getCellStyle().getDataFormatString())) {
                            //System.out.println(cell.getNumericCellValue()+":日期格式："+cell.getCellStyle().getDataFormatString());
//                            value = DateUtils.getMillis(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())) / 1000;
                        } else {
                            //System.out.println(cell.getNumericCellValue()+":格式："+cell.getCellStyle().getDataFormatString());
                            value = cell.getNumericCellValue();
                        }
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        break;
                    default:
                        value = cell.toString();
                }
                if (value != null && !value.equals("")) {
                    //单元格不为空，则加入列表
                    linked.add(value);
                }
            }
            if (linked.size()!= 0) {
                list.add(linked);
            }
        }
        return list;
    }

    /**
     * 导出excel
     * @param excel_name 导出的excel路径（需要带.xlsx)
     * @param headList  excel的标题备注名称
     * @param fieldList excel的标题字段（与数据中map中键值对应）
     * @param dataList  excel数据
     * @throws Exception
     */
    public static void createExcel(String excel_name, String[] headList,
                                   String[] fieldList, List<Map<String, Object>> dataList)
            throws Exception {
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值
        XSSFSheet sheet = workbook.createSheet();
        // 在索引0的位置创建行（最顶端的行）
        XSSFRow row = sheet.createRow(0);
        // 设置excel头（第一行）的头名称
        for (int i = 0; i < headList.length; i++) {

            // 在索引0的位置创建单元格（左上端）
            XSSFCell cell = row.createCell(i);
            // 定义单元格为字符串类型
            cell.setCellType(STRING);
            // 在单元格中输入一些内容
            cell.setCellValue(headList[i]);
        }
        // ===============================================================
        //添加数据
        for (int n = 0; n < dataList.size(); n++) {
            // 在索引1的位置创建行（最顶端的行）
            XSSFRow row_value = sheet.createRow(n + 1);
            Map<String, Object> dataMap = dataList.get(n);
            // ===============================================================
            for (int i = 0; i < fieldList.length; i++) {

                // 在索引0的位置创建单元格（左上端）
                XSSFCell cell = row_value.createCell(i);
                // 定义单元格为字符串类型
                cell.setCellType(STRING);
                // 在单元格中输入一些内容
                cell.setCellValue((dataMap.get(fieldList[i])).toString());
            }
            // ===============================================================
        }
        // 新建一输出文件流
        FileOutputStream fos = new FileOutputStream(excel_name);
        // 把相应的Excel 工作簿存盘
        workbook.write(fos);
        fos.flush();
        // 操作结束，关闭文件
        fos.close();
    }

    /**
     * 导出excel
     * @param excel_name 导出的excel路径（需要带.xlsx)
     * @param dataList  excel数据
     * @throws Exception
     */
    public static void createExcel(String excel_name, List<List<Object>> dataList,String sheetName,Map<String,XSSFSheet> sheetMap)
            throws Exception {
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名sheetName
        XSSFSheet sheet = workbook.createSheet(sheetName);
// 设置字体
        CellStyle redStyle = workbook.createCellStyle();
        XSSFFont redFont = workbook.createFont();
//字体
        redFont.setFontName("宋体");
        redStyle.setFont(redFont);
        for(String key:sheetMap.keySet()){
            XSSFSheet sheet2 = workbook.createSheet(key);
            for (int i = (sheetMap.get(key).getFirstRowNum()); i <= (sheetMap.get(key).getLastRowNum()); i++) {
                XSSFRow row = sheet2.createRow(i);
//                row = sheetMap.get(key).getRow(i);
                if(sheetMap.get(key).getRow(i)==null||sheetMap.get(key).getRow(i).equals("")){
                    row = null;
                    continue;
                }
                for (int j = 0; j <= sheetMap.get(key).getRow(i).getLastCellNum(); j++) {
                    XSSFCell cell = row.createCell(j);
                    if(sheetMap.get(key).getRow(i).getCell(j)==null||sheetMap.get(key).getRow(i).getCell(j).equals("")){
                        cell.setCellValue("");
                        break;
                    }
                    cell.setCellValue(sheetMap.get(key).getRow(i).getCell(j).toString());
                    cell.setCellStyle(redStyle);
                }
            }
        }



        // ===============================================================
        //添加数据
        for (int n = 0; n < dataList.size(); n++) {
            // 在索引0的位置创建行（最顶端的行）
            XSSFRow row_value = sheet.createRow(n);
            List<Object> dataRow = dataList.get(n);
            // ===============================================================
            for (int i = 0; i < dataRow.size(); i++) {

                // 在索引0的位置创建单元格（左上端）
                XSSFCell cell = row_value.createCell(i);
                    try {
                        cell.setCellType(NUMERIC);
                        cell.setCellValue(Integer.parseInt(dataRow.get(i).toString()));
                    }catch (NumberFormatException e){
                        try {
                            cell.setCellType(NUMERIC);
                            cell.setCellValue(Double.parseDouble(dataRow.get(i).toString()));
                        }catch(Exception e2){
                            // 定义单元格为字符串类型
                            cell.setCellType(STRING);
                            // 在单元格中输入一些内容
                            cell.setCellValue(dataRow.get(i).toString());
                        }


                    }


                cell.setCellStyle(redStyle);
            }
        }
        // 新建一输出文件流
        FileOutputStream fos = new FileOutputStream(excel_name);
        // 把相应的Excel 工作簿存盘
        workbook.write(fos);
        fos.flush();
        // 操作结束，关闭文件
        fos.close();
        System.out.println("excel文件生成成功");
    }
}