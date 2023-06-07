package com.zpepdi.qj_heating.Utils;

import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils1 {
    public static boolean wordTopdfByAspose(InputStream inputStream, OutputStream outputStream) {

        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return false;
        }
        try {
            // 将源文件保存在com.aspose.words.Document中，具体的转换格式依靠里面的save方法
            com.aspose.words.Document doc = new com.aspose.words.Document(inputStream);

            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,EPUB, XPS, SWF 相互转换
            doc.save(outputStream, SaveFormat.PDF);

            System.out.println("word转换完毕");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }



    public static boolean pdfTowordByAspose(String pdfpath, String wordpath) {

        // 验证License 若不验证则转化出的pdf文档会有水印产生
        if (!getLicense()) {
            return false;
        }
        try {
            // 将源文件保存在com.aspose.words.Document中，具体的转换格式依靠里面的save方法
            com.aspose.words.Document doc = new com.aspose.words.Document(pdfpath);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,EPUB, XPS, SWF 相互转换
            doc.save(wordpath, SaveFormat.DOCX);

            System.out.println("word转换完毕");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    // 官方文档的要求 无需理会
    public static boolean getLicense() {
        boolean result = false;
        try {
            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
