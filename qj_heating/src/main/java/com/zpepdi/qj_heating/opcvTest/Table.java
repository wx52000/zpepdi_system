package com.zpepdi.qj_heating.opcvTest;




import com.zpepdi.qj_heating.entity.TableList;
import com.zpepdi.qj_heating.opcvTest.utils.Base64Util;
import com.zpepdi.qj_heating.opcvTest.utils.FileUtil;
import com.zpepdi.qj_heating.opcvTest.utils.GsonUtils;
import com.zpepdi.qj_heating.opcvTest.utils.HttpUtil;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 表格文字识别
 */
public class Table {

    public static String table(String name) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/table";
        try {
            // 本地文件路径
//            String filePath = "C:\\Users\\Administrator\\Desktop\\tt.png";
//            String filePath = "C:\\Users\\Administrator\\Desktop\\ttt.png";
            String filePath = "G:\\pdf\\pdfTOimg\\"+name;
//            String filePath = "C:\\Users\\Administrator\\Desktop\\test2.png";
//            String filePath = "C:\\Users\\Administrator\\Desktop\\Image_22.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.5dbaa60c6301a019a7b6af09d49721f6.2592000.1680331911.282335-29505861";

            String result = HttpUtil.post(url, accessToken, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String result = Table.table("image.png");
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
            for(JsonBean.B.body bo: body){
                //如果不是同一行
                if(!row_start.equals(bo.getRow_start())){
                    bt1 = 2;
                    row_start = bo.getRow_start();
                    System.out.println();
                }
                //内容不为空
                if(!bo.getWords().isEmpty()){
                    if(bt1 == 1){
                        arr.add(bo.getWords().replace("\n", ""));
//                        tableList.getBtList().add(bo.getWords().replace("\n", ""));
                    }
//                    System.out.print(bo.getWords()+"\t");
                    System.out.print(bo.getWords().replace("\n", "")+"\t");
                }else {
                    System.out.print("\t");
                }
            }
            if(!arr.isEmpty()){
                tableList.setBtList(arr);
            }
        }


        System.out.println("表格如下");
        System.out.println(tableList);


    }
}