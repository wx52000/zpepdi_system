package com.zpepdi.qj_airhammer.entity;

public class Flow {




    //管道名
    private String GName;

    // 管道类别
    private String Name;
    // 压力
    private Double Mpa;
    // 温度
    private Double Temperature;
    // 外径
    private Double Out;
    // 壁厚
    private Double Width;
    //体积流量
    private Double Volume;
    //质量流量
    private Double Weight;
    //压力单位
    private String P;
    private Double Actual;
    //温度单位
    private String T;
    private String Medium;

    public Double getX() {
        return X;
    }

    public void setX(Double x) {
        X = x;
    }

    private Double X;

    public Double getIn() {
        return In;
    }

    public void setIn(Double in) {
        In = in;
    }

    private Double In;


    private String Conclusion;
    private String Speed;

    private String Medium1;
    private String V;
    private String W;

    public String getMedium() {
        return Medium;
    }

    public void setMedium(String medium) {
        Medium = medium;
    }

    public Double getOut() {
        return Out;
    }

    public void setOut(Double out) {
        Out = out;
    }

    public Double getWidth() {
        return Width;
    }
    public void setWidth(Double width) {
        Width = width;
    }

    public Double getVolume() {
        return Volume;
    }

    public void setVolume(Double volume) {
        Volume = volume;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }
    public Double getActual() {
        return Actual;
    }
    public void setActual(Double actual) {
        Actual = actual;
    }


    public String getP() {
        return P;
    }

    public void setP(String p) {
        P = p;
    }

    public String getT() {
        return T;
    }

    public void setT(String t) {
        T = t;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getMpa() {
        return Mpa;
    }

    public void setMpa(Double mpa) {
        Mpa = mpa;
    }

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public String getGName() {
        return GName;
    }

    public void setGName(String GName) {
        this.GName = GName;
    }

    public String getConclusion() {
        return Conclusion;
    }

    public void setConclusion(String conclusion) {
        Conclusion = conclusion;
    }

    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String speed) {
        Speed = speed;
    }

    public String getMedium1() {
        return Medium1;
    }

    public void setMedium1(String medium1) {
        Medium1 = medium1;
    }

    public String getV() {
        return V;
    }

    public void setV(String v) {
        V = v;
    }

    public String getW() {
        return W;
    }

    public void setW(String w) {
        W = w;
    }
}
