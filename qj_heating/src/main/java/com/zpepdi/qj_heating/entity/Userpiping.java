package com.zpepdi.qj_heating.entity;

/**
 * 用户管道实体类
 */
public class Userpiping {
    //id
    private Integer id;
    //用户工号
    private String username;
    //用户名
    private String name;
//    序号
    private String xuhao;
//    管道名
    private String gdsortid;
    private String gdname;
//    标准
    private String radio;
//    管道类型
    private String radio2;
//    钢管类型
    private String value1;
//    材料
    private String value2;
//    标准号
    private String bzhao;
//    介质
    private String value3;
//    推荐流速
    private String recommend;
//    试算流速
    private String recommend2;
//    工作温度
    private String wendu;
    private String wenduUnit;
//    工作压力
    private String yali;
    private String yaliUnit;
//    质量流量
    private String pipingG;
//    容积流量
    private String pipingQ;
//    介质比容
    private String birong;
//    试算内径
    private String pipingDi;
//    订货外径
    private String pipingDo;
//    订货内径
    private String dhneijing;
//    设计温度
    private String sjwendu;
    private String sjwenduUnit;
//    设计压力
    private String sjyali;
    private String sjyaliUnit;
//    许用应力
    private String yingli;
    private String isyingliinput;
//    修正系数
    private String pipingY;
//    许用应力修正系数
    private String pipingyingliY;
//    附加厚度
    private String pipingC;
//    最小壁厚
    private String pipingSm;
//    壁厚负偏差
    private String pipingC1;
//    壁厚负偏差附加值
    private String endC1;
//    计算壁厚
    private String pipingSc;
//    外径正公差
    private String pipingAA;
//    取用计算壁厚
    private String pipingAG;
//    订货壁厚
    private String dhbihou;
//    内径
    private String endnj;
//    流速
    private String endls;
    private String bihouZheng;
    //是否外径管
    private String defstr1;
    private Integer defstr2;
    private String defstr3;
    private String defstr4;
    private String defstr5;
    private String defstr6;
    private String defstr7;
    private String defstr8;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXuhao() {
        return xuhao;
    }

    public void setXuhao(String xuhao) {
        this.xuhao = xuhao;
    }

    public String getGdname() {
        return gdname;
    }

