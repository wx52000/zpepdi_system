package com.zpepdi.qj_airhammer.service.impl;

import com.zpepdi.qj_airhammer.service.PtService;
import com.zpepdi.qj_airhammer.capacity.Water;
import com.zpepdi.qj_airhammer.dao.WaterDao;
import com.zpepdi.qj_airhammer.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
@Service
public class PtServiceImpl implements PtService {

    @Autowired
    private WaterDao waterDao;


    @Override
    public Result pt(Map<String, Object> map){
        Water water=new Water();
        String p=map.get("p").toString();
        String bp=map.get("bp").toString();
        String t=map.get("t").toString();
        String bt=map.get("bt").toString();
        String x1=map.get("X").toString();
        double mark;
        if(x1.equals("NaN")){
            mark=1;
            x1="1";
        }
        else{
            mark=2;
        }
        double Btemp=0;
        double BMpa=0;
        double mpa = 0;
        String x=map.get("mpa").toString();
        String y=map.get("temperature").toString();
        if(x.equals("")){
            x="0";
        }
        if(y.equals("")){
            y="0";
        }
        if(p.equals("MPa")){
            mpa=Double.valueOf(x);
        }
        else if(p.equals("kg/cm^2")){
            mpa=Double.valueOf(x)* 0.0980665;
        }
        else if(p.equals("bar")){
            mpa=Double.valueOf(x)* 0.1;
        }
        else if(p.equals("kPa")){
            mpa=Double.valueOf(x)* 0.001;
        }
        else if(p.equals("psi-1b/in^2")){
            mpa=Double.valueOf(x)* 0.006894757;
        }
        else if(p.equals("atm")){
            mpa=Double.valueOf(x)* 0.1013325;
        }
        double temp=0;
        if(t.equals("℃")){
            temp=Double.valueOf(y);
        }
        else if(t.equals("℉")){
            temp=(Double.valueOf(y)-32)* 5.0 / 9.0;
        }
        else if(t.equals("K 开尔文")){
            temp=Double.valueOf(y)- 273.15;
        }
        else if(t.equals("R 兰金")){
            temp=(Double.valueOf(y)-491.67)* 5.0 / 9.0;
        }
        double Mparesult=water.Ps(temp);
        if(Mparesult!=-1000){
            if(bp.equals("MPa")){
                BMpa=Mparesult;
            }
            else if(bp.equals("kg/cm^2")){
                BMpa=Mparesult/ 0.0980665;
            }
            else if(bp.equals("bar")){
                BMpa=Mparesult/ 0.1;
            }
            else if(bp.equals("kPa")){
                BMpa=Mparesult/ 0.001;
            }
            else if(bp.equals("psi-1b/in^2")){
                BMpa=Mparesult/ 0.006894757;
            }
            else if(bp.equals("atm")){
                BMpa=Mparesult/ 0.1013325;
            }
        }
        else{
            BMpa=-1000;
        }
        double Tempresult=water.Ts(mpa);
        if(Tempresult!=-1000){
            if(bt.equals("℃")){
                Btemp=Tempresult;
            }
            else if(bt.equals("℉")){
                Btemp=Tempresult* 9.0 / 5.0 + 32;
            }
            else if(bt.equals("K 开尔文")){
                Btemp=Tempresult+ 273.15;
            }
            else if(bt.equals("R 兰金")){
                Btemp=Tempresult* 9.0 / 5.0 + 491.67;
            }
        }
        else{
            Btemp=-1000;
        }
        String meduim=water.m_type_PT(mpa,temp);
        double x2=1;
        double x3=1;
        if(meduim.equals("饱和汽水")){
            x2=Double.valueOf(x1);
            x3=Double.valueOf(x1);
        }
        System.out.println(x2);
        System.out.println(x1);
        double h=water.h_pt(mpa,temp,x3);
        double s=water.s_pt(mpa,temp,x3);
        double v=water.v_pt(mpa,temp);
        double den=water.den_pt(mpa,temp);
        double u=water.u_pt(mpa,temp);
        double cv=water.Cv_pt(mpa,temp);
        double cp=water.Cp_pt(mpa,temp);
        double w=water.w_pt(mpa,temp);
        Map<String,Object> map1=new HashMap<>();
        map1.put("H",h);
        map1.put("X",x2);
        map1.put("mark",mark);
        map1.put("S",s);
        map1.put("V",v);
        map1.put("Den",den);
        map1.put("U",u);
        map1.put("Cv",cv);
        map1.put("Cp",cp);
        map1.put("W",w);
        map1.put("BTemp",Btemp);
        map1.put("BMpa",BMpa);
        map1.put("meduim",meduim);
        return Result.ok(map1);



    }


