package com.zpepdi.qj_airhammer.capacity;

public class FlowCompute {

    public double compute(double Out,double Width,double mpa,double temp,double x,double weight,double volume){Water water =new Water();
    double Q=0;
    double in=Out/1000-2*Width/1000;
    double ap=Math.PI*Math.pow((in/2),2);
    double v=water.v_pt(mpa,temp,x);
    if(weight!=0){
        Q=weight*v;
    }
    else{
        Q=volume;
        weight=volume/v;
    }
    double result=Q/ap;
    return result;
    }
}
