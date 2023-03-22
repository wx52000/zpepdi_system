package com.zpepdi.qj_airhammer.entity;

import com.alibaba.excel.annotation.ExcelProperty;

public class Excel{
    @ExcelProperty(value="管道号",index=0)
    private String number;
    @ExcelProperty(value="作用点",index=1)
    private String point;
    @ExcelProperty(value="长度L",index=2)
    private String length;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }



    @ExcelProperty(value="方向",index=3)
    private String direction;
    @ExcelProperty(value="力F（N）",index=4)
    private String f;

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    @ExcelProperty(value="取整（N）",index=5)
    private String fz;
    @ExcelProperty(value="两倍取整（N）",index=6)
    private String tf;
}