    @Override
    public Result ph(Map<String, Object> map){
        Water water=new Water();

        String p=map.get("p").toString();
        String bt=map.get("bt").toString();
        double Btemp=0;
        double BMpa=0;
        double mpa = 0;
        String x=map.get("mpa").toString();
        String y=map.get("h").toString();
        if(x.equals("")){
            x="0";
        }
        if(y.equals("")){
            y="0";
        }
        if(p.equals("MPa")){
            mpa=Double.valueOf(x);
        }
        else if(p.equals("kg/cm^2")){
            mpa=Double.valueOf(x)* 0.0980665;
        }
        else if(p.equals("bar")){
            mpa=Double.valueOf(x)* 0.1;
        }
        else if(p.equals("kPa")){
            mpa=Double.valueOf(x)* 0.001;
        }
        else if(p.equals("psi-1b/in^2")){
            mpa=Double.valueOf(x)* 0.006894757;
        }
        else if(p.equals("atm")){
            mpa=Double.valueOf(x)* 0.1013325;
        }
        double Tempresult=water.Ts(mpa);
        if(Tempresult!=-1000){
            if(bt.equals("℃")){
                Btemp=Tempresult;
            }
            else if(bt.equals("℉")){
                Btemp=Tempresult* 9.0 / 5.0 + 32;
            }
            else if(bt.equals("K 开尔文")){
                Btemp=Tempresult+ 273.15;
            }
            else if(bt.equals("R 兰金")){
                Btemp=Tempresult* 9.0 / 5.0 + 491.67;
            }
        }
        else{
            Btemp=-1000;
        }

        double h=Double.valueOf(y);
        String meduim=water.m_type_Ph(mpa,h);
        double gd=water.x_ph(mpa,h);
        double t=water.t_ph(mpa,h);
        double s=water.s_ph(mpa,h);
        double v=water.v_ph(mpa,h);
        double den=water.den_ph(mpa,h);
        double u=water.u_ph(mpa,h);
        double cv=water.cv_ph(mpa,h);
        double cp=water.cp_ph(mpa,h);
        double w=water.w_ph(mpa,h);
        Map<String,Object> map1=new HashMap<>();
        map1.put("T",t);
        map1.put("X",gd);
        map1.put("S",s);
        map1.put("V",v);
        map1.put("Den",den);
        map1.put("U",u);
        map1.put("Cv",cv);
        map1.put("Cp",cp);
        map1.put("W",w);
        map1.put("BTemp",Btemp);
        map1.put("BMpa",BMpa);
        map1.put("meduim",meduim);
        return Result.ok(map1);



    }

