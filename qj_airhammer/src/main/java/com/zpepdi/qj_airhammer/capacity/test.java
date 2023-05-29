package com.zpepdi.qj_airhammer.capacity;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class test {
    public  static void main(String[] args){
        Water water=new Water();
        double mpa=1;
        double t=179.8856;
        double x=0.1;
        NumberFormat nf=NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(12);
        String s=nf.format(water.v_pt(mpa,t,x));
        System.out.println(s);
    }

}
