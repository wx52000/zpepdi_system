package zpepdi.system.tools;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Download {
    public static void downloadFile(HttpServletResponse response, String name , String fileName) {
        if (fileName != null) {
            //设置文件路径
            File file = new File(name);
            if (file.exists()) {
                response.reset();
//                response.setHeader("content-type", "application/octet-stream");
//                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Expose-Headers","*");
                response.setContentType("application/octet-stream");
                try {
                    response.addHeader("Content-Disposition", "attachment;filename=" +  URLEncoder.encode(fileName,"UTF-8"));
                    response.addHeader("Content-Length", "" + file.length());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void zip(String zipFileName, String inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, new File(inputFile), "");
        out.flush();
        out.close();
    }
    public static void zip(ZipOutputStream  out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }

}