    @Override
    public Result ps(Map<String, Object> map){
        Water water=new Water();
        String p=map.get("p").toString();
        String bt=map.get("bt").toString();
        double Btemp=0;
        double BMpa=0;
        double mpa = 0;
        String x=map.get("mpa").toString();
        String y=map.get("s").toString();
        if(x.equals("")){
            x="0";
        }
        if(y.equals("")){
            y="0";
        }
        if(p.equals("MPa")){
            mpa=Double.valueOf(x);
        }
        else if(p.equals("kg/cm^2")){
            mpa=Double.valueOf(x)* 0.0980665;
        }
        else if(p.equals("bar")){
            mpa=Double.valueOf(x)* 0.1;
        }
        else if(p.equals("kPa")){
            mpa=Double.valueOf(x)* 0.001;
        }
        else if(p.equals("psi-1b/in^2")){
            mpa=Double.valueOf(x)* 0.006894757;
        }
        else if(p.equals("atm")){
            mpa=Double.valueOf(x)* 0.1013325;
        }
        double Tempresult=water.Ts(mpa);
        if(Tempresult!=-1000){
            if(bt.equals("℃")){
                Btemp=Tempresult;
            }
            else if(bt.equals("℉")){
                Btemp=Tempresult* 9.0 / 5.0 + 32;
            }
            else if(bt.equals("K 开尔文")){
                Btemp=Tempresult+ 273.15;
            }
            else if(bt.equals("R 兰金")){
                Btemp=Tempresult* 9.0 / 5.0 + 491.67;
            }
        }
        else{
            Btemp=-1000;
        }

        double s=Double.valueOf(y);
        String meduim=water.m_type_Ps(mpa,s);
        double t=water.t_ps(mpa,s);
        double h=water.h_ps(mpa,s);
        double v=water.v_ps(mpa,s);
        double den=water.den_ps(mpa,s);
        double u=water.u_ps(mpa,s);
        double cv=water.cv_ps(mpa,s);
        double cp=water.cp_ps(mpa,s);
        double w=water.w_ps(mpa,s);
        double x1=water.x_ps(mpa,s);
        Map<String,Object> map1=new HashMap<>();
        map1.put("T",t);
        map1.put("H",h);
        map1.put("V",v);
        map1.put("Den",den);
        map1.put("U",u);
        map1.put("Cv",cv);
        map1.put("Cp",cp);
        map1.put("W",w);
        map1.put("BTemp",Btemp);
        map1.put("BMpa",BMpa);
        map1.put("meduim",meduim);
        map1.put("X",x1);
        return Result.ok(map1);



    }


    @Override
    public Result hs(Map<String, Object> map){
        Water water=new Water();
        String x=map.get("h").toString();
        String y=map.get("s").toString();
        if(x.equals("")){
            x="0";
        }
        if(y.equals("")){
            y="0";
        }
        double h=Double.valueOf(x);
        double s=Double.valueOf(y);
        String meduim=water.m_type_hs(h,s);
        double t=water.t_hs(h,s);
        double p=water.p_hs(h,s);
        double v=water.v_hs(h,s);
        double den=water.den_hs(h,s);
        double u=water.u_hs(h,s);
        double cv=water.cv_hs(h,s);
        double cp=water.cp_hs(h,s);
        double w=water.w_hs(h,s);
        double x1=water.x_hs(h,s);
        Map<String,Object> map1=new HashMap<>();
        map1.put("T",t);
        map1.put("P",p);
        map1.put("V",v);
        map1.put("Den",den);
        map1.put("U",u);
        map1.put("Cv",cv);
        map1.put("Cp",cp);
        map1.put("W",w);
        map1.put("meduim",meduim);
        map1.put("X",x1);
        return Result.ok(map1);



    }

