package com.zpepdi.qj_airhammer.entity;

import java.math.BigDecimal;
import java.util.Formatter;

public class AirHammer {
    private int id;
    // 最大工作压力
    private BigDecimal Mpa;
    // 最大工作温度
    private BigDecimal Temperature;
    // 最大工作流量
    private BigDecimal Flow;
    // 阀门关闭时间
    private BigDecimal time;
// 蒸汽比容
    private BigDecimal V;
    //C=(k*p*v)1/2
    private BigDecimal C;
    //临界长度
    private BigDecimal Lcr;
    //最大不平衡力Fmax
    private BigDecimal Fmax;


    //最大不平衡力Fmax
    private BigDecimal Length;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMpa() {
        return Mpa;
    }

    public void setMpa(BigDecimal mpa) {
        Mpa = mpa;
    }

    public BigDecimal getTemperature() {
        return Temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        Temperature = temperature;
    }

    public BigDecimal getFlow() {
        return Flow;
    }

    public void setFlow(BigDecimal flow) {
        Flow = flow;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public BigDecimal getV() {
        return V;
    }

    public void setV(BigDecimal v) {
        V = v;
    }

    public BigDecimal getC() {
        return C;
    }

    public void setC(BigDecimal c) {
        C = c;
    }

    public BigDecimal getLcr() {
        return Lcr;
    }

    public void setLcr(BigDecimal lcr) {
        Lcr = lcr;
    }

    public BigDecimal getFmax() {
        return Fmax;
    }

    public void setFmax(BigDecimal fmax) {
        Fmax = fmax;
    }
    public BigDecimal getLength() {
        return Length;
    }

    public void setLength(BigDecimal length) {
        Length = length;
    }
}
