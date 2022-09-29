package com.zpepdi.feign_service.tools;

import feign.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class Download {

    public static void downloadFile(Response serviceResponse,HttpServletResponse response) {
        InputStream inputStream = null;
        try {
            Response.Body body = serviceResponse.body();
            inputStream = body.asInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", serviceResponse.headers().get("Content-Disposition").toString().replace("[","").replace("]",""));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            int length = 0;
            byte[] temp = new byte[1024 * 10];
            while ((length = bufferedInputStream.read(temp)) != -1) {
                bufferedOutputStream.write(temp, 0, length);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            bufferedInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