    @Override
    public Result jl(Map<String, Object> map){
        Water water=new Water();
        String Mpa1=map.get("Mpa1").toString();
        String Mpa3=map.get("Mpa3").toString();
        String T1=map.get("T1").toString();
        String p1=map.get("p1").toString();
        String p2=map.get("p2").toString();
        String p3=map.get("p3").toString();
        String t1=map.get("t1").toString();
        String t2=map.get("t2").toString();
        String x1=map.get("X").toString();
        double mark;
        if(x1.equals("NaN")){
            mark=1;
            x1="1";
        }
        else{
            mark=2;
        }
        double mpa = 0;
        if(Mpa1.equals("")){
            Mpa1="0";
        }
        if(Mpa3.equals("")){
            Mpa3="0";
        }
        if(T1.equals("")){
            T1="0";
        }
        if(p1.equals("MPa")){
            mpa=Double.valueOf(Mpa1);
        }
        else if(p1.equals("kg/cm^2")){
            mpa=Double.valueOf(Mpa1)* 0.0980665;
        }
        else if(p1.equals("bar")){
            mpa=Double.valueOf(Mpa1)* 0.1;
        }
        else if(p1.equals("kPa")){
            mpa=Double.valueOf(Mpa1)* 0.001;
        }
        else if(p1.equals("psi-1b/in^2")){
            mpa=Double.valueOf(Mpa1)* 0.006894757;
        }
        else if(p1.equals("atm")){
            mpa=Double.valueOf(Mpa1)* 0.1013325;
        }
        double temp=0;
        if(t1.equals("℃")){
            temp=Double.valueOf(T1);
        }
        else if(t1.equals("℉")){
            temp=(Double.valueOf(T1)-32)* 5.0 / 9.0;
        }
        else if(t1.equals("K 开尔文")){
            temp=Double.valueOf(T1)- 273.15;
        }
        else if(t1.equals("R 兰金")){
            temp=(Double.valueOf(T1)-491.67)* 5.0 / 9.0;
        }
        double mpa3=0;
        if(p3.equals("MPa")){
            mpa3=Double.valueOf(Mpa3);
        }
        else if(p3.equals("kg/cm^2")){
            mpa3=Double.valueOf(Mpa3)* 0.0980665;
        }
        else if(p3.equals("bar")){
            mpa3=Double.valueOf(Mpa3)* 0.1;
        }
        else if(p3.equals("kPa")){
            mpa3=Double.valueOf(Mpa3)* 0.001;
        }
        else if(p3.equals("psi-1b/in^2")){
            mpa3=Double.valueOf(Mpa3)* 0.006894757;
        }
        else if(p3.equals("atm")){
            mpa3=Double.valueOf(Mpa3)* 0.1013325;
        }
        String m_type=water.m_type_PT(mpa,temp);
        double x3=1;
        double x4=1;
        if(m_type.equals("饱和汽水")){
            x3=Double.valueOf(x1);
            x4=Double.valueOf(x1);
        }
        double h=water.h_pt(mpa,temp,x4);
        double mpa2=mpa-mpa3;
        if(p2.equals("MPa")){
            mpa2=mpa2;
        }
        else if(p2.equals("kg/cm^2")){
            mpa2=mpa2* 0.0980665;
        }
        else if(p2.equals("bar")){
            mpa2=mpa2* 0.1;
        }
        else if(p2.equals("kPa")){
            mpa2=mpa2* 0.001;
        }
        else if(p2.equals("psi-1b/in^2")){
            mpa2=mpa2* 0.006894757;
        }
        else if(p2.equals("atm")){
            mpa2=mpa2* 0.1013325;
        }
        double temp2= water.t_ph(mpa2,h);
        if(temp2!=-1000){
            if(t2.equals("℃")){
                temp2=temp2;
            }
            else if(t2.equals("℉")){
                temp2=(temp2-32)* 5.0 / 9.0;
            }
            else if(t2.equals("K 开尔文")){
                temp2=temp2- 273.15;
            }
            else if(t2.equals("R 兰金")){
                temp2=(temp2-491.67)* 5.0 / 9.0;
            }
        }
        double x2=water.x_ph(mpa2,h);
        String m_type2= water.m_type_Ph(mpa2,h);
        Map<String,Object> map1=new HashMap<>();
        map1.put("Mpa2",mpa2);
        map1.put("H",h);
        map1.put("X1",x3);
        map1.put("mark",mark);
        map1.put("X2",x2);
        map1.put("meduim1",m_type);
        map1.put("meduim2",m_type2);
        map1.put("T2",temp2);
        return Result.ok(map1);
    }
    @Override
    public Result ds(Map<String, Object> map){
        Water water=new Water();
        String Mpa1=map.get("Mpa1").toString();
        String Mpa2=map.get("Mpa2").toString();
        String T1=map.get("T1").toString();
        String p1=map.get("p1").toString();
        String p2=map.get("p2").toString();
        String t1=map.get("t1").toString();
        String t2=map.get("t2").toString();
        String x1=map.get("X").toString();
        double mark;
        if(x1.equals("NaN")){
            mark=1;
            x1="1";
        }
        else{
            mark=2;
        }
        double mpa = 0;
        if(Mpa1.equals("")){
            Mpa1="0";
        }
        if(Mpa2.equals("")){
            Mpa2="0";
        }
        if(T1.equals("")){
            T1="0";
        }
        if(p1.equals("MPa")){
            mpa=Double.valueOf(Mpa1);
        }
        else if(p1.equals("kg/cm^2")){
            mpa=Double.valueOf(Mpa1)* 0.0980665;
        }
        else if(p1.equals("bar")){
            mpa=Double.valueOf(Mpa1)* 0.1;
        }
        else if(p1.equals("kPa")){
            mpa=Double.valueOf(Mpa1)* 0.001;
        }
        else if(p1.equals("psi-1b/in^2")){
            mpa=Double.valueOf(Mpa1)* 0.006894757;
        }
        else if(p1.equals("atm")){
            mpa=Double.valueOf(Mpa1)* 0.1013325;
        }
        double temp=0;

        if(t1.equals("℃")){
            temp=Double.valueOf(T1);
        }
        else if(t1.equals("℉")){
            temp=(Double.valueOf(T1)-32)* 5.0 / 9.0;
        }
        else if(t1.equals("K 开尔文")){
            temp=Double.valueOf(T1)- 273.15;
        }
        else if(t1.equals("R 兰金")){
            temp=(Double.valueOf(T1)-491.67)* 5.0 / 9.0;
        }
        double mpa3=0;

        String m_type=water.m_type_PT(mpa,temp);
        double x=1;
        double x3=1;
        double x4=1;
        if(m_type.equals("饱和汽水")){
            x3=Double.valueOf(x1);
            x4=Double.valueOf(x1);
        }
        double s=water.s_pt(mpa,temp,x4);
        double mpa2=Double.valueOf(Mpa2);
        if(p2.equals("MPa")){
            mpa2=mpa2;
        }
        else if(p2.equals("kg/cm^2")){
            mpa2=mpa2* 0.0980665;
        }
        else if(p2.equals("bar")){
            mpa2=mpa2* 0.1;
        }
        else if(p2.equals("kPa")){
            mpa2=mpa2* 0.001;
        }
        else if(p2.equals("psi-1b/in^2")){
            mpa2=mpa2* 0.006894757;
        }
        else if(p2.equals("atm")){
            mpa2=mpa2* 0.1013325;
        }
        double temp2=water.t_ps(mpa2,s);
        if(t2.equals("℃")){
            temp2=temp2;
        }
        else if(t2.equals("℉")){
            temp2=(temp2-32)* 5.0 / 9.0;
        }
        else if(t2.equals("K 开尔文")){
            temp2=temp2- 273.15;
        }
        else if(t2.equals("R 兰金")){
            temp2=(temp2-491.67)* 5.0 / 9.0;
        }
        double x2=water.x_ps(mpa2,s);
        String m_type2=water.m_type_Ps(mpa2,s);
        Map<String,Object> map1=new HashMap<>();
        map1.put("Mpa2",mpa2);
        map1.put("S",s);
        map1.put("X1",x3);
        map1.put("mark",mark);
        map1.put("X2",x2);
        map1.put("meduim1",m_type);
        map1.put("meduim2",m_type2);
        map1.put("T2",temp2);
        return Result.ok(map1);
    }

    @Override
    public Result save(Integer userId,Map<String, Object> map){
        String kind=map.get("kind").toString();
        Object bySelect=waterDao.doSelect(userId,kind);
        System.out.println(bySelect);
        if(StringUtils.isEmpty(bySelect)){
            waterDao.doInsert(userId,map);
        }
        else{
            waterDao.doUpdate(userId,map);
        }
        return null;
    }

    @Override
    public Result show(Integer userId,Map<String, Object> map){
        return Result.ok(waterDao.show(userId,map));
    }

}