    public void setGdname(String gdname) {
        this.gdname = gdname;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getRadio2() {
        return radio2;
    }

    public void setRadio2(String radio2) {
        this.radio2 = radio2;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getBzhao() {
        return bzhao;
    }

    public void setBzhao(String bzhao) {
        this.bzhao = bzhao;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getRecommend2() {
        return recommend2;
    }

    public void setRecommend2(String recommend2) {
        this.recommend2 = recommend2;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getYali() {
        return yali;
    }

    public void setYali(String yali) {
        this.yali = yali;
    }

    public String getPipingG() {
        return pipingG;
    }

    public void setPipingG(String pipingG) {
        this.pipingG = pipingG;
    }

    public String getPipingQ() {
        return pipingQ;
    }

    public void setPipingQ(String pipingQ) {
        this.pipingQ = pipingQ;
    }

    public String getBirong() {
        return birong;
    }

    public void setBirong(String birong) {
        this.birong = birong;
    }

    public String getPipingDi() {
        return pipingDi;
    }

    public void setPipingDi(String pipingDi) {
        this.pipingDi = pipingDi;
    }

    public String getPipingDo() {
        return pipingDo;
    }

    public void setPipingDo(String pipingDo) {
        this.pipingDo = pipingDo;
    }

    public String getDhneijing() {
        return dhneijing;
    }

    public void setDhneijing(String dhneijing) {
        this.dhneijing = dhneijing;
    }

    public String getSjwendu() {
        return sjwendu;
    }

    public void setSjwendu(String sjwendu) {
        this.sjwendu = sjwendu;
    }

    public String getSjyali() {
        return sjyali;
    }

    public void setSjyali(String sjyali) {
        this.sjyali = sjyali;
    }

    public String getYingli() {
        return yingli;
    }

    public void setYingli(String yingli) {
        this.yingli = yingli;
    }

    public String getPipingY() {
        return pipingY;
    }

    public void setPipingY(String pipingY) {
        this.pipingY = pipingY;
    }

    public String getPipingyingliY() {
        return pipingyingliY;
    }

    public void setPipingyingliY(String pipingyingliY) {
        this.pipingyingliY = pipingyingliY;
    }

    public String getPipingC() {
        return pipingC;
    }

    public void setPipingC(String pipingC) {
        this.pipingC = pipingC;
    }

    public String getPipingSm() {
        return pipingSm;
    }

    public void setPipingSm(String pipingSm) {
        this.pipingSm = pipingSm;
    }

    public String getPipingC1() {
        return pipingC1;
    }

    public void setPipingC1(String pipingC1) {
        this.pipingC1 = pipingC1;
    }

    public String getEndC1() {
        return endC1;
    }

    public void setEndC1(String endC1) {
        this.endC1 = endC1;
    }

    public String getPipingSc() {
        return pipingSc;
    }

    public void setPipingSc(String pipingSc) {
        this.pipingSc = pipingSc;
    }

    public String getPipingAA() {
        return pipingAA;
    }

    public void setPipingAA(String pipingAA) {
        this.pipingAA = pipingAA;
    }

    public String getPipingAG() {
        return pipingAG;
    }

    public void setPipingAG(String pipingAG) {
        this.pipingAG = pipingAG;
    }

    public String getDhbihou() {
        return dhbihou;
    }

    public void setDhbihou(String dhbihou) {
        this.dhbihou = dhbihou;
    }

    public String getEndnj() {
        return endnj;
    }

    public void setEndnj(String endnj) {
        this.endnj = endnj;
    }

    public String getEndls() {
        return endls;
    }

    public void setEndls(String endls) {
        this.endls = endls;
    }

    public String getDefstr1() {
        return defstr1;
    }

    public void setDefstr1(String defstr1) {
        this.defstr1 = defstr1;
    }

    public Integer getDefstr2() {
        return defstr2;
    }

    public void setDefstr2(Integer defstr2) {
        this.defstr2 = defstr2;
    }

    public String getDefstr3() {
        return defstr3;
    }

    public void setDefstr3(String defstr3) {
        this.defstr3 = defstr3;
    }

    public String getDefstr4() {
        return defstr4;
    }

    public void setDefstr4(String defstr4) {
        this.defstr4 = defstr4;
    }

    public String getDefstr5() {
        return defstr5;
    }

    public void setDefstr5(String defstr5) {
        this.defstr5 = defstr5;
    }

    public String getDefstr6() {
        return defstr6;
    }

    public void setDefstr6(String defstr6) {
        this.defstr6 = defstr6;
    }

    public String getDefstr7() {
        return defstr7;
    }

    public void setDefstr7(String defstr7) {
        this.defstr7 = defstr7;
    }

    public String getDefstr8() {
        return defstr8;
    }

    public void setDefstr8(String defstr8) {
        this.defstr8 = defstr8;
    }

    public String getWenduUnit() {
        return wenduUnit;
    }

    public void setWenduUnit(String wenduUnit) {
        this.wenduUnit = wenduUnit;
    }

    public String getYaliUnit() {
        return yaliUnit;
    }

    public void setYaliUnit(String yaliUnit) {
        this.yaliUnit = yaliUnit;
    }

    public String getSjwenduUnit() {
        return sjwenduUnit;
    }

    public void setSjwenduUnit(String sjwenduUnit) {
        this.sjwenduUnit = sjwenduUnit;
    }

    public String getSjyaliUnit() {
        return sjyaliUnit;
    }

    public void setSjyaliUnit(String sjyaliUnit) {
        this.sjyaliUnit = sjyaliUnit;
    }

    public String getIsyingliinput() {
        return isyingliinput;
    }

    public void setIsyingliinput(String isyingliinput) {
        this.isyingliinput = isyingliinput;
    }

    public String getBihouZheng() {
        return bihouZheng;
    }

    public void setBihouZheng(String bihouZheng) {
        this.bihouZheng = bihouZheng;
    }

    public String getGdsortid() {
        return gdsortid;
    }

    public void setGdsortid(String gdsortid) {
        this.gdsortid = gdsortid;
    }

    @Override
    public String toString() {
        return "Userpiping{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", xuhao='" + xuhao + '\'' +
                ", gdsortid='" + gdsortid + '\'' +
                ", gdname='" + gdname + '\'' +
                ", radio='" + radio + '\'' +
                ", radio2='" + radio2 + '\'' +
                ", value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", bzhao='" + bzhao + '\'' +
                ", value3='" + value3 + '\'' +
                ", recommend='" + recommend + '\'' +
                ", recommend2='" + recommend2 + '\'' +
                ", wendu='" + wendu + '\'' +
                ", wenduUnit='" + wenduUnit + '\'' +
                ", yali='" + yali + '\'' +
                ", yaliUnit='" + yaliUnit + '\'' +
                ", pipingG='" + pipingG + '\'' +
                ", pipingQ='" + pipingQ + '\'' +
                ", birong='" + birong + '\'' +
                ", pipingDi='" + pipingDi + '\'' +
                ", pipingDo='" + pipingDo + '\'' +
                ", dhneijing='" + dhneijing + '\'' +
                ", sjwendu='" + sjwendu + '\'' +
                ", sjwenduUnit='" + sjwenduUnit + '\'' +
                ", sjyali='" + sjyali + '\'' +
                ", sjyaliUnit='" + sjyaliUnit + '\'' +
                ", yingli='" + yingli + '\'' +
                ", isyingliinput='" + isyingliinput + '\'' +
                ", pipingY='" + pipingY + '\'' +
                ", pipingyingliY='" + pipingyingliY + '\'' +
                ", pipingC='" + pipingC + '\'' +
                ", pipingSm='" + pipingSm + '\'' +
                ", pipingC1='" + pipingC1 + '\'' +
                ", endC1='" + endC1 + '\'' +
                ", pipingSc='" + pipingSc + '\'' +
                ", pipingAA='" + pipingAA + '\'' +
                ", pipingAG='" + pipingAG + '\'' +
                ", dhbihou='" + dhbihou + '\'' +
                ", endnj='" + endnj + '\'' +
                ", endls='" + endls + '\'' +
                ", bihouZheng='" + bihouZheng + '\'' +
                ", defstr1='" + defstr1 + '\'' +
                ", defstr2=" + defstr2 +
                ", defstr3='" + defstr3 + '\'' +
                ", defstr4='" + defstr4 + '\'' +
                ", defstr5='" + defstr5 + '\'' +
                ", defstr6='" + defstr6 + '\'' +
                ", defstr7='" + defstr7 + '\'' +
                ", defstr8='" + defstr8 + '\'' +
                '}';
    }
}
