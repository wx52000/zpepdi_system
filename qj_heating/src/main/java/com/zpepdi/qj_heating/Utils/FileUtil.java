package com.zpepdi.qj_heating.Utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 获取正文文件内容，doc方法
     *
     * @param path
     * @return
     */
    public static Map<String, String> getContentDoc(String path) {
        Map<String, String> map = new HashMap();
        StringBuffer content = new StringBuffer("");
        String result = "0";  // 0表示获取正常，1表示获取异常
        InputStream is = null;
        Logger logger = null;
        try {
            is = new FileInputStream(new File(path));
            // 2003版本的word
            WordExtractor extractor = new WordExtractor(is);  // 2003版本 仅doc格式文件可处理，docx文件不可处理
            String[] paragraphText = extractor.getParagraphText();   // 获取段落，段落缩进无法获取，可以在前添加空格填充
            if (paragraphText != null && paragraphText.length > 0) {
                for (String paragraph : paragraphText) {
                    if (!paragraph.startsWith("    ")) {
                        content.append(paragraph.trim()).append("\r\n");
                    } else {
                        content.append(paragraph);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("doc解析正文异常:" + e);
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
            map.put("content", content.toString());
        }
        return map;
    }

    /**
     * 获取正文文件内容，wps方法
     *
     * @param path
     * @return
     */
    public static Map<String, String> getContentWps(String path) {
        Map<String, String> map = new HashMap();
        StringBuffer content = new StringBuffer("");
        String result = "0";  // 0表示获取正常，1表示获取异常
        InputStream is = null;
        Logger logger = null;
        try {
            is = new FileInputStream(new File(path));
            // wps版本word
            HWPFDocument hwpf = new HWPFDocument(is);
            WordExtractor wordExtractor = new WordExtractor(hwpf);
            // 文档文本内容
            String[] paragraphText1 = wordExtractor.getParagraphText();
            if (paragraphText1 != null && paragraphText1.length > 0) {
                for (String paragraph : paragraphText1) {
                    if (!paragraph.startsWith("    ")) {
                        content.append(paragraph.trim()).append("\r\n");
                    } else {
                        content.append(paragraph);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("wps解析正文异常:" + e);
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
            map.put("content", content.toString());
        }
        return map;
    }

    /**
     * 创建动态荷载max文件
     * @param lujing
     * @param newsumdata
     * @throws IOException
     */
    public static void createFile(String lujing,Map<String,List<String>> newsumdata) throws IOException {
        //创建文本对象
        XWPFDocument docxDocument = new XWPFDocument();
        //创建第一段落
        XWPFParagraph paragraphX = docxDocument.createParagraph();
        paragraphX.setAlignment(ParagraphAlignment.LEFT);//对齐方式
        //创建段落中的run
        XWPFRun run = paragraphX.createRun();
        run.setText("fliepath:  "+lujing);
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
    public static void createFile2(String lujing,Map<String,List<String>> newsumdata) throws IOException {
        //创建文本对象
        XWPFDocument docxDocument = new XWPFDocument();
        //创建第一段落
        XWPFParagraph paragraphX = docxDocument.createParagraph();
        paragraphX.setAlignment(ParagraphAlignment.LEFT);//对齐方式
        //创建段落中的run
        XWPFRun run = paragraphX.createRun();
        run.setText("fliepath:  "+lujing);
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
     * 创建默认页眉
     *
     * @param docx XWPFDocument文档对象
     * @param text 页眉文本
     * @return 返回文档帮助类对象，可用于方法链调用
     * @throws XmlException XML异常
     * @throws IOException IO异常
     * @throws InvalidFormatException 非法格式异常
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
