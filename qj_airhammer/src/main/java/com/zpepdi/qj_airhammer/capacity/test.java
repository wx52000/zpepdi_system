package com.zpepdi.qj_airhammer.capacity;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class test {
    public  static void main(String[] args){
        Water water=new Water();
        double mpa=1;
        double t=2500;
        NumberFormat nf=NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(12);
        String x=nf.format(water.s_pt(1,179.8856,0.864));
        System.out.println(x);
    }

}
