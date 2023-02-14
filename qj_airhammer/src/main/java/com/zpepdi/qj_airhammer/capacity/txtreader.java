package com.zpepdi.qj_airhammer.capacity;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class txtreader {
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(
                new FileReader("C:\\Users\\ZPEPDI-3D\\Desktop\\新建文本文档 (6).txt"));
        //创建一个文件字节输出流来输出文件
        FileOutputStream out=new FileOutputStream("C:\\Users\\ZPEPDI-3D\\Desktop\\新建文本文档 (10).txt",true);
        String line=null;        //定义一个字符串，初始化null
        int x=1;
        while((line=reader.readLine())!=null) {//文件一行一行的读，直到读到文件尾
            String a=null;
            String b=null;
            String replace="n("+x+")";
            String need="n["+(x-1)+"]";

            line=line.replace(replace,need);
            a=line.substring(8,14);
            b=line.substring(15);
            line=a+"BigDecimal.valueOf("+b+");";
            out.write(line.getBytes());
            out.write("\n".getBytes());
            x++;
        }
        out.close();//关闭流
        reader.close();

    }

}
