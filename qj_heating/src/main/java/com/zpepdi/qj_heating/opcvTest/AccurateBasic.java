package com.zpepdi.qj_heating.opcvTest;

import com.zpepdi.qj_heating.opcvTest.utils.Base64Util;
import com.zpepdi.qj_heating.opcvTest.utils.FileUtil;
import com.zpepdi.qj_heating.opcvTest.utils.GsonUtils;
import com.zpepdi.qj_heating.opcvTest.utils.HttpUtil;

import java.net.URLEncoder;
import java.util.List;

/**
 * 通用文字识别（高精度版）
 */
public class AccurateBasic {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String accurateBasic() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
        try {
            // 本地文件路径
            String filePath = "G:\\pdf\\pdfTOimg\\Image_39.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.5dbaa60c6301a019a7b6af09d49721f6.2592000.1680331911.282335-29505861";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String jsonString = AccurateBasic.accurateBasic();

        JsonTxtBean jsonTxtBean = GsonUtils.fromJson(jsonString, JsonTxtBean.class);
        List<JsonTxtBean.B> words_result = jsonTxtBean.getWords_result();
        for(JsonTxtBean.B word : words_result){
            System.out.println(word.getWords());
        }



    }
}