package com.zpepdi.qj_heating.Utils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.Pictures;
import com.zpepdi.qj_heating.entity.Userpiping;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.slf4j.Logger;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class FileUtil {

    /**
     * 获取正文文件内容，docx方法
     *
     * @param file
     * @return
     */
    public static Map<String, String> getContentDocx(File file) {
        Map<String, String> map = new HashMap();
        StringBuffer content = new StringBuffer("");
        String result = "0";  // 0表示获取正常，1表示获取异常
        InputStream is = null;
        Logger logger = null;
        try {
            //根据需求入参也可以改为文件路径，对应的输入流部分改为new File(路径)即可
            is = new FileInputStream(file);
            // 2007版本的word
            XWPFDocument xwpf = new XWPFDocument(is);    // 2007版本，仅支持docx文件处理
            List<XWPFParagraph> paragraphs = xwpf.getParagraphs();
            List<XWPFHeader> pageHeaders = xwpf.getHeaderList();
            if (paragraphs != null && paragraphs.size() > 0) {
                for (XWPFParagraph paragraph : paragraphs) {
                    if (!paragraph.getParagraphText().startsWith("    ")) {
                        content.append(paragraph.getParagraphText().trim()).append("\r\n");
                    } else {
                        content.append(paragraph.getParagraphText());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("docx解析正文异常:" + e);
            result = "1"; // 出现异常
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("" + e);
                }
            }
            map.put("result", result);
            map.put("content", String.valueOf(content));
        }
        return map;
    }


    /**
     * 将MultipartFile转换为File
     *
     *
     * @param outFilePath 参数
     * @param multiFile 参数
     * @return 执行结果
     */
    public static File multipartFileToFile(String outFilePath, MultipartFile multiFile) {
        // 获取文件名
        if (null == multiFile) {
            return null;
        }
        String fileName = multiFile.getOriginalFilename();
        if (null == fileName) {
            return null;
        }
        try {
            File toFile;
            InputStream ins;
            ins = multiFile.getInputStream();
            //指定存储路径
            toFile = new File(outFilePath.concat(File.separator).concat(multiFile.getOriginalFilename()));
            inputStreamToFile(ins, toFile);
            return toFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void inputStreamToFile(InputStream ins, File file) {
        try (OutputStream os = new FileOutputStream(file)) {
            int bytesRead;
            int bytes = 8192;
            byte[] buffer = new byte[bytes];
            while ((bytesRead = ins.read(buffer, 0, bytes)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MultipartFile转换为InputStream
    public static InputStream multipartToInputStream(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = null;
        File file = null;
        try {
            // 创建临时文件
            file = File.createTempFile("temp", null);
            // 把multipartFile写入临时文件
            multipartFile.transferTo(file);
            // 使用文件创建 inputStream 流
            inputStream = new FileInputStream(file);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 最后记得删除文件
            file.deleteOnExit();
            // 关闭流
            inputStream.close();
        }
        return inputStream;
    }

    /**
     * 创建动态荷载max文件
     * @param lujing
     * @param newsumdata
     * @throws IOException
     */
    public static void createFile(String Directoryname,String lujing,Map<String,List<String>> newsumdata) throws IOException {
        //创建文本对象
        XWPFDocument docxDocument = new XWPFDocument();
        //创建第一段落
        XWPFParagraph paragraphX = docxDocument.createParagraph();
        paragraphX.setAlignment(ParagraphAlignment.LEFT);//对齐方式
        //创建段落中的run
        XWPFRun run = paragraphX.createRun();
        run.setText("Directoryname:  "+Directoryname);
        run.addCarriageReturn();//回车键
        run.addCarriageReturn();//回车键
        for(String key:newsumdata.keySet()){
            run.setText(key+"\t");
            for(String s:newsumdata.get(key)){
                run.setText(s+"\t");
            }
            run.addCarriageReturn();//回车键
        }

        String test = "         ------Forces( N.)-------     -----Moments( N.m. )-----\r\n" +
                "                                                         NODE      FX       FY       FZ         MX       MY       MZ";
        createDefaultHeader(docxDocument,test);
        String path=lujing+"\\动态max荷载.docx";
        File file = new File(path);
        FileOutputStream stream = new FileOutputStream(file);
        docxDocument.write(stream);
        stream.close();
        System.out.println("荷载文件生成完成!");
    }

    /**
     * 创建动态位移max文件
     * @param lujing
     * @param newsumdata
     * @throws IOException
     */
    public static void createFile2(String Directoryname,String lujing,Map<String,List<String>> newsumdata) throws IOException {
        //创建文本对象
        XWPFDocument docxDocument = new XWPFDocument();
        //创建第一段落
        XWPFParagraph paragraphX = docxDocument.createParagraph();
        paragraphX.setAlignment(ParagraphAlignment.LEFT);//对齐方式
        //创建段落中的run
        XWPFRun run = paragraphX.createRun();
        run.setText("Directoryname:  "+Directoryname);
        run.addCarriageReturn();//回车键
        run.addCarriageReturn();//回车键
        for(String key:newsumdata.keySet()){
            run.setText(key+"\t\t");
            for(String s:newsumdata.get(key)){
                run.setText(s+"\t\t");
            }
            run.addCarriageReturn();//回车键
        }


        String path=lujing+"\\动态max位移.docx";
        File file = new File(path);
        FileOutputStream stream = new FileOutputStream(file);
        docxDocument.write(stream);
        stream.close();
        System.out.println("位移文件生成完成!");
    }

    /**
     * 创建计算书文件(已经弃用)
     * @param pipingList
     * @throws IOException
     */
    public static void createjisuanFile(List<Userpiping> pipingList) throws Exception {

        //得到项目目录
        ApplicationHome applicationHome = new ApplicationHome(new FileUtil().getClass());
        String absolutePath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        //创建文本对象
        XWPFDocument docxDocument = new XWPFDocument();
        //创建第一段落
        XWPFParagraph paragraphX2 = docxDocument.createParagraph();
        paragraphX2.setAlignment(ParagraphAlignment.CENTER);//对齐方式
        //第一行工程名称
        XWPFRun run2 = paragraphX2.createRun();
        run2.setText("工程名称:  "+"");
        run2.addCarriageReturn();//回车键
        for(Userpiping gd:pipingList){
            //创建第一段落
            XWPFParagraph paragraphX = docxDocument.createParagraph();
            paragraphX.setAlignment(ParagraphAlignment.LEFT);//对齐方式
            //创建段落中的run
            XWPFRun run = paragraphX.createRun();
            run.setText("管道名称:  ");
            XWPFRun run3 = paragraphX.createRun();
            run3.setText(gd.getGdname());
            run3.setBold(true);//加粗
            run3.setUnderline(UnderlinePatterns.SINGLE);
            run3.addBreak();

            XWPFRun run4 = paragraphX.createRun();
            String wai;
            if("true".equals(gd.getDefstr1())){
                wai = "按外径计算";
            }else {
                wai = "按内径计算";
            }
            run4.setText("计算方法:  "+wai);
            run4.addBreak();

            XWPFRun run5 = paragraphX.createRun();
            run5.setText("钢管类型:  "+gd.getValue1());
            run5.addBreak();

            XWPFRun run6 = paragraphX.createRun();
            run6.setText("管道材料:  "+gd.getValue2());
            run6.addBreak();

            XWPFRun run7 = paragraphX.createRun();
            run7.setText("  标准号:  "+gd.getBzhao());
            run7.addBreak();

            XWPFRun run8 = paragraphX.createRun();
            run8.setText("    介质:  "+gd.getValue3());
            run8.addBreak();


            //创建一个表格
            XWPFTable table = docxDocument.createTable(10, 6);
            CTTblPr tblPr = table.getCTTbl().getTblPr();
            CTString tblStyle = tblPr.addNewTblStyle();
            tblStyle.setVal("StyleTable");
            List<XWPFTableRow> rows = table.getRows();
            for(XWPFTableRow row:rows){
                CTTrPr ctTrPr = row.getCtRow().addNewTrPr();
                CTHeight ctHeight = ctTrPr.addNewTrHeight();
                ctHeight.setVal(BigInteger.valueOf(80));

                List<XWPFTableCell> Cells = row.getTableCells();
                for(XWPFTableCell cell:Cells){
                    CTTcPr ctTcPr = cell.getCTTc().addNewTcPr();
                    CTVerticalJc ctVerticalJc = ctTcPr.addNewVAlign();
                    ctVerticalJc.setVal(STVerticalJc.CENTER);
//                    cell.getCTTc().getTcPr().addNewTcBorders().addNewLeft().setVal(STBorder.NIL);//设置无边框
//                    cell.getCTTc().getTcPr().addNewTcBorders().addNewRight().setVal(STBorder.NIL);//设置无边框
//                    cell.getCTTc().getTcPr().addNewTcBorders().addNewBottom().setVal(STBorder.NIL);//设置无边框
//                    cell.getCTTc().getTcPr().addNewTcBorders().addNewTop().setVal(STBorder.NIL);//设置无边框

                    XWPFParagraph xwpfParagraph = cell.getParagraphs().get(0);
                    XWPFRun run1 = xwpfParagraph.createRun();
                    run1.setFontSize(10);
                    run1.setFontFamily("宋体");
                    run1.setText("测试数据");
                    xwpfParagraph.setAlignment(ParagraphAlignment.CENTER);
                }
            }






//            XWPFRun imagerun = paragraphX.createRun();
//            //管径计算公式
//            String imgpath=absolutePath+"\\src\\main\\resources\\img\\njwai.png";
//            imagerun.addBreak();
//            FileInputStream fileInputStream = new FileInputStream(imgpath);
//            imagerun.addPicture(fileInputStream,Document.PICTURE_TYPE_PNG,imgpath, Units.toEMU(120),Units.toEMU(40));
//            imagerun.addBreak(BreakType.PAGE);
        }

        String path=absolutePath+"\\src\\main\\resources";
        File file = new File(path,"管径计算书.docx");
        FileOutputStream stream = new FileOutputStream(file);
        docxDocument.write(stream);
        stream.close();
        System.out.println("管径计算书生成完成!");
    }

    /**
     * 创建计算书文件2
     * @param pipingList
     * @throws IOException
     */
    public static void createjisuanFile2(List<Userpiping> pipingList) throws IOException {
        //得到项目目录
        ApplicationHome applicationHome = new ApplicationHome(new FileUtil().getClass());
        String absolutePath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath();
        //模板地址
        String templateFilePath=absolutePath+"\\src\\main\\resources\\";
        //生成文件的保存地址
        String destFilePath = absolutePath+"\\src\\main\\resources\\";
        Map<String,Object> map = new HashMap<>();
        List<Map<String,Object>> maps = new ArrayList<>();

        for(Userpiping gd:pipingList){
            Map<String,Object> map1 = new HashMap<>();
            map1.put("gdsortid",gd.getGdsortid());
            map1.put("gdname",gd.getGdname());
            map1.put("ggtype",gd.getValue1());
            map1.put("cailiao",gd.getValue2());
            map1.put("jiezhi",gd.getValue3());
            map1.put("bzhao",gd.getBzhao());
            map1.put("ssliusu",gd.getRecommend2());
            map1.put("tjliusu",gd.getRecommend());
            map1.put("liuliang",gd.getPipingG());
            map1.put("tjliuliang",gd.getPipingQ());
            map1.put("yali",gd.getYali());
            map1.put("wendu",gd.getWendu());
            map1.put("birong",gd.getBirong());
            //图片
            map1.put("image", Pictures.ofLocal(templateFilePath+"img\\njwai.png").size(150,50).create());
            map1.put("di",gd.getPipingDi());
            if("true".equals(gd.getDefstr1())){
                map1.put("worn","外");
                map1.put("wornzhi",gd.getPipingDo());
                map1.put("bhimage",Pictures.ofLocal(templateFilePath+"img\\waibh.png").size(200,50).create());
                map1.put("norw","内");
                map1.put("endnorw",gd.getEndnj());
            }else {
                map1.put("worn","内");
                map1.put("wornzhi",gd.getDhneijing());
                map1.put("bhimage",Pictures.ofLocal(templateFilePath+"img\\neibh.png").size(200,50).create());
                map1.put("norw","外");
                map1.put("endnorw",gd.getPipingDo());
            }
            map1.put("sjyali",gd.getSjyali());
            map1.put("sjwendu",gd.getSjwendu());
            map1.put("yingli",gd.getYingli());
            if("false".equals(gd.getIsyingliinput())){
                map1.put("isyingliinput","此许用应力为手工输入值");
            }
            map1.put("y",gd.getPipingY());
            map1.put("yingliy",gd.getPipingyingliY());
            map1.put("fujiahd",gd.getPipingC());
            map1.put("sm",gd.getPipingSm());
            Double m = Double.valueOf(gd.getPipingC1());
            map1.put("a",String.format("%.3f",(m*100)/(100-(m*100))));
            map1.put("c",gd.getEndC1());
            if("wan".equals(gd.getRadio2())){
                map1.put("wguan",gd.getDefstr8()+"倍弯曲半径");
            }
            map1.put("sc",gd.getPipingSc());
            map1.put("sn",gd.getDhbihou());
            map1.put("endls",gd.getEndls());

            maps.add(map1);

        }


        map.put("tests",maps);
        map.put("gcname","工程名字暂未确定");
        map.put("index","1");
        map.put("bc","0");
        map.put("pizhun","pizhun");
        map.put("shenhe","shenhe");
        map.put("jiaoyan","jiaoyan");
        map.put("bianxie","bianxie");

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        map.put("year",year);
        map.put("month",month);
        map.put("gongshiimg",Pictures.ofLocal(templateFilePath+"img\\gongshi.png").create());

        ConfigureBuilder builder = Configure.builder();
        XWPFTemplate compile = XWPFTemplate.compile(templateFilePath + "模板.docx",builder.build());
        compile.render(map);
        compile.writeToFile(destFilePath+"管道规格计算书.docx");
    }
    /**
     * 创建默认页眉
     *
     * @param docx XWPFDocument文档对象
     * @param text 页眉文本
     * @return 返回文档帮助类对象，可用于方法链调用
     * @throws XmlException XML异常
     * @throws IOException IO异常
     * @throws FileNotFoundException 找不到文件异常
     */
    public static void createDefaultHeader(final XWPFDocument docx, final String text){
        CTP ctp = CTP.Factory.newInstance();
        XWPFParagraph paragraph = new XWPFParagraph(ctp, docx);
        ctp.addNewR().addNewT().setStringValue(text);
        ctp.addNewR().addNewT().setSpace(SpaceAttribute.Space.PRESERVE);
        CTSectPr sectPr = docx.getDocument().getBody().isSetSectPr() ? docx.getDocument().getBody().getSectPr() : docx.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
        XWPFHeader header = policy.createHeader(STHdrFtr.DEFAULT, new XWPFParagraph[] { paragraph });
        header.setXWPFDocument(docx);
    }

}
