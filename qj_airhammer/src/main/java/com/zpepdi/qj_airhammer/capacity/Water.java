package com.zpepdi.qj_airhammer.capacity;

import com.fasterxml.jackson.databind.node.DoubleNode;
import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;
import org.bouncycastle.math.ec.ECMultiplier;

import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_DOWN;

public class Water {
    double R = 0.461526;

    public String subregion_pT(Double mpa, Double temperature) {
        String subregion_pT="0";
        double T = temperature + 273.15;
        boolean a = mpa > 23 && mpa <= 23.5;
        boolean b = mpa > 23.5 && mpa <= 25;
        boolean c = mpa > 25 && mpa <= 40;
        boolean d = mpa > 40 && mpa <= 100;
        boolean e = mpa > 22.5 && mpa <= 23;
        boolean f = mpa > Ps(643.15 - 273.15) && mpa <= 22.5;
        boolean g = mpa > 20.5 && mpa <= Ps(643.15 - 273.15);
        boolean h = mpa > 19.0088118917393 && mpa <= 20.5;
        boolean i = mpa > 22.064 && mpa <= 22.11;
        if (T < 273.15 || T > 2273.15) {
            return "0";
        }
        if (T > 1073.15 && (mpa > 50 || mpa <= 0.000611657)) {
            return "0";
        }
        if (mpa > 100 || mpa <= 0.000611657) {
            return "0";
        } else {
            if (T >= 273.15 && T <= 623.15) {
                if (mpa > 22.064) {
                    subregion_pT= "1";
                }
                if (Math.abs(temperature - Ts(mpa)) < 0.0001) {
                    subregion_pT= "4";
                }
                if (mpa > Ps(temperature) && mpa <= 100) {
                    subregion_pT= "1";
                }
                if(!(mpa > 22.064) && !(Math.abs(temperature - Ts(mpa)) < 0.0001) && !(mpa > Ps(temperature)&&mpa <= 100)){
                    subregion_pT= "2";
                }

            }
            if (T > 623.15 && T <= 863.15) {
                if (mpa > 0 && mpa <= PB23(temperature)) {
                    subregion_pT= "2";
                } else {
                    if (mpa == Double.valueOf(22.064) && T == Double.valueOf(647.096)) {
                        subregion_pT= "Critical point";
                    }
                    if (d && temperature <= T3ab(mpa)) {
                        subregion_pT= "3a";
                    }
                    if (d && temperature > T3ab(mpa)) {
                        subregion_pT= "3b";
                    }
                    if (c && temperature <= T3cd(mpa)) {
                        subregion_pT= "3c";
                    }
                    if (c && (temperature > T3cd(mpa) && temperature <= T3ab(mpa))) {
                        subregion_pT= "3d";
                    }
                    if (c && (temperature > T3ab(mpa) && temperature <= T3ef(mpa))) {
                        subregion_pT= "3e";
                    }
                    if (c && temperature > T3ef(mpa)) {
                        subregion_pT= "3f";
                    }
                    if (b && temperature <= T3cd(mpa)) {
                        subregion_pT= "3c";
                    }
                    if (b && (temperature > T3cd(mpa) && temperature <= T3gh(mpa))) {
                        subregion_pT= "3g";
                    }
                    if (b && (temperature > T3gh(mpa) && temperature <= T3ef(mpa))) {
                        subregion_pT= "3h";
                    }
                    if (b && (temperature > T3ef(mpa) && temperature <= T3ij(mpa))) {
                        subregion_pT= "3i";
                    }
                    if (b && (temperature > T3ij(mpa) && temperature <= T3jk(temperature))) {
                        subregion_pT= "3j";
                    }
                    if (b && temperature > T3jk(mpa)) {
                        subregion_pT= "3k";
                    }
                    if (a && temperature <= T3cd(mpa)) {
                        subregion_pT= "3c";
                    }
                    if (a && (temperature > T3cd(mpa) && temperature <= T3gh(mpa))) {
                        subregion_pT= "3l";
                    }
                    if (a && (temperature > T3gh(mpa) && temperature <= T3ef(temperature))) {
                        subregion_pT= "3h";
                    }
                    if (a && (temperature > T3ef(mpa) && temperature <= T3ij(mpa))) {
                        subregion_pT= "3i";
                    }
                    if (a && (temperature > T3ij(mpa) && temperature <= T3jk(mpa))) {
                        subregion_pT= "3j";
                    }
                    if (a && temperature > T3jk(mpa)) {
                        subregion_pT= "3k";
                    }
                    if (e && temperature <= T3cd(mpa)) {
                        subregion_pT= "3c";
                    }
                    if (e && (temperature > T3cd(mpa) && temperature <= T3gh(mpa))) {
                        subregion_pT= "3l";
                    }
                    if (e && (temperature > T3gh(mpa) && temperature <= T3mn(mpa))) {
                        subregion_pT= "3m";
                    }
                    if (e && (temperature > T3mn(mpa) && temperature <= T3ef(mpa))) {
                        subregion_pT= "3n";
                    }
                    if (e && (temperature > T3ef(mpa) && temperature <= T3op(mpa))) {
                        subregion_pT= "3o";
                    }
                    if (e && (temperature > T3op(mpa) && temperature <= T3ij(mpa))) {
                        subregion_pT= "3p";
                    }
                    if (e && (temperature > T3ij(mpa) && temperature <= T3jk(mpa))) {
                        subregion_pT= "3j";
                    }
                    if (e && temperature > T3jk(mpa)) {
                        subregion_pT= "3k";
                    }
                    if (f && temperature <= T3cd(mpa)) {
                        subregion_pT= "3c";
                    }
                    if (f && (temperature > T3cd(mpa) && temperature <= T3qu(mpa))) {
                        subregion_pT= "3q";
                    }
                    if (f && (temperature > T3rx(mpa) && temperature <= T3jk(mpa))) {
                        subregion_pT= "3r";
                    }
                    if (f && temperature > T3jk(mpa)) {
                        subregion_pT= "3k";
                    }
                    if (g && temperature <= T3cd(mpa)) {
                        subregion_pT= "3c";
                    }
                    if (g && (temperature > T3cd(mpa) && temperature <= Ts(mpa))) {
                        subregion_pT= "3s";
                    }
                    if (g && (temperature >= Ts(mpa) && temperature <= T3jk(mpa))) {
                        subregion_pT= "3r";
                    }
                    if (g && (temperature > T3jk(mpa))) {
                        subregion_pT= "3k";
                    }
                    if (h && temperature <= T3cd(mpa)) {
                        subregion_pT= "3c";
                    }
                    if (h && (temperature > T3cd(mpa) && temperature <= Ts(mpa))) {
                        subregion_pT= "3s";
                    }
                    if (h && temperature >= Ts(mpa)) {
                        subregion_pT= "3t";
                    }
                    if ((mpa > Ps(623.15 - 273.15) && mpa <= 19.0088118917393) && temperature <= Ts(mpa)) {
                        subregion_pT= "3c";
                    }
                    if ((mpa > Ps(623.15 - 273.15) && mpa <= 19.0088118917393) && temperature >= Ts(mpa)) {
                        subregion_pT= "3t";
                    }
                    if ((mpa > 22.11 && mpa <= 22.5) && (temperature > T3qu(mpa) && temperature <= T3uv(mpa))) {
                        subregion_pT= "3u";
                    }
                    if ((mpa > 22.11 && mpa <= 22.5) && (temperature > T3uv(mpa) && temperature <= T3ef(mpa))) {
                        subregion_pT= "3v";
                    }
                    if ((mpa > 22.11 && mpa <= 22.5) && (temperature > T3ef(mpa) && temperature <= T3wx(mpa))) {
                        subregion_pT= "3w";
                    }
                    if ((mpa > 22.11 && mpa <= 22.5) && (temperature > T3wx(mpa) && temperature <= T3rx(mpa))) {
                        subregion_pT= "3x";
                    }
                    if (i && (temperature > T3qu(mpa) && temperature <= T3uv(mpa))) {
                        subregion_pT= "3u";
                    }
                    if (i && (temperature > T3uv(mpa) && temperature <= T3ef(mpa))) {
                        subregion_pT= "3y";
                    }
                    if (i && (temperature > T3ef(mpa) && temperature <= T3wx(mpa))) {
                        subregion_pT= "3z";
                    }
                    if (i && (temperature > T3wx(mpa) && temperature <= T3rx(mpa))) {
                        subregion_pT= "3x";
                    }
                    if (temperature <= Ts(mpa) && (mpa > 21.93161551 && mpa <= 22.064) && (temperature > T3qu(mpa) && temperature <= T3uv(mpa))) {
                        subregion_pT= "3u";
                    }
                    if (temperature <= Ts(mpa) && (mpa > 21.93161551 && mpa <= 22.064) && temperature > T3uv(mpa)) {
                        subregion_pT= "3y";
                    }
                    if (temperature <= Ts(mpa) && (mpa > Ps(643.15 - 273.15) && mpa <= 21.93161551) && temperature > T3qu(mpa)) {
                        subregion_pT= "3u";
                    }
                    if (temperature >= Ts(mpa) && (mpa > 21.90096265 && mpa <= 22.064) && temperature <= T3wx(mpa)) {
                        subregion_pT= "3z";
                    }
                    if (temperature >= Ts(mpa) && (mpa > 21.90096265 && mpa <= 22.064) && (temperature > T3wx(mpa) && temperature <= T3rx(mpa))) {
                        subregion_pT= "3x";
                    }
                    if (temperature >= Ts(mpa) && (mpa > Ps(643.15 - 273.15) && mpa <= 21.90096265) && temperature <= T3rx(mpa)) {
                        subregion_pT= "3x";
                    }
                    if (mpa >= Ps(temperature) && mpa < 22.064) {
                        if (Math.abs(temperature - Ts(mpa)) < 0.0001) {
                            subregion_pT= "4";
                        }
                    }
//                    else {
//                        subregion_pT= "0";
//                    }
                }
            }
            if ((T > 863.15 && T <= 1073.15) && (mpa > 0.000611657 && mpa <= 100)) {
                subregion_pT= "2";
            } if ((T >= 1073.15 && T <= 2273.15) && (mpa > 0.000611657 && mpa <= 50)) {
                subregion_pT= "5";
            }
            if (T >= 273.15 && T <= 647.096) {
                if (mpa > 0.000611657 && mpa <= 22.064) {
                    if (Math.abs(temperature - Ts(mpa)) < 0.0001) {
                        if ((mpa == Double.valueOf(22.064) && T == Double.valueOf(647.096))) {
                            subregion_pT= "Critical point";
                        } else {
                            subregion_pT= "4";
                        }
                    }
                }
            }
//            else {
//                subregion_pT= "0";
//            }
        }

        return subregion_pT;
    }

    public double v_pt(double Mpa, double temperature,double x) {
        String areatype = subregion_pT(Mpa, temperature);
        if (areatype.equals("Critical point"))
            return v3z(Mpa, temperature);
        else if (areatype.equals("1"))
            return v_1(Mpa, temperature);
        else if (areatype.equals("2"))
            return v_2(Mpa, temperature);
        else if (areatype.equals("3a"))
            return v3a(Mpa, temperature);
        else if (areatype.equals("3b"))
            return v3b(Mpa, temperature);
        else if (areatype.equals("3c"))
            return v3c(Mpa, temperature);
        else if (areatype.equals("3d"))
            return v3d(Mpa, temperature);
        else if (areatype.equals("3e"))
            return v3e(Mpa, temperature);
        else if (areatype.equals("3f"))
            return v3f(Mpa, temperature);
        else if (areatype.equals("3g"))
            return v3g(Mpa, temperature);
        else if (areatype.equals("3h"))
            return v3h(Mpa, temperature);
        else if (areatype.equals("3i"))
            return v3i(Mpa, temperature);
        else if (areatype.equals("3j"))
            return v3j(Mpa, temperature);
        else if (areatype.equals("3k"))
            return v3k(Mpa, temperature);
        else if (areatype.equals("3l"))
            return v3l(Mpa, temperature);
        else if (areatype.equals("3m"))
            return v3m(Mpa, temperature);
        else if (areatype.equals("3n"))
            return v3n(Mpa, temperature);
        else if (areatype.equals("3o"))
            return v3o(Mpa, temperature);
        else if (areatype.equals("3p"))
            return v3p(Mpa, temperature);
        else if (areatype.equals("3q"))
            return v3q(Mpa, temperature);
        else if (areatype.equals("3r"))
            return v3r(Mpa, temperature);
        else if (areatype.equals("3s"))
            return v3s(Mpa, temperature);
        else if (areatype.equals("3t"))
            return v3t(Mpa, temperature);
        else if (areatype.equals("3u"))
            return v3u(Mpa, temperature);
        else if (areatype.equals("3v"))
            return v3v(Mpa, temperature);
        else if (areatype.equals("3w"))
            return v3w(Mpa, temperature);
        else if (areatype.equals("3x"))
            return v3x(Mpa, temperature);
        else if (areatype.equals("3y"))
            return v3y(Mpa, temperature);
        else if (areatype.equals("3z"))
            return v3z(Mpa, temperature);
        else if (areatype.equals("4"))
            return vm(Mpa, temperature, x);
        else if (areatype.equals("5"))
            return v_5(Mpa, temperature);
        else
            return -1000;

    }

    private double v3l(double mpa, double temperature) {
        double v_star = 0.0026;
        double p_star = 24;
        double t_star = 650;
        double a = 0.908;
        double b = 0.989;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -12, -12, -12, -10, -10, -8, -8, -8, -8, -8, -8, -8, -6, -5, -5, -4, -4, -3, -3, -3, -3, -2, -2, -2, -1, -1, -1, 0, 0, 0, 0, 1, 1, 2, 4, 5, 5, 6, 10, 10, 14};
        int[] J = {14, 16, 18, 20, 22, 14, 24, 6, 10, 12, 14, 18, 24, 36, 8, 4, 5, 7, 16, 1, 3, 18, 20, 2, 3, 10, 0, 1, 3, 0, 1, 2, 12, 0, 16, 1, 0, 0, 1, 14, 4, 12, 10};
        double[] n = new double[43];
        n[0] = 2607020586.47537;
        n[1] = -188277213604704.0;
        n[2] = 5.54923870289667E+18;
        n[3] = -7.58966946387758E+22;
        n[4] = 4.13865186848908E+26;
        n[5] = -815038000738.06;
        n[6] = -3.81458260489955E+32;
        n[7] = -0.0123239564600519;
        n[8] = 22609563.1437174;
        n[9] = -495017809506.72;
        n[10] = 5.29482996422863E+15;
        n[11] = -4.44359478746295E+22;
        n[12] = 5.21635864527315E+34;
        n[13] = -4.87095672740742E+54;
        n[14] = -714430.209937547;
        n[15] = 0.127868634615495;
        n[16] = -10.0752127917598;
        n[17] = 7774514.3796099;
        n[18] = -1.08105480796471E+24;
        n[19] = -0.00000357578581169659;
        n[20] = -2.12857169423484;
        n[21] = 2.70706111085238E+29;
        n[22] = -6.95953622348829E+32;
        n[23] = 0.11060902747228;
        n[24] = 72.1559163361354;
        n[25] = -306367307532219.0;
        n[26] = 0.000026583961888553;
        n[27] = 0.0253392392889754;
        n[28] = -214.443041836579;
        n[29] = 0.937846601489667;
        n[30] = 2.231840431017;
        n[31] = 33.8401222509191;
        n[32] = 4.94237237179718E+20;
        n[33] = -0.198068404154428;
        n[34] = -1.4141534988114E+30;
        n[35] = -99.3862421613651;
        n[36] = 125.070534142731;
        n[37] = -996.473529004439;
        n[38] = 47313.7909872765;
        n[39] = 1.16662121219322E+32;
        n[40] = -3.15874976271533E+15;
        n[41] = -4.45703369196945E+32;
        n[42] = 6.42794932373694E+32;
        double x = 0;
        for (int i = 0; i < 43; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3m(double mpa, double temperature) {
        double v_star = 0.0028;
        double p_star = 23;
        double t_star = 650;
        double a = 1;
        double b = 0.997;
        int c = 1;
        int d = 4;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 3, 8, 20, 1, 3, 4, 5, 1, 6, 2, 4, 14, 2, 5, 3, 0, 1, 1, 1, 28, 2, 16, 0, 5, 0, 3, 4, 12, 16, 1, 8, 14, 0, 2, 3, 4, 8, 14, 24};
        int[] J = {0, 0, 0, 2, 5, 5, 5, 5, 6, 6, 7, 8, 8, 10, 10, 12, 14, 14, 18, 20, 20, 22, 22, 24, 24, 28, 28, 28, 28, 28, 32, 32, 32, 36, 36, 36, 36, 36, 36, 36};
        double[] n = new double[40];
        n[0] = 0.811384363481847;
        n[1] = -5681.99310990094;
        n[2] = -17865719817.2556;
        n[3] = 7.95537657613427E+31;
        n[4] = -81456.8209346872;
        n[5] = -65977456.7602874;
        n[6] = -15286114865.9302;
        n[7] = -560165667510.446;
        n[8] = 458384.828593949;
        n[9] = -38575400038384.8;
        n[10] = 45373580.0004273;
        n[11] = 939454935735.563;
        n[12] = 2.66572856432938E+27;
        n[13] = -5475783138.99097;
        n[14] = 200725701112386.0;
        n[15] = 1850072455632.39;
        n[16] = 185135446.828337;
        n[17] = -170451090076.385;
        n[18] = 157890366037614.0;
        n[19] = -2.02530509748774E+15;
        n[20] = 3.6819392618357E+59;
        n[21] = 1.70215539458936E+17;
        n[22] = 6.39234909918741E+41;
        n[23] = -821698160721956.0;
        n[24] = -7.95260241872306E+23;
        n[25] = 2.3341586947851002E+17;
        n[26] = -6.00079934586803E+22;
        n[27] = 5.94584382273384E+24;
        n[28] = 1.89461279349492E+39;
        n[29] = -8.10093428842645E+45;
        n[30] = 1.88813911076809E+21;
        n[31] = 1.11052244098768E+35;
        n[32] = 2.91133958602503E+45;
        n[33] = -3.2942192395146E+21;
        n[34] = -1.37570282536696E+25;
        n[35] = 1.81508996303902E+27;
        n[36] = -3.46865122768353E+29;
        n[37] = -2.1196114877426E+37;
        n[38] = -1.28617899887675E+48;
        n[39] = 4.79817895699239E+64;
        double x = 0;
        for (int i = 0; i < 43; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3k(double mpa, double temperature) {
        double v_star = 0.0077;
        double p_star = 25;
        double t_star = 680;
        double a = 0.802;
        double b = 0.935;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-2, -2, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 5, 5, 5, 6, 6, 6, 6, 8, 10, 12};
        int[] J = {10, 12, -5, 6, -12, -6, -2, -1, 0, 1, 2, 3, 14, -3, -2, 0, 1, 2, -8, -6, -3, -2, 0, 4, -12, -6, -3, -12, -10, -8, -5, -12, -12, -10};
        double[] n = new double[34];
        n[0] = -401215699.576099;
        n[1] = 48450147831.8406;
        n[2] = 0.00000000000000394721471363678;
        n[3] = 37262.9967374147;
        n[4] = -3.69794374168666E-30;
        n[5] = -0.00000000000000380436407012452;
        n[6] = 0.000000475361629970233;
        n[7] = -0.000879148916140706;
        n[8] = 0.844317863844331;
        n[9] = 12.24331626566;
        n[10] = -104.529634830279;
        n[11] = 589.702771277429;
        n[12] = -29102685116444.4;
        n[13] = 0.0000017034307284185;
        n[14] = -0.000277617606975748;
        n[15] = -3.44709605486686;
        n[16] = 22.1333862447095;
        n[17] = -194.646110037079;
        n[18] = 0.000000000000000808354639772825;
        n[19] = -0.000000000018084520914547;
        n[20] = -0.00000696664158132412;
        n[21] = -0.00181057560300994;
        n[22] = 2.55830298579027;
        n[23] = 3289.13873658481;
        n[24] = -1.73270241249904E-19;
        n[25] = -0.000000661876792558034;
        n[26] = -0.0039568892342125;
        n[27] = 6.04203299819132E-18;
        n[28] = -0.0000000000000400879935920517;
        n[29] = 0.00000000160751107464958;
        n[30] = 0.0000383719409025556;
        n[31] = -0.00000000000000649565446702457;
        n[32] = -0.00000000000149095328506;
        n[33] = 0.00000000541449377329581;
        double x = 0;
        for (int i = 0; i < 34; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3j(double mpa, double temperature) {
        double v_star = 0.0054;
        double p_star = 25;
        double t_star = 670;
        double a = 0.875;
        double b = 0.964;
        double c = 0.5;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 0, 0, 1, 1, 1, 2, 2, 3, 4, 4, 5, 5, 5, 6, 10, 12, 12, 14, 14, 14, 16, 18, 20, 20, 24, 24, 28, 28};
        int[] J = {-1, 0, 1, -2, -1, 1, -1, 1, -2, -2, 2, -3, -2, 0, 3, -6, -8, -3, -10, -8, -5, -10, -12, -12, -10, -12, -6, -12, -5};
        double[] n = new double[29];
        n[0] = -0.00011137131739554;
        n[1] = 1.00342892423685;
        n[2] = 5.30615581928979;
        n[3] = 0.00000179058760078792;
        n[4] = -0.000728541958464774;
        n[5] = -18.7576133371704;
        n[6] = 0.00199060874071849;
        n[7] = 24.357475537729;
        n[8] = -0.000177040785499444;
        n[9] = -0.0025968038522713;
        n[10] = -198.704578406823;
        n[11] = 0.0000738627790224287;
        n[12] = -0.00236264692844138;
        n[13] = -1.61023121314333;
        n[14] = 6223.22971786473;
        n[15] = -0.00000000960754116701669;
        n[16] = -0.0000000000510572269720488;
        n[17] = 0.00767373781404211;
        n[18] = 0.00000000000000663855469485254;
        n[19] = -0.000000000717590735526745;
        n[20] = 0.0000146564542926508;
        n[21] = 0.00000000000309029474277013;
        n[22] = -0.000000000000000464216300971708;
        n[23] = -0.0000000000000390499637961161;
        n[24] = -0.000000000236716126781431;
        n[25] = 0.00000000000454652854268717;
        n[26] = -0.00422271787482497;
        n[27] = 0.0000000000283911742354706;
        n[28] = 2.70929002720228;
        double x = 0;
        for (int i = 0; i < 29; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3i(double mpa, double temperature) {
        double v_star = 0.0041;
        double p_star = 25;
        double t_star = 660;
        double a = 0.94;
        double b = 0.984;
        double c = 0.5;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 0, 0, 1, 1, 1, 1, 2, 3, 3, 4, 4, 4, 5, 5, 5, 7, 7, 8, 8, 10, 12, 12, 12, 14, 14, 14, 14, 18, 18, 18, 18, 18, 20, 20, 22, 24, 24, 32, 32, 36, 36};
        int[] J = {0, 1, 10, -4, -2, -1, 0, 0, -5, 0, -3, -2, -1, -6, -1, 12, -4, -3, -6, 10, -8, -12, -6, -4, -10, -8, -4, 5, -12, -10, -8, -6, 2, -12, -10, -12, -12, -8, -10, -5, -10, -8};
        double[] n = new double[42];
        n[0] = 1.06905684359136;
        n[1] = -1.48620857922333;
        n[2] = 259862256980408.0;
        n[3] = -0.00000000000446352055678749;
        n[4] = -0.000000566620757170032;
        n[5] = -0.00235302885736849;
        n[6] = -0.269226321968839;
        n[7] = 9.22024992944392;
        n[8] = 0.00000000000357633505503772;
        n[9] = -17.3942565562222;
        n[10] = 0.00000700681785556229;
        n[11] = -0.000267050351075768;
        n[12] = -2.31779669675624;
        n[13] = -0.000000000000753533046979752;
        n[14] = 4.81337131452891;
        n[15] = -2.23286270422356E+21;
        n[16] = -0.0000118746004987383;
        n[17] = 0.00646412934136496;
        n[18] = -0.000000000410588536330937;
        n[19] = 4.22739537057241E+19;
        n[20] = 0.000000000000313698180473812;
        n[21] = 1.6439533434504E-24;
        n[22] = -0.00000339823323754373;
        n[23] = -0.0135268639905021;
        n[24] = -0.00000000000000723252514211625;
        n[25] = 0.00000000184386437538366;
        n[26] = -0.0463959533752385;
        n[27] = -99226310037675.0;
        n[28] = 6.88169154439335E-17;
        n[29] = -0.0000000000222620998452197;
        n[30] = -0.0000000540843018624083;
        n[31] = 0.00345570606200257;
        n[32] = 42227580030.4086;
        n[33] = -0.00000000000000126974478770487;
        n[34] = 0.000000000927237985153679;
        n[35] = 0.0000000000000612670812016489;
        n[36] = -0.00000000000722693924063497;
        n[37] = -0.000383669502636822;
        n[38] = 0.000374684572410204;
        n[39] = -93197.6897511086;
        n[40] = -0.0247690616026922;
        n[41] = 65.8110546759474;
        double x = 0;

        for (int i = 0; i < 43; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3h(double mpa, double temperature) {
        double v_star = 0.0032;
        double p_star = 25;
        double t_star = 660;
        double a = 0.898;
        double b = 0.983;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -10, -10, -10, -10, -10, -10, -8, -8, -8, -8, -8, -6, -6, -6, -5, -5, -5, -4, -4, -3, -3, -2, -1, -1, 0, 1, 1};
        int[] J = {8, 12, 4, 6, 8, 10, 14, 16, 0, 1, 6, 7, 8, 4, 6, 8, 2, 3, 4, 2, 4, 1, 2, 0, 0, 2, 0, 0, 2};
        double[] n = new double[29];
        n[0] = 0.0561379678887577;
        n[1] = 7741354215.87083;
        n[2] = 0.00000000111482975877938;
        n[3] = -0.00143987128208183;
        n[4] = 1936.9655876492;
        n[5] = -605971823.585005;
        n[6] = 17195156812433.7;
        n[7] = -1.85461154985145E+16;
        n[8] = 3.8785116807801E-17;
        n[9] = -0.0000000000000395464327846105;
        n[10] = -170.875935679023;
        n[11] = -2120.1062070122;
        n[12] = 17768333.7348191;
        n[13] = 11.0177443629575;
        n[14] = -234396.091693313;
        n[15] = -6561744.21999594;
        n[16] = 0.0000156362212977396;
        n[17] = -2.129462570214;
        n[18] = 13.5249306374858;
        n[19] = 0.177189164145813;
        n[20] = 1394.99167345464;
        n[21] = -0.00703670932036388;
        n[22] = -0.152011044389648;
        n[23] = 0.0000981916922991113;
        n[24] = 0.00147199658618076;
        n[25] = 20.2618487025578;
        n[26] = 0.89934551894424;
        n[27] = -0.211346402240858;
        n[28] = 24.9971752957491;
        double x = 0;

        for (int i = 0; i < 43; i++) {

            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3g(double mpa, double temperature) {
        double v_star = 0.0027;
        double p_star = 25;
        double t_star = 660;
        double a = 0.872;
        double b = 0.971;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -12, -12, -12, -12, -10, -10, -10, -8, -8, -8, -8, -6, -6, -5, -5, -4, -3, -2, -2, -2, -2, -1, -1, -1, 0, 0, 0, 1, 1, 1, 3, 5, 6, 8, 10, 10};
        int[] J = {7, 12, 14, 18, 22, 24, 14, 20, 24, 7, 8, 10, 12, 8, 22, 7, 20, 22, 7, 3, 5, 14, 24, 2, 8, 18, 0, 1, 2, 0, 1, 3, 24, 22, 12, 3, 0, 6};
        double[] n = new double[38];
        n[0] = 0.0000412209020652996;
        n[1] = -1149872.38280587;
        n[2] = 9481808850.3208;
        n[3] = -1.95788865718971E+17;
        n[4] = 4.962507048713E+24;
        n[5] = -1.05549884548496E+28;
        n[6] = -758642165988.278;
        n[7] = -9.22172769596101E+22;
        n[8] = 7.25379072059348E+29;
        n[9] = -61.7718249205859;
        n[10] = 10755.5033344858;
        n[11] = -37954580.2336487;
        n[12] = 228646846221.831;
        n[13] = -4997410.93010619;
        n[14] = -2.80214310054101E+30;
        n[15] = 1049154.06769586;
        n[16] = 6.13754229168619E+27;
        n[17] = 8.02056715528378E+31;
        n[18] = -29861781.9828065;
        n[19] = -91.0782540134681;
        n[20] = 135033.227281565;
        n[21] = -7.12949383408211E+18;
        n[22] = -1.04578785289542E+36;
        n[23] = 30.4331584444093;
        n[24] = 5932507979.59445;
        n[25] = -3.64174062110798E+27;
        n[26] = 0.921791403532461;
        n[27] = -0.337693609657471;
        n[28] = -72.4644143758508;
        n[29] = -0.110480239272601;
        n[30] = 5.36516031875059;
        n[31] = -2914.41872156205;
        n[32] = 6.16338176535305E+39;
        n[33] = -1.2088917586118E+38;
        n[34] = 8.18396024524612E+22;
        n[35] = 940781944.835829;
        n[36] = -36727.9669545448;
        n[37] = -8.37513931798655E+15;
        double x = 0;

        for (int i = 0; i < 38; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3f(double mpa, double temperature) {
        double v_star = 0.0064;
        double p_star = 40;
        double t_star = 730;
        double a = 0.587;
        double b = 0.891;
        double c = 0.5;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 5, 5, 6, 7, 7, 10, 12, 12, 12, 14, 14, 14, 14, 14, 16, 16, 18, 18, 20, 20, 20, 22, 24, 24, 28, 32};
        int[] J = {-3, -2, -1, 0, 1, 2, -1, 1, 2, 3, 0, 1, -5, -2, 0, -3, -8, 1, -6, -4, 1, -6, -10, -8, -4, -12, -10, -8, -6, -4, -10, -8, -12, -10, -12, -10, -6, -12, -12, -4, -12, -12};
        double[] n = new double[42];
        n[0] = -0.0000000251756547792325;
        n[1] = 0.00000601307193668763;
        n[2] = -0.00100615977450049;
        n[3] = 0.999969140252192;
        n[4] = 2.14107759236486;
        n[5] = -16.5175571959086;
        n[6] = -0.00141987303638727;
        n[7] = 2.69251915156554;
        n[8] = 34.9741815858722;
        n[9] = -30.0208695771783;
        n[10] = -1.31546288252539;
        n[11] = -8.39091277286169;
        n[12] = 0.000000000181545608337015;
        n[13] = -0.000591099206478909;
        n[14] = 1.52115067087106;
        n[15] = 0.0000252956470663225;
        n[16] = 0.00000000000000100726265203786;
        n[17] = -1.4977453386065;
        n[18] = -0.000000000793940970562969;
        n[19] = -0.000150290891264717;
        n[20] = 1.51205531275133;
        n[21] = 0.00000470942606221652;
        n[22] = 0.000000000000195049710391712;
        n[23] = -0.00000000911627886266077;
        n[24] = 0.000604374640201265;
        n[25] = -0.000000000000000225132933900136;
        n[26] = 0.00000000000610916973582981;
        n[27] = -0.000000303063908043404;
        n[28] = -0.0000137796070798409;
        n[29] = -0.000919296736666106;
        n[30] = 0.000000000639288223132545;
        n[31] = 0.000000753259479898699;
        n[32] = -0.000000000000400321478682929;
        n[33] = 0.00000000756140294351614;
        n[34] = -0.00000000000912082054034891;
        n[35] = -0.0000000237612381140539;
        n[36] = 0.0000269586010591874;
        n[37] = -0.0000000000732828135157839;
        n[38] = 0.00000000024199557830666;
        n[39] = -0.000405735532730322;
        n[40] = 0.000000000189424143498011;
        n[41] = -0.000000000486632965074563;
        double x = 0;
        for (int i = 0; i < 42; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3e(double mpa, double temperature) {
        double v_star = 0.0032;
        double p_star = 40;
        double t_star = 710;
        double a = 0.587;
        double b = 0.918;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -10, -10, -10, -10, -10, -8, -8, -8, -6, -5, -4, -4, -3, -3, -3, -2, -2, -2, -2, -1, 0, 0, 1, 1, 1, 2, 2};
        int[] J = {14, 16, 3, 6, 10, 14, 16, 7, 8, 10, 6, 6, 2, 4, 2, 6, 7, 0, 1, 3, 4, 0, 0, 1, 0, 4, 6, 0, 2};
        double[] n = new double[29];
        n[0] = 715815808.404721;
        n[1] = -114328360753.449;
        n[2] = 0.0000000000037653100201572;
        n[3] = -0.0000903983668691157;
        n[4] = 665695.908836252;
        n[5] = 5353641749.60127;
        n[6] = 79497740233.5603;
        n[7] = 92.2230563421437;
        n[8] = -142586.073991215;
        n[9] = -1117963.81424162;
        n[10] = 8961.2162964076;
        n[11] = -6699.89239070491;
        n[12] = 0.00451242538486834;
        n[13] = -33.9731325977713;
        n[14] = -1.20523111552278;
        n[15] = 47599.2667717124;
        n[16] = -266627.750390341;
        n[17] = -0.000153314954386524;
        n[18] = 0.305638404828265;
        n[19] = 123.654999499486;
        n[20] = -1043.90794213011;
        n[21] = -0.0157496516174308;
        n[22] = 0.685331118940253;
        n[23] = 1.78373462873903;
        n[24] = -0.54467412487891;
        n[25] = 2045.29931318843;
        n[26] = -22834.2359328752;
        n[27] = 0.413197481515899;
        n[28] = -34.1931835910405;
        double x = 0;
        for (int i = 0; i < 29; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3d(double mpa, double temperature) {

        double v_star = 0.0029;
        double p_star = 40;
        double t_star = 690;
        double a = 0.559;
        double b = 0.939;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -12, -12, -12, -12, -10, -10, -10, -10, -10, -10, -10, -8, -8, -8, -8, -6, -6, -5, -5, -5, -5, -4, -4, -4, -3, -3, -2, -2, -1, -1, -1, 0, 0, 1, 1, 3};
        int[] J = {4, 6, 7, 10, 12, 16, 0, 2, 4, 6, 8, 10, 14, 3, 7, 8, 10, 6, 8, 1, 2, 5, 7, 0, 1, 7, 2, 4, 0, 1, 0, 1, 5, 0, 2, 0, 6, 0};
        double[] n = new double[38];
        n[0] = -0.000000000452484847171645;
        n[1] = 0.0000315210389538801;
        n[2] = -0.00214991352047545;
        n[3] = 508.058874808345;
        n[4] = -12712303.6845932;
        n[5] = 1153711331204.97;
        n[6] = -0.000000000000000197805728776273;
        n[7] = 0.0000000000241554806033972;
        n[8] = -0.00000156481703640525;
        n[9] = 0.00277211346836625;
        n[10] = -20.3578994462286;
        n[11] = 1443694.89909053;
        n[12] = -41125421794.6539;
        n[13] = 0.00000623449786243773;
        n[14] = -22.1774281146038;
        n[15] = -68931.5087933158;
        n[16] = -19541952.5060713;
        n[17] = 3163.73510564015;
        n[18] = 2240407.54426988;
        n[19] = -0.00000436701347922356;
        n[20] = -0.000404213852833996;
        n[21] = -348.153203414663;
        n[22] = -385294.213555289;
        n[23] = 0.000000135203700099403;
        n[24] = 0.000134648383271089;
        n[25] = 125031.835351736;
        n[26] = 0.0968123678455841;
        n[27] = 225.660517512438;
        n[28] = -0.000190102435341872;
        n[29] = -0.0299628410819229;
        n[30] = 0.00500833915372121;
        n[31] = 0.387842482998411;
        n[32] = -1385.35367777182;
        n[33] = 0.870745245971773;
        n[34] = 1.71946252068742;
        n[35] = -0.0326650121426383;
        n[36] = 4980.44171727877;
        n[37] = 0.00551478022765087;
        double x = 0;
        for (int i = 0; i < 38; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3c(double mpa, double temperature) {

        double v_star = 0.0022;
        double p_star = 40;
        double t_star = 690;
        double a = 0.259;
        double b = 0.903;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -12, -10, -10, -10, -8, -8, -8, -6, -5, -5, -5, -4, -4, -3, -3, -2, -2, -2, -1, -1, -1, 0, 0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 8};
        int[] J = {6, 8, 10, 6, 8, 10, 5, 6, 7, 8, 1, 4, 7, 2, 8, 0, 3, 0, 4, 5, 0, 1, 2, 0, 1, 2, 0, 2, 0, 1, 3, 7, 0, 7, 1};
        double[] n = new double[35];
        n[0] = 3.1196778876303;
        n[1] = 27671.3458847564;
        n[2] = 32258310.3403269;
        n[3] = -342.416065095363;
        n[4] = -899732.529907377;
        n[5] = -79389204.9821251;
        n[6] = 95.3193003217388;
        n[7] = 2297.84742345072;
        n[8] = 175336.675322499;
        n[9] = 7912143.65222792;
        n[10] = 0.0000319933345844209;
        n[11] = -65.9508863555767;
        n[12] = -833426.563212851;
        n[13] = 0.0645734680583292;
        n[14] = -3820310.20570813;
        n[15] = 0.0000406398848470079;
        n[16] = 31.0327498492008;
        n[17] = -0.000892996718483724;
        n[18] = 234.604891591616;
        n[19] = 3775.15668966951;
        n[20] = 0.0158646812591361;
        n[21] = 0.707906336241843;
        n[22] = 12.601622514657;
        n[23] = 0.736143655772152;
        n[24] = 0.676544268999101;
        n[25] = -17.8100588189137;
        n[26] = -0.156531975531713;
        n[27] = 11.7707430048158;
        n[28] = 0.0840143653860447;
        n[29] = -0.186442467471949;
        n[30] = -44.0170203949645;
        n[31] = 1232904.23502494;
        n[32] = -0.0240650039730845;
        n[33] = -1070777.16660869;
        n[34] = 0.0438319858566475;
        double x = 0;
        for (int i = 0; i < 35; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x,e) * v_star;
        return x;
    }

    private double v3n(double mpa, double temperature) {
        double v_star = 0.0031;
        double p_star = 23;
        double t_star = 650;
        double a = 0.976;
        double b = 0.997;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 3, 4, 6, 7, 10, 12, 14, 18, 0, 3, 5, 6, 8, 12, 0, 3, 7, 12, 2, 3, 4, 2, 4, 7, 4, 3, 5, 6, 0, 0, 3, 1, 0, 1, 0, 1, 0, 1};
        int[] J = {-12, -12, -12, -12, -12, -12, -12, -12, -12, -10, -10, -10, -10, -10, -10, -8, -8, -8, -8, -6, -6, -6, -5, -5, -5, -4, -3, -3, -3, -2, -1, -1, 0, 1, 1, 2, 4, 5, 6};
        double[] n = new double[39];
        n[0] = 2.80967799943151E-39;
        n[1] = 6.14869006573609E-31;
        n[2] = 5.82238667048942E-28;
        n[3] = 3.90628369238462E-23;
        n[4] = 8.21445758255119E-21;
        n[5] = 0.00000000000000402137961842776;
        n[6] = 0.000000000000651718171878301;
        n[7] = -0.0000000211773355803058;
        n[8] = 0.00264953354380072;
        n[9] = -1.35031446451331E-32;
        n[10] = -6.07246643970893E-24;
        n[11] = -4.02352115234494E-19;
        n[12] = -7.44938506925544E-17;
        n[13] = 0.000000000000189917206526237;
        n[14] = 0.00000364975183508473;
        n[15] = 1.77274872361946E-26;
        n[16] = -3.34952758812999E-19;
        n[17] = -0.00000000421537726098389;
        n[18] = -0.0391048167929649;
        n[19] = 0.0000000000000541276911564176;
        n[20] = 0.00000000000705412100773699;
        n[21] = 0.00000000258585887897486;
        n[22] = -0.0000000000493111362030162;
        n[23] = -0.00000158649699894543;
        n[24] = -0.5250374278861;
        n[25] = 0.00220019901729615;
        n[26] = -0.00643064132636925;
        n[27] = 62.9154149015048;
        n[28] = 135.147318617061;
        n[29] = 0.000000240560808321713;
        n[30] = -0.000890763306701305;
        n[31] = -4402.09599407714;
        n[32] = -302.807107747776;
        n[33] = 1591.58748314599;
        n[34] = 232534.272709876;
        n[35] = -792681.2071326;
        n[36] = -86987136466.2769;
        n[37] = 354542769185.671;
        n[38] = 400849240129329.0;
        double x = 0;
        for (int i = 0; i < 35; i++) {
            x = x + n[i] * Math.pow(pi - a, I[i]) * Math.pow(theta - b, J[i]);
        }
        x = Math.exp(x) * (v_star);
        return x;
    }

    private double v3q(double mpa, double temperature) {
        double v_star = 0.0022;
        double p_star = 23;
        double t_star = 650;
        double a = 0.848;
        double b = 0.983;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -10, -10, -10, -10, -8, -6, -5, -5, -4, -4, -3, -2, -2, -2, -2, -1, -1, -1, 0, 1, 1, 1};
        int[] J = {10, 12, 6, 7, 8, 10, 8, 6, 2, 5, 3, 4, 3, 0, 1, 2, 4, 0, 1, 2, 0, 0, 1, 3};
        double[] n = new double[24];
        n[0] = -82043.384325995;
        n[1] = 47327151846.1586;
        n[2] = -0.0805950021005413;
        n[3] = 32.860002543598;
        n[4] = -3566.1702998249;
        n[5] = -1729857814.33335;
        n[6] = 35176923.2729192;
        n[7] = -775489.259985144;
        n[8] = 0.0000710346691966018;
        n[9] = 99349.9883820274;
        n[10] = -0.64209417190457;
        n[11] = -6128.42816820083;
        n[12] = 232.808472983776;
        n[13] = -0.0000142808220416837;
        n[14] = -0.00643596060678456;
        n[15] = -4.28577227475614;
        n[16] = 2256.89939161918;
        n[17] = 0.0010035565172151;
        n[18] = 0.333491455143516;
        n[19] = 1.09697576888873;
        n[20] = 0.961917379376452;
        n[21] = -0.0838165632204598;
        n[22] = 2.47795908411492;
        n[23] = -3191.14969006533;
        double x = 0;

        for (int i = 0; i < 24; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3s(double mpa, double temperature) {

        double v_star = 0.0022;
        double p_star = 21;
        double t_star = 640;
        double a = 0.886;
        double b = 0.99;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -10, -8, -6, -5, -5, -4, -4, -3, -3, -2, -1, -1, -1, 0, 0, 0, 0, 1, 1, 3, 3, 3, 4, 4, 4, 5, 14};
        int[] J = {20, 24, 22, 14, 36, 8, 16, 6, 32, 3, 8, 4, 1, 2, 3, 0, 1, 4, 28, 0, 32, 0, 1, 2, 3, 18, 24, 4, 24};
        double[] n = new double[29];
        n[0] = -5.32466612140254E+22;
        n[1] = 1.00415480000824E+31;
        n[2] = -1.91540001821367E+29;
        n[3] = 1.05618377808847E+16;
        n[4] = 2.02281884477061E+58;
        n[5] = 88458547.2596134;
        n[6] = 1.66540181638363E+22;
        n[7] = -313563.197669111;
        n[8] = -1.85662327545324E+53;
        n[9] = -0.0624942093918942;
        n[10] = -5041607241.3259;
        n[11] = 18751.4491833092;
        n[12] = 0.00121399979993217;
        n[13] = 1.88317043049455;
        n[14] = -1670.7350396206;
        n[15] = 0.965961650599775;
        n[16] = 2.94885696802488;
        n[17] = -65391.5627346115;
        n[18] = 6.04012200163444E+49;
        n[19] = -0.198339358557937;
        n[20] = -1.75984090163501E+57;
        n[21] = 3.56314881403987;
        n[22] = -575.991255144384;
        n[23] = 45621.3415338071;
        n[24] = -10917404.4987829;
        n[25] = 4.37796099975134E+33;
        n[26] = -6.16552611135792E+45;
        n[27] = 1935687689.17797;
        n[28] = 9.50898170425042E+53;
        double x = 0;
        for (int i = 0; i < 29; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3t(double mpa, double temperature) {
        double v_star = 0.0088;
        double p_star = 20;
        double t_star = 650;
        double a = 0.803;
        double b = 1.02;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 7, 7, 7, 7, 7, 10, 10, 10, 10, 10, 18, 20, 22, 22, 24, 28, 32, 32, 32, 36};
        int[] J = {0, 1, 4, 12, 0, 10, 0, 6, 14, 3, 8, 0, 10, 3, 4, 7, 20, 36, 10, 12, 14, 16, 22, 18, 32, 22, 36, 24, 28, 22, 32, 36, 36};
        double[] n = new double[33];
        n[0] = 1.55287249586268;
        n[1] = 6.64235115009031;
        n[2] = -2893.6623672721;
        n[3] = -3859232023098.48;
        n[4] = -2.91002915783761;
        n[5] = -829088246858.083;
        n[6] = 1.768148996752188;
        n[7] = -534686695.713469;
        n[8] = 1.60464608687834E+17;
        n[9] = 196435.366560186;
        n[10] = 1566374275417.29;
        n[11] = -1.78154560260006;
        n[12] = -2.29746237623692E+15;
        n[13] = 38565900.1648006;
        n[14] = 1105544467.90543;
        n[15] = -67707383068734.9;
        n[16] = -3.27910592086523E+30;
        n[17] = -3.41552040860644E+50;
        n[18] = -5.27251339709047E+20;
        n[19] = 2.45375640937055E+23;
        n[20] = -1.68776617209269E+26;
        n[21] = 3.58958955867578E+28;
        n[22] = -6.56475280339411E+35;
        n[23] = 3.55286045512301E+38;
        n[24] = 5.6902145441327E+57;
        n[25] = -7.00584546433113E+47;
        n[26] = -7.05772623326374E+64;
        n[27] = 1.66861176200148E+52;
        n[28] = -3.00475129680486E+60;
        n[29] = -6.68481295196808E+50;
        n[30] = 4.28432338620678E+68;
        n[31] = -4.44227367758304E+71;
        n[32] = -2.81396013562745E+76;
        double x = 0;
        for (int i = 0; i < 33; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * v_star;
        return x;
    }

    private double v3o(double mpa, double temperature) {
        double v_star = 0.0034;
        double p_star = 23;
        double t_star = 650;
        double a = 0.974;
        double b = 0.996;
        double c = 0.5;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 0, 0, 2, 3, 4, 4, 4, 4, 4, 5, 5, 6, 7, 8, 8, 8, 10, 10, 14, 14, 20, 20, 24};
        int[] J = {-12, -4, -1, -1, -10, -12, -8, -5, -4, -1, -4, -3, -8, -12, -10, -8, -4, -12, -8, -12, -8, -12, -10, -12};
        double[] n = new double[24];
        n[0] = 1.28746023979718E-35;
        n[1] = -0.00000000000735234770382342;
        n[2] = 0.0028907869214915;
        n[3] = 0.244482731907223;
        n[4] = 1.41733492030985E-24;
        n[5] = -3.54533853059476E-29;
        n[6] = -5.94539202901431E-18;
        n[7] = -0.00000000585188401782779;
        n[8] = 0.00000201377325411803;
        n[9] = 1.38647388209306;
        n[10] = -0.0000173959365084772;
        n[11] = 0.00137680878349369;
        n[12] = 0.00000000000000814897605805513;
        n[13] = 4.25596631351839E-26;
        n[14] = -3.87449113787755E-18;
        n[15] = 0.00000000000013981474793024;
        n[16] = -0.00171849638951521;
        n[17] = 6.41890529513296E-22;
        n[18] = 0.0000000000118960578072018;
        n[19] = -1.55282762571611E-18;
        n[20] = 0.0000000233907907347507;
        n[21] = -0.000000000000174093247766213;
        n[22] = 0.00000000377682649089149;
        n[23] = -0.0000000000516720236575302;
        double x = 0;

        for (int i = 0; i < 24; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3r(double mpa, double temperature) {
        double v_star = 0.0054;
        double p_star = 23;
        double t_star = 650;
        double a = 0.874;
        double b = 0.982;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-8, -8, -3, -3, -3, -3, -3, 0, 0, 0, 0, 3, 3, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 12, 14};
        int[] J = {6, 14, -3, 3, 4, 5, 8, -1, 0, 1, 5, -6, -2, -12, -10, -8, -5, -12, -10, -8, -6, -5, -4, -3, -2, -12, -12};
        double[] n = new double[27];
        n[0] = 0.00144165955660863;
        n[1] = -7014385996282.58;
        n[2] = -8.30946716459219E-17;
        n[3] = 0.261975135368109;
        n[4] = 393.097214706245;
        n[5] = -10433.4030654021;
        n[6] = 490112654.154211;
        n[7] = -0.000147104222772069;
        n[8] = 1.03602748043408;
        n[9] = 3.05308890065089;
        n[10] = -3997452.76971264;
        n[11] = 0.0000000000056923371959375;
        n[12] = -0.0464923504407778;
        n[13] = -5.35400396512906E-18;
        n[14] = 0.000000000000399988795693162;
        n[15] = -0.000000536479560201811;
        n[16] = 0.0159536722411202;
        n[17] = 0.00000000000000270303248860217;
        n[18] = 0.0000000244247453858506;
        n[19] = -0.00000983430636716454;
        n[20] = 0.0663513144224454;
        n[21] = -9.93456957845006;
        n[22] = 546.491323528491;
        n[23] = -14336.5406393758;
        n[24] = 150764.974125511;
        n[25] = -0.000000000337209709340105;
        n[26] = 0.00000000377501980025469;
        double x = 0;

        for (int i = 0; i < 27; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3u(double mpa, double temperature) {
        double v_star = 0.0026;
        double p_star = 23;
        double t_star = 650;
        double a = 0.902;
        double b = 0.988;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -10, -10, -10, -8, -8, -8, -6, -6, -5, -5, -5, -3, -1, -1, -1, -1, 0, 0, 1, 2, 2, 3, 5, 5, 5, 6, 6, 8, 8, 10, 12, 12, 12, 14, 14, 14, 14};
        int[] J = {14, 10, 12, 14, 10, 12, 14, 8, 12, 4, 8, 12, 2, -1, 1, 12, 14, -3, 1, -2, 5, 10, -5, -4, 2, 3, -5, 2, -8, 8, -4, -12, -4, 4, -12, -10, -6, 6};
        double[] n = new double[38];
        n[0] = 1.2208834925835501E+17;
        n[1] = 1042164686.08488;
        n[2] = -8.82666931564652E+15;
        n[3] = 2.59929510849499E+19;
        n[4] = 222612779142211.0;
        n[5] = -8.78473585050085E+17;
        n[6] = -3.14432577551552E+21;
        n[7] = -2169349169962.85;
        n[8] = 1.59079648196849E+20;
        n[9] = -339.567617303423;
        n[10] = 8843876513378.36;
        n[11] = -8.43405926846418E+20;
        n[12] = 11.4178193518022;
        n[13] = -0.000122708229235641;
        n[14] = -106.201671767107;
        n[15] = 9.03443213959313E+24;
        n[16] = -6.93996270370852E+27;
        n[17] = 0.00000000648916718965575;
        n[18] = 7189.57567127851;
        n[19] = 0.00105581745346187;
        n[20] = -651903203602581.0;
        n[21] = -1.60116813274676E+24;
        n[22] = -0.00000000510254294237837;
        n[23] = -0.152355388953402;
        n[24] = 677143292290.144;
        n[25] = 276378438378930.0;
        n[26] = 0.0116862983141686;
        n[27] = -30142694798017.1;
        n[28] = 0.000000016971981388484;
        n[29] = 1.04674840020929E+26;
        n[30] = -10801.690456014;
        n[31] = -0.000000000000990623601934295;
        n[32] = 5361164.83602738;

        n[33] = 2.26145963747881E+21;
        n[34] = -0.00000000048873156577621;
        n[35] = 0.000015100154888067;
        n[36] = -22770.046464392;
        n[37] = -7.81754507698846E+27;

        double x = 0;
        for (int i = 0; i < 38; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3p(double mpa, double temperature) {
        double v_star = 0.0041;
        double p_star = 23;
        double t_star = 650;
        double a = 0.972;
        double b = 0.997;
        double c = 0.5;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 0, 0, 0, 1, 2, 3, 3, 4, 6, 7, 7, 8, 10, 12, 12, 12, 14, 14, 14, 16, 18, 20, 22, 24, 24, 36};
        int[] J = {-1, 0, 1, 2, 1, -1, -3, 0, -2, -2, -5, -4, -2, -3, -12, -6, -5, -10, -8, -3, -8, -8, -10, -10, -12, -8, -12};
        double[] n = new double[27];
        n[0] = -0.0000982825342010366;
        n[1] = 1.05145700850612;
        n[2] = 116.033094095084;
        n[3] = 3246.64750281543;
        n[4] = -1235.92348610137;
        n[5] = -0.0561403450013495;
        n[6] = 0.0000000856677401640869;
        n[7] = 236.313425393924;
        n[8] = 0.00972503292350109;
        n[9] = -1.03001994531927;
        n[10] = -0.00000000149653706199162;
        n[11] = -0.0000215743778861592;
        n[12] = -8.34452198291445;
        n[13] = 0.586602660564988;
        n[14] = 3.43480022104968E-26;
        n[15] = 0.00000816256095947021;
        n[16] = 0.00294985697916798;
        n[17] = 7.11730466276584E-17;
        n[18] = 0.000000000400954763806941;
        n[19] = 10.7766027032853;
        n[20] = -0.000000409449599138182;
        n[21] = -0.00000729121307758902;
        n[22] = 0.00000000677107970938909;
        n[23] = 0.0000000602745973022975;
        n[24] = -0.0000000000382323011855257;
        n[25] = 0.00179946628317437;
        n[26] = -0.000345042834640005;
        double x = 0;

        for (int i = 0; i < 27; i++) {

            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3v(double mpa, double temperature) {
        double v_star = 0.0031;
        double p_star = 23;
        double t_star = 650;
        double a = 0.96;
        double b = 0.995;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-10, -8, -6, -6, -6, -6, -6, -6, -5, -5, -5, -5, -5, -5, -4, -4, -4, -4, -3, -3, -3, -2, -2, -1, -1, 0, 0, 0, 1, 1, 3, 4, 4, 4, 5, 8, 10, 12, 14};
        int[] J = {-8, -12, -12, -3, 5, 6, 8, 10, 1, 2, 6, 8, 10, 14, -12, -10, -6, 10, -3, 10, 12, 2, 4, -2, 0, -2, 6, 10, -12, -10, 3, -6, 3, 10, 2, -12, -2, -3, 1};
        double[] n = new double[39];
        n[0] = -4.15652812061591E-55;
        n[1] = 1.77441742924043E-61;
        n[2] = -3.57078668203377E-55;
        n[3] = 3.59252213604114E-26;
        n[4] = -25.9123736380269;
        n[5] = 59461.976619346;
        n[6] = -62418400710.3158;
        n[7] = 3.13080299915944E+16;
        n[8] = 0.00000000105006446192036;
        n[9] = -0.00000192824336984852;
        n[10] = 654144.373749937;
        n[11] = 5131174628650.44;
        n[12] = -6.97595750347391E+18;
        n[13] = -1.03977184454767E+28;
        n[14] = 1.19563135540666E-48;
        n[15] = -4.36677034051655E-42;
        n[16] = 9.26990036530639E-30;
        n[17] = 5.87793105620748E+20;
        n[18] = 2.80375725094731E-18;
        n[19] = -1.92359972440634E+22;
        n[20] = 7.42705723302738E+26;
        n[21] = -51.7429682450605;
        n[22] = 8206120.48645469;
        n[23] = -0.00000000188214882341448;
        n[24] = 0.0184587261114837;
        n[25] = -0.00000135830407782663;
        n[26] = -7.23681885626348E+16;
        n[27] = -2.23449194054124E+26;
        n[28] = -1.11526741826431E-35;
        n[29] = 2.76032601145151E-29;
        n[30] = 134856491567853.0;
        n[31] = 0.00000000065244029334586;
        n[32] = 5.1065511977436E+16;

        n[33] = -4.68138358908732E+31;
        n[34] = -7.60667491183279E+15;
        n[35] = -4.17247986986821E-19;
        n[36] = 31254567775610.4;
        n[37] = -100375333864186.0;
        n[38] = 2.47761392329058E+26;
        double x = 0;

        for (int i = 0; i < 39; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3w(double mpa, double temperature) {
        double v_star = 0.0039;
        double p_star = 23;
        double t_star = 650;
        double a = 0.959;
        double b = 0.995;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -10, -10, -8, -8, -8, -6, -6, -6, -6, -5, -4, -4, -3, -3, -2, -2, -1, -1, -1, 0, 0, 1, 2, 2, 3, 3, 5, 5, 5, 8, 8, 10, 10};
        int[] J = {8, 14, -1, 8, 6, 8, 14, -4, -3, 2, 8, -10, -1, 3, -10, 3, 1, 2, -8, -4, 1, -12, 1, -1, -1, 2, -12, -5, -10, -8, -6, -12, -10, -12, -8};
        double[] n = new double[35];
        n[0] = -0.0000000586219133817016;
        n[1] = -89446035500.5526;
        n[2] = 5.31168037519774E-31;
        n[3] = 0.109892402329239;
        n[4] = -0.0575368389425212;
        n[5] = 22827.6853990249;
        n[6] = -1.58548609655002E+18;
        n[7] = 3.29865748576503E-28;
        n[8] = -6.34987981190669E-25;
        n[9] = 0.00000000615762068640611;
        n[10] = -96110924.0985747;
        n[11] = -4.06274286652625E-45;
        n[12] = -0.000000000000471103725498077;
        n[13] = 0.725937724828145;
        n[14] = 1.87768525763682E-39;
        n[15] = -1033.08436323771;
        n[16] = -0.0662552816342168;
        n[17] = 579.51404176571;
        n[18] = 2.37416732616644E-27;
        n[19] = 0.00000000000000271700235739893;
        n[20] = -90.78862134836;
        n[21] = -1.71242509570207E-37;
        n[22] = 156.792067854621;
        n[23] = 0.92326135790147;
        n[24] = -5.97865988422577;
        n[25] = 3219887.67636389;
        n[26] = -3.99441390042203E-30;
        n[27] = 0.0000000493429086046981;
        n[28] = 8.12036983370565E-20;
        n[29] = -0.00000000000207610284654137;
        n[30] = -0.000000340821291419719;
        n[31] = 5.42000573372233E-18;
        n[32] = -0.000000000000856711586510214;

        n[33] = 0.0000000000000266170454405981;
        n[34] = 0.00000858133791857099;
        double x = 0;

        for (int i = 0; i < 35; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3x(double mpa, double temperature) {
        double v_star = 0.0049;
        double p_star = 23;
        double t_star = 650;
        double a = 0.91;
        double b = 0.988;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-8, -6, -5, -4, -4, -4, -3, -3, -1, 0, 0, 0, 1, 1, 2, 3, 3, 3, 4, 5, 5, 5, 6, 8, 8, 8, 8, 10, 12, 12, 12, 12, 14, 14, 14, 14};
        int[] J = {14, 10, 10, 1, 2, 14, -2, 12, 5, 0, 4, 10, -10, -1, 6, -12, 0, 8, 3, -6, -2, 1, 1, -6, -3, 1, 8, -8, -10, -8, -5, -4, -12, -10, -8, -6};
        double[] n = new double[36];
        n[0] = 3.77373741298151E+18;
        n[1] = -5071008837229.13;
        n[2] = -1.0336322559886E+15;
        n[3] = 0.00000184790814320773;
        n[4] = -0.000924729378390945;
        n[5] = -4.25999562292738E+23;
        n[6] = -0.000000000000462307771873973;
        n[7] = 1.07319065855767E+21;
        n[8] = 64866249228.0682;
        n[9] = 2.44200600688281;
        n[10] = -8515357334.84258;
        n[11] = 1.69894481433592E+21;
        n[12] = 2.1578022250902E-27;
        n[13] = -0.320850551367334;
        n[14] = -3.8264244845861E+16;
        n[15] = -2.75386077674421E-29;
        n[16] = -563199.253391666;
        n[17] = -3.26068646279314E+20;
        n[18] = 39794900155318.4;
        n[19] = 0.000000100824008584757;
        n[20] = 16223.4569738433;
        n[21] = -43235522531.9745;
        n[22] = -592874245598.61;
        n[23] = 1.33061647281106;
        n[24] = 1573381.97797544;
        n[25] = 25818961427085.3;
        n[26] = 2.62413209706358E+24;
        n[27] = -0.0920011937431142;
        n[28] = 0.00220213765905426;
        n[29] = -11.0433759109547;
        n[30] = 8470048.70612087;
        n[31] = -592910695.762536;
        n[32] = -0.000018302717326966;

        n[33] = 0.181339603516302;
        n[34] = -1192.28759669889;
        n[35] = 4308676.58061468;
        double x = 0;

        for (int i = 0; i < 36; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3y(double mpa, double temperature) {
        double v_star = 0.0031;
        double p_star = 22;
        double t_star = 650;
        double a = 0.996;
        double b = 0.994;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {0, 0, 0, 0, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 8, 8, 10, 12};
        int[] J = {-3, 1, 5, 8, 8, -4, -1, 4, 5, -8, 4, 8, -6, 6, -2, 1, -8, -2, -5, -8};
        double[] n = new double[20];
        n[0] = -0.000000000525597995024633;
        n[1] = 5834.41305228407;
        n[2] = -1.34778968457925E+16;
        n[3] = 1.18973500934212E+25;
        n[4] = -1.59096490904708E+26;
        n[5] = -0.000000315839902302021;
        n[6] = 496.212197158239;
        n[7] = 3.27777227273171E+18;
        n[8] = -5.27114657850696E+21;
        n[9] = 2.10017506281863E-17;
        n[10] = 7.05106224399834E+20;
        n[11] = -2.66713136106469E+30;
        n[12] = -0.0000000145370512554562;
        n[13] = 1.4933391705313E+27;
        n[14] = -14979562.0287641;
        n[15] = -3.818819062711E+15;
        n[16] = 0.0000724660165585797;
        n[17] = -93780816955019.3;
        n[18] = 5144114683.76383;
        n[19] = -82819.8594040141;

        double x = 0;

        for (int i = 0; i < 20; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double vm(double mpa, double temperature, double x) {
        double vm=-1000;
        if (Math.abs(temperature - 350) < 0.0001) {
            temperature = 350;
        }

        if (temperature <= 350) {
            vm= v_1(mpa, temperature) * (1 - x) + v_2(mpa, temperature) * x;
        }
        else{if (mpa <= 19.0088118917393)
            vm= v3c(mpa, temperature) * (1 - x) + v3t(mpa, temperature) * x;
        else if (mpa > 19.0088118917393 && mpa <= 20.5)
            vm= v3s(mpa, temperature) * (1 - x) + v3t(mpa, temperature) * x;
        else if (mpa > 20.5 && mpa <= 21.04336731898)
            vm= v3s(mpa, temperature) * (1 - x) + v3r(mpa, temperature) * x;
        else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
            vm= v3u(mpa, temperature) * (1 - x) + v3x(mpa, temperature) * x;
        } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
            vm= v3u(mpa, temperature) * (1 - x) + v3z(mpa, temperature) * x;
        } else if (mpa > 21.93161551 && mpa <= 22.064) {
            vm= v3y(mpa, temperature) * (1 - x) + v3z(mpa, temperature) * x;
        }}
        return vm;
    }

    private double v_5(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = pi * (Gamma_o_pi_5(mpa, temperature) + Gamma_r_pi_5(mpa, temperature)) * T * R / mpa / 1000;
        return x;
    }

    private double Gamma_r_pi_5(double mpa, double temperature) {
        int[] I = {1, 1, 1, 2, 2, 3};
        int[] J = {1, 2, 3, 3, 9, 7};
        double[] n = new double[6];
        n[0] = 0.0015736404855259;
        n[1] = 0.00090153761673944;
        n[2] = -0.0050270077677648;
        n[3] = 0.0000022440037409485;
        n[4] = -0.0000041163275453471;
        n[5] = 0.000000037919454822955;
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        for (int i = 0; i < n.length; i++) {
            double y = Math.pow(pi, I[i] - 1);
            double z = Math.pow(tau, J[i]);
            x = x + n[i] * I[i] * y * z;
        }
        return x;


    }

    private double Gamma_o_pi_5(double mpa, double temperature) {
        double p_star = 1;
        double pi = mpa / p_star;
        double x = 1 / pi;
        return x;
    }

    private double v3b(double mpa, double temperature) {
        double v_star = 0.0041;
        double p_star = 100;
        double t_star = 860;
        double a = 0.28;
        double b = 0.779;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -10, -10, -8, -6, -6, -6, -5, -5, -5, -4, -4, -4, -3, -3, -3, -3, -3, -2, -2, -2, -1, -1, 0, 0, 1, 1, 2, 3, 4, 4};
        int[] J = {10, 12, 8, 14, 8, 5, 6, 8, 5, 8, 10, 2, 4, 5, 0, 1, 2, 3, 5, 0, 2, 5, 0, 2, 0, 1, 0, 2, 0, 2, 0, 1};
        double[] n = new double[32];
        n[0] = -0.0827670470003621;
        n[1] = 41.6887126010565;
        n[2] = 0.0483651982197059;
        n[3] = -29103.2084950276;
        n[4] = -111.422582236948;
        n[5] = -0.0202300083904014;
        n[6] = 294.002509338515;
        n[7] = 140.244997609658;
        n[8] = -344.384158811459;
        n[9] = 361.182452612149;
        n[10] = -1406.99677420738;
        n[11] = -0.00202023902676481;
        n[12] = 171.346792457471;
        n[13] = -4.25597804058632;
        n[14] = 0.00000691346085000334;
        n[15] = 0.00151140509678925;
        n[16] = -0.0416375290166236;
        n[17] = -41.3754957011042;
        n[18] = -50.6673295721637;
        n[19] = -0.000572212965569023;
        n[20] = 6.08817368401785;
        n[21] = 23.9600660256161;
        n[22] = 0.0122261479925384;
        n[23] = 2.16356057692938;
        n[24] = 0.398198903368642;
        n[25] = -0.116892827834085;
        n[26] = -0.102845919373532;
        n[27] = -0.492676637589284;
        n[28] = 0.065554045640679;
        n[29] = -0.24046253507853;
        n[30] = -0.0269798180310075;
        n[31] = 0.128369435967012;
        double x = 0;

        for (int i = 0; i < 32; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v3a(double mpa, double temperature) {
        double v_star = 0.0024;
        double p_star = 100;
        double t_star = 760;
        double a = 0.085;
        double b = 0.817;
        int c = 1;
        int d = 1;
        int e = 1;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-12, -12, -12, -10, -10, -10, -8, -8, -8, -6, -5, -5, -5, -4, -3, -3, -3, -3, -2, -2, -2, -1, -1, -1, 0, 0, 1, 1, 2, 2};
        int[] J = {5, 10, 12, 5, 10, 12, 5, 8, 10, 1, 1, 5, 10, 8, 0, 1, 3, 6, 0, 2, 3, 0, 1, 2, 0, 1, 0, 2, 0, 2};
        double[] n = new double[30];
        n[0] = 0.00110879558823853;
        n[1] = 572.616740810616;
        n[2] = -76705.1948380852;
        n[3] = -0.0253321069529674;
        n[4] = 6280.08049345689;
        n[5] = 234105.654131876;
        n[6] = 0.216867826045856;
        n[7] = -156.237904341963;
        n[8] = -26989.3956176613;
        n[9] = -0.000180407100085505;
        n[10] = 0.00116732227668261;
        n[11] = 26.698704085604;
        n[12] = 28277.6617243286;
        n[13] = -2424.31520029523;
        n[14] = 0.000435217323022733;
        n[15] = -0.0122494831387441;
        n[16] = 1.79357604019989;
        n[17] = 44.2729521058314;
        n[18] = -0.00593223489018342;
        n[19] = 0.453186261685774;
        n[20] = 1.3582570312914;
        n[21] = 0.0408748415856745;
        n[22] = 0.474686397863312;
        n[23] = 1.18646814997915;
        n[24] = 0.546987265727549;
        n[25] = 0.195266770452643;
        n[26] = -0.0502268790869663;
        n[27] = -0.369645308193377;
        n[28] = 0.0063382803752842;
        n[29] = 0.0797441793901017;
        double x = 0;
        for (int i = 0; i < 30; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);
        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double v_2(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 540;

        double T = temperature + 273.15;
        double pi = mpa / p_star;

        double fina = pi * (Gamma_o_pi_2(mpa, temperature) + Gamma_r_pi_2(mpa, temperature)) * T * R / mpa / 1000;
        return fina;


    }

    private double Gamma_r_pi_2(double mpa, double temperature) {
        int[] I = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 6, 6, 6, 7, 7, 7, 8, 8, 9, 10, 10, 10, 16, 16, 18, 20, 20, 20, 21, 22, 23, 24, 24, 24};
        int[] J = {0, 1, 2, 3, 6, 1, 2, 4, 7, 36, 0, 1, 3, 6, 35, 1, 2, 3, 7, 3, 16, 35, 0, 11, 25, 8, 36, 13, 4, 10, 14, 29, 50, 57, 20, 35, 48, 21, 53, 39, 26, 40, 58};
        double[] n = new double[43];
        n[0] = -0.0017731742473213;
        n[1] = -0.017834862292358;
        n[2] = -0.045996013696365;
        n[3] = -0.057581259083432;
        n[4] = -0.05032527872793;
        n[5] = -0.00003303264167023;
        n[6] = -0.00018948987516315;
        n[7] = -0.0039392777243355;
        n[8] = -0.043797295650573;
        n[9] = -0.000026674547914087;
        n[10] = 0.000000020481737692309;
        n[11] = 0.00000043870667284435;
        n[12] = -0.00003227767723857;
        n[13] = -0.0015033924542148;
        n[14] = -0.040668253562649;
        n[15] = -0.00000000078847309559367;
        n[16] = 0.000000012790717852285;
        n[17] = 0.00000048225372718507;
        n[18] = 0.0000022922076337661;
        n[19] = -0.000000000016714766451061;
        n[20] = -0.0021171472321355;
        n[21] = -23.895741934104;
        n[22] = -5.905956432427E-18;
        n[23] = -0.0000012621808899101;
        n[24] = -0.038946842435739;
        n[25] = 0.000000000011256211360459;
        n[26] = -8.2311340897998;
        n[27] = 0.000000019809712802088;
        n[28] = 1.0406965210174E-19;
        n[29] = -0.00000000000010234747095929;

        n[30] = -0.0000000010018179379511;
        n[31] = -0.000000000080882908646985;
        n[32] = 0.10693031879409;
        n[33] = -0.33662250574171;
        n[34] = 8.9185845355421E-25;
        n[35] = 0.00000000000030629316876232;
        n[36] = -0.0000042002467698208;
        n[37] = -5.9056029685639E-26;
        n[38] = 0.0000037826947613457;
        n[39] = -0.0000000000000012768608934681;
        n[40] = 7.3087610595061E-29;
        n[41] = 5.5414715350778E-17;
        n[42] = -0.0000009436970724121;
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        for (int i = 0; i < n.length; i++) {
            double y = Math.pow(pi, I[i] - 1);
            double z = Math.pow(tau - 0.5, J[i]);
            x = x + n[i] * I[i] * y * z;
        }
        return x;


    }

    private double Gamma_o_pi_2(double mpa, double temperature) {
        double p_star = 1;
        double x = 1;
        double pi = mpa / p_star;

        return 1 / pi;
    }

    private double v_1(double mpa, double temperature) {
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;

        double x = pi * Gamma_pi_1(mpa, temperature) * T * R / mpa / 1000;
        return x;


    }

    private double Gamma_pi_1(double mpa, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 8, 8, 21, 23, 29, 30, 31, 32};
        int[] J = {-2, -1, 0, 1, 2, 3, 4, 5, -9, -7, -1, 0, 1, 3, -3, 0, 1, 3, 17, -4, 0, 6, -5, -2, 10, -8, -11, -6, -29, -31, -38, -39, -40, -41};
        double[] n = new double[34];
        n[0] = 0.14632971213167;
        n[1] = -0.84548187169114;
        n[2] = -3.756360367204;
        n[3] = 3.3855169168385;
        n[4] = -0.95791963387872;
        n[5] = 0.15772038513228;
        n[6] = -0.016616417199501;
        n[7] = 0.00081214629983568;
        n[8] = 0.00028319080123804;
        n[9] = -0.00060706301565874;
        n[10] = -0.018990068218419;
        n[11] = -0.032529748770505;
        n[12] = -0.021841717175414;
        n[13] = -0.00005283835796993;
        n[14] = -0.00047184321073267;
        n[15] = -0.00030001780793026;
        n[16] = 0.000047661393906987;
        n[17] = -0.0000044141845330846;
        n[18] = -0.00000000000000072694996297594;
        n[19] = -0.000031679644845054;
        n[20] = -0.0000028270797985312;
        n[21] = -0.00000000085205128120103;
        n[22] = -0.0000022425281908;
        n[23] = -0.00000065171222895601;
        n[24] = -0.00000000000014341729937924;
        n[25] = -0.00000040516996860117;
        n[26] = -0.0000000012734301741641;
        n[27] = -0.00000000017424871230634;
        n[28] = -6.8762131295531E-19;
        n[29] = 1.4478307828521E-20;

        n[30] = 2.6335781662795E-23;
        n[31] = -1.1947622640071E-23;
        n[32] = 1.8228094581404E-24;
        n[33] = -9.3537087292458E-26;
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        for (int i = 0; i < n.length; i++) {
            double y = Math.pow(7.1 - pi, I[i] - 1);
            double z = Math.pow(tau - 1.222, J[i]);
            x = x + (-1) * n[i] * I[i] * y * z;
        }
        return x;


    }

    private double v3z(double mpa, double temperature) {
        double v_star = 0.0038;
        double p_star = 22;
        double t_star = 650;
        double a = 0.993;
        double b = 0.994;
        int c = 1;
        int d = 1;
        int e = 4;
        double pi = mpa / p_star;
        double T = temperature + 273.15;
        double theta = T / t_star;
        int[] I = {-8, -6, -5, -5, -4, -4, -4, -3, -3, -3, -2, -1, 0, 1, 2, 3, 3, 6, 6, 6, 6, 8, 8};
        int[] J = {3, 6, 6, 8, 5, 6, 8, -2, 5, 6, 2, -6, 3, 1, 6, -6, -2, -6, -5, -4, -1, -8, -4};
        double[] n = new double[23];
        n[0] = 0.000000000024400789229065;
        n[1] = -4630574.30331242;
        n[2] = 7288032747.77712;
        n[3] = 3.27776302858856E+15;
        n[4] = -1105981701.18409;
        n[5] = -3238999157299.57;
        n[6] = 9.23814007023245E+15;
        n[7] = 0.000000000000842250080413712;
        n[8] = 663221436245.506;
        n[9] = -167170186672139.0;
        n[10] = 2537.49358701391;
        n[11] = -8.19731559610523E-21;
        n[12] = 328380587890.663;
        n[13] = -62500479.1171543;
        n[14] = 8.03197957462023E+20;
        n[15] = -0.0000000000204397011338353;
        n[16] = -3783.91047055938;
        n[17] = 0.0097287654593862;
        n[18] = 15.4355721681459;
        n[19] = -3739.62862928643;
        n[20] = -68285901137.4572;
        n[21] = -0.000248488015614543;
        n[22] = 3945360.49497068;
        if (mpa == Double.valueOf(22.064) && temperature == Double.valueOf(647.096)) {
            double z = 1 / 322;
            return z;
        }
        double x = 0;

        for (int i = 0; i < 23; i++) {
            double y = Math.pow(pi - a, c);
            double z = Math.pow(theta - b, d);
            x = x + n[i] * Math.pow(y, I[i]) * Math.pow(z, J[i]);

        }
        x = Math.pow(x, e) * (v_star);
        return x;
    }

    private double T3ij(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, 3, 4};
        double[] n = new double[5];
        double p_star = 1;
        double t_star = 1;
        n[0] = 584.814781649163;
        n[1] = -0.616179320924617;
        n[2] = 0.260763050899562;
        n[3] = -0.00587071076864459;
        n[4] = 0.0000515308185433082;

        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 5; i++) {
            double y = Math.pow(pi, I[i]);
            x = x + n[i] * y;
        }
        x = x * t_star - 273.15;
        return x;

    }

    private double T3op(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, -1, -2};
        double[] n = new double[5];
        double p_star = 1;
        double t_star = 1;
        n[0] = 969.461372400213;
        n[1] = -332.500170441278;
        n[2] = 64.2859598466067;
        n[3] = 773.845935768222;
        n[4] = -1523.13732937084;

        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 5; i++) {
            double y = Math.pow(Math.log(pi), I[i]);
            x = x + n[i] * y;
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double T3jk(double mpa) {

        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, 3, 4};
        double[] n = new double[5];
        double p_star = 1;
        double t_star = 1;
        n[0] = 617.229772068439;
        n[1] = -7.70600270141675;
        n[2] = 0.697072596851896;
        n[3] = -0.0157391839848015;
        n[4] = 0.000137897492684194;

        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 5; i++) {
            double y = Math.pow(pi, I[i]);
            x = x + n[i] * y;
        }
        x = x * t_star - 273.15;
        return x;

    }

    private double T3rx(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, 3};
        double[] n = new double[4];
        double p_star = 1;
        double t_star = 1;
        n[0] = 584.561202520006;
        n[1] = -1.02961025163669;
        n[2] = 0.243293362700452;
        n[3] = -0.00294905044740799;
        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 4; i++) {
            double y = Math.pow(pi, I[i]);
            x = x + n[i] * y;
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double T3wx(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, -1, -2};
        double[] n = new double[5];
        double p_star = 1;
        double t_star = 1;
        n[0] = 7.2805260914538;
        n[1] = 97.3505869861952;
        n[2] = 14.7370491183191;
        n[3] = 329.196213998375;
        n[4] = 873.371668682417;
        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 5; i++) {
            double y = Math.pow(Math.log(pi), I[i]);
            x = x + n[i] * y;
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double T3uv(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, 3};
        double[] n = new double[4];
        double p_star = 1;
        double t_star = 1;
        n[0] = 528.199646263062;
        n[1] = 8.90579602135307;
        n[2] = -0.222814134903755;
        n[3] = 0.00286791682263697;
        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 4; i++) {
            double y = Math.pow(pi, I[i]);
            x = x + n[i] * y;
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double T3qu(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, 3};
        double[] n = new double[4];
        double p_star = 1;
        double t_star = 1;
        n[0] = 565.603648239126;
        n[1] = 5.29062258221222;
        n[2] = -0.102020639611016;
        n[3] = 0.00122240301070145;
        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 4; i++) {
            double y = Math.pow(pi, I[i]);
            x = x + n[i] * y;
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double T3mn(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, 3};
        double[] n = new double[4];
        double p_star = 1;
        double t_star = 1;
        n[0] = 535.339483742384;
        n[1] = 7.61978122720128;
        n[2] = -0.158365725441648;
        n[3] = 0.00192871054508108;
        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 4; i++) {
            double y = Math.pow(pi, I[i]);
            x = x + n[i] * y;
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double T3gh(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, 3, 4};
        double[] n = new double[5];
        double p_star = 1;
        double t_star = 1;
        n[0] = -24928.4240900418;
        n[1] = 4281.43584791546;
        n[2] = -269.02917314013;
        n[3] = 7.51608051114157;
        n[4] = -0.0787105249910383;
        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 4; i++) {
            x = x + n[i] *Math.pow(pi, I[i]);
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double T3ef(double mpa) {
        double pi;
        double p_star = 1;
        double t_star = 1;
        pi = mpa / p_star;
        double x;
        x = (3.727888004 * (pi - 22.064) + 647.096) * t_star - 273.15;


        return x;
    }

    public double Ts(double mpa) {
        double d;
        double e;
        double F;
        double G;
        double beta;
        double T;
        double[] n = new double[10];
        n[0] = 1167.0521452767;
        n[1] = -724213.16703206;
        n[2] = -17.073846940092;
        n[3] = 12020.82470247;
        n[4] = -3232555.0322333;
        n[5] = 14.91510861353;
        n[6] = -4823.2657361591;
        n[7] = 405113.40542057;
        n[8] = -0.23855557567849;
        n[9] = 650.17534844798;
        double p_star = 1;
        double t_star = 1;
        if (mpa < 0.000611213 || mpa > 22.064) {
            return -1000;
        }
        beta = Math.pow(mpa / p_star, 0.25);
        e = Math.pow(beta, 2) + n[2] * beta + n[5];
        F = n[0] * Math.pow(beta, 2) + n[3] * beta + n[6];
        G = n[1] * Math.pow(beta, 2) + n[4] * beta + n[7];
        d = 2 * G / (-F - Math.pow(Math.pow(F, 2) - 4 * e * G, 0.5));

        double x = (n[9] + d - Math.pow((Math.pow((n[9] + d), 2) - 4 * (n[8] + n[9] * d)), 0.5)) / 2 * t_star - 273.15;
        return x;


    }

    public double Ps(double temperature) {
        double a;
        double b;
        double c;
        double theta;
        double T;
        double[] n = new double[10];
        n[0] = 1167.0521452767;
        n[1] = -724213.16703206;
        n[2] = -17.073846940092;
        n[3] = 12020.82470247;
        n[4] = -3232555.0322333;
        n[5] = 14.91510861353;
        n[6] = -4823.2657361591;
        n[7] = 405113.40542057;
        n[8] = -0.23855557567849;
        n[9] = 650.17534844798;
        double p_star = 1;
        double t_star = 1;
        T = temperature + 273.15;
        if (T < 273.15 || T > 647.096) {
            return -1000;
        }
        theta = T / t_star + n[8] / (T / t_star - n[9]);
        a = Math.pow(theta, 2) + n[0] * theta + n[1];
        b = n[2] * Math.pow(theta, 2) + n[3] * theta + n[4];
        c = n[5] * Math.pow(theta, 2) + n[6] * theta + n[7];

        double x = Math.pow((2 * c / (-b + Math.pow((Math.pow(b, 2) - 4 * a * c), 0.5))), 4) * p_star;
        return x;


    }

    public double PB23(double temperature) {
        double theta;
        double T;
        double f;
        double s;
        double[] n = new double[3];
        double p_star = 1;
        double t_star = 1;
        n[0] = 348.05185628969;
        n[1] = -1.1671859879975;
        n[2] = 0.0010192970039326;
        T = temperature + 273.15;
        if (T < 613.15 || T > 863.15) {
            return -1000;
        }
        theta = T / t_star;
        s = (n[0] + n[1] * theta + n[2] * Math.pow(theta, 2)) * p_star;
        return s;
    }

    public double T3ab(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, -1, -2};
        double[] n = new double[5];
        double p_star = 1;
        double t_star = 1;
        n[0] = 1547.93642129415;
        n[1] = -187.661219490113;
        n[2] = 21.3144632222113;
        n[3] = -1918.87498864292;
        n[4] = 918.419702359447;
        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 5; i++) {

            x = x + n[i] * Math.pow((Math.log(pi)), I[i]);

        }
        x = x * t_star - 273.15;
        return x;
    }

    public double T3cd(double mpa) {
        double pi;
        double T;
        double f;
        double s;
        int[] I = {0, 1, 2, 3};
        double[] n = new double[4];
        double p_star = 1;
        double t_star = 1;
        n[0] = 585.276966696349;
        n[1] = 2.78233532206915;
        n[2] = -0.0127283549295878;
        n[3] = 0.000159090746562729;

        pi = mpa / p_star;
        double x = 0;
        for (int i = 0; i < 4; i++) {
            x = x + n[i] * Math.pow(pi, I[i]);

        }
        x = x * t_star - 273.15;
        return x;
    }

    /*
    h计算
     */
    public double h_result(double Mpa, double temperature) {
        int x = 1;
        String areatype = subregion_pT(Mpa, temperature);
        if (areatype.equals("Critical point"))
            return h_3z(Mpa, temperature);
        else if (areatype.equals("1"))
            return h_1(Mpa, temperature);
        else if (areatype.equals("2"))
            return h_2(Mpa, temperature);
        else if (areatype.equals("3a"))
            return h_3a(Mpa, temperature);
        else if (areatype.equals("3b"))
            return h_3b(Mpa, temperature);
        else if (areatype.equals("3c"))
            return h_3c(Mpa, temperature);
        else if (areatype.equals("3d"))
            return h_3d(Mpa, temperature);
        else if (areatype.equals("3e"))
            return h_3e(Mpa, temperature);
        else if (areatype.equals("3f"))
            return h_3f(Mpa, temperature);
        else if (areatype.equals("3g"))
            return h_3g(Mpa, temperature);
        else if (areatype.equals("3h"))
            return h_3h(Mpa, temperature);
        else if (areatype.equals("3i"))
            return h_3i(Mpa, temperature);
        else if (areatype.equals("3j"))
            return h_3j(Mpa, temperature);
        else if (areatype.equals("3k"))
            return h_3k(Mpa, temperature);
        else if (areatype.equals("3l"))
            return h_3l(Mpa, temperature);
        else if (areatype.equals("3m"))
            return h_3m(Mpa, temperature);
        else if (areatype.equals("3n"))
            return h_3n(Mpa, temperature);
        else if (areatype.equals("3o"))
            return h_3o(Mpa, temperature);
        else if (areatype.equals("3p"))
            return h_3p(Mpa, temperature);
        else if (areatype.equals("3q"))
            return h_3q(Mpa, temperature);
        else if (areatype.equals("3r"))
            return h_3r(Mpa, temperature);
        else if (areatype.equals("3s"))
            return h_3s(Mpa, temperature);
        else if (areatype.equals("3t"))
            return h_3t(Mpa, temperature);
        else if (areatype.equals("3u"))
            return h_3u(Mpa, temperature);
        else if (areatype.equals("3v"))
            return h_3v(Mpa, temperature);
        else if (areatype.equals("3w"))
            return h_3w(Mpa, temperature);
        else if (areatype.equals("3x"))
            return h_3x(Mpa, temperature);
        else if (areatype.equals("3y"))
            return h_3y(Mpa, temperature);
        else if (areatype.equals("3z"))
            return h_3z(Mpa, temperature);
        else if (areatype.equals("4"))
            return hm(Mpa, temperature, x);
        else if (areatype.equals("5"))
            return h_5(Mpa, temperature);
        else
            return -1000;

    }

    private double hm(double mpa, double temperature, double x) {
        double h=-1000;
        if (Math.abs(temperature - 350) < 0.0001) {
            temperature = 350;
        }
        if (temperature <= 350) {
            h = h_1(mpa, temperature) * (1 - x) + h_2(mpa, temperature) * x;
        }
        else{
            if (mpa <= 19.0088118917393) {
                h = h_3c(mpa, temperature) * (1 - x) + h_3t(mpa, temperature) * x;
            } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                h = h_3s(mpa, temperature) * (1 - x) + h_3t(mpa, temperature) * x;
            } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                h = h_3s(mpa, temperature) * (1 - x) + h_3r(mpa, temperature) * x;
            } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                h = h_3u(mpa, temperature) * (1 - x) + h_3x(mpa, temperature) * x;
            } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                h = h_3u(mpa, temperature) * (1 - x) + h_3z(mpa, temperature) * x;
            } else if (mpa > 21.93161551 && mpa <= 22.064) {
                h = h_3y(mpa, temperature) * (1 - x) + h_3z(mpa, temperature) * x;
            }
        }

        return h;
    }

    private double h_3y(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3y(mpa, temperature), temperature);
    }

    private double h_3x(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3x(mpa, temperature), temperature);
    }

    private double h_3w(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3w(mpa, temperature), temperature);
    }

    private double h_3v(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3v(mpa, temperature), temperature);
    }

    private double h_3u(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3u(mpa, temperature), temperature);
    }

    private double h_3t(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3t(mpa, temperature), temperature);
    }

    private double h_3s(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3s(mpa, temperature), temperature);
    }

    private double h_3r(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3r(mpa, temperature), temperature);
    }

    private double h_3q(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3q(mpa, temperature), temperature);
    }

    private double h_3p(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3p(mpa, temperature), temperature);
    }

    private double h_3o(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3o(mpa, temperature), temperature);
    }

    private double h_3n(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3n(mpa, temperature), temperature);
    }

    private double h_3m(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3m(mpa, temperature), temperature);
    }

    private double h_3l(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3l(mpa, temperature), temperature);
    }

    private double h_3k(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3k(mpa, temperature), temperature);
    }

    private double h_3j(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3j(mpa, temperature), temperature);
    }

    private double h_3i(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3i(mpa, temperature), temperature);
    }

    private double h_3h(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3h(mpa, temperature), temperature);
    }

    private double h_3g(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3g(mpa, temperature), temperature);
    }

    private double h_3f(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3f(mpa, temperature), temperature);
    }

    private double h_3e(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3e(mpa, temperature), temperature);
    }

    private double h_3d(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3d(mpa, temperature), temperature);
    }

    private double h_3c(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3c(mpa, temperature), temperature);
    }

    private double h_3b(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3b(mpa, temperature), temperature);
    }

    private double h_3a(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3a(mpa, temperature), temperature);
    }

    private double h_2(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        return (tau * (Gamma_o_tau_2(mpa, temperature) + Gamma_r_tau_2(mpa, temperature))) * R * T;
    }

    private double h_3z(double mpa, double temperature) {
        double a = 1;
        return h_3(1 / v3z(mpa, temperature), temperature);
    }

    private double h_3(double rho, double temperature) {
        double rho_star = 322;
        double t_star = 647.096;
        double T = temperature + 273.15;
        double delta = rho / rho_star;
        double tau = t_star / T;

        return (tau * phi_tau_3(rho, temperature) + delta * phi_delta_3(rho, temperature)) * R * T;

    }

    private double h_1(double mpa, double temperature) {
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        return tau * Gamma_tau_1(mpa, temperature) * T * R;

    }

    private double h_5(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;


        return (tau * (Gamma_o_tau_5(mpa, temperature) + Gamma_r_tau_5(mpa, temperature))) * R * T;
    }

    private double Gamma_o_tau_2(double mpa, double temperature) {
        int[] J = {0, 1, -5, -4, -3, -2, -1, 2, 3};
        double[] n = new double[9];
        n[0] = -9.6927686500217;
        n[1] = 10.086655968018;
        n[2] = -0.005608791128302;
        n[3] = 0.071452738081455;
        n[4] = -0.40710498223928;
        n[5] = 1.4240819171444;
        n[6] = -4.383951131945;
        n[7] = -0.28408632460772;
        n[8] = 0.021268463753307;
        double p_star = 1;
        double t_star = 540;
        double x = 0;

        double T = temperature + 273.15;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * J[i] * Math.pow(tau, (J[i] - 1));
        }
        return x;

    }

    private double Gamma_r_tau_2(double mpa, double temperature) {
        int[] I = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 6, 6, 6, 7, 7, 7, 8, 8, 9, 10, 10, 10, 16, 16, 18, 20, 20, 20, 21, 22, 23, 24, 24, 24};
        int[] J = {0, 1, 2, 3, 6, 1, 2, 4, 7, 36, 0, 1, 3, 6, 35, 1, 2, 3, 7, 3, 16, 35, 0, 11, 25, 8, 36, 13, 4, 10, 14, 29, 50, 57, 20, 35, 48, 21, 53, 39, 26, 40, 58};
        double[] n = new double[43];
        n[0] = -0.0017731742473213;
        n[1] = -0.017834862292358;
        n[2] = -0.045996013696365;
        n[3] = -0.057581259083432;
        n[4] = -0.05032527872793;
        n[5] = -0.00003303264167023;
        n[6] = -0.00018948987516315;
        n[7] = -0.0039392777243355;
        n[8] = -0.043797295650573;
        n[9] = -0.000026674547914087;
        n[10] = 0.000000020481737692309;
        n[11] = 0.00000043870667284435;
        n[12] = -0.00003227767723857;
        n[13] = -0.0015033924542148;
        n[14] = -0.040668253562649;
        n[15] = -0.00000000078847309559367;
        n[16] = 0.000000012790717852285;
        n[17] = 0.00000048225372718507;
        n[18] = 0.0000022922076337661;
        n[19] = -0.000000000016714766451061;
        n[20] = -0.0021171472321355;
        n[21] = -23.895741934104;
        n[22] = -5.905956432427E-18;
        n[23] = -0.0000012621808899101;
        n[24] = -0.038946842435739;
        n[25] = 0.000000000011256211360459;
        n[26] = -8.2311340897998;
        n[27] = 0.000000019809712802088;
        n[28] = 1.0406965210174E-19;
        n[29] = -0.00000000000010234747095929;
        n[30] = -0.0000000010018179379511;
        n[31] = -0.000000000080882908646985;
        n[32] = 0.10693031879409;
        n[33] = -0.33662250574171;
        n[34] = 8.9185845355421E-25;
        n[35] = 0.00000000000030629316876232;
        n[36] = -0.0000042002467698208;
        n[37] = -5.9056029685639E-26;
        n[38] = 0.0000037826947613457;
        n[39] = -0.0000000000000012768608934681;
        n[40] = 7.3087610595061E-29;
        n[41] = 5.5414715350778E-17;
        n[42] = -0.0000009436970724121;
        double p_star = 1;
        double t_star = 540;
        double x = 0;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * Math.pow(pi, I[i]) * J[i] * Math.pow((tau - 0.5), (J[i] - 1));

        }
        return x;
    }


    private double Gamma_r_tau_5(double mpa, double temperature) {
        int[] I = {1, 1, 1, 2, 2, 3};
        int[] J = {1, 2, 3, 3, 9, 7};
        double[] n = new double[6];
        n[0] = 0.0015736404855259;
        n[1] = 0.00090153761673944;
        n[2] = -0.0050270077677648;
        n[3] = 0.0000022440037409485;
        n[4] = -0.0000041163275453471;
        n[5] = 0.000000037919454822955;
        double p_star = 1;
        double t_star = 1000;
        double x = 0;
        double y = 1;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * Math.pow(pi, I[i]) * J[i] * Math.pow(tau, (J[i] - 1));

        }
        return x;
    }

    private double Gamma_o_tau_5(double mpa, double temperature) {
        int[] J = {0, 1, -3, -2, -1, 2};
        double[] n = new double[6];
        n[0] = -13.179983674201;
        n[1] = 6.8540841634434;
        n[2] = -0.024805148933466;
        n[3] = 0.36901534980333;
        n[4] = -3.1161318213925;
        n[5] = -0.32961626538917;

        double p_star = 1;
        double t_star = 1000;
        double x = 0;
        double y = 1;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * J[i] * Math.pow(tau, (J[i] - 1));
        }
        return x;
    }


    private double Gamma_tau_1(double mpa, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 8, 8, 21, 23, 29, 30, 31, 32};
        int[] J = {-2, -1, 0, 1, 2, 3, 4, 5, -9, -7, -1, 0, 1, 3, -3, 0, 1, 3, 17, -4, 0, 6, -5, -2, 10, -8, -11, -6, -29, -31, -38, -39, -40, -41};
        double[] n = new double[34];
        n[0] = 0.14632971213167;
        n[1] = -0.84548187169114;
        n[2] = -3.756360367204;
        n[3] = 3.3855169168385;
        n[4] = -0.95791963387872;
        n[5] = 0.15772038513228;
        n[6] = -0.016616417199501;
        n[7] = 0.00081214629983568;
        n[8] = 0.00028319080123804;
        n[9] = -0.00060706301565874;
        n[10] = -0.018990068218419;
        n[11] = -0.032529748770505;
        n[12] = -0.021841717175414;
        n[13] = -0.00005283835796993;
        n[14] = -0.00047184321073267;
        n[15] = -0.00030001780793026;
        n[16] = 0.000047661393906987;
        n[17] = -0.0000044141845330846;
        n[18] = -0.00000000000000072694996297594;
        n[19] = -0.000031679644845054;
        n[20] = -0.0000028270797985312;
        n[21] = -0.00000000085205128120103;
        n[22] = -0.0000022425281908;
        n[23] = -0.00000065171222895601;
        n[24] = -0.00000000000014341729937924;
        n[25] = -0.00000040516996860117;
        n[26] = -0.0000000012734301741641;
        n[27] = -0.00000000017424871230634;
        n[28] = -6.8762131295531E-19;
        n[29] = 1.4478307828521E-20;

        n[30] = 2.6335781662795E-23;
        n[31] = -1.1947622640071E-23;
        n[32] = 1.8228094581404E-24;
        n[33] = -9.3537087292458E-26;
        double p_star = 16.53;
        double t_star = 1386;
        double x = 0;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * Math.pow(7.1 - pi, I[i]) * J[i] * Math.pow(tau - 1.222, (J[i] - 1));

        }
        return x;
    }


    private double phi_delta_3(double rho, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 8, 9, 9, 10, 10, 11};
        int[] J = {0, 0, 1, 2, 7, 10, 12, 23, 2, 6, 15, 17, 0, 2, 6, 7, 22, 26, 0, 2, 4, 16, 26, 0, 2, 4, 26, 1, 3, 26, 0, 2, 26, 2, 26, 2, 26, 0, 1, 26};
        double[] n = new double[40];
        n[0] = 1.0658070028513;
        n[1] = -15.732845290239;
        n[2] = 20.944396974307;
        n[3] = -7.6867707878716;
        n[4] = 2.6185947787954;
        n[5] = -2.808078114862;
        n[6] = 1.2053369696517;
        n[7] = -0.0084566812812502;
        n[8] = -1.2654315477714;
        n[9] = -1.1524407806681;
        n[10] = 0.88521043984318;
        n[11] = -0.64207765181607;
        n[12] = 0.38493460186671;
        n[13] = -0.85214708824206;
        n[14] = 4.8972281541877;
        n[15] = -3.0502617256965;
        n[16] = 0.039420536879154;
        n[17] = 0.12558408424308;
        n[18] = -0.2799932969871;
        n[19] = 1.389979956946;
        n[20] = -2.018991502357;
        n[21] = -0.0082147637173963;
        n[22] = -0.47596035734923;
        n[23] = 0.0439840744735;
        n[24] = -0.44476435428739;
        n[25] = 0.90572070719733;
        n[26] = 0.70522450087967;
        n[27] = 0.10770512626332;
        n[28] = -0.32913623258954;
        n[29] = -0.50871062041158;
        n[30] = -0.022175400873096;
        n[31] = 0.094260751665092;
        n[32] = 0.16436278447961;
        n[33] = -0.013503372241348;
        n[34] = -0.014834345352472;
        n[35] = 0.00057922953628084;
        n[36] = 0.0032308904703711;
        n[37] = 0.000080964802996215;
        n[38] = -0.00016557679795037;
        n[39] = -0.000044923899061815;
        double rho_star = 322;
        double x = 1;
        double t_star = 647.096;
        double T = temperature + 273.15;
        double tau = t_star / T;
        double delta = rho / rho_star;
        double y = n[0] / delta;

        for (int i = 1; i < n.length; i++) {
            y = y + n[i] * I[i] * Math.pow(delta, (I[i] - 1)) * Math.pow(tau, J[i]);
        }
        return y;
    }

    private double phi_tau_3(double rho, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 8, 9, 9, 10, 10, 11};
        int[] J = {0, 0, 1, 2, 7, 10, 12, 23, 2, 6, 15, 17, 0, 2, 6, 7, 22, 26, 0, 2, 4, 16, 26, 0, 2, 4, 26, 1, 3, 26, 0, 2, 26, 2, 26, 2, 26, 0, 1, 26};
        double[] n = new double[40];
        n[0] = 1.0658070028513;
        n[1] = -15.732845290239;
        n[2] = 20.944396974307;
        n[3] = -7.6867707878716;
        n[4] = 2.6185947787954;
        n[5] = -2.808078114862;
        n[6] = 1.2053369696517;
        n[7] = -0.0084566812812502;
        n[8] = -1.2654315477714;
        n[9] = -1.1524407806681;
        n[10] = 0.88521043984318;
        n[11] = -0.64207765181607;
        n[12] = 0.38493460186671;
        n[13] = -0.85214708824206;
        n[14] = 4.8972281541877;
        n[15] = -3.0502617256965;
        n[16] = 0.039420536879154;
        n[17] = 0.12558408424308;
        n[18] = -0.2799932969871;
        n[19] = 1.389979956946;
        n[20] = -2.018991502357;
        n[21] = -0.0082147637173963;
        n[22] = -0.47596035734923;
        n[23] = 0.0439840744735;
        n[24] = -0.44476435428739;
        n[25] = 0.90572070719733;
        n[26] = 0.70522450087967;
        n[27] = 0.10770512626332;
        n[28] = -0.32913623258954;
        n[29] = -0.50871062041158;
        n[30] = -0.022175400873096;
        n[31] = 0.094260751665092;
        n[32] = 0.16436278447961;
        n[33] = -0.013503372241348;
        n[34] = -0.014834345352472;
        n[35] = 0.00057922953628084;
        n[36] = 0.0032308904703711;
        n[37] = 0.000080964802996215;
        n[38] = -0.00016557679795037;
        n[39] = -0.000044923899061815;
        double rho_star = 322;
        double t_star = 647.096;
        double T = temperature + 273.15;
        double tau = t_star / T;
        double delta = rho / rho_star;
        double y = 0;
        for (int i = 1; i < n.length; i++) {
            y = y + n[i] * Math.pow(delta, I[i]) * J[i] * Math.pow(tau, J[i] - 1);
        }
        return y;
    }

    public double s_pt(double Mpa, double temperature,double x) {

        String areatype = subregion_pT(Mpa, temperature);
        if (areatype.equals("Critical point"))
            return s_3z(Mpa, temperature);
        else if (areatype.equals("1"))
            return s_1(Mpa, temperature);
        else if (areatype.equals("2"))
            return s_2(Mpa, temperature);
        else if (areatype.equals("3a"))
            return s_3a(Mpa, temperature);
        else if (areatype.equals("3b"))
            return s_3b(Mpa, temperature);
        else if (areatype.equals("3c"))
            return s_3c(Mpa, temperature);
        else if (areatype.equals("3d"))
            return s_3d(Mpa, temperature);
        else if (areatype.equals("3e"))
            return s_3e(Mpa, temperature);
        else if (areatype.equals("3f"))
            return s_3f(Mpa, temperature);
        else if (areatype.equals("3g"))
            return s_3g(Mpa, temperature);
        else if (areatype.equals("3h"))
            return s_3h(Mpa, temperature);
        else if (areatype.equals("3i"))
            return s_3i(Mpa, temperature);
        else if (areatype.equals("3j"))
            return s_3j(Mpa, temperature);
        else if (areatype.equals("3k"))
            return s_3k(Mpa, temperature);
        else if (areatype.equals("3l"))
            return s_3l(Mpa, temperature);
        else if (areatype.equals("3m"))
            return s_3m(Mpa, temperature);
        else if (areatype.equals("3n"))
            return s_3n(Mpa, temperature);
        else if (areatype.equals("3o"))
            return s_3o(Mpa, temperature);
        else if (areatype.equals("3p"))
            return s_3p(Mpa, temperature);
        else if (areatype.equals("3q"))
            return s_3q(Mpa, temperature);
        else if (areatype.equals("3r"))
            return s_3r(Mpa, temperature);
        else if (areatype.equals("3s"))
            return s_3s(Mpa, temperature);
        else if (areatype.equals("3t"))
            return s_3t(Mpa, temperature);
        else if (areatype.equals("3u"))
            return s_3u(Mpa, temperature);
        else if (areatype.equals("3v"))
            return s_3v(Mpa, temperature);
        else if (areatype.equals("3w"))
            return s_3w(Mpa, temperature);
        else if (areatype.equals("3x"))
            return s_3x(Mpa, temperature);
        else if (areatype.equals("3y"))
            return s_3y(Mpa, temperature);
        else if (areatype.equals("3z"))
            return s_3z(Mpa, temperature);
        else if (areatype.equals("4"))
            return sm(Mpa, temperature, x);
        else if (areatype.equals("5"))
            return s_5(Mpa, temperature);
        else
            return -1000;

    }

    public double u_pt(double Mpa, double temperature,double x) {
        String areatype = subregion_pT(Mpa, temperature);
        if (areatype.equals("Critical point"))
            return u_3z(Mpa, temperature);
        else if (areatype.equals("1"))
            return u_1(Mpa, temperature);
        else if (areatype.equals("2"))
            return u_2(Mpa, temperature);
        else if (areatype.equals("3a"))
            return u_3a(Mpa, temperature);
        else if (areatype.equals("3b"))
            return u_3b(Mpa, temperature);
        else if (areatype.equals("3c"))
            return u_3c(Mpa, temperature);
        else if (areatype.equals("3d"))
            return u_3d(Mpa, temperature);
        else if (areatype.equals("3e"))
            return u_3e(Mpa, temperature);
        else if (areatype.equals("3f"))
            return u_3f(Mpa, temperature);
        else if (areatype.equals("3g"))
            return u_3g(Mpa, temperature);
        else if (areatype.equals("3h"))
            return u_3h(Mpa, temperature);
        else if (areatype.equals("3i"))
            return u_3i(Mpa, temperature);
        else if (areatype.equals("3j"))
            return u_3j(Mpa, temperature);
        else if (areatype.equals("3k"))
            return u_3k(Mpa, temperature);
        else if (areatype.equals("3l"))
            return u_3l(Mpa, temperature);
        else if (areatype.equals("3m"))
            return u_3m(Mpa, temperature);
        else if (areatype.equals("3n"))
            return u_3n(Mpa, temperature);
        else if (areatype.equals("3o"))
            return u_3o(Mpa, temperature);
        else if (areatype.equals("3p"))
            return u_3p(Mpa, temperature);
        else if (areatype.equals("3q"))
            return u_3q(Mpa, temperature);
        else if (areatype.equals("3r"))
            return u_3r(Mpa, temperature);
        else if (areatype.equals("3s"))
            return u_3s(Mpa, temperature);
        else if (areatype.equals("3t"))
            return u_3t(Mpa, temperature);
        else if (areatype.equals("3u"))
            return u_3u(Mpa, temperature);
        else if (areatype.equals("3v"))
            return u_3v(Mpa, temperature);
        else if (areatype.equals("3w"))
            return u_3w(Mpa, temperature);
        else if (areatype.equals("3x"))
            return u_3x(Mpa, temperature);
        else if (areatype.equals("3y"))
            return u_3y(Mpa, temperature);
        else if (areatype.equals("3z"))
            return u_3z(Mpa, temperature);
        else if (areatype.equals("4"))
            return um(Mpa, temperature, x);
        else if (areatype.equals("5"))
            return u_5(Mpa, temperature);
        else
            return -1000;

    }

    public double Cv_pt(double Mpa, double temperature,double x) {

        String areatype = subregion_pT(Mpa, temperature);
        if (areatype.equals("Critical point"))
            return cv_3z(Mpa, temperature);
        else if (areatype.equals("1"))
            return cv_1(Mpa, temperature);
        else if (areatype.equals("2"))
            return cv_2(Mpa, temperature);
        else if (areatype.equals("3a"))
            return cv_3a(Mpa, temperature);
        else if (areatype.equals("3b"))
            return cv_3b(Mpa, temperature);
        else if (areatype.equals("3c"))
            return cv_3c(Mpa, temperature);
        else if (areatype.equals("3d"))
            return cv_3d(Mpa, temperature);
        else if (areatype.equals("3e"))
            return cv_3e(Mpa, temperature);
        else if (areatype.equals("3f"))
            return cv_3f(Mpa, temperature);
        else if (areatype.equals("3g"))
            return cv_3g(Mpa, temperature);
        else if (areatype.equals("3h"))
            return cv_3h(Mpa, temperature);
        else if (areatype.equals("3i"))
            return cv_3i(Mpa, temperature);
        else if (areatype.equals("3j"))
            return cv_3j(Mpa, temperature);
        else if (areatype.equals("3k"))
            return cv_3k(Mpa, temperature);
        else if (areatype.equals("3l"))
            return cv_3l(Mpa, temperature);
        else if (areatype.equals("3m"))
            return cv_3m(Mpa, temperature);
        else if (areatype.equals("3n"))
            return cv_3n(Mpa, temperature);
        else if (areatype.equals("3o"))
            return cv_3o(Mpa, temperature);
        else if (areatype.equals("3p"))
            return cv_3p(Mpa, temperature);
        else if (areatype.equals("3q"))
            return cv_3q(Mpa, temperature);
        else if (areatype.equals("3r"))
            return cv_3r(Mpa, temperature);
        else if (areatype.equals("3s"))
            return cv_3s(Mpa, temperature);
        else if (areatype.equals("3t"))
            return cv_3t(Mpa, temperature);
        else if (areatype.equals("3u"))
            return cv_3u(Mpa, temperature);
        else if (areatype.equals("3v"))
            return cv_3v(Mpa, temperature);
        else if (areatype.equals("3w"))
            return cv_3w(Mpa, temperature);
        else if (areatype.equals("3x"))
            return cv_3x(Mpa, temperature);
        else if (areatype.equals("3y"))
            return cv_3y(Mpa, temperature);
        else if (areatype.equals("3z"))
            return cv_3z(Mpa, temperature);
        else if (areatype.equals("4"))
            return cvm(Mpa, temperature, x);
        else if (areatype.equals("5"))
            return cv_5(Mpa, temperature);
        else
            return -1000;

    }

    public double Cp_pt(double Mpa, double temperature,double x) {

        String areatype = subregion_pT(Mpa, temperature);
        if (areatype.equals("Critical point"))
            return cp_3z(Mpa, temperature);
        else if (areatype.equals("1"))
            return cp_1(Mpa, temperature);
        else if (areatype.equals("2"))
            return cp_2(Mpa, temperature);
        else if (areatype.equals("3a"))
            return cp_3a(Mpa, temperature);
        else if (areatype.equals("3b"))
            return cp_3b(Mpa, temperature);
        else if (areatype.equals("3c"))
            return cp_3c(Mpa, temperature);
        else if (areatype.equals("3d"))
            return cp_3d(Mpa, temperature);
        else if (areatype.equals("3e"))
            return cp_3e(Mpa, temperature);
        else if (areatype.equals("3f"))
            return cp_3f(Mpa, temperature);
        else if (areatype.equals("3g"))
            return cp_3g(Mpa, temperature);
        else if (areatype.equals("3h"))
            return cp_3h(Mpa, temperature);
        else if (areatype.equals("3i"))
            return cp_3i(Mpa, temperature);
        else if (areatype.equals("3j"))
            return cp_3j(Mpa, temperature);
        else if (areatype.equals("3k"))
            return cp_3k(Mpa, temperature);
        else if (areatype.equals("3l"))
            return cp_3l(Mpa, temperature);
        else if (areatype.equals("3m"))
            return cp_3m(Mpa, temperature);
        else if (areatype.equals("3n"))
            return cp_3n(Mpa, temperature);
        else if (areatype.equals("3o"))
            return cp_3o(Mpa, temperature);
        else if (areatype.equals("3p"))
            return cp_3p(Mpa, temperature);
        else if (areatype.equals("3q"))
            return cp_3q(Mpa, temperature);
        else if (areatype.equals("3r"))
            return cp_3r(Mpa, temperature);
        else if (areatype.equals("3s"))
            return cp_3s(Mpa, temperature);
        else if (areatype.equals("3t"))
            return cp_3t(Mpa, temperature);
        else if (areatype.equals("3u"))
            return cp_3u(Mpa, temperature);
        else if (areatype.equals("3v"))
            return cp_3v(Mpa, temperature);
        else if (areatype.equals("3w"))
            return cp_3w(Mpa, temperature);
        else if (areatype.equals("3x"))
            return cp_3x(Mpa, temperature);
        else if (areatype.equals("3y"))
            return cp_3y(Mpa, temperature);
        else if (areatype.equals("3z"))
            return cp_3z(Mpa, temperature);
        else if (areatype.equals("4"))
            return cpm(Mpa, temperature, x);
        else if (areatype.equals("5"))
            return cp_5(Mpa, temperature);
        else
            return -1000;

    }

    public double w_pt(double Mpa, double temperature,double x) {
        String areatype = subregion_pT(Mpa, temperature);
        if (areatype.equals("Critical point"))
            return w_3z(Mpa, temperature);
        else if (areatype.equals("1"))
            return w_1(Mpa, temperature);
        else if (areatype.equals("2"))
            return w_2(Mpa, temperature);
        else if (areatype.equals("3a"))
            return w_3a(Mpa, temperature);
        else if (areatype.equals("3b"))
            return w_3b(Mpa, temperature);
        else if (areatype.equals("3c"))
            return w_3c(Mpa, temperature);
        else if (areatype.equals("3d"))
            return w_3d(Mpa, temperature);
        else if (areatype.equals("3e"))
            return w_3e(Mpa, temperature);
        else if (areatype.equals("3f"))
            return w_3f(Mpa, temperature);
        else if (areatype.equals("3g"))
            return w_3g(Mpa, temperature);
        else if (areatype.equals("3h"))
            return w_3h(Mpa, temperature);
        else if (areatype.equals("3i"))
            return w_3i(Mpa, temperature);
        else if (areatype.equals("3j"))
            return w_3j(Mpa, temperature);
        else if (areatype.equals("3k"))
            return w_3k(Mpa, temperature);
        else if (areatype.equals("3l"))
            return w_3l(Mpa, temperature);
        else if (areatype.equals("3m"))
            return w_3m(Mpa, temperature);
        else if (areatype.equals("3n"))
            return w_3n(Mpa, temperature);
        else if (areatype.equals("3o"))
            return w_3o(Mpa, temperature);
        else if (areatype.equals("3p"))
            return w_3p(Mpa, temperature);
        else if (areatype.equals("3q"))
            return w_3q(Mpa, temperature);
        else if (areatype.equals("3r"))
            return w_3r(Mpa, temperature);
        else if (areatype.equals("3s"))
            return w_3s(Mpa, temperature);
        else if (areatype.equals("3t"))
            return w_3t(Mpa, temperature);
        else if (areatype.equals("3u"))
            return w_3u(Mpa, temperature);
        else if (areatype.equals("3v"))
            return w_3v(Mpa, temperature);

        else if (areatype.equals("3w"))
            return w_3w(Mpa, temperature);
        else if (areatype.equals("3x"))
            return w_3x(Mpa, temperature);
        else if (areatype.equals("3y"))
            return w_3y(Mpa, temperature);
        else if (areatype.equals("3z"))
            return w_3z(Mpa, temperature);
        else if (areatype.equals("4"))
            return wm(Mpa, temperature, x);
        else if (areatype.equals("5"))
            return w_5(Mpa, temperature);
        else
            return -1000;

    }


    public double h_pt(double Mpa, double temperature, double x) {
        String areatype = subregion_pT(Mpa, temperature);
        if (areatype.equals("Critical point"))
            return h_3z(Mpa, temperature);
        else if (areatype.equals("1"))
            return h_1(Mpa, temperature);
        else if (areatype.equals("2"))
            return h_2(Mpa, temperature);
        else if (areatype.equals("3a"))
            return h_3a(Mpa, temperature);
        else if (areatype.equals("3b"))
            return h_3b(Mpa, temperature);
        else if (areatype.equals("3c"))
            return h_3c(Mpa, temperature);
        else if (areatype.equals("3d"))
            return h_3d(Mpa, temperature);
        else if (areatype.equals("3e"))
            return h_3e(Mpa, temperature);
        else if (areatype.equals("3f"))
            return h_3f(Mpa, temperature);
        else if (areatype.equals("3g"))
            return h_3g(Mpa, temperature);
        else if (areatype.equals("3h"))
            return h_3h(Mpa, temperature);
        else if (areatype.equals("3i"))
            return h_3i(Mpa, temperature);
        else if (areatype.equals("3j"))
            return h_3j(Mpa, temperature);
        else if (areatype.equals("3k"))
            return h_3k(Mpa, temperature);
        else if (areatype.equals("3l"))
            return h_3l(Mpa, temperature);
        else if (areatype.equals("3m"))
            return h_3m(Mpa, temperature);
        else if (areatype.equals("3n"))
            return h_3n(Mpa, temperature);
        else if (areatype.equals("3o"))
            return h_3o(Mpa, temperature);
        else if (areatype.equals("3p"))
            return h_3p(Mpa, temperature);
        else if (areatype.equals("3q"))
            return h_3q(Mpa, temperature);
        else if (areatype.equals("3r"))
            return h_3r(Mpa, temperature);
        else if (areatype.equals("3s"))
            return h_3s(Mpa, temperature);
        else if (areatype.equals("3t"))
            return h_3t(Mpa, temperature);
        else if (areatype.equals("3u"))
            return h_3u(Mpa, temperature);
        else if (areatype.equals("3v"))
            return h_3v(Mpa, temperature);
        else if (areatype.equals("3w"))
            return h_3w(Mpa, temperature);
        else if (areatype.equals("3x"))
            return h_3x(Mpa, temperature);
        else if (areatype.equals("3y"))
            return h_3y(Mpa, temperature);
        else if (areatype.equals("3z"))
            return h_3z(Mpa, temperature);
        else if (areatype.equals("4"))
            return hm(Mpa, temperature, x);
        else if (areatype.equals("5"))
            return h_5(Mpa, temperature);
        else
            return -1000;

    }

    public double den_pt(double Mpa, double temperature,double x) {
        double den_pt;
        double a = v_pt(Mpa, temperature,x);
        if (a == Double.valueOf(0) || a == Double.valueOf(-1000)) {

            den_pt = -1000;
        } else {
            den_pt = 1 / a;
        }
        return den_pt;
    }

    /*
s_pt计算
 */
    private double sm(double mpa, double temperature, double x) {
        double h=-1000;
        if (Math.abs(temperature - 350) < 0.0001) {
            temperature = 350;
        }
        if (temperature <= 350) {
            h = s_1(mpa, temperature) * (1 - x) + s_2(mpa, temperature) * x;
        }
        else{
            if (mpa <= 19.0088118917393) {
                h = s_3c(mpa, temperature) * (1 - x) + s_3t(mpa, temperature) * x;
            } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                h = s_3s(mpa, temperature) * (1 - x) + s_3t(mpa, temperature) * x;
            } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                h = s_3s(mpa, temperature) * (1 - x) + s_3r(mpa, temperature) * x;
            } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                h = s_3u(mpa, temperature) * (1 - x) + s_3x(mpa, temperature) * x;
            } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                h = s_3u(mpa, temperature) * (1 - x) + s_3z(mpa, temperature) * x;
            } else if (mpa > 21.93161551 && mpa <= 22.064) {
                h = s_3y(mpa, temperature) * (1 - x) + s_3z(mpa, temperature) * x;
            }
            
        }
        
        return h;
    }


    private double s_3y(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3y(mpa, temperature), temperature);
    }

    private double s_3x(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3x(mpa, temperature), temperature);
    }

    private double s_3w(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3w(mpa, temperature), temperature);
    }

    private double s_3v(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3v(mpa, temperature), temperature);
    }

    private double s_3u(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3u(mpa, temperature), temperature);
    }

    private double s_3t(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3t(mpa, temperature), temperature);
    }

    private double s_3s(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3s(mpa, temperature), temperature);
    }

    private double s_3r(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3r(mpa, temperature), temperature);
    }

    private double s_3q(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3q(mpa, temperature), temperature);
    }

    private double s_3p(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3p(mpa, temperature), temperature);
    }

    private double s_3o(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3o(mpa, temperature), temperature);
    }

    private double s_3n(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3n(mpa, temperature), temperature);
    }

    private double s_3m(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3m(mpa, temperature), temperature);
    }

    private double s_3l(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3l(mpa, temperature), temperature);
    }

    private double s_3k(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3k(mpa, temperature), temperature);
    }

    private double s_3j(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3j(mpa, temperature), temperature);
    }

    private double s_3i(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3i(mpa, temperature), temperature);
    }

    private double s_3h(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3h(mpa, temperature), temperature);
    }

    private double s_3g(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3g(mpa, temperature), temperature);
    }

    private double s_3f(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3f(mpa, temperature), temperature);
    }

    private double s_3e(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3e(mpa, temperature), temperature);
    }

    private double s_3d(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3d(mpa, temperature), temperature);
    }

    private double s_3c(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3c(mpa, temperature), temperature);
    }

    private double s_3b(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3b(mpa, temperature), temperature);
    }

    private double s_3a(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3a(mpa, temperature), temperature);
    }

    private double s_2(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        return (tau * (Gamma_o_tau_2(mpa, temperature) + Gamma_r_tau_2(mpa, temperature)) - (Gamma_o_2(mpa, temperature) + Gamma_r_2(mpa, temperature))) * R;
    }

    private double Gamma_r_2(double mpa, double temperature) {
        int[] I = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 6, 6, 6, 7, 7, 7, 8, 8, 9, 10, 10, 10, 16, 16, 18, 20, 20, 20, 21, 22, 23, 24, 24, 24};
        int[] J = {0, 1, 2, 3, 6, 1, 2, 4, 7, 36, 0, 1, 3, 6, 35, 1, 2, 3, 7, 3, 16, 35, 0, 11, 25, 8, 36, 13, 4, 10, 14, 29, 50, 57, 20, 35, 48, 21, 53, 39, 26, 40, 58};
        double[] n = new double[43];
        n[0] = -0.0017731742473213;
        n[1] = -0.017834862292358;
        n[2] = -0.045996013696365;
        n[3] = -0.057581259083432;
        n[4] = -0.05032527872793;
        n[5] = -0.00003303264167023;
        n[6] = -0.00018948987516315;
        n[7] = -0.0039392777243355;
        n[8] = -0.043797295650573;
        n[9] = -0.000026674547914087;
        n[10] = 0.000000020481737692309;
        n[11] = 0.00000043870667284435;
        n[12] = -0.00003227767723857;
        n[13] = -0.0015033924542148;
        n[14] = -0.040668253562649;
        n[15] = -0.00000000078847309559367;
        n[16] = 0.000000012790717852285;
        n[17] = 0.00000048225372718507;
        n[18] = 0.0000022922076337661;
        n[19] = -0.000000000016714766451061;
        n[20] = -0.0021171472321355;
        n[21] = -23.895741934104;
        n[22] = -5.905956432427E-18;
        n[23] = -0.0000012621808899101;
        n[24] = -0.038946842435739;
        n[25] = 0.000000000011256211360459;
        n[26] = -8.2311340897998;
        n[27] = 0.000000019809712802088;
        n[28] = 1.0406965210174E-19;
        n[29] = -0.00000000000010234747095929;
        n[30] = -0.0000000010018179379511;
        n[31] = -0.000000000080882908646985;
        n[32] = 0.10693031879409;
        n[33] = -0.33662250574171;
        n[34] = 8.9185845355421E-25;
        n[35] = 0.00000000000030629316876232;
        n[36] = -0.0000042002467698208;
        n[37] = -5.9056029685639E-26;
        n[38] = 0.0000037826947613457;
        n[39] = -0.0000000000000012768608934681;
        n[40] = 7.3087610595061E-29;
        n[41] = 5.5414715350778E-17;
        n[42] = -0.0000009436970724121;
        double p_star = 1;
        double t_star = 540;
        double x = 0;
        double y = 1;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * Math.pow(pi, I[i]) * Math.pow(tau - 0.5, J[i]);

        }
        return x;
    }

    private double Gamma_o_2(double mpa, double temperature) {
        int[] J = {0, 1, -5, -4, -3, -2, -1, 2, 3};
        double[] n = new double[9];
        n[0] = -9.6927686500217;
        n[1] = 10.086655968018;
        n[2] = -0.005608791128302;
        n[3] = 0.071452738081455;
        n[4] = -0.40710498223928;
        n[5] = 1.4240819171444;
        n[6] = -4.383951131945;
        n[7] = -0.28408632460772;
        n[8] = 0.021268463753307;
        double p_star = 1;
        double t_star = 540;
        double y = 1;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = Math.log(pi);
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * Math.pow(tau, J[i]);
        }
        return x;
    }

    private double s_3z(double mpa, double temperature) {
        double a = 1;
        return s_3(1 / v3z(mpa, temperature), temperature);
    }

    private double s_3(double rho, double temperature) {
        double rho_star = 322;
        double t_star = 647.096;
        double T = temperature + 273.15;
        double tau = t_star / T;

        return (tau * phi_tau_3(rho, temperature) - phi_3(rho, temperature)) * R;

    }

    private double phi_3(double rho, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 8, 9, 9, 10, 10, 11};
        int[] J = {0, 0, 1, 2, 7, 10, 12, 23, 2, 6, 15, 17, 0, 2, 6, 7, 22, 26, 0, 2, 4, 16, 26, 0, 2, 4, 26, 1, 3, 26, 0, 2, 26, 2, 26, 2, 26, 0, 1, 26};
        double[] n = new double[40];
        n[0] = 1.0658070028513;
        n[1] = -15.732845290239;
        n[2] = 20.944396974307;
        n[3] = -7.6867707878716;
        n[4] = 2.6185947787954;
        n[5] = -2.808078114862;
        n[6] = 1.2053369696517;
        n[7] = -0.0084566812812502;
        n[8] = -1.2654315477714;
        n[9] = -1.1524407806681;
        n[10] = 0.88521043984318;
        n[11] = -0.64207765181607;
        n[12] = 0.38493460186671;
        n[13] = -0.85214708824206;
        n[14] = 4.8972281541877;
        n[15] = -3.0502617256965;
        n[16] = 0.039420536879154;
        n[17] = 0.12558408424308;
        n[18] = -0.2799932969871;
        n[19] = 1.389979956946;
        n[20] = -2.018991502357;
        n[21] = -0.0082147637173963;
        n[22] = -0.47596035734923;
        n[23] = 0.0439840744735;
        n[24] = -0.44476435428739;
        n[25] = 0.90572070719733;
        n[26] = 0.70522450087967;
        n[27] = 0.10770512626332;
        n[28] = -0.32913623258954;
        n[29] = -0.50871062041158;
        n[30] = -0.022175400873096;
        n[31] = 0.094260751665092;
        n[32] = 0.16436278447961;
        n[33] = -0.013503372241348;
        n[34] = -0.014834345352472;
        n[35] = 0.00057922953628084;
        n[36] = 0.0032308904703711;
        n[37] = 0.000080964802996215;
        n[38] = -0.00016557679795037;
        n[39] = -0.000044923899061815;
        double rho_star = 322;
        double t_stat = 647.096;
        double T = temperature + 273.15;
        double tau = t_stat / T;
        double delta = rho / rho_star;
        double y = n[0]*Math.log(delta);
        for (int i = 1; i < n.length; i++) {

            y = y + n[i] * Math.pow(delta, I[i]) * Math.pow(tau, J[i]);

        }
        return y;
    }

    private double s_1(double mpa, double temperature) {
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double tau = t_star / T;
        return (tau * Gamma_tau_1(mpa, temperature) - Gamma_1(mpa, temperature)) * R;

    }

    private double Gamma_1(double mpa, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 8, 8, 21, 23, 29, 30, 31, 32};
        int[] J = {-2, -1, 0, 1, 2, 3, 4, 5, -9, -7, -1, 0, 1, 3, -3, 0, 1, 3, 17, -4, 0, 6, -5, -2, 10, -8, -11, -6, -29, -31, -38, -39, -40, -41};
        double[] n = new double[34];
        n[0] = 0.14632971213167;
        n[1] = -0.84548187169114;
        n[2] = -3.756360367204;
        n[3] = 3.3855169168385;
        n[4] = -0.95791963387872;
        n[5] = 0.15772038513228;
        n[6] = -0.016616417199501;
        n[7] = 0.00081214629983568;
        n[8] = 0.00028319080123804;
        n[9] = -0.00060706301565874;
        n[10] = -0.018990068218419;
        n[11] = -0.032529748770505;
        n[12] = -0.021841717175414;
        n[13] = -0.00005283835796993;
        n[14] = -0.00047184321073267;
        n[15] = -0.00030001780793026;
        n[16] = 0.000047661393906987;
        n[17] = -0.0000044141845330846;
        n[18] = -0.00000000000000072694996297594;
        n[19] = -0.000031679644845054;
        n[20] = -0.0000028270797985312;
        n[21] = -0.00000000085205128120103;
        n[22] = -0.0000022425281908;
        n[23] = -0.00000065171222895601;
        n[24] = -0.00000000000014341729937924;
        n[25] = -0.00000040516996860117;
        n[26] = -0.0000000012734301741641;
        n[27] = -0.00000000017424871230634;
        n[28] = -6.8762131295531E-19;
        n[29] = 1.4478307828521E-20;
        n[30] = 2.6335781662795E-23;
        n[31] = -1.1947622640071E-23;
        n[32] = 1.8228094581404E-24;
        n[33] = -9.3537087292458E-26;
        double p_star = 16.53;
        double t_star = 1386;
        double x = 0;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * Math.pow(7.1 - pi, I[i]) * Math.pow(tau - 1.222, J[i]);
        }
        return x;
    }

    private double s_5(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        return (tau * (Gamma_o_tau_5(mpa, temperature) + Gamma_r_tau_5(mpa, temperature)) - (Gamma_o_5(mpa, temperature) + Gamma_r_5(mpa, temperature))) * R;
    }

    private double Gamma_r_5(double mpa, double temperature) {
        int[] I = {1, 1, 1, 2, 2, 3};
        int[] J = {1, 2, 3, 3, 9, 7};
        double[] n = new double[6];
        n[0] = 0.0015736404855259;
        n[1] = 0.00090153761673944;
        n[2] = -0.0050270077677648;
        n[3] = 0.0000022440037409485;
        n[4] = -0.0000041163275453471;
        n[5] = 0.000000037919454822955;
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * Math.pow(pi, I[i]) * Math.pow(tau, J[i]);
        }
        return x;
    }

    private double Gamma_o_5(double mpa, double temperature) {
        int[] J = {0, 1, -3, -2, -1, 2};
        double[] n = new double[6];
        n[0] = -13.179983674201;
        n[1] = 6.8540841634434;
        n[2] = -0.024805148933466;
        n[3] = 0.36901534980333;
        n[4] = -3.1161318213925;
        n[5] = -0.32961626538917;

        double p_star = 1;
        double t_star = 1000;
        double x = 0;
        double y = 1;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * Math.pow(tau, J[i]);
        }
        return x;
    }


    /*
    u_pt 计算
     */
    private double um(double mpa, double temperature, double x) {
        double h=-1000;
        if (Math.abs(temperature - 350) < 0.0001) {
            temperature = 350;
        }
        if (Math.abs(x - 0) <= 0.001) {
            if (temperature <= 350) {
                h = u_1(mpa, temperature);
            }
            else{
                if (mpa <= 19.0088118917393) {
                    h = u_3c(mpa, temperature);
                } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                    h = u_3s(mpa, temperature);
                } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                    h = u_3s(mpa, temperature);
                } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                    h = u_3u(mpa, temperature);
                } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                    h = u_3u(mpa, temperature);
                } else if (mpa > 21.93161551 && mpa <= 22.064) {
                    h = u_3y(mpa, temperature);
                }
            }
        }
        if (Math.abs(x - 1) <= 0.001) {
            if (temperature <= 350) {
                h = u_2(mpa, temperature);
            }
            else{
                if (mpa <= 19.0088118917393) {
                    h = u_3t(mpa, temperature);
                } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                    h = u_3t(mpa, temperature);
                } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                    h = u_3r(mpa, temperature);
                } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                    h = u_3x(mpa, temperature);
                } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                    h = u_3z(mpa, temperature);
                } else if (mpa > 21.93161551 && mpa <= 22.064) {
                    h = u_3z(mpa, temperature);
                }
            }

        }

        return h;
    }

    private double u_3y(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3y(mpa, temperature), temperature);
    }

    private double u_3x(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3x(mpa, temperature), temperature);
    }

    private double u_3w(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3w(mpa, temperature), temperature);
    }

    private double u_3v(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3v(mpa, temperature), temperature);
    }

    private double u_3u(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3u(mpa, temperature), temperature);
    }

    private double u_3t(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3t(mpa, temperature), temperature);
    }

    private double u_3s(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3s(mpa, temperature), temperature);
    }

    private double u_3r(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3r(mpa, temperature), temperature);
    }

    private double u_3q(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3q(mpa, temperature), temperature);
    }

    private double u_3p(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3p(mpa, temperature), temperature);
    }

    private double u_3o(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3o(mpa, temperature), temperature);
    }

    private double u_3n(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3n(mpa, temperature), temperature);
    }

    private double u_3m(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3m(mpa, temperature), temperature);
    }

    private double u_3l(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3l(mpa, temperature), temperature);
    }

    private double u_3k(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3k(mpa, temperature), temperature);
    }

    private double u_3j(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3j(mpa, temperature), temperature);
    }

    private double u_3i(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3i(mpa, temperature), temperature);
    }

    private double u_3h(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3h(mpa, temperature), temperature);
    }

    private double u_3g(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3g(mpa, temperature), temperature);
    }

    private double u_3f(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3f(mpa, temperature), temperature);
    }

    private double u_3e(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3e(mpa, temperature), temperature);
    }

    private double u_3d(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3d(mpa, temperature), temperature);
    }

    private double u_3c(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3c(mpa, temperature), temperature);
    }

    private double u_3b(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3b(mpa, temperature), temperature);
    }

    private double u_3a(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3a(mpa, temperature), temperature);
    }

    private double u_3z(double mpa, double temperature) {
        double a = 1;
        return u_3(1 / v3x(mpa, temperature), temperature);
    }

    private double u_2(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        return (tau * (Gamma_o_tau_2(mpa, temperature) + Gamma_r_tau_2(mpa, temperature)) - pi * (Gamma_o_pi_2(mpa, temperature) + Gamma_r_pi_2(mpa, temperature))) * T * R;
    }

    private double u_3(double rho, double temperature) {
        double rho_star = 322;
        double t_star = 647.096;
        double T = temperature + 273.15;
        double delta = rho / rho_star;
        double tau = t_star / T;

        return tau * phi_tau_3(rho, temperature) * T * R;

    }

    private double u_1(double mpa, double temperature) {
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        return (tau * Gamma_tau_1(mpa, temperature) - pi * Gamma_pi_1(mpa, temperature)) * R * T;

    }

    private double u_5(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        return (tau * (Gamma_o_tau_5(mpa, temperature) + Gamma_r_tau_5(mpa, temperature)) - pi * (Gamma_o_pi_5(mpa, temperature) + Gamma_r_pi_5(mpa, temperature))) * T * R;
    }


    /*
cv_pt计算
 */
    private double cvm(double mpa, double temperature, double x) {
        double h=-1000;
        if (Math.abs(temperature - 350) < 0.0001) {
            temperature = 350;
        }
        if (Math.abs(x - 0) <= 0.001) {
            if (temperature <= 350) {
                h = cv_1(mpa, temperature);
            }
            else{
                if (mpa <= 19.0088118917393) {
                    h = cv_3c(mpa, temperature);
                } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                    h = cv_3s(mpa, temperature);
                } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                    h = cv_3s(mpa, temperature);
                } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                    h = cv_3u(mpa, temperature);
                } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                    h = cv_3u(mpa, temperature);
                } else if (mpa > 21.93161551 && mpa <= 22.064) {
                    h = cv_3y(mpa, temperature);
                }
            }

        }
        if (Math.abs(x - 1) <= 0.001) {
            if (temperature <= 350) {
                h = cv_2(mpa, temperature);
            }

            else{
                if (mpa <= 19.0088118917393) {
                    h = cv_3t(mpa, temperature);
                } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                    h = cv_3t(mpa, temperature);
                } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                    h = cv_3r(mpa, temperature);
                } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                    h = cv_3x(mpa, temperature);
                } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                    h = cv_3z(mpa, temperature);
                } else if (mpa > 21.93161551 && mpa <= 22.064) {
                    h = cv_3z(mpa, temperature);
                }
            }

        }

        return h;
    }

    private double cv_3y(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3y(mpa, temperature), temperature);
    }

    private double cv_3x(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3x(mpa, temperature), temperature);
    }

    private double cv_3w(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3w(mpa, temperature), temperature);
    }

    private double cv_3v(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3v(mpa, temperature), temperature);
    }

    private double cv_3u(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3u(mpa, temperature), temperature);
    }

    private double cv_3t(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3t(mpa, temperature), temperature);
    }

    private double cv_3s(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3s(mpa, temperature), temperature);
    }

    private double cv_3r(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3r(mpa, temperature), temperature);
    }

    private double cv_3q(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3q(mpa, temperature), temperature);
    }

    private double cv_3p(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3p(mpa, temperature), temperature);
    }

    private double cv_3o(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3o(mpa, temperature), temperature);
    }

    private double cv_3n(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3n(mpa, temperature), temperature);
    }

    private double cv_3m(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3m(mpa, temperature), temperature);
    }

    private double cv_3l(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3l(mpa, temperature), temperature);
    }

    private double cv_3k(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3k(mpa, temperature), temperature);
    }

    private double cv_3j(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3j(mpa, temperature), temperature);
    }

    private double cv_3i(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3i(mpa, temperature), temperature);
    }

    private double cv_3h(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3h(mpa, temperature), temperature);
    }

    private double cv_3g(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3g(mpa, temperature), temperature);
    }

    private double cv_3f(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3f(mpa, temperature), temperature);
    }

    private double cv_3e(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3e(mpa, temperature), temperature);
    }

    private double cv_3d(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3d(mpa, temperature), temperature);
    }

    private double cv_3c(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3c(mpa, temperature), temperature);
    }

    private double cv_3b(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3b(mpa, temperature), temperature);
    }

    private double cv_3a(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3a(mpa, temperature), temperature);
    }

    private double cv_3z(double mpa, double temperature) {
        double a = 1;
        return cv_3(1 / v3z(mpa, temperature), temperature);
    }

    private double cv_2(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        double fi = (-1 * Math.pow(tau, 2) * (Gamma_o_tautau_2(mpa, temperature) + Gamma_r_tautau_2(mpa, temperature)) - Math.pow((1 + pi * Gamma_r_pi_2(mpa, temperature) - tau * pi * Gamma_r_pitau_2(mpa, temperature)), 2) / (1 - Math.pow(pi, 2) * Gamma_r_pipi_2(mpa, temperature))) * R;

        return fi;
    }

    private double Gamma_r_pitau_2(double mpa, double temperature) {

        int[] I = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 6, 6, 6, 7, 7, 7, 8, 8, 9, 10, 10, 10, 16, 16, 18, 20, 20, 20, 21, 22, 23, 24, 24, 24};
        int[] J = {0, 1, 2, 3, 6, 1, 2, 4, 7, 36, 0, 1, 3, 6, 35, 1, 2, 3, 7, 3, 16, 35, 0, 11, 25, 8, 36, 13, 4, 10, 14, 29, 50, 57, 20, 35, 48, 21, 53, 39, 26, 40, 58};
        double[] n = new double[43];
        n[0] = -0.0017731742473213;
        n[1] = -0.017834862292358;
        n[2] = -0.045996013696365;
        n[3] = -0.057581259083432;
        n[4] = -0.05032527872793;
        n[5] = -0.00003303264167023;
        n[6] = -0.00018948987516315;
        n[7] = -0.0039392777243355;
        n[8] = -0.043797295650573;
        n[9] = -0.000026674547914087;
        n[10] = 0.000000020481737692309;
        n[11] = 0.00000043870667284435;
        n[12] = -0.00003227767723857;
        n[13] = -0.0015033924542148;
        n[14] = -0.040668253562649;
        n[15] = -0.00000000078847309559367;
        n[16] = 0.000000012790717852285;
        n[17] = 0.00000048225372718507;
        n[18] = 0.0000022922076337661;
        n[19] = -0.000000000016714766451061;
        n[20] = -0.0021171472321355;
        n[21] = -23.895741934104;
        n[22] = -5.905956432427E-18;
        n[23] = -0.0000012621808899101;
        n[24] = -0.038946842435739;
        n[25] = 0.000000000011256211360459;
        n[26] = -8.2311340897998;
        n[27] = 0.000000019809712802088;
        n[28] = 1.0406965210174E-19;
        n[29] = -0.00000000000010234747095929;

        n[30] = -0.0000000010018179379511;
        n[31] = -0.000000000080882908646985;
        n[32] = 0.10693031879409;
        n[33] = -0.33662250574171;
        n[34] = 8.9185845355421E-25;
        n[35] = 0.00000000000030629316876232;
        n[36] = -0.0000042002467698208;
        n[37] = -5.9056029685639E-26;
        n[38] = 0.0000037826947613457;
        n[39] = -0.0000000000000012768608934681;
        n[40] = 7.3087610595061E-29;
        n[41] = 5.5414715350778E-17;
        n[42] = -0.0000009436970724121;
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        double y = 1;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * I[i] * Math.pow(pi, (I[i] - 1)) * J[i] * Math.pow((tau - 0.5), (J[i] - 1));
        }
        return x;
    }

    private double Gamma_r_pipi_2(double mpa, double temperature) {
        int[] I = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 6, 6, 6, 7, 7, 7, 8, 8, 9, 10, 10, 10, 16, 16, 18, 20, 20, 20, 21, 22, 23, 24, 24, 24};
        int[] J = {0, 1, 2, 3, 6, 1, 2, 4, 7, 36, 0, 1, 3, 6, 35, 1, 2, 3, 7, 3, 16, 35, 0, 11, 25, 8, 36, 13, 4, 10, 14, 29, 50, 57, 20, 35, 48, 21, 53, 39, 26, 40, 58};
        double[] n = new double[43];
        n[0] = -0.0017731742473213;
        n[1] = -0.017834862292358;
        n[2] = -0.045996013696365;
        n[3] = -0.057581259083432;
        n[4] = -0.05032527872793;
        n[5] = -0.00003303264167023;
        n[6] = -0.00018948987516315;
        n[7] = -0.0039392777243355;
        n[8] = -0.043797295650573;
        n[9] = -0.000026674547914087;
        n[10] = 0.000000020481737692309;
        n[11] = 0.00000043870667284435;
        n[12] = -0.00003227767723857;
        n[13] = -0.0015033924542148;
        n[14] = -0.040668253562649;
        n[15] = -0.00000000078847309559367;
        n[16] = 0.000000012790717852285;
        n[17] = 0.00000048225372718507;
        n[18] = 0.0000022922076337661;
        n[19] = -0.000000000016714766451061;
        n[20] = -0.0021171472321355;
        n[21] = -23.895741934104;
        n[22] = -5.905956432427E-18;
        n[23] = -0.0000012621808899101;
        n[24] = -0.038946842435739;
        n[25] = 0.000000000011256211360459;
        n[26] = -8.2311340897998;
        n[27] = 0.000000019809712802088;
        n[28] = 1.0406965210174E-19;
        n[29] = -0.00000000000010234747095929;

        n[30] = -0.0000000010018179379511;
        n[31] = -0.000000000080882908646985;
        n[32] = 0.10693031879409;
        n[33] = -0.33662250574171;
        n[34] = 8.9185845355421E-25;
        n[35] = 0.00000000000030629316876232;
        n[36] = -0.0000042002467698208;
        n[37] = -5.9056029685639E-26;
        n[38] = 0.0000037826947613457;
        n[39] = -0.0000000000000012768608934681;
        n[40] = 7.3087610595061E-29;
        n[41] = 5.5414715350778E-17;
        n[42] = -0.0000009436970724121;
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        double y = 1;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * I[i] * (I[i] - 1) * Math.pow(pi, (I[i] - 2)) * Math.pow((tau - 0.5), J[i]);
        }
        return x;
    }

    private double Gamma_r_tautau_2(double mpa, double temperature) {
        int[] I = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 6, 6, 6, 7, 7, 7, 8, 8, 9, 10, 10, 10, 16, 16, 18, 20, 20, 20, 21, 22, 23, 24, 24, 24};
        int[] J = {0, 1, 2, 3, 6, 1, 2, 4, 7, 36, 0, 1, 3, 6, 35, 1, 2, 3, 7, 3, 16, 35, 0, 11, 25, 8, 36, 13, 4, 10, 14, 29, 50, 57, 20, 35, 48, 21, 53, 39, 26, 40, 58};
        double[] n = new double[43];
        n[0] = -0.0017731742473213;
        n[1] = -0.017834862292358;
        n[2] = -0.045996013696365;
        n[3] = -0.057581259083432;
        n[4] = -0.05032527872793;
        n[5] = -0.00003303264167023;
        n[6] = -0.00018948987516315;
        n[7] = -0.0039392777243355;
        n[8] = -0.043797295650573;
        n[9] = -0.000026674547914087;
        n[10] = 0.000000020481737692309;
        n[11] = 0.00000043870667284435;
        n[12] = -0.00003227767723857;
        n[13] = -0.0015033924542148;
        n[14] = -0.040668253562649;
        n[15] = -0.00000000078847309559367;
        n[16] = 0.000000012790717852285;
        n[17] = 0.00000048225372718507;
        n[18] = 0.0000022922076337661;
        n[19] = -0.000000000016714766451061;
        n[20] = -0.0021171472321355;
        n[21] = -23.895741934104;
        n[22] = -5.905956432427E-18;
        n[23] = -0.0000012621808899101;
        n[24] = -0.038946842435739;
        n[25] = 0.000000000011256211360459;
        n[26] = -8.2311340897998;
        n[27] = 0.000000019809712802088;
        n[28] = 1.0406965210174E-19;
        n[29] = -0.00000000000010234747095929;

        n[30] = -0.0000000010018179379511;
        n[31] = -0.000000000080882908646985;
        n[32] = 0.10693031879409;
        n[33] = -0.33662250574171;
        n[34] = 8.9185845355421E-25;
        n[35] = 0.00000000000030629316876232;
        n[36] = -0.0000042002467698208;
        n[37] = -5.9056029685639E-26;
        n[38] = 0.0000037826947613457;
        n[39] = -0.0000000000000012768608934681;
        n[40] = 7.3087610595061E-29;
        n[41] = 5.5414715350778E-17;
        n[42] = -0.0000009436970724121;
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        double y = 1;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * Math.pow(pi, I[i]) * J[i] * (J[i] - 1) * Math.pow((tau - 0.5), (J[i] - 2));
        }
        return x;
    }

    private double Gamma_o_tautau_2(double mpa, double temperature) {
        int[] J = {0, 1, -5, -4, -3, -2, -1, 2, 3};
        double[] n = new double[9];
        n[0] = -9.6927686500217;
        n[1] = 10.086655968018;
        n[2] = -0.005608791128302;
        n[3] = 0.071452738081455;
        n[4] = -0.40710498223928;
        n[5] = 1.4240819171444;
        n[6] = -4.383951131945;
        n[7] = -0.28408632460772;
        n[8] = 0.021268463753307;
        double p_star = 1;
        double t_star = 540;
        double x = 0;
        double y = 1;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * J[i] * (J[i] - 1) * Math.pow(tau, (J[i] - 2));
        }
        return x;
    }

    private double cv_3(double rho, double temperature) {
        double rho_star = 322;
        double t_star = 647.096;
        double T = temperature + 273.15;
        double delta = rho / rho_star;
        double tau = t_star / T;

        double first = (-1 * Math.pow(tau, 2) * phi_tautau_3(rho, temperature)) * R;
        return first;

    }

    private double phi_tautau_3(double rho, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 8, 9, 9, 10, 10, 11};
        int[] J = {0, 0, 1, 2, 7, 10, 12, 23, 2, 6, 15, 17, 0, 2, 6, 7, 22, 26, 0, 2, 4, 16, 26, 0, 2, 4, 26, 1, 3, 26, 0, 2, 26, 2, 26, 2, 26, 0, 1, 26};
        double[] n = new double[40];
        n[0] = 1.0658070028513;
        n[1] = -15.732845290239;
        n[2] = 20.944396974307;
        n[3] = -7.6867707878716;
        n[4] = 2.6185947787954;
        n[5] = -2.808078114862;
        n[6] = 1.2053369696517;
        n[7] = -0.0084566812812502;
        n[8] = -1.2654315477714;
        n[9] = -1.1524407806681;
        n[10] = 0.88521043984318;
        n[11] = -0.64207765181607;
        n[12] = 0.38493460186671;
        n[13] = -0.85214708824206;
        n[14] = 4.8972281541877;
        n[15] = -3.0502617256965;
        n[16] = 0.039420536879154;
        n[17] = 0.12558408424308;
        n[18] = -0.2799932969871;
        n[19] = 1.389979956946;
        n[20] = -2.018991502357;
        n[21] = -0.0082147637173963;
        n[22] = -0.47596035734923;
        n[23] = 0.0439840744735;
        n[24] = -0.44476435428739;
        n[25] = 0.90572070719733;
        n[26] = 0.70522450087967;
        n[27] = 0.10770512626332;
        n[28] = -0.32913623258954;
        n[29] = -0.50871062041158;
        n[30] = -0.022175400873096;
        n[31] = 0.094260751665092;
        n[32] = 0.16436278447961;
        n[33] = -0.013503372241348;
        n[34] = -0.014834345352472;
        n[35] = 0.00057922953628084;
        n[36] = 0.0032308904703711;
        n[37] = 0.000080964802996215;
        n[38] = -0.00016557679795037;
        n[39] = -0.000044923899061815;
        double rho_star = 322;
        double x = 1;
        double t_stat = 647.096;
        double T = temperature + 273.15;
        double tau = t_stat / T;
        double delta = rho / rho_star;
        double y = 0;
        double two;
        double one;
        for (int i = 1; i < n.length; i++) {
            y = y + n[i] * Math.pow(delta, I[i]) * J[i] * (J[i] - 1) * Math.pow(tau, (J[i] - 2));

        }
        return y;
    }

    private double cv_1(double mpa, double temperature) {
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double f = (-1 * Math.pow(tau, 2) * Gamma_tautau_1(mpa, temperature) + Math.pow((Gamma_pi_1(mpa, temperature) - tau * Gamma_pitau_1(mpa, temperature)), 2) / Gamma_pipi_1(mpa, temperature)) * R;
        return f;


    }

    private double Gamma_pipi_1(double mpa, double temperature) {

        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 8, 8, 21, 23, 29, 30, 31, 32};
        int[] J = {-2, -1, 0, 1, 2, 3, 4, 5, -9, -7, -1, 0, 1, 3, -3, 0, 1, 3, 17, -4, 0, 6, -5, -2, 10, -8, -11, -6, -29, -31, -38, -39, -40, -41};
        double[] n = new double[34];
        n[0] = 0.14632971213167;
        n[1] = -0.84548187169114;
        n[2] = -3.756360367204;
        n[3] = 3.3855169168385;
        n[4] = -0.95791963387872;
        n[5] = 0.15772038513228;
        n[6] = -0.016616417199501;
        n[7] = 0.00081214629983568;
        n[8] = 0.00028319080123804;
        n[9] = -0.00060706301565874;
        n[10] = -0.018990068218419;
        n[11] = -0.032529748770505;
        n[12] = -0.021841717175414;
        n[13] = -0.00005283835796993;
        n[14] = -0.00047184321073267;
        n[15] = -0.00030001780793026;
        n[16] = 0.000047661393906987;
        n[17] = -0.0000044141845330846;
        n[18] = -0.00000000000000072694996297594;
        n[19] = -0.000031679644845054;
        n[20] = -0.0000028270797985312;
        n[21] = -0.00000000085205128120103;
        n[22] = -0.0000022425281908;
        n[23] = -0.00000065171222895601;
        n[24] = -0.00000000000014341729937924;
        n[25] = -0.00000040516996860117;
        n[26] = -0.0000000012734301741641;
        n[27] = -0.00000000017424871230634;
        n[28] = -6.8762131295531E-19;
        n[29] = 1.4478307828521E-20;

        n[30] = 2.6335781662795E-23;
        n[31] = -1.1947622640071E-23;
        n[32] = 1.8228094581404E-24;
        n[33] = -9.3537087292458E-26;
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        double y = 1;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * I[i] * (I[i] - 1) * Math.pow((7.1 - pi), (I[i] - 2)) * Math.pow((tau - 1.222), J[i]);
        }
        return x;
    }

    private double Gamma_pitau_1(double mpa, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 8, 8, 21, 23, 29, 30, 31, 32};
        int[] J = {-2, -1, 0, 1, 2, 3, 4, 5, -9, -7, -1, 0, 1, 3, -3, 0, 1, 3, 17, -4, 0, 6, -5, -2, 10, -8, -11, -6, -29, -31, -38, -39, -40, -41};
        double[] n = new double[34];
        n[0] = 0.14632971213167;
        n[1] = -0.84548187169114;
        n[2] = -3.756360367204;
        n[3] = 3.3855169168385;
        n[4] = -0.95791963387872;
        n[5] = 0.15772038513228;
        n[6] = -0.016616417199501;
        n[7] = 0.00081214629983568;
        n[8] = 0.00028319080123804;
        n[9] = -0.00060706301565874;
        n[10] = -0.018990068218419;
        n[11] = -0.032529748770505;
        n[12] = -0.021841717175414;
        n[13] = -0.00005283835796993;
        n[14] = -0.00047184321073267;
        n[15] = -0.00030001780793026;
        n[16] = 0.000047661393906987;
        n[17] = -0.0000044141845330846;
        n[18] = -0.00000000000000072694996297594;
        n[19] = -0.000031679644845054;
        n[20] = -0.0000028270797985312;
        n[21] = -0.00000000085205128120103;
        n[22] = -0.0000022425281908;
        n[23] = -0.00000065171222895601;
        n[24] = -0.00000000000014341729937924;
        n[25] = -0.00000040516996860117;
        n[26] = -0.0000000012734301741641;
        n[27] = -0.00000000017424871230634;
        n[28] = -6.8762131295531E-19;
        n[29] = 1.4478307828521E-20;

        n[30] = 2.6335781662795E-23;
        n[31] = -1.1947622640071E-23;
        n[32] = 1.8228094581404E-24;
        n[33] = -9.3537087292458E-26;
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        double y = 1;
        for (int i = 0; i < n.length; i++) {

            x = x + (-1) * n[i] * I[i] * Math.pow((7.1 - pi), (I[i] - 1)) * J[i] * Math.pow((tau - 1.222), (J[i] - 1));
        }
        return x;
    }

    private double Gamma_tautau_1(double mpa, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 8, 8, 21, 23, 29, 30, 31, 32};
        int[] J = {-2, -1, 0, 1, 2, 3, 4, 5, -9, -7, -1, 0, 1, 3, -3, 0, 1, 3, 17, -4, 0, 6, -5, -2, 10, -8, -11, -6, -29, -31, -38, -39, -40, -41};
        double[] n = new double[34];
        n[0] = 0.14632971213167;
        n[1] = -0.84548187169114;
        n[2] = -3.756360367204;
        n[3] = 3.3855169168385;
        n[4] = -0.95791963387872;
        n[5] = 0.15772038513228;
        n[6] = -0.016616417199501;
        n[7] = 0.00081214629983568;
        n[8] = 0.00028319080123804;
        n[9] = -0.00060706301565874;
        n[10] = -0.018990068218419;
        n[11] = -0.032529748770505;
        n[12] = -0.021841717175414;
        n[13] = -0.00005283835796993;
        n[14] = -0.00047184321073267;
        n[15] = -0.00030001780793026;
        n[16] = 0.000047661393906987;
        n[17] = -0.0000044141845330846;
        n[18] = -0.00000000000000072694996297594;
        n[19] = -0.000031679644845054;
        n[20] = -0.0000028270797985312;
        n[21] = -0.00000000085205128120103;
        n[22] = -0.0000022425281908;
        n[23] = -0.00000065171222895601;
        n[24] = -0.00000000000014341729937924;
        n[25] = -0.00000040516996860117;
        n[26] = -0.0000000012734301741641;
        n[27] = -0.00000000017424871230634;
        n[28] = -6.8762131295531E-19;
        n[29] = 1.4478307828521E-20;

        n[30] = 2.6335781662795E-23;
        n[31] = -1.1947622640071E-23;
        n[32] = 1.8228094581404E-24;
        n[33] = -9.3537087292458E-26;
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        double y = 1;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * Math.pow((7.1 - pi), I[i]) * J[i] * (J[i] - 1) * Math.pow((tau - 1.222), (J[i] - 2));
        }
        return x;
    }

    private double cv_5(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;


        return (-1 * Math.pow(tau, 2) * (Gamma_o_tautau_5(mpa, temperature) + Gamma_r_tautau_5(mpa, temperature)) - Math.pow((1 + pi * Gamma_r_pi_5(mpa, temperature) - tau * pi * Gamma_r_pitau_5(mpa, temperature)), 2) / (1 - Math.pow(pi, 2) * Gamma_r_pipi_5(mpa, temperature))) * R;
    }

    private double Gamma_r_pipi_5(double mpa, double temperature) {
        int[] I = {1, 1, 1, 2, 2, 3};
        int[] J = {1, 2, 3, 3, 9, 7};
        double[] n = new double[6];
        n[0] = 0.0015736404855259;
        n[1] = 0.00090153761673944;
        n[2] = -0.0050270077677648;
        n[3] = 0.0000022440037409485;
        n[4] = -0.0000041163275453471;
        n[5] = 0.000000037919454822955;
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * I[i] * (I[i] - 1) * Math.pow(pi, (I[i] - 2)) * Math.pow(tau, J[i]);
        }
        return x;
    }

    private double Gamma_r_pitau_5(double mpa, double temperature) {
        int[] I = {1, 1, 1, 2, 2, 3};
        int[] J = {1, 2, 3, 3, 9, 7};
        double[] n = new double[6];
        n[0] = 0.0015736404855259;
        n[1] = 0.00090153761673944;
        n[2] = -0.0050270077677648;
        n[3] = 0.0000022440037409485;
        n[4] = -0.0000041163275453471;
        n[5] = 0.000000037919454822955;
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * I[i] * Math.pow(pi, (I[i] - 1)) * J[i] * Math.pow(tau, (J[i] - 1));
        }
        return x;
    }

    private double Gamma_r_tautau_5(double mpa, double temperature) {
        int[] I = {1, 1, 1, 2, 2, 3};
        int[] J = {1, 2, 3, 3, 9, 7};
        double[] n = new double[6];
        n[0] = 0.0015736404855259;
        n[1] = 0.00090153761673944;
        n[2] = -0.0050270077677648;
        n[3] = 0.0000022440037409485;
        n[4] = -0.0000041163275453471;
        n[5] = 0.000000037919454822955;
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        double x = 0;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * Math.pow(pi, I[i]) * J[i] * (J[i] - 1) * Math.pow(tau, (J[i] - 2));
        }
        return x;
    }

    private double Gamma_o_tautau_5(double mpa, double temperature) {

        int[] J = {0, 1, -3, -2, -1, 2};
        double[] n = new double[6];
        n[0] = -13.179983674201;
        n[1] = 6.8540841634434;
        n[2] = -0.024805148933466;
        n[3] = 0.36901534980333;
        n[4] = -3.1161318213925;
        n[5] = -0.32961626538917;

        double p_star = 1;
        double t_star = 1000;
        double x = 0;
        double y = 1;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;
        for (int i = 0; i < n.length; i++) {

            x = x + n[i] * J[i] * (J[i] - 1) * Math.pow(tau, (J[i] - 2));
        }
        return x;

    }




    /*
    cp 计算
     */

    private double cpm(double mpa, double temperature, double x) {
        double h=-1000;
        if (Math.abs(temperature - 350) < 0.0001) {
            temperature = 350;
        }
        if (Math.abs(x - 0) <= 0.001) {
            if (temperature <= 350) {
                h = cp_1(mpa, temperature);
            }
           else{
               if (mpa <= 19.0088118917393) {
                    h = cp_3c(mpa, temperature);
                } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                    h = cp_3s(mpa, temperature);
                } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                    h = cp_3s(mpa, temperature);
                } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                    h = cp_3u(mpa, temperature);
                } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                    h = cp_3u(mpa, temperature);
                } else if (mpa > 21.93161551 && mpa <= 22.064) {
                    h = cp_3y(mpa, temperature);
                }
            }
        } if (Math.abs(x - 1) <= 0.001) {
            if (temperature <= 350) {
                h = cp_2(mpa, temperature);
            }
            else{
                if (mpa <= 19.0088118917393) {
                    h = cp_3t(mpa, temperature);
                } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                    h = cp_3t(mpa, temperature);
                } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                    h = cp_3r(mpa, temperature);
                } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                    h = cp_3x(mpa, temperature);
                } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                    h = cp_3z(mpa, temperature);
                } else if (mpa > 21.93161551 && mpa <= 22.064) {
                    h = cp_3z(mpa, temperature);
                }
            }

        }

        return h;
    }

    private double cp_3y(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3y(mpa, temperature), temperature);
    }

    private double cp_3x(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3x(mpa, temperature), temperature);
    }

    private double cp_3w(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3w(mpa, temperature), temperature);
    }

    private double cp_3v(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3v(mpa, temperature), temperature);
    }

    private double cp_3u(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3u(mpa, temperature), temperature);
    }

    private double cp_3t(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3t(mpa, temperature), temperature);
    }

    private double cp_3s(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3s(mpa, temperature), temperature);
    }

    private double cp_3r(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3r(mpa, temperature), temperature);
    }

    private double cp_3q(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3q(mpa, temperature), temperature);
    }

    private double cp_3p(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3p(mpa, temperature), temperature);
    }

    private double cp_3o(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3o(mpa, temperature), temperature);
    }

    private double cp_3n(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3n(mpa, temperature), temperature);
    }

    private double cp_3m(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3m(mpa, temperature), temperature);
    }

    private double cp_3l(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3l(mpa, temperature), temperature);
    }

    private double cp_3k(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3k(mpa, temperature), temperature);
    }

    private double cp_3j(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3j(mpa, temperature), temperature);
    }

    private double cp_3i(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3i(mpa, temperature), temperature);
    }

    private double cp_3h(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3h(mpa, temperature), temperature);
    }

    private double cp_3g(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3g(mpa, temperature), temperature);
    }

    private double cp_3f(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3f(mpa, temperature), temperature);
    }

    private double cp_3e(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3e(mpa, temperature), temperature);
    }

    private double cp_3d(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3d(mpa, temperature), temperature);
    }

    private double cp_3c(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3c(mpa, temperature), temperature);
    }

    private double cp_3b(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3b(mpa, temperature), temperature);
    }

    private double cp_3a(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3a(mpa, temperature), temperature);
    }

    private double cp_3z(double mpa, double temperature) {
        double a = 1;
        return cp_3(1 / v3z(mpa, temperature), temperature);
    }

    private double cp_2(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        double fi = (-1 * Math.pow(tau, 2) * (Gamma_o_tautau_2(mpa, temperature) + Gamma_r_tautau_2(mpa, temperature))) * R;

        return fi;
    }

    private double cp_3(double rho, double temperature) {
        double rho_star = 322;
        double t_star = 647.096;
        double T = temperature + 273.15;
        double delta = rho / rho_star;
        double tau = t_star / T;

        double first = (-1 * Math.pow(tau, 2) * phi_tautau_3(rho, temperature) + Math.pow((delta * phi_delta_3(rho, temperature) - delta * tau * phi_deltatau_3(rho, temperature)), 2) / (2 * delta * phi_delta_3(rho, temperature) + Math.pow(delta, 2) * phi_deltadelta_3(rho, temperature))) * R;
        return first;

    }

    private double phi_deltadelta_3(double rho, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 8, 9, 9, 10, 10, 11};
        int[] J = {0, 0, 1, 2, 7, 10, 12, 23, 2, 6, 15, 17, 0, 2, 6, 7, 22, 26, 0, 2, 4, 16, 26, 0, 2, 4, 26, 1, 3, 26, 0, 2, 26, 2, 26, 2, 26, 0, 1, 26};
        double[] n = new double[40];
        n[0] = 1.0658070028513;
        n[1] = -15.732845290239;
        n[2] = 20.944396974307;
        n[3] = -7.6867707878716;
        n[4] = 2.6185947787954;
        n[5] = -2.808078114862;
        n[6] = 1.2053369696517;
        n[7] = -0.0084566812812502;
        n[8] = -1.2654315477714;
        n[9] = -1.1524407806681;
        n[10] = 0.88521043984318;
        n[11] = -0.64207765181607;
        n[12] = 0.38493460186671;
        n[13] = -0.85214708824206;
        n[14] = 4.8972281541877;
        n[15] = -3.0502617256965;
        n[16] = 0.039420536879154;
        n[17] = 0.12558408424308;
        n[18] = -0.2799932969871;
        n[19] = 1.389979956946;
        n[20] = -2.018991502357;
        n[21] = -0.0082147637173963;
        n[22] = -0.47596035734923;
        n[23] = 0.0439840744735;
        n[24] = -0.44476435428739;
        n[25] = 0.90572070719733;
        n[26] = 0.70522450087967;
        n[27] = 0.10770512626332;
        n[28] = -0.32913623258954;
        n[29] = -0.50871062041158;
        n[30] = -0.022175400873096;
        n[31] = 0.094260751665092;
        n[32] = 0.16436278447961;
        n[33] = -0.013503372241348;
        n[34] = -0.014834345352472;
        n[35] = 0.00057922953628084;
        n[36] = 0.0032308904703711;
        n[37] = 0.000080964802996215;
        n[38] = -0.00016557679795037;
        n[39] = -0.000044923899061815;
        double rho_star = 322;
        double x = 1;
        double t_stat = 647.096;
        double T = temperature + 273.15;
        double tau = t_stat / T;
        double delta = rho / rho_star;
        double y = (-n[0]) / Math.pow(delta, 2);

        double one;
        for (int i = 1; i < n.length; i++) {

            y = y + n[i] * I[i] * (I[i] - 1) * Math.pow(delta, (I[i] - 2)) * Math.pow(tau, J[i]);


        }
        return y;
    }

    private double phi_deltatau_3(double rho, double temperature) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 8, 9, 9, 10, 10, 11};
        int[] J = {0, 0, 1, 2, 7, 10, 12, 23, 2, 6, 15, 17, 0, 2, 6, 7, 22, 26, 0, 2, 4, 16, 26, 0, 2, 4, 26, 1, 3, 26, 0, 2, 26, 2, 26, 2, 26, 0, 1, 26};
        double[] n = new double[40];
        n[0] = 1.0658070028513;
        n[1] = -15.732845290239;
        n[2] = 20.944396974307;
        n[3] = -7.6867707878716;
        n[4] = 2.6185947787954;
        n[5] = -2.808078114862;
        n[6] = 1.2053369696517;
        n[7] = -0.0084566812812502;
        n[8] = -1.2654315477714;
        n[9] = -1.1524407806681;
        n[10] = 0.88521043984318;
        n[11] = -0.64207765181607;
        n[12] = 0.38493460186671;
        n[13] = -0.85214708824206;
        n[14] = 4.8972281541877;
        n[15] = -3.0502617256965;
        n[16] = 0.039420536879154;
        n[17] = 0.12558408424308;
        n[18] = -0.2799932969871;
        n[19] = 1.389979956946;
        n[20] = -2.018991502357;
        n[21] = -0.0082147637173963;
        n[22] = -0.47596035734923;
        n[23] = 0.0439840744735;
        n[24] = -0.44476435428739;
        n[25] = 0.90572070719733;
        n[26] = 0.70522450087967;
        n[27] = 0.10770512626332;
        n[28] = -0.32913623258954;
        n[29] = -0.50871062041158;
        n[30] = -0.022175400873096;
        n[31] = 0.094260751665092;
        n[32] = 0.16436278447961;
        n[33] = -0.013503372241348;
        n[34] = -0.014834345352472;
        n[35] = 0.00057922953628084;
        n[36] = 0.0032308904703711;
        n[37] = 0.000080964802996215;
        n[38] = -0.00016557679795037;
        n[39] = -0.000044923899061815;
        double rho_star = 322;
        double x = 1;
        double t_stat = 647.096;
        double T = temperature + 273.15;
        double tau = t_stat / T;
        double delta = rho / rho_star;
        double y = 0;
        double two;
        double one;
        for (int i = 1; i < n.length; i++) {

            y = y + n[i] * I[i] * Math.pow(delta, (I[i] - 1)) * J[i] * Math.pow(tau, (J[i] - 1));


        }
        return y;
    }

    private double cp_1(double mpa, double temperature) {
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        return -1 * Math.pow(tau, 2) * Gamma_tautau_1(mpa, temperature) * R;


    }

    private double cp_5(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        return (-1 * Math.pow(tau, 2) * (Gamma_o_tautau_5(mpa, temperature) + Gamma_r_tautau_5(mpa, temperature))) * R;
    }


    /*
    w_pt 计算
     */
    private double wm(double mpa, double temperature, double x) {
        double h=-1000;
        if (Math.abs(temperature - 350) < 0.0001) {
            temperature = 350;
        }
        if (Math.abs(x - 0) <= 0.001) {
            if (temperature <= 350) {
                h = w_1(mpa, temperature);
            }
            else {
                if (mpa <= 19.0088118917393) {
                    h = w_3c(mpa, temperature);
                } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                    h = w_3s(mpa, temperature);
                } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                    h = w_3s(mpa, temperature);
                } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                    h = w_3u(mpa, temperature);
                } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                    h = w_3u(mpa, temperature);
                } else if (mpa > 21.93161551 && mpa <= 22.064) {
                    h = w_3y(mpa, temperature);
                }
            }
        }
        if (Math.abs(x - 1) <= 0.001) {
            if (temperature <= 350) {
                h = w_2(mpa, temperature);
            } else {
                if (mpa <= 19.0088118917393) {
                    h = w_3t(mpa, temperature);
                } else if (mpa > 19.0088118917393 && mpa <= 20.5) {
                    h = w_3t(mpa, temperature);
                } else if (mpa > 20.5 && mpa <= 21.04336731898) {
                    h = w_3r(mpa, temperature);
                } else if (mpa > 21.04336731898 && mpa <= 21.90096265) {
                    h = w_3x(mpa, temperature);
                } else if (mpa > 21.90096265 && mpa <= 21.93161551) {
                    h = w_3z(mpa, temperature);
                } else if (mpa > 21.93161551 && mpa <= 22.064) {
                    h = w_3z(mpa, temperature);
                }
            }
        } 
        return h;
    }

    private double w_3y(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3y(mpa, temperature), temperature);
    }

    private double w_3x(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3x(mpa, temperature), temperature);
    }

    private double w_3w(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3w(mpa, temperature), temperature);
    }

    private double w_3v(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3v(mpa, temperature), temperature);
    }

    private double w_3u(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3u(mpa, temperature), temperature);
    }

    private double w_3t(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3t(mpa, temperature), temperature);
    }

    private double w_3s(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3s(mpa, temperature), temperature);
    }

    private double w_3r(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3r(mpa, temperature), temperature);
    }

    private double w_3q(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3q(mpa, temperature), temperature);
    }

    private double w_3p(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3p(mpa, temperature), temperature);
    }

    private double w_3o(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3o(mpa, temperature), temperature);
    }

    private double w_3n(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3n(mpa, temperature), temperature);
    }

    private double w_3m(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3m(mpa, temperature), temperature);
    }

    private double w_3l(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3l(mpa, temperature), temperature);
    }

    private double w_3k(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3k(mpa, temperature), temperature);
    }

    private double w_3j(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3j(mpa, temperature), temperature);
    }

    private double w_3i(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3i(mpa, temperature), temperature);
    }

    private double w_3h(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3h(mpa, temperature), temperature);
    }

    private double w_3g(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3g(mpa, temperature), temperature);
    }

    private double w_3f(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3f(mpa, temperature), temperature);
    }

    private double w_3e(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3e(mpa, temperature), temperature);
    }

    private double w_3d(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3d(mpa, temperature), temperature);
    }

    private double w_3c(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3c(mpa, temperature), temperature);
    }

    private double w_3b(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3b(mpa, temperature), temperature);
    }

    private double w_3a(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3a(mpa, temperature), temperature);
    }

    private double w_2(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 540;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        double x = Math.pow(((Math.pow((1 + pi * Gamma_r_pi_2(mpa, temperature)), 2) / ((1 - Math.pow(pi, 2) * Gamma_r_pipi_2(mpa, temperature)) + Math.pow((1 + pi * Gamma_r_pi_2(mpa, temperature) - tau * pi * Gamma_r_pitau_2(mpa, temperature)), 2) / (Math.pow(tau, 2) * (Gamma_o_tautau_2(mpa, temperature) + Gamma_r_tautau_2(mpa, temperature))))) * 1000 * R * T), 0.5);
        return x;
    }

    private double w_3z(double mpa, double temperature) {
        double a = 1;
        return w_3(1 / v3z(mpa, temperature), temperature);
    }

    private double w_3(double rho, double temperature) {
        double rho_star = 322;
        double t_star = 647.096;
        double T = temperature + 273.15;
        double delta = rho / rho_star;
        double tau = t_star / T;
        double x = Math.pow(((2 * delta * phi_delta_3(rho, temperature) + Math.pow(delta, 2) * phi_deltadelta_3(rho, temperature) - Math.pow((delta * phi_delta_3(rho, temperature) - delta * tau * phi_deltatau_3(rho, temperature)), 2) / (Math.pow(tau, 2) * phi_tautau_3(rho, temperature))) * R * T * 1000), 0.5);
        return x;

    }

    private double w_1(double mpa, double temperature) {
        double p_star = 16.53;
        double t_star = 1386;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        return Math.pow((Math.pow(Gamma_pi_1(mpa, temperature), 2) * R * T / (Math.pow((Gamma_pi_1(mpa, temperature) - tau * Gamma_pitau_1(mpa, temperature)), 2) / Math.pow(tau, 2) / Gamma_tautau_1(mpa, temperature) - Gamma_pipi_1(mpa, temperature)) * 1000), 0.5);

    }

    private double w_5(double mpa, double temperature) {
        double p_star = 1;
        double t_star = 1000;
        double T = temperature + 273.15;
        double pi = mpa / p_star;
        double tau = t_star / T;

        double x = Math.pow(((Math.pow((1 + pi * Gamma_r_pi_5(mpa, temperature)), 2) / ((1 - Math.pow(pi, 2) * Gamma_r_pipi_5(mpa, temperature)) + Math.pow((1 + pi * Gamma_r_pi_5(mpa, temperature) - tau * pi * Gamma_r_pitau_5(mpa, temperature)), 2) / (Math.pow(tau, 2) * (Gamma_o_tautau_5(mpa, temperature) + Gamma_r_tautau_5(mpa, temperature))))) * 1000 * R * T), 0.5);


        return x;
    }


    /*
  ph计算
   */
    public String subregion_ph(double p, double h) {
        String areatype="0";
        String areatype_check;
        double temperature;
        if (p > 0.000611657 && p <= 4) {
            if (h > h_1(p, 0) && h < h_1(p, Ts(p))) {
                areatype = "1";
            }
            if (h >= h_1(p, Ts(p)) && h <= h_2(p, Ts(p))) {
                areatype = "4";
            }
            if (h > h_2(p, Ts(p)) && h <= h_2(p, 800)) {
                areatype = "2a";
            }

        }
        if (p > 4 && p <= 6.5467) {
            if (h > h_1(p, 0) && h < h_1(p, Ts(p))) {
                areatype = "1";
            }
            if (h >= h_1(p, Ts(p)) && h <= h_2(p, Ts(p))) {
                areatype = "4";
            }
            if (h > h_2(p, Ts(p)) && h <= h_2(p, 800)) {
                areatype = "2b";
            }
        }
        if (p > 6.5467 && p <= Ps(350)) {
            if (h > h_1(p, 0) && h < h_1(p, Ts(p))) {
                areatype = "1";
            }
            if (h >= h_1(p, Ts(p)) && h <= h_2(p, Ts(p))) {
                areatype = "4";
            }
            if (h > h_2(p, Ts(p)) && h <= hB2bc(p)) {
                areatype = "2c";
            }
            if (h > hB2bc(p) && h <= h_2(p, 800)) {
                areatype = "2b";
            }
        }
        if (p > Ps(350) && p <= 22.064) {
            if (h > h_1(p, 0) && h < h_1(p, 350)) {
                areatype = "1";
            }
            if (h >= h_1(p, 350) && h <= 2087.5468451172) {
                if (p > p3sat_h(h)) {
                    areatype = "3a";
                } else {
                    areatype = "4";
                }
            }
            if (h > 2087.5468451172 && h <= h_pt(p, TB23(p), 1)) {
                if (p > p3sat_h(h)) {
                    areatype = "3b";
                } else {
                    areatype = "4";
                }
            }
            if (h > h_pt(p, TB23(p), 1) && h <= hB2bc(p)) {
                areatype = "2c";
            }
            if (h > hB2bc(p) && h <= h_2(p, 800)) {
                areatype = "2b";
            }
        }
        if (p > 22.064 && p <= 100) {
            if (h > h_1(p, 0) && h <= h_1(p, 350)) {
                areatype = "1";
            }
            if (h > h_1(p, 350) && h <= h3ab(p)) {
                areatype = "3a";
            }
            if (h > h3ab(p) && h <= h_pt(p, TB23(p), 1)) {
                areatype = "3b";
            }
            if (h > h_pt(p, TB23(p), 1) && h <= hB2bc(p)) {
                areatype = "2c";
            }
            if (h > hB2bc(p) && h <= h_2(p, 800)) {
                areatype = "2b";
            }

        }
        if (areatype.equals("0")) {
            return "0";
        }

        if (areatype.equals("1")) {
            temperature = p_h_to_T_1(p, h);
        } else if (areatype.equals("2a")) {
            temperature = p_h_to_T_2a(p, h);
        } else if (areatype.equals("2b")) {
            temperature = p_h_to_T_2b(p, h);
        } else if (areatype.equals("2c")) {
            temperature = p_h_to_T_2c(p, h);
        } else if (areatype.equals("3a")) {
            temperature = p_h_to_T_3a(p, h);
        } else if (areatype.equals("3b")) {
            temperature = p_h_to_T_3b(p, h);
        } else if (areatype.equals("4")) {
            temperature = Ts(p);
        } else {
            temperature = -1;
        }

        areatype_check = subregion_pT(p, temperature);
        if (areatype.charAt(0) == '1') {
            if (areatype_check.charAt(0) == '3') {
            }
            if (!areatype.substring(0, 1).equals(areatype_check.substring(0, 1))) {
                areatype = "4";
            }
        }
        if (areatype.charAt(0) == '2') {
            if (areatype_check.charAt(0) == '3' || areatype_check.charAt(0) == '5') {
            }
            if (!areatype.substring(0, 1).equals(areatype_check.substring(0, 1))) {
                areatype = "4";
            }
        }
        if (areatype.charAt(0) == '3') {
            if (areatype_check.charAt(0) == '1' || areatype_check.charAt(0) == '2' || areatype_check.charAt(0) == '5') {
            }
            if (!areatype.substring(0, 1).equals(areatype_check.substring(0, 1))) {
                areatype = "4";
            }
        }
        if (areatype.charAt(0) == '5') {
            if (areatype_check.charAt(0) == '2') {
            }
            if (!areatype.substring(0, 1).equals(areatype_check.substring(0, 1))) {
                areatype = "4";
            }
        }
        return areatype;
    }

    private double p_h_to_T_3b(double p, double h) {
        int[] I = {-12, -12, -10, -10, -10, -10, -10, -8, -8, -8, -8, -8, -6, -6, -6, -4, -4, -3, -2, -2, -1, -1, -1, -1, -1, -1, 0, 0, 1, 3, 5, 6, 8};
        int[] J = {0, 1, 0, 1, 5, 10, 12, 0, 1, 2, 4, 10, 0, 1, 2, 0, 1, 5, 0, 4, 2, 4, 6, 10, 14, 16, 0, 2, 1, 1, 1, 1, 1};
        double[] n = new double[33];

        n[0] = 0.000032325457364492;
        n[1] = -0.000127575556587181;
        n[2] = -0.000475851877356068;
        n[3] = 0.00156183014181602;
        n[4] = 0.105724860113781;
        n[5] = -85.8514221132534;
        n[6] = 724.140095480911;
        n[7] = 0.00296475810273257;
        n[8] = -0.00592721983365988;
        n[9] = -0.0126305422818666;
        n[10] = -0.115716196364853;
        n[11] = 84.9000969739595;
        n[12] = -0.0108602260086615;
        n[13] = 0.0154304475328851;
        n[14] = 0.0750455441524466;
        n[15] = 0.0252520973612982;
        n[16] = -0.0602507901232996;
        n[17] = -3.07622221350501;
        n[18] = -0.0574011959864879;
        n[19] = 5.03471360939849;
        n[20] = -0.925081888584834;
        n[21] = 3.91733882917546;
        n[22] = -77.314600713019;
        n[23] = 9493.08762098587;
        n[24] = -1410437.19679409;
        n[25] = 8491662.30819026;
        n[26] = 0.861095729446704;
        n[27] = 0.32334644281172;
        n[28] = 0.873281936020439;
        n[29] = -0.436653048526683;
        n[30] = 0.286596714529479;
        n[31] = -0.131778331276228;
        n[32] = 0.00676682064330275;


        double p_star = 100;
        double t_star = 860;
        double h_star = 2800;
        double eta = h / h_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x + n[num] * Math.pow((pi + 0.298), I[num]) * Math.pow((eta - 0.72), J[num]);
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double p_h_to_T_3a(double p, double h) {
        int[] I = {-12, -12, -12, -12, -12, -12, -12, -12, -10, -10, -10, -8, -8, -8, -8, -5, -3, -2, -2, -2, -1, -1, 0, 0, 1, 3, 3, 4, 4, 10, 12};
        int[] J = {0, 1, 2, 6, 14, 16, 20, 22, 1, 5, 12, 0, 2, 4, 10, 2, 0, 1, 3, 4, 0, 2, 0, 1, 1, 0, 1, 0, 3, 4, 5};
        double[] n = new double[31];

        n[0] = -0.000000133645667811215;
        n[1] = 0.00000455912656802978;
        n[2] = -0.0000146294640700979;
        n[3] = 0.0063934131297008;
        n[4] = 372.783927268847;
        n[5] = -7186.54377460447;
        n[6] = 573494.7521034;
        n[7] = -2675693.29111439;
        n[8] = -0.0000334066283302614;
        n[9] = -0.0245479214069597;
        n[10] = 47.8087847764996;
        n[11] = 0.00000764664131818904;
        n[12] = 0.00128350627676972;
        n[13] = 0.0171219081377331;
        n[14] = -8.51007304583213;
        n[15] = -0.0136513461629781;
        n[16] = -0.00000384460997596657;
        n[17] = 0.00337423807911655;
        n[18] = -0.551624873066791;
        n[19] = 0.72920227710747;
        n[20] = -0.00992522757376041;
        n[21] = -0.119308831407288;
        n[22] = 0.793929190615421;
        n[23] = 0.454270731799386;
        n[24] = 0.20999859125991;
        n[25] = -0.00642109823904738;
        n[26] = -0.023515586860454;
        n[27] = 0.00252233108341612;
        n[28] = -0.00764885133368119;
        n[29] = 0.0136176427574291;
        n[30] = -0.0133027883575669;


        double p_star = 100;
        double t_star = 760;
        double h_star = 2300;
        double eta = h / h_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x + n[num] * Math.pow((pi + 0.24), I[num]) * Math.pow((eta - 0.615), J[num]);
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double p_h_to_T_2c(double p, double h) {
        int[] I = {-7, -7, -6, -6, -5, -5, -2, -2, -1, -1, 0, 0, 1, 1, 2, 6, 6, 6, 6, 6, 6, 6, 6};
        int[] J = {0, 4, 0, 2, 0, 2, 0, 1, 0, 2, 0, 1, 4, 8, 4, 0, 1, 4, 10, 12, 16, 20, 22};
        double[] n = new double[23];
        n[0] = -3236839855524.2;
        n[1] = 7326335090218.1;
        n[2] = 358250899454.47;
        n[3] = -583401318515.9;
        n[4] = -10783068217.47;
        n[5] = 20825544563.171;
        n[6] = 610747.83564516;
        n[7] = 859777.2253558;
        n[8] = -25745.72360417;
        n[9] = 31081.088422714;
        n[10] = 1208.2315865936;
        n[11] = 482.19755109255;
        n[12] = 3.7966001272486;
        n[13] = -10.842984880077;
        n[14] = -0.04536417267666;
        n[15] = 0.00000000000014559115658698;
        n[16] = 0.000000000001126159740723;
        n[17] = -0.000000000017804982240686;
        n[18] = 0.00000012324579690832;
        n[19] = -0.0000011606921130984;
        n[20] = 0.000027846367088554;
        n[21] = -0.00059270038474176;
        n[22] = 0.0012918582991878;

        double p_star = 1;
        double t_star = 1;
        double h_star = 2000;
        double eta = h / h_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x + n[num] * Math.pow((pi + 25), I[num]) * Math.pow((eta - 1.8), J[num]);
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double p_h_to_T_2b(double p, double h) {
        int[] I = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 6, 7, 7, 9, 9};
        int[] J = {0, 1, 2, 12, 18, 24, 28, 40, 0, 2, 6, 12, 18, 24, 28, 40, 2, 8, 18, 40, 1, 2, 12, 24, 2, 12, 18, 24, 28, 40, 18, 24, 40, 28, 2, 28, 1, 40};
        double[] n = new double[38];
        n[0] = 1489.5041079516;
        n[1] = 743.07798314034;
        n[2] = -97.708318797837;
        n[3] = 2.4742464705674;
        n[4] = -0.63281320016026;
        n[5] = 1.1385952129658;
        n[6] = -0.47811863648625;
        n[7] = 0.0085208123431544;
        n[8] = 0.93747147377932;
        n[9] = 3.3593118604916;
        n[10] = 3.3809355601454;
        n[11] = 0.16844539671904;
        n[12] = 0.73875745236695;
        n[13] = -0.47128737436186;
        n[14] = 0.15020273139707;
        n[15] = -0.002176411421975;
        n[16] = -0.021810755324761;
        n[17] = -0.10829784403677;
        n[18] = -0.046333324635812;
        n[19] = 0.000071280351959551;
        n[20] = 0.00011032831789999;
        n[21] = 0.00018955248387902;
        n[22] = 0.0030891541160537;
        n[23] = 0.0013555504554949;
        n[24] = 0.00000028640237477456;
        n[25] = -0.000010779857357512;
        n[26] = -0.000076462712454814;
        n[27] = 0.000014052392818316;
        n[28] = -0.000031083814331434;
        n[29] = -0.0000010302738212103;
        n[30] = 0.0000002821728163504;
        n[31] = 0.0000012704902271945;
        n[32] = 0.000000073803353468292;
        n[33] = -0.000000011030139238909;
        n[34] = -0.000000000000081456365207833;
        n[35] = -0.000000000025180545682962;
        n[36] = -1.7565233969407E-18;
        n[37] = 0.0000000000000086934156344163;
        double p_star = 1;
        double t_star = 1;
        double h_star = 2000;
        double eta = h / h_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x + n[num] * Math.pow((pi - 2), I[num]) * Math.pow((eta - 2.6), J[num]);
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double p_h_to_T_2a(double p, double h) {
        int[] I = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7};
        int[] J = {0, 1, 2, 3, 7, 20, 0, 1, 2, 3, 7, 9, 11, 18, 44, 0, 2, 7, 36, 38, 40, 42, 44, 24, 44, 12, 32, 44, 32, 36, 42, 34, 44, 28};
        double[] n = new double[34];
        n[0] = 1089.8952318288;
        n[1] = 849.51654495535;
        n[2] = -107.81748091826;
        n[3] = 33.153654801263;
        n[4] = -7.4232016790248;
        n[5] = 11.765048724356;
        n[6] = 1.844574935579;
        n[7] = -4.1792700549624;
        n[8] = 6.2478196935812;
        n[9] = -17.344563108114;
        n[10] = -200.58176862096;
        n[11] = 271.96065473796;
        n[12] = -455.11318285818;
        n[13] = 3091.9688604755;
        n[14] = 252266.40357872;
        n[15] = -0.0061707422868339;
        n[16] = -0.31078046629583;
        n[17] = 11.670873077107;
        n[18] = 128127984.04046;
        n[19] = -985549096.23276;

        n[20] = 2822454697.3002;
        n[21] = -3594897141.0703;
        n[22] = 1722734991.3197;
        n[23] = -13551.334240775;
        n[24] = 12848734.66465;
        n[25] = 1.3865724283226;
        n[26] = 235988.32556514;
        n[27] = -13105236.545054;
        n[28] = 7399.9835474766;
        n[29] = -551966.9703006;
        n[30] = 3715408.5996233;
        n[31] = 19127.72923966;
        n[32] = -415351.64835634;
        n[33] = -62.459855192507;
        double p_star = 1;
        double t_star = 1;
        double h_star = 2000;
        double eta = h / h_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x + n[num] * Math.pow(pi, I[num]) * Math.pow((eta - 2.1), J[num]);
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double p_h_to_T_1(double p, double h) {
        int[] I = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 5, 6};
        int[] J = {0, 1, 2, 6, 22, 32, 0, 1, 2, 3, 4, 10, 32, 10, 32, 10, 32, 32, 32, 32};
        double[] n = new double[20];
        n[0] = -238.72489924521;
        n[1] = 404.21188637945;
        n[2] = 113.49746881718;
        n[3] = -5.8457616048039;
        n[4] = -0.0001528548241314;
        n[5] = -0.0000010866707695377;
        n[6] = -13.391744872602;
        n[7] = 43.211039183559;
        n[8] = -54.010067170506;
        n[9] = 30.535892203916;
        n[10] = -6.5964749423638;
        n[11] = 0.0093965400878363;
        n[12] = 0.0000001157364750534;
        n[13] = -0.000025858641282073;
        n[14] = -0.0000000040644363084799;
        n[15] = 0.000000066456186191635;
        n[16] = 0.000000000080670734103027;
        n[17] = -0.00000000000093477771213947;
        n[18] = 0.0000000000000058265442020601;
        n[19] = -1.5020185953503E-17;
        double t_star = 1;
        double p_star = 1;
        double h_star = 2500;

        double pi = p / p_star;
        double eta = h / h_star;
        double x = 0;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * Math.pow(pi, I[i]) * Math.pow((eta + 1), J[i]);
        }
        x = x * t_star - 273.15;
        return x;
    }

    private double TB23(double p) {

        double p_star = 1;
        double t_star = 1;
        double pi = p / p_star;
        double[] n = new double[3];
        n[0] = 0.0010192970039326;
        n[1] = 572.54459862746;
        n[2] = 13.91883977887;

        if (p <= 16.5292 || p > 100) {
            return -1;
        }
        double first = (n[1] + Math.pow(((pi - n[2]) / n[0]), 0.5)) * t_star - 273.15;
        return first;
    }

    private double h3ab(double mpa) {
        double p_star = 1;
        double h_star = 1;
        double pi = mpa / p_star;
        double[] n = new double[4];
        n[0] = 2014.64004206875;
        n[1] = 3.74696550136983;
        n[2] = -0.0219921901054187;
        n[3] = 0.000087513168600995;
        double first = (n[0] + n[1] * pi + n[2] * Math.pow(pi, 2) + n[3] * Math.pow(pi, 3)) * h_star;
        return first;
    }


    private double p3sat_h(double h) {
        int[] I = {0, 1, 1, 1, 1, 5, 7, 8, 14, 20, 22, 24, 28, 36};
        int[] J = {0, 1, 3, 4, 36, 3, 0, 24, 16, 16, 3, 18, 8, 24};
        double p_star = 22;
        double h_star = 2600;
        double[] n = new double[14];
        n[0] = 0.600073641753024;
        n[1] = -9.36203654849857;
        n[2] = 24.6590798594147;
        n[3] = -107.014222858224;
        n[4] = -91582131580576.8;
        n[5] = -8623.32011700662;
        n[6] = -23.5837344740032;
        n[7] = 2.52304969384128E+17;
        n[8] = -3.89718771997719E+18;
        n[9] = -3.33775713645296E+22;
        n[10] = 35649946963.6328;
        n[11] = -1.48547544720641E+26;
        n[12] = 3.30611514838798E+18;
        n[13] = 8.13641294467829E+37;
        double eta = h / h_star;
        double x = 0;
        for (int i = 0; i < n.length; i++) {
            x = x + n[i] * Math.pow((eta - 1.02), I[i]) * Math.pow((eta - 0.608), J[i]);
        }

        return x*p_star;
    }

    private double hB2bc(double mpa) {
        double p_star = 1;
        double h_star = 1;
        double pi = mpa / p_star;
        double[] n = new double[3];
        n[0] = 0.00012809002730136;
        n[1] = 2652.6571908428;
        n[2] = 4.5257578905948;

        return n[1] + Math.pow(((pi - n[2]) / n[0]), 0.5) * h_star;
    }


    /*
    已知ps求结果
     */
    public double t_ph(double p, double h) {
        String areatype;
        double t_ph;
        areatype = subregion_ph(p, h);
        if (areatype.equals("0")) {
            t_ph = -1000;
        }

        if (areatype.equals("1")) {
            t_ph = p_h_to_T_1(p, h);
        } else if (areatype.equals("2a")) {
            t_ph = p_h_to_T_2a(p, h);
        } else if (areatype.equals("2b")) {
            t_ph = p_h_to_T_2b(p, h);
        } else if (areatype.equals("2c")) {
            t_ph = p_h_to_T_2c(p, h);
        }
        else if (areatype.equals("3a")) {
            t_ph = p_h_to_T_3a(p, h);
        } else if (areatype.equals("3b")) {
            t_ph = p_h_to_T_3b(p, h);
        } else if (areatype.equals("4")) {
            t_ph = Ts(p);
        } else {
            t_ph = -1000;
        }
        return t_ph;
    }


    public double x_ph(double p, double h) {
        String areatype;
        double x_ph;
        double Temp;
        double wh;
        double sh;
        areatype = subregion_ph(p, h);
        if (areatype.equals("0")) {
           return -1000;
        }
        if (!areatype.equals("4")) {
            x_ph = -1000;
        } else {
            Temp = Ts(p);
            wh = h_pt(p, Temp, 0);
            sh = h_pt(p, Temp, 1);
            x_ph = (h - wh) / (sh - wh);
            if (x_ph > 1) {
                x_ph = 1;
            } 
            if (x_ph < 0) {
                x_ph = 0;
            }
        }
        return x_ph;
    }


    public double v_ph(double p, double h) {
        String areatype;
        double v_ph;
        double x = 1;
        double Temp;
        areatype = subregion_ph(p, h);
        if (areatype.equals("0")) {
            v_ph = -1000;
        }

        else if (areatype.equals("3a")) {
            v_ph = p_h_to_v_3a(p, h);
        }
        else if (areatype.equals("3b")) {
            v_ph = p_h_to_v_3b(p, h);
        }
        else if (areatype.equals("4")) {
            x = x_ph(p, h);
            Temp = Ts(p);
            v_ph = vm(p, Temp, x);
        } 
        else {
            Temp = t_ph(p, h);
            v_ph = v_pt(p, Temp,x);
        }
        return v_ph;
    }

    private double p_h_to_v_3b(double p, double h) {
        int[] I = {-12, -12, -8, -8, -8, -8, -8, -8, -6, -6, -6, -6, -6, -6, -4, -4, -4, -3, -3, -2, -2, -1, -1, -1, -1, 0, 1, 1, 2, 2};
        int[] J = {0, 1, 0, 1, 3, 6, 7, 8, 0, 1, 2, 5, 6, 10, 3, 6, 10, 0, 2, 1, 2, 0, 1, 4, 5, 0, 0, 1, 2, 6};
        double[] n = new double[30];
        n[0] = -0.00000000225196934336318;
        n[1] = 0.0000000140674363313486;
        n[2] = 0.0000023378408528056;
        n[3] = -0.0000331833715229001;
        n[4] = 0.00107956778514318;
        n[5] = -0.271382067378863;
        n[6] = 1.07202262490333;
        n[7] = -0.853821329075382;
        n[8] = -0.0000215214194340526;
        n[9] = 0.00076965608822273;
        n[10] = -0.00431136580433864;
        n[11] = 0.453342167309331;
        n[12] = -0.507749535873652;
        n[13] = -100.475154528389;
        n[14] = -0.219201924648793;
        n[15] = -3.21087965668917;
        n[16] = 607.567815637771;
        n[17] = 0.000557686450685932;
        n[18] = 0.18749904002955;
        n[19] = 0.00905368030448107;
        n[20] = 0.285417173048685;
        n[21] = 0.0329924030996098;
        n[22] = 0.239897419685483;
        n[23] = 4.82754995951394;
        n[24] = -11.8035753702231;
        n[25] = 0.169490044091791;
        n[26] = -0.0179967222507787;
        n[27] = 0.0371810116332674;
        n[28] = -0.0536288335065096;
        n[29] = 1.6069710109252;

        double p_star = 100;
        double v_star = 0.0088;
        double h_star = 2800;
        double eta = h / h_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x + n[num] * Math.pow((pi + 0.0661), I[num]) * Math.pow((eta - 0.72), J[num]);
        }
        x = x * v_star;
        return x;
    }

    private double p_h_to_v_3a(double p, double h) {
        int[] I = {-12, -12, -12, -12, -10, -10, -10, -8, -8, -6, -6, -6, -4, -4, -3, -2, -2, -1, -1, -1, -1, 0, 0, 1, 1, 1, 2, 2, 3, 4, 5, 8};
        int[] J = {6, 8, 12, 18, 4, 7, 10, 5, 12, 3, 4, 22, 2, 3, 7, 3, 16, 0, 1, 2, 3, 0, 1, 0, 1, 2, 0, 2, 0, 2, 2, 2};
        double[] n = new double[32];
        n[0] = 0.00529944062966028;
        n[1] = -0.170099690234461;
        n[2] = 11.1323814312927;
        n[3] = -2178.98123145125;
        n[4] = -0.000506061827980875;
        n[5] = 0.556495239685324;
        n[6] = -9.43672726094016;
        n[7] = -0.297856807561527;
        n[8] = 93.9353943717186;
        n[9] = 0.0192944939465981;
        n[10] = 0.421740664704763;
        n[11] = -3689141.2628233;
        n[12] = -0.00737566847600639;
        n[13] = -0.354753242424366;
        n[14] = -1.99768169338727;
        n[15] = 1.15456297059049;
        n[16] = 5683.6687581596;
        n[17] = 0.00808169540124668;
        n[18] = 0.172416341519307;
        n[19] = 1.04270175292927;
        n[20] = -0.297691372792847;
        n[21] = 0.560394465163593;
        n[22] = 0.275234661176914;
        n[23] = -0.148347894866012;
        n[24] = -0.0651142513478515;
        n[25] = -2.92468715386302;
        n[26] = 0.0664876096952665;
        n[27] = 3.52335014263844;
        n[28] = -0.0146340792313332;
        n[29] = -2.24503486668184;
        n[30] = 1.10533464706142;
        n[31] = -0.0408757344495612;


        double p_star = 100;
        double v_star = 0.0028;
        double h_star = 2100;
        double eta = h / h_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x + n[num] * Math.pow((pi + 0.128), I[num]) * Math.pow((eta - 0.727), J[num]);
        }
        x = x * v_star;
        return x;
    }

    public double den_ph(double p, double h) {
        double den_ph;
        double a = v_ph(p, h);
        if (a == Double.valueOf(0) || a == Double.valueOf(-1000)) {
            den_ph = -1000;
        } else {
            den_ph = 1 / a;
        }
        return den_ph;
    }

    public double s_ph(double p, double h) {
        String areatype;
        double s_ph = -1000;
        double x;
        double Temp = t_ph(p, h);
        areatype = subregion_ph(p, h);
        if (areatype.equals("0")) {
            s_ph = -1000;
        }

        if (areatype.equals("3a") || areatype.equals("3b")) {
            s_ph = s_3(1 / v_ph(p, h), Temp);
        } 
        else if (areatype.equals("4")) {
            x = x_ph(p, h);
            s_ph = sm(p, Temp, x);
        } 
        else{
            s_ph = s_pt(p, Temp,1);
        }
        return s_ph;
    }

    public double u_ph(double p, double h) {
        String areatype;
        double u_ph;
        double x=1;
        double Temp = t_ph(p, h);
        areatype = subregion_ph(p, h);
        if (areatype.equals("0")) {
            return -1000;
        }

        if (areatype.equals("3a") || areatype.equals("3b")) {
            u_ph = u_3(1 / v_ph(p, h), Temp);
        } else if (areatype.equals("4")) {
            x = x_ph(p, h);
            u_ph = um(p, Temp, x);
        } else {
            u_ph = u_pt(p, Temp,x);
        }
        return u_ph;
    }

    public double cv_ph(double p, double h) {
        String areatype;
        double cv_ph;
        double x=1;
        double Temp = t_ph(p, h);
        areatype = subregion_ph(p, h);
        if (areatype.equals("0")) {
            cv_ph = -1000;
        }

        if (areatype.equals("3a") || areatype.equals("3b")) {
            cv_ph = cv_3(1 / v_ph(p, h), Temp);
        } else if (areatype.equals("4")) {
            x = x_ph(p, h);
            cv_ph = cvm(p, Temp, x);
        } else {
            cv_ph = Cv_pt(p, Temp,x);
        }
        return cv_ph;
    }

    public double cp_ph(double p, double h) {
        String areatype;
        double cp_ph;
        double x=1;
        double Temp = t_ph(p, h);
        areatype = subregion_ph(p, h);
        if (areatype.equals("0")) {
            cp_ph = -1000;
        }

        if (areatype.equals("3a") || areatype.equals("3b")) {
            cp_ph = cp_3(1 / v_ph(p, h), Temp);
        } else if (areatype.equals("4")) {
            x = x_ph(p, h);
            cp_ph = cpm(p, Temp, x);
        } else {
            cp_ph = Cp_pt(p, Temp,x);
        }
        return cp_ph;
    }

    public double w_ph(double p, double h) {
        String areatype;
        double w_ph;
        double x=1;
        double Temp = t_ph(p, h);
        areatype = subregion_ph(p, h);
        if (areatype.equals("0")) {
            w_ph = -1000;
        }

        if (areatype.equals("3a") || areatype.equals("3b")) {
            w_ph = w_3(1 / v_ph(p, h), Temp);
        } else if (areatype.equals("4")) {
            x = x_ph(p, h);
            w_ph = wm(p, Temp, x);
        } else {
            w_ph = w_pt(p, Temp,x);
        }
        return w_ph;
    }


    /*
    ps计算
     */
    public String subregion_ps(double p, double s) {
        String areatype="0";
        String areatype_check;
        double Temp;
        if (p > 0.000611657 && p <= 4) {
            if (s > s_1(p, 0) && s < s_1(p, Ts(p))) {
                areatype = "1";
            }
            if (s >= s_1(p, Ts(p)) && s <= s_2(p, Ts(p))){
                areatype = "4";}
            if (s > s_2(p, Ts(p)) && s <= s_2(p, 800)){
                areatype = "2a";}
        } if (p > 4 && p <= 6.5467) {
            if (s > s_1(p, 0) && s < s_1(p, Ts(p))){
                areatype = "1";}
           if (s >= s_1(p, Ts(p)) && s <= s_2(p, Ts(p))){
                areatype = "4";}
           if (s > s_2(p, Ts(p)) && s <= s_2(p, 800)){
                areatype = "2b";}
        }
        if (p > 6.5467 && p <= Ps(350)) {
            if (s > s_1(p, 0) && s < s_1(p, Ts(p))){
                areatype = "1";}
            if (s >= s_1(p, Ts(p)) && s <= s_2(p, Ts(p))){
                areatype = "4";}
           if (s > s_2(p, Ts(p)) && s <= 5.85){
                areatype = "2c";}
           if (s > 5.85 && s <= s_2(p, 800)){
                areatype = "2b";}

        } if (p > Ps(350) && p <= 22.064) {
            if (s > s_1(p, 0) && s < s_1(p, 350)){
                areatype = "1";}
            if (s >= s_1(p, 350) && s <= 4.41202148224) {
                if (p > p3sat_s(s)){
                    areatype = "3a";}
                else{
                    areatype = "4";}
            }
            if (s > 4.41202148224 && s <= s_pt(p, TB23(p),1)) {
                if (p > p3sat_s(s)){
                    areatype = "3b";}
                else{
                    areatype = "4";}
            }
            if (s > s_pt(p, TB23(p),1) && s <= 5.85){
                areatype = "2c";}
            if (s > 5.85 && s <= s_2(p, 800)){
                areatype = "2b";}

        }
        if (p > 22.064 && p <= 100) {
            if (s > s_1(p, 0) && s <= s_1(p, 350)){
                areatype = "1";}

            if (s > s_1(p, 350) && s <= 4.41202148224){
                areatype = "3a";}

            if (s > 4.41202148224 && s <= s_pt(p, TB23(p),1)){
                areatype = "3b";}

            if (s > s_pt(p, TB23(p),1) && s <= 5.85){
                areatype = "2c";}

            if (s > 5.85 && s <= s_2(p, 800)){
                areatype = "2b";}

        }
        if (areatype.equals("0")) {
            return "0";
        }
        if(areatype.equals("1")){
            Temp = p_s_to_T_1(p, s);
        }
        else if(areatype.equals("2a")){
            Temp = p_s_to_T_2a(p, s);
        }
        else if(areatype.equals("2b")){
            Temp = p_s_to_T_2b(p, s);
        }
        else if(areatype.equals("2c")){
            Temp = p_s_to_T_2c(p, s);
        }
        else if(areatype.equals("3a")){
            Temp = p_s_to_T_3a(p, s);
        }
        else if(areatype.equals("3b")){
            Temp = p_s_to_T_3b(p, s);
        }
        else if(areatype.equals("4")){
            Temp = Ts(p);
        }
        else{
            Temp = -1;
        }
        areatype_check = subregion_pT(p, Temp);
        if (areatype.charAt(0) == '1') {
            if (areatype_check.charAt(0) == '3') {
            }
            if (!areatype.substring(0, 1).equals(areatype_check.substring(0, 1))) {
                areatype = "4";
            }
        }
        if (areatype.charAt(0) == '2') {
            if (areatype_check.charAt(0) == '3' || areatype_check.charAt(0) == '5') {
            }
            if (!areatype.substring(0, 1).equals(areatype_check.substring(0, 1))) {
                areatype = "4";
            }
        }
        if (areatype.charAt(0) == '3') {
            if (areatype_check.charAt(0) == '1' || areatype_check.charAt(0) == '2' || areatype_check.charAt(0) == '5') {
            }
            if (!areatype.substring(0, 1).equals(areatype_check.substring(0, 1))) {
                areatype = "4";
            }
        }
        if (areatype.charAt(0) == '5') {
            if (areatype_check.charAt(0) == '2') {
            }
            if (!areatype.substring(0, 1).equals(areatype_check.substring(0, 1))) {
                areatype = "4";
            }
        }
        return areatype;


    }
    private double p_s_to_T_3b(double p, double s) {
        int[] I = {-12, -12, -12, -12, -8, -8, -8, -6, -6, -6, -5, -5, -5, -5, -5, -4, -3, -3, -2, 0, 2, 3, 4, 5, 6, 8, 12, 14};
        int[] J = {1, 3, 4, 7, 0, 1, 3, 0, 2, 4, 0, 1, 2, 4, 6, 12, 1, 6, 2, 0, 1, 1, 0, 24, 0, 3, 1, 2};
        double[] n = new double[28];

        n[0] = 0.52711170160166;
        n[1] = -40.1317830052742;
        n[2] = 153.020073134484;
        n[3] = -2247.99398218827;
        n[4] = -0.193993484669048;
        n[5] = -1.40467557893768;
        n[6] = 42.6799878114024;
        n[7] = 0.752810643416743;
        n[8] = 22.6657238616417;
        n[9] = -622.873556909932;
        n[10] = -0.660823667935396;
        n[11] = 0.841267087271658;
        n[12] = -25.3717501764397;
        n[13] = 485.708963532948;
        n[14] = 880.531517490555;
        n[15] = 2650155.92794626;
        n[16] = -0.359287150025783;
        n[17] = -656.991567673753;
        n[18] = 2.41768149185367;
        n[19] = 0.856873461222588;
        n[20] = 0.655143675313458;
        n[21] = -0.213535213206406;
        n[22] = 0.00562974957606348;
        n[23] = -316955725450471.0;
        n[24] = -0.000699997000152457;
        n[25] = 0.0119845803210767;
        n[26] = 0.0000193848122022095;
        n[27] = -0.0000215095749182309;


        double t_star = 860;
        double p_star = 100;

        double s_star = 5.3;
        double sigma = s / s_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((pi + 0.76) , I[num]) * Math.pow((sigma - 0.818) , J[num]);
        }
        x = x  * t_star - 273.15;
        return x;
    }

    private double p_s_to_T_3a(double p, double s) {
        int[] I = {-12, -12, -10, -10, -10, -10, -8, -8, -8, -8, -6, -6, -6, -5, -5, -5, -4, -4, -4, -2, -2, -1, -1, 0, 0, 0, 1, 2, 2, 3, 8, 8, 10};
        int[] J = {28, 32, 4, 10, 12, 14, 5, 7, 8, 28, 2, 6, 32, 0, 14, 32, 6, 10, 36, 1, 4, 1, 6, 0, 1, 4, 0, 0, 3, 2, 0, 1, 2};
        double[] n = new double[33];

        n[0] = 1500420082.63875;
        n[1] = -159397258480.424;
        n[2] = 0.000502181140217975;
        n[3] = -67.2057767855466;
        n[4] = 1450.58545404456;
        n[5] = -8238.8953488889;
        n[6] = -0.154852214233853;
        n[7] = 11.2305046746695;
        n[8] = -29.7000213482822;
        n[9] = 43856513263.5495;
        n[10] = 0.00137837838635464;
        n[11] = -2.97478527157462;
        n[12] = 9717779473494.13;
        n[13] = -0.0000571527767052398;
        n[14] = 28830.794977842;
        n[15] = -74442828926270.3;
        n[16] = 12.8017324848921;
        n[17] = -368.275545889071;
        n[18] = 6.64768904779177E+15;
        n[19] = 0.044935925195888;
        n[20] = -4.22897836099655;
        n[21] = -0.240614376434179;
        n[22] = -4.74341365254924;
        n[23] = 0.72409399912611;
        n[24] = 0.923874349695897;
        n[25] = 3.99043655281015;
        n[26] = 0.0384066651868009;
        n[27] = -0.00359344365571848;
        n[28] = -0.735196448821653;
        n[29] = 0.188367048396131;
        n[30] = 0.000141064266818704;
        n[31] = -0.00257418501496337;
        n[32] = 0.00123220024851555;



        double t_star = 760;
        double p_star = 100;

        double s_star =4.4;
        double sigma = s / s_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((pi + 0.24) , I[num]) * Math.pow((sigma - 0.703) , J[num]);
        }
        x = x  * t_star - 273.15;
        return x;
    }

    private double p_s_to_T_2c(double p, double s) {
        int[] I = {-2, -2, -1, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 7, 7};
        int[] J = {0, 1, 0, 0, 1, 2, 3, 0, 1, 3, 4, 0, 1, 2, 0, 1, 5, 0, 1, 4, 0, 1, 2, 0, 1, 0, 1, 3, 4, 5};
        double[] n = new double[30];

        n[0] = 909.68501005365;
        n[1] = 2404.566708842;
        n[2] = -591.6232638713;
        n[3] = 541.45404128074;
        n[4] = -270.98308411192;
        n[5] = 979.76525097926;
        n[6] = -469.66772959435;
        n[7] = 14.399274604723;
        n[8] = -19.104204230429;
        n[9] = 5.3299167111971;
        n[10] = -21.252975375934;
        n[11] = -0.3114733441376;
        n[12] = 0.60334840894623;
        n[13] = -0.042764839702509;
        n[14] = 0.0058185597255259;
        n[15] = -0.014597008284753;
        n[16] = 0.0056631175631027;
        n[17] = -0.000076155864584577;
        n[18] = 0.00022440342919332;
        n[19] = -0.000012561095013413;
        n[20] = 0.00000063323132660934;
        n[21] = -0.0000020541989675375;
        n[22] = 0.000000036405370390082;
        n[23] = -0.0000000029759897789215;
        n[24] = 0.000000010136618529763;
        n[25] = 0.0000000000059925719692351;
        n[26] = -0.000000000020677870105164;
        n[27] = -0.000000000020874278181886;
        n[28] = 0.00000000010162166825088999;
        n[29] = -0.00000000016429828281347;

        double t_star = 1;
        double p_star = 1;

        double s_star =2.9251;
        double sigma = s / s_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow(pi , I[num]) * Math.pow((2 - sigma) , J[num]);
        }
        x = x  * t_star - 273.15;
        return x;
    }

    private double p_s_to_T_2b(double p, double s) {
        int[] I = {-6, -6, -5, -5, -4, -4, -4, -3, -3, -3, -3, -2, -2, -2, -2, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5};
        int[] J = {0, 11, 0, 11, 0, 1, 11, 0, 1, 11, 12, 0, 1, 6, 10, 0, 1, 5, 8, 9, 0, 1, 2, 4, 5, 6, 9, 0, 1, 2, 3, 7, 8, 0, 1, 5, 0, 1, 3, 0, 1, 0, 1, 2};
        double[] n = new double[44];

        n[0] = 316876.65083497;
        n[1] = 20.864175881858;
        n[2] = -398593.99803599;
        n[3] = -21.816058518877;
        n[4] = 223697.85194242;
        n[5] = -2784.1703445817;
        n[6] = 9.920743607148;
        n[7] = -75197.512299157;
        n[8] = 2970.8605951158;
        n[9] = -3.4406878548526;
        n[10] = 0.38815564249115;
        n[11] = 17511.29508575;
        n[12] = -1423.7112854449;
        n[13] = 1.0943803364167;
        n[14] = 0.89971619308495;
        n[15] = -3375.9740098958;
        n[16] = 471.62885818355;
        n[17] = -1.9188241993679;
        n[18] = 0.41078580492196;
        n[19] = -0.33465378172097;
        n[20] = 1387.0034777505;
        n[21] = -406.63326195838;
        n[22] = 41.72734715961;
        n[23] = 2.1932549434532;
        n[24] = -1.0320050009077;
        n[25] = 0.35882943516703;
        n[26] = 0.0052511453726066;
        n[27] = 12.838916450705;
        n[28] = -2.8642437219381;
        n[29] = 0.56912683664855;
        n[30] = -0.099962954584931;
        n[31] = -0.0032632037778459;
        n[32] = 0.00023320922576723;
        n[33] = -0.1533480985745;
        n[34] = 0.029072288239902;
        n[35] = 0.00037534702741167;
        n[36] = 0.0017296691702411;
        n[37] = -0.00038556050844504;
        n[38] = -0.000035017712292608;
        n[39] = -0.000014566393631492;
        n[40] = 0.0000056420857267269;
        n[41] = 0.000000041286150074605;
        n[42] = -0.000000020684671118824;
        n[43] = 0.0000000016409393674725;


        double t_star = 1;
        double p_star = 1;

        double s_star =0.7853;
        double sigma = s / s_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow(pi , I[num]) * Math.pow((10 - sigma) , J[num]);
        }
        x = x  * t_star - 273.15;
        return x;
    }

    private double p_s_to_T_2a(double p, double s) {
        double[] I = {-1.5, -1.5, -1.5, -1.5, -1.5, -1.5, -1.25, -1.25, -1.25, -1, -1, -1, -1, -1, -1, -0.75, -0.75, -0.5, -0.5, -0.5, -0.5, -0.25, -0.25, -0.25, -0.25, 0.25, 0.25, 0.25, 0.25, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.75, 0.75, 0.75, 0.75, 1, 1, 1.25, 1.25, 1.5, 1.5};
        int[] J = {-24, -23, -19, -13, -11, -10, -19, -15, -6, -26, -21, -17, -16, -9, -8, -15, -14, -26, -13, -9, -7, -27, -25, -11, -6, 1, 4, 8, 11, 0, 1, 5, 6, 10, 14, 16, 0, 4, 9, 17, 7, 18, 3, 15, 5, 18};
        double[] n = new double[46];

        n[0] = -392359.83861984;
        n[1] = 515265.7382727;
        n[2] = 40482.443161048;
        n[3] = -321.93790923902;
        n[4] = 96.961424218694;
        n[5] = -22.867846371773;
        n[6] = -449429.14124357;
        n[7] = -5011.8336020166;
        n[8] = 0.35684463560015;
        n[9] = 44235.33584819;
        n[10] = -13673.388811708;
        n[11] = 421632.60207864;
        n[12] = 22516.925837475;
        n[13] = 474.42144865646;
        n[14] = -149.31130797647;
        n[15] = -197811.26320452;
        n[16] = -23554.39947076;
        n[17] = -19070.616302076;
        n[18] = 55375.669883164;
        n[19] = 3829.3691437363;
        n[20] = -603.91860580567;
        n[21] = 1936.3102620331;
        n[22] = 4266.064369861;
        n[23] = -5978.0638872718;
        n[24] = -704.01463926862;
        n[25] = 338.36784107553;
        n[26] = 20.862786635187;
        n[27] = 0.033834172656196;
        n[28] = -0.000043124428414893;
        n[29] = 166.53791356412;
        n[30] = -139.86292055898;
        n[31] = -0.78849547999872;
        n[32] = 0.072132411753872;
        n[33] = -0.0059754839398283;
        n[34] = -0.000012141358953904;
        n[35] = 0.00000023227096733871;
        n[36] = -10.538463566194;
        n[37] = 2.0718925496502;
        n[38] = -0.072193155260427;
        n[39] = 0.0000002074988708112;
        n[40] = -0.018340657911379;
        n[41] = 0.00000029036272348696;
        n[42] = 0.21037527893619;
        n[43] = 0.00025681239729999;
        n[44] = -0.012799002933781;
        n[45] = -0.0000082198102652018;



        double t_star = 1;
        double p_star = 1;

        double s_star =2;
        double sigma = s / s_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow(pi , I[num]) * Math.pow((sigma - 2) , J[num]);
        }
        x = x  * t_star - 273.15;
        return x;
    }

    private double p_s_to_T_1(double p, double s) {
        int[] I = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 4};
        int[] J = {0, 1, 2, 3, 11, 31, 0, 1, 2, 3, 12, 31, 0, 1, 2, 9, 31, 10, 32, 32};
        double[] n = new double[20];

        n[0] = 174.78268058307;
        n[1] = 34.806930892873;
        n[2] = 6.5292584978455;
        n[3] = 0.33039981775489;
        n[4] = -0.00000019281382923196;
        n[5] = -2.4909197244573E-23;
        n[6] = -0.26107636489332;
        n[7] = 0.22592965981586;
        n[8] = -0.064256463395226;
        n[9] = 0.0078876289270526;
        n[10] = 0.00000000035672110607366;
        n[11] = 1.7332496994895E-24;
        n[12] = 0.00056608900654837;
        n[13] = -0.00032635483139717;
        n[14] = 0.000044778286690632;
        n[15] = -0.00000000051322156908507;
        n[16] = -4.2522657042207E-26;
        n[17] = 0.00000000000026400441360689;
        n[18] = 7.8124600459723E-29;
        n[19] = -3.0732199903668E-31;



        double t_star = 1;
        double p_star = 1;

        double s_star =1;
        double sigma = s / s_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow(pi, I[num]) * Math.pow((sigma + 2), J[num]);
        }
        x = x  * t_star - 273.15;
        return x;
    }
    private double p3sat_s(double s) {
        int[] I = {0, 1, 1, 4, 12, 12, 16, 24, 28, 32};
        int[] J = {0, 1, 32, 7, 4, 14, 36, 10, 0, 18};
        double[] n = new double[10];

        n[0] = 0.639767553612785;
        n[1] = -12.9727445396014;
        n[2] = -2.24595125848403E+15;
        n[3] = 1774667.41801846;
        n[4] = 7170793495.71538;
        n[5] = -3.78829107169011E+17;
        n[6] = -9.55586736431328E+34;
        n[7] = 1.87269814676188E+23;
        n[8] = 119254746466.473;
        n[9] = 1.10649277244882E+36;




        double p_star = 22;

        double s_star =5.2;
        double sigma = s / s_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((sigma - 1.03) , I[num]) * Math.pow((sigma - 0.699) , J[num]);
        }
        x = x  * p_star;
        return x;
    }

    public double t_ps(double p, double s) {
        String areatype;
        double t_ps;
        double Temp;
        areatype = subregion_ps(p, s);
        if (areatype.equals("0")) {
            Temp = -1000;
        }

        if (areatype.equals("1")) {
            Temp = p_s_to_T_1(p, s);
        } else if (areatype.equals("2a")) {
            Temp = p_s_to_T_2a(p, s);
        } else if (areatype.equals("2b")) {
            Temp = p_s_to_T_2b(p, s);
        } else if (areatype.equals("2c")) {
            Temp = p_s_to_T_2c(p, s);
        } else if (areatype.equals("3a")) {
            Temp = p_s_to_T_3a(p, s);
        } else if (areatype.equals("3b")) {
            Temp = p_s_to_T_3b(p, s);
        } else if (areatype.equals("4")) {
            Temp = Ts(p);
        } else {
            Temp = -1000;
        }
        return Temp;
    }

    public double x_ps(double p, double s) {
        String areatype;

        double x_ps;
        double ws;
        double ss;
        double Temp;
        areatype = subregion_ps(p, s);
        if (areatype.equals("0")) {
            x_ps = -1000;
        }
        if(!areatype.equals("4")){
            x_ps = -1000;
        }
        else{
            Temp = Ts(p);
            ws = s_pt(p, Temp, 0);
            ss = s_pt(p, Temp, 1);
            x_ps = (s - ws) / (ss - ws);
            if(x_ps > 1 )    {
            x_ps = 1;}
            else if(x_ps < 0){
                    x_ps = 0;}
        }
        return x_ps;
    }

    public double v_ps(double p, double s) {
        String areatype;

        double v_ps;
        double x=1;
        double Temp;
        areatype = subregion_ps(p, s);
        if (areatype.equals("0")) {
            v_ps = -1000;
        }
        if(areatype.equals("3a")){
            v_ps = p_s_to_v_3a(p, s);
        }
        else if(areatype.equals("3b")){
            v_ps = p_s_to_v_3b(p, s);
        }
        else if(areatype.equals("4")){
            x = x_ps(p, s);
            Temp = Ts(p);
            v_ps = vm(p, Temp, x);
        }
        else{
            Temp = t_ps(p, s);
            v_ps = v_pt(p, Temp,x);
        }
        return v_ps;
    }

    private double p_s_to_v_3a(double p, double s) {
        int[] I = {-12, -12, -12, -10, -10, -10, -10, -8, -8, -8, -8, -6, -5, -4, -3, -3, -2, -2, -1, -1, 0, 0, 0, 1, 2, 4, 5, 6};
        int[] J = {10, 12, 14, 4, 8, 10, 20, 5, 6, 14, 16, 28, 1, 5, 2, 4, 3, 8, 1, 2, 0, 1, 3, 0, 0, 2, 2, 0};
        double[] n = new double[28];

        n[0] = 79.5544074093975;
        n[1] = -2382.6124298459;
        n[2] = 17681.3100617787;
        n[3] = -0.00110524727080379;
        n[4] = -15.3213833655326;
        n[5] = 297.544599376982;
        n[6] = -35031520.6871242;
        n[7] = 0.277513761062119;
        n[8] = -0.523964271036888;
        n[9] = -148011.182995403;
        n[10] = 1600148.99374266;
        n[11] = 1708023226634.27;
        n[12] = 0.000246866996006494;
        n[13] = 1.6532608479798;
        n[14] = -0.118008384666987;
        n[15] = 2.537986423559;
        n[16] = 0.965127704669424;
        n[17] = -28.2172420532826;
        n[18] = 0.203224612353823;
        n[19] = 1.10648186063513;
        n[20] = 0.52612794845128;
        n[21] = 0.277000018736321;
        n[22] = 1.08153340501132;
        n[23] = -0.0744127885357893;
        n[24] = 0.0164094443541384;
        n[25] = -0.0680468275301065;
        n[26] = 0.025798857610164;
        n[27] = -0.000145749861944416;



        double v_star =0.0028;
        double p_star = 100;

        double s_star = 4.4;
        double sigma = s / s_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] *Math.pow((pi + 0.187) , I[num]) * Math.pow((sigma - 0.755) , J[num]);
        }
        x = x  * v_star;
        return x;
    }

    private double p_s_to_v_3b(double p, double s) {

        int[] I = {-12, -12, -12, -12, -12, -12, -10, -10, -10, -10, -8, -5, -5, -5, -4, -4, -4, -4, -3, -2, -2, -2, -2, -2, -2, 0, 0, 0, 1, 1, 2};
        int[] J = {0, 1, 2, 3, 5, 6, 0, 1, 2, 4, 0, 1, 2, 3, 0, 1, 2, 3, 1, 0, 1, 2, 3, 4, 12, 0, 1, 2, 0, 2, 2};
        double[] n = new double[31];

        n[0] = 0.0000591599780322238;
        n[1] = -0.00185465997137856;
        n[2] = 0.0104190510480013;
        n[3] = 0.0059864730203859;
        n[4] = -0.771391189901699;
        n[5] = 1.72549765557036;
        n[6] = -0.000467076079846526;
        n[7] = 0.0134533823384439;
        n[8] = -0.0808094336805495;
        n[9] = 0.508139374365767;
        n[10] = 0.00128584643361683;
        n[11] = -1.63899353915435;
        n[12] = 5.86938199318063;
        n[13] = -2.92466667918613;
        n[14] = -0.00614076301499537;
        n[15] = 5.76199014049172;
        n[16] = -12.1613320606788;
        n[17] = 1.67637540957944;
        n[18] = -7.44135838773463;
        n[19] = 0.0378168091437659;
        n[20] = 4.01432203027688;
        n[21] = 16.0279837479185;
        n[22] = 3.17848779347728;
        n[23] = -3.58362310304853;
        n[24] = -1159952.60446827;
        n[25] = 0.199256573577909;
        n[26] = -0.122270624794624;
        n[27] = -19.1449143716586;
        n[28] = -0.0150448002905284;
        n[29] = 14.6407900162154;
        n[30] = -3.2747778718823;




        double v_star =0.0088;
        double p_star = 100;

        double s_star = 5.3;
        double sigma = s / s_star;
        double pi = p / p_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((pi + 0.298) , I[num]) * Math.pow((sigma - 0.816) , J[num]);
        }
        x = x  * v_star;
        return x;
    }

    public double den_ps(double p, double s) {

        double a;
        double den_ps;
        a = v_ps(p, s);
        if (a == Double.valueOf(0) || a == Double.valueOf(-1000) ){
                den_ps = -1000;}
        else{
                den_ps = 1 / a;}
        return den_ps;
    }

    public double h_ps(double p, double s) {
        String areatype;

        double h_ps;
        double x;
        double Temp;
        areatype = subregion_ps(p, s);
        if (areatype.equals("0")) {
            return  -1000;
        }
        Temp = t_ps(p, s);
        if(areatype.equals("3a")||areatype.equals("3b")){
            h_ps = h_3(1 / v_ps(p, s), Temp);
        }
        else if(areatype.equals("4")){
            x = x_ps(p, s);
            h_ps = hm(p, Temp, x);
        }
        else{
            h_ps = h_pt(p, Temp,1);
        }
        return h_ps;
    }

    public double u_ps(double p, double s) {
        String areatype;

        double u_ps;
        double x=1;
        double Temp;
        areatype = subregion_ps(p, s);
        if (areatype.equals("0")) {
           return  -1000;
        }
        Temp = t_ps(p, s);
        if(areatype.equals("3a")||areatype.equals("3b")){
            u_ps = u_3(1 / v_ps(p, s), Temp);
        }
        else if(areatype.equals("4")){
            x = x_ps(p, s);
            u_ps = um(p, Temp, x);
        }
        else{
            u_ps = u_pt(p, Temp,x);
        }
        return u_ps;
    }

    public double cv_ps(double p, double s) {
        String areatype;

        double cv_ps;
        double x=1;
        double Temp;
        areatype = subregion_ps(p, s);
        if (areatype.equals("0")) {
           return  -1000;
        }
        Temp = t_ps(p, s);
        if(areatype.equals("3a")||areatype.equals("3b")){
            cv_ps = cv_3(1 / v_ps(p, s), Temp);
        }
        else if(areatype.equals("4")){
            x = x_ps(p, s);
            cv_ps = cvm(p, Temp, x);
        }
        else{
            cv_ps = Cv_pt(p, Temp,x);
        }
        return cv_ps;
    }

    public double cp_ps(double p, double s) {
        String areatype;

        double cp_ps;
        double x=1;
        double Temp;
        areatype = subregion_ps(p, s);
        if (areatype.equals("0")) {
           return  -1000;
        }
        Temp = t_ps(p, s);
        if(areatype.equals("3a")||areatype.equals("3b")){
            cp_ps = cp_3(1 / v_ps(p, s), Temp);
        }
        else if(areatype.equals("4")){
            x = x_ps(p, s);
            cp_ps = cpm(p, Temp, x);
        }
        else{
            cp_ps = Cp_pt(p, Temp,x);
        }
        return cp_ps;
    }
    
    public double w_ps(double p, double s) {
        String areatype;
        double w_ps;
        double x=1;
        double Temp;
        areatype = subregion_ps(p, s);
        if (areatype.equals("0")) {
            return -1000;
        }
        Temp = t_ps(p, s);
        if(areatype.equals("3a")||areatype.equals("3b")){
            w_ps = w_3(1 / v_ps(p, s), Temp);
        }
        else if(areatype.equals("4")){
            x = x_ps(p, s);
            w_ps = wm(p, Temp, x);
        }
        else{
            w_ps = w_pt(p, Temp,x);
        }
        return w_ps;
    }

    /*
    已知焓熵
     */
    private String subregion_hs(double h, double s) {
        String areatype="0";
        String areatype_check;
        double T1,p1,p2;
        if(s>=-0.0001545495919&&s<=3.397782955){
            if(h>wh1_s(s)){
                areatype="1";
            }
            else{
                areatype="4";
            }
        }
        if(s > 3.397782955 && s <= 3.77828134){
            if(h > hB13_s(s)){
                areatype = "3a";
            }
            if(h <= hB13_s(s) && h > wh1_s(s)){
                areatype = "1";
            }
            if(h <= wh1_s(s)){
                areatype = "4";
            }

        }
       if(s > 3.77828134 && s <= 4.41202148223476){
            if(h > wh3a_s(s)){
                areatype = "3a";
            }
            else{
                areatype = "4";
            }
        }

       if(s > 4.41202148223476 && s <= 5.85){
            if(h <= sh2c3b_s(s)){
                areatype = "4";
            }
            else{
                if(s <= 5.048096828 || h <= 2563.592003){
                    areatype = "3b";
                }
               if(s >= 5.260578707 || h >= 2812.942061 ){
                    areatype = "2c";
                }

                if(!(s <= 5.048096828 || h <= 2563.592003)&&!(s >= 5.260578707 || h >= 2812.942061)){
                    p1 = h_s_to_p_2c(h, s);
                    p2 = PB23(TB23_hs(h, s));
                    if (p1 > p2){
                    areatype = "3b";}
                    else{
                        areatype = "2c";}
                }
            }
        }
        if(s > 5.85 && s <= 6.06970915952){
            if(h > sh2ab_s(s)){
                areatype = "2b";
            }
            else{
                areatype = "4";
            }
        }
        if(s > 6.06970915952 && s <= 7.85234039988){
            if(h > h2ab_s(s)){
                areatype = "2b";
            }
            if(h <= h2ab_s(s) && h > sh2ab_s(s)){
                areatype = "2a";
            }
            if(!(h > h2ab_s(s))&&!(h <= h2ab_s(s) && h > sh2ab_s(s))){
                areatype = "4";
            }
        }
        if(s > 7.85234039988 && s <= 9.155759395){
            if (h > sh2ab_s(s)){
                    areatype = "2a";}
            else{
                    areatype = "4";}
        }
        if(s > 9.155759395 && s <= 11.9207196808){
                areatype = "2a";}

        return areatype;
    }

    private double h2ab_s(double s) {
        double[] n = new double[4];
        n[0] = -3498.98083432139;
        n[1] = 2575.60716905876;
        n[2] = -421.073558227969;
        n[3] = 27.6349063799944;
        double h_star = 1;
        double s_star = 1;
        double sigma = s / s_star;
        double x = 0;
            x = (n[0] + n[1] * sigma + n[2] * Math.pow(sigma , 2) + n[3] * Math.pow(sigma,3)) * h_star;

        return x;
    }

    private double sh2ab_s(double s) {
        int[] I = {1, 1, 2, 2, 4, 4, 7, 8, 8, 10, 12, 12, 18, 20, 24, 28, 28, 28, 28, 28, 32, 32, 32, 32, 32, 36, 36, 36, 36, 36};
        int[] J = {8, 24, 4, 32, 1, 2, 7, 5, 12, 1, 0, 7, 10, 12, 32, 8, 12, 20, 22, 24, 2, 7, 12, 14, 24, 10, 12, 20, 22, 28};
        double[] n = new double[30];
        n[0] = -524.581170928788;
        n[1] = -9269472.18142218;
        n[2] = -237.385107491666;
        n[3] = 21077015581.2776;
        n[4] = -23.9494562010986;
        n[5] = 221.802480294197;
        n[6] = -5104725.33393438;
        n[7] = 1249813.96109147;
        n[8] = 2000084369.96201;
        n[9] = -815.158509791035;
        n[10] = -157.612685637523;
        n[11] = -11420042233.2791;
        n[12] = 6.62364680776872E+15;
        n[13] = -2.27622818296144E+18;
        n[14] = -1.71048081348406E+31;
        n[15] = 6.60788766938091E+15;
        n[16] = 1.66320055886021E+22;
        n[17] = -2.18003784381501E+29;
        n[18] = -7.87276140295618E+29;
        n[19] = 1.51062329700346E+31;
        n[20] = 7957321.70300541;
        n[21] = 1.31957647355347E+15;
        n[22] = -3.2509706829914E+23;
        n[23] = -4.18600611419248E+25;
        n[24] = 2.97478906557467E+34;
        n[25] = -9.53588761745473E+19;
        n[26] = 1.66957699620939E+24;
        n[27] = -1.75407764869978E+32;
        n[28] = 3.47581490626396E+34;
        n[29] = -7.10971318427851E+38;
        double h_star = 2800;
        double s1_star = 5.21;
        double s2_star = 9.2;
        double sigma1 = s / s1_star;
        double sigma2 = s / s2_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((Math.pow(sigma1 , (-1)) - 0.513) , I[num]) * Math.pow((sigma2 - 0.524) , J[num]);
        }
        x =  Math.exp(x) * h_star;
        return x;
    }

    private double TB23_hs(double h, double s) {
        int[] I = {-12, -10, -8, -4, -3, -2, -2, -2, -2, 0, 1, 1, 1, 3, 3, 5, 6, 6, 8, 8, 8, 12, 12, 14, 14};
        int[] J = {10, 8, 3, 4, 3, -6, 2, 3, 4, 0, -3, -2, 10, -2, -1, -5, -6, -3, -8, -2, -1, -12, -1, -12, 1};
        double[] n = new double[25];
        n[0] = 0.00062909626082981;
        n[1] = -0.000823453502583165;
        n[2] = 0.0000000515446951519474;
        n[3] = -1.17565945784945;
        n[4] = 3.48519684726192;
        n[5] = -0.00000000000507837382408313;
        n[6] = -2.84637670005479;
        n[7] = -2.36092263939673;
        n[8] = 6.01492324973779;
        n[9] = 1.48039650824546;
        n[10] = 0.000360075182221907;
        n[11] = -0.0126700045009952;
        n[12] = -1221843.32521413;
        n[13] = 0.149276502463272;
        n[14] = 0.698733471798484;
        n[15] = -0.0252207040114321;
        n[16] = 0.0147151930985213;
        n[17] = -1.08618917681849;
        n[18] = -0.000936875039816322;
        n[19] = 81.9877897570217;
        n[20] = -182.041861521835;
        n[21] = 0.00000261907376402688;
        n[22] = -29162.6417025961;
        n[23] = 0.0000140660774926165;
        n[24] = 7832370.62349385;


        double h_star = 3000;
        double t_star = 900;
        double s_star = 5.3;
        double sigma = s / s_star;
        double eta = h / h_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((eta - 0.727) , I[num]) * Math.pow((sigma - 0.864) , J[num]);
        }
        x = x* t_star - 273.15;
        return x;

    }

    private double h_s_to_p_2c(double h, double s) {
        int[] I = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 5, 5, 5, 5, 6, 6, 10, 12, 16};
        int[] J = {0, 1, 2, 3, 4, 8, 0, 2, 5, 8, 14, 2, 3, 7, 10, 18, 0, 5, 8, 16, 18, 18, 1, 4, 6, 14, 8, 18, 7, 7, 10};
        double[] n = new double[31];
        n[0] = 0.112225607199012;
        n[1] = -3.39005953606712;
        n[2] = -32.0503911730094;
        n[3] = -197.5973051049;
        n[4] = -407.693861553446;
        n[5] = 13294.3775222331;
        n[6] = 1.70846839774007;
        n[7] = 37.3694198142245;
        n[8] = 3581.44365815434;
        n[9] = 423014.446424664;
        n[10] = -751071025.760063;
        n[11] = 52.3446127607898;
        n[12] = -228.351290812417;
        n[13] = -960652.417056937;
        n[14] = -80705929.2526074;
        n[15] = 1626980172256.69;
        n[16] = 0.772465073604171;
        n[17] = 46392.9973837746;
        n[18] = -13731788.5134128;
        n[19] = 1704703926305.12;
        n[20] = -25110462818730.8;
        n[21] = 31774883083552.0;
        n[22] = 53.8685623675312;
        n[23] = -55308.9094625169;
        n[24] = -1028615.22421405;
        n[25] = 2042494187562.34;
        n[26] = 273918446.626977;
        n[27] = -2.63963146312685E+15;
        n[28] = -1078908541.08088;
        n[29] = -29649262098.0124;
        n[30] = -1.11754907323424E+15;

        double h_star = 3500;
        double p_star = 100;
        double s_star = 5.9;
        double sigma = s / s_star;
        double eta = h / h_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((eta - 0.7) , I[num]) * Math.pow((sigma - 1.1) , J[num]);
        }
        x = Math.pow(x,4) * p_star;
        return x;
    }

    private double sh2c3b_s(double s) {
        int[] I = {0, 0, 0, 1, 1, 5, 6, 7, 8, 8, 12, 16, 22, 22, 24, 36};
        int[] J = {0, 3, 4, 0, 12, 36, 12, 16, 2, 20, 32, 36, 2, 32, 7, 20};
        double[] n = new double[16];
        n[0] = 1.04351280732769;
        n[1] = -2.27807912708513;
        n[2] = 1.80535256723202;
        n[3] = 0.420440834792042;
        n[4] = -105721.24483466;
        n[5] = 4.36911607493884E+24;
        n[6] = -328032702839.753;
        n[7] = -6.7868676080427E+15;
        n[8] = 7439.57464645363;
        n[9] = -3.56896445355761E+19;
        n[10] = 1.67590585186801E+31;
        n[11] = -3.55028625419105E+37;
        n[12] = 396611982166.538;
        n[13] = -4.14716268484468E+40;
        n[14] = 3.59080103867382E+18;
        n[15] = -1.16994334851995E+40;
        double h_star = 2800;
        double s_star = 5.9;
        double sigma = s / s_star;

        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((sigma - 1.02) , I[num]) * Math.pow((sigma - 0.726) , J[num]);
        }
        x = Math.pow(x,4)  * h_star;
        return x;
    }

    private double wh3a_s(double s) {
        int[] I = {0, 0, 0, 0, 2, 3, 4, 4, 5, 5, 6, 7, 7, 7, 10, 10, 10, 32, 32};
        int[] J = {1, 4, 10, 16, 1, 36, 3, 16, 20, 36, 4, 2, 28, 32, 14, 32, 36, 0, 6};
        double[] n = new double[19];

        n[0] = 0.822673364673336;
        n[1] = 0.181977213534479;
        n[2] = -0.0112000260313624;
        n[3] = -0.000746778287048033;
        n[4] = -0.179046263257381;
        n[5] = 0.0424220110836657;
        n[6] = -0.341355823438768;
        n[7] = -2.09881740853565;
        n[8] = -8.22477343323596;
        n[9] = -4.99684082076008;
        n[10] = 0.191413958471069;
        n[11] = 0.0581062241093136;
        n[12] = -1655.05498701029;
        n[13] = 1588.70443421201;
        n[14] = -85.0623535172818;
        n[15] = -31771.4386511207;
        n[16] = -94589.0406632871;
        n[17] = -0.0000013927384708869;
        n[18] = 0.63105253224098;



        double h_star = 1700;

        double s_star = 3.8;
        double sigma = s / s_star;

        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((sigma - 1.09) , I[num]) * Math.pow((sigma + 0.0000366) , J[num]);
        }
        x = x  * h_star;
        return x;
    }

    private double hB13_s(double s) {
        int[] I ={0, 1, 1, 3, 5, 6};
        int[] J = {0, -2, 2, -12, -4, -3};
        double[] n = new double[27];

        n[0] = 0.913965547600543;
        n[1] = -0.0000430944856041991;
        n[2] = 60.3235694765419;
        n[3] = 1.17518273082168E-18;
        n[4] = 0.220000904781292;
        n[5] = -69.0815545851641;

        double h_star = 1700;

        double s_star = 3.8;
        double sigma = s / s_star;

        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x + n[num] * Math.pow((sigma - 0.884) , I[num]) * Math.pow((sigma - 0.864) , J[num]);
        }
        x = x  * h_star;
        return x;
    }

    private double wh1_s(double s) {
        int[] I = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 7, 8, 12, 12, 14, 14, 16, 20, 20, 22, 24, 28, 32, 32};
        int[] J = {14, 36, 3, 16, 0, 5, 4, 36, 4, 16, 24, 18, 24, 1, 4, 2, 4, 1, 22, 10, 12, 28, 8, 3, 0, 6, 8};
        double[] n = new double[27];

        n[0] = 0.332171191705237;
        n[1] = 0.000611217706323496;
        n[2] = -8.82092478906822;
        n[3] = -0.45562819254325;
        n[4] = -0.0000263483840850452;
        n[5] = -22.3949661148062;
        n[6] = -4.28398660164013;
        n[7] = -0.616679338856916;
        n[8] = -14.682303110404;
        n[9] = 284.523138727299;
        n[10] = -113.398503195444;
        n[11] = 1156.71380760859;
        n[12] = 395.551267359325;
        n[13] = -1.54891257229285;
        n[14] = 19.4486637751291;
        n[15] = -3.57915139457043;
        n[16] = -3.35369414148819;
        n[17] = -0.66442679633246;
        n[18] = 32332.1885383934;
        n[19] = 3317.66744667084;
        n[20] = -22350.1257931087;
        n[21] = 5739538.75852936;
        n[22] = 173.226193407919;
        n[23] = -0.0363968822121321;
        n[24] = 0.000000834596332878346;
        n[25] = 5.03611916682674;
        n[26] = 65.5444787064505;


        double h_star = 1700;

        double s_star = 3.8;
        double sigma = s / s_star;

        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((sigma - 1.09) , I[num]) * Math.pow((sigma + 0.0000366) , J[num]);
        }
        x = x  * h_star;
        return x;
    }

    public double p_hs(double h, double s){
        String areatype;
        double Temp;
        double p;
        double x;
    double p_hs;
    areatype = subregion_hs(h, s);
    if(areatype.equals("0")){
        p_hs=-1000;
    }
    if(areatype.equals("1")){
        p = h_s_to_p_1(h, s);
    }
    else if(areatype.equals("2a")){
        p = h_s_to_p_2a(h, s);
    }
    else if(areatype.equals("2b")){
        p = h_s_to_p_2b(h, s);
    }
    else if(areatype.equals("2c")){
        p = h_s_to_p_2c(h, s);
    }
    else if(areatype.equals("3a")){
        p = h_s_to_p_3a(h, s);
    }
    else if(areatype.equals("3b")){
        p = h_s_to_p_3b(h, s);
    }
    else if(areatype.equals("4")){
        if (s >= 5.210887825){
            p = Ps(Ts_hs(h, s));
        }
        else{
            p = -1000;}
    }
    else{
        p=-1000;
    }
    p_hs=p;
    return p_hs;
}

    private double Ts_hs(double h, double s) {
        int[] I ={0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 5, 5, 6, 6, 6, 8, 10, 10, 12, 14, 14, 16, 16, 18, 18, 18, 20, 28};
        int[] J ={0, 3, 12, 0, 1, 2, 5, 0, 5, 8, 0, 2, 3, 4, 0, 1, 1, 2, 4, 16, 6, 8, 22, 1, 20, 36, 24, 1, 28, 12, 32, 14, 22, 36, 24, 36};
        double[] n = new double[36];
        n[0] = 0.179882673606601;
        n[1] = -0.267507455199603;
        n[2] = 1.162767226126;
        n[3] = 0.147545428713616;
        n[4] = -0.512871635973248;
        n[5] = 0.421333567697984;
        n[6] = 0.56374952218987;
        n[7] = 0.429274443819153;
        n[8] = -3.3570455214214;
        n[9] = 10.8890916499278;
        n[10] = -0.248483390456012;
        n[11] = 0.30415322190639;
        n[12] = -0.494819763939905;
        n[13] = 1.07551674933261;
        n[14] = 0.0733888415457688;
        n[15] = 0.0140170545411085;
        n[16] = -0.106110975998808;
        n[17] = 0.0168324361811875;
        n[18] = 1.25028363714877;
        n[19] = 1013.16840309509;
        n[20] = -1.51791558000712;
        n[21] = 52.4277865990866;
        n[22] = 23049.5545563912;
        n[23] = 0.0249459806365456;
        n[24] = 2107964.67412137;
        n[25] = 366836848.613065;
        n[26] = -144814105.365163;
        n[27] = -0.0017927637300359;
        n[28] = 4899556021.00459;
        n[29] = 471.262212070518;
        n[30] = -82929439019.8652;
        n[31] = -1715.45662263191;
        n[32] = 3557776.82973575;
        n[33] = 586062760258.436;
        n[34] = -12988763.5078195;
        n[35] = 31724744937.1057;
        double h_star = 2800;
        double t_star = 550;
        double s_star = 9.2;
        double sigma = s / s_star;
        double eta = h / h_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((eta -0.119) , I[num]) * Math.pow((sigma -1.07) , J[num]);
        }
        x =x*  t_star - 273.15;
        return x;
    }

    private double h_s_to_p_3b(double h, double s) {
        int[] I = {-12, -12, -12, -12, -12, -10, -10, -10, -10, -8, -8, -6, -6, -6, -6, -5, -4, -4, -4, -3, -3, -3, -3, -2, -2, -1, 0, 2, 2, 5, 6, 8, 10, 14, 14};
        int[] J = {2, 10, 12, 14, 20, 2, 10, 14, 18, 2, 8, 2, 6, 7, 8, 10, 4, 5, 8, 1, 3, 5, 6, 0, 1, 0, 3, 0, 1, 0, 1, 1, 1, 3, 7};
        double[] n = new double[35];
        n[0] = 0.000000000000125244360717979;
        n[1] = -0.0126599322553713;
        n[2] = 5.06878030140626;
        n[3] = 31.7847171154202;
        n[4] = -391041.161399932;
        n[5] = -0.0000000000975733406392044;
        n[6] = -18.6312419488279;
        n[7] = 510.973543414101;
        n[8] = 373847.005822362;
        n[9] = 0.0000000299804024666572;
        n[10] = 20.0544393820342;
        n[11] = -0.00000498030487662829;
        n[12] = -10.230180636003;
        n[13] = 55.2819126990325;
        n[14] = -206.211367510878;
        n[15] = -7940.12232324823;
        n[16] = 7.82248472028153;
        n[17] = -58.6544326902468;
        n[18] = 3550.73647696481;
        n[19] = -0.000115303107290162;
        n[20] = -1.75092403171802;
        n[21] = 257.98168774816;
        n[22] = -727.048374179467;
        n[23] = 0.000121644822609198;
        n[24] = 0.0393137871762692;
        n[25] = 0.00704181005909296;
        n[26] = -82.910820069811;
        n[27] = -0.26517881813125;
        n[28] = 13.7531682453991;
        n[29] = -52.2394090753046;
        n[30] = 2405.56298941048;
        n[31] = -22736.1631268929;
        n[32] = 89074.6343932567;
        n[33] = -23923456.5822486;
        n[34] = 5687958081.29714;

        double h_star = 2800;
        double p_star = 16.6;
        double s_star = 5.3;
        double sigma = s / s_star;
        double eta = h / h_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((eta - 0.681) , I[num]) * Math.pow((sigma - 0.792) , J[num]);
        }
        x = p_star / x;
        return x;
    }

    private double h_s_to_p_3a(double h, double s) {
        int[] I = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 7, 8, 10, 10, 14, 18, 20, 22, 22, 24, 28, 28, 32, 32};
        int[] J = {0, 1, 5, 0, 3, 4, 8, 14, 6, 16, 0, 2, 3, 0, 1, 4, 5, 28, 28, 24, 1, 32, 36, 22, 28, 36, 16, 28, 36, 16, 36, 10, 28};
        double[] n = new double[33];
        n[0] = 7.70889828326934;
        n[1] = -26.0835009128688;
        n[2] = 267.416218930389;
        n[3] = 17.2221089496844;
        n[4] = -293.54233214597;
        n[5] = 614.135601882478;
        n[6] = -61056.2757725674;
        n[7] = -65127225.1118219;
        n[8] = 73591.9313521937;
        n[9] = -11664650591.4191;
        n[10] = 35.5267086434461;
        n[11] = -596.144543825955;
        n[12] = -475.842430145708;
        n[13] = 69.6781965359503;
        n[14] = 335.674250377312;
        n[15] = 25052.6809130882;
        n[16] = 146997.380630766;
        n[17] = 5.38069315091534E+19;
        n[18] = 1.43619827291346E+21;
        n[19] = 3.64985866165994E+19;
        n[20] = -2547.41561156775;
        n[21] = 2.40120197096563E+27;
        n[22] = -3.93847464679496E+29;
        n[23] = 1.47073407024852E+24;
        n[24] = -4.26391250432059E+31;
        n[25] = 1.94509340621077E+38;
        n[26] = 6.66212132114896E+23;
        n[27] = 7.06777016552858E+33;
        n[28] = 1.75563621975576E+41;
        n[29] = 1.08408607429124E+28;
        n[30] = 7.30872705175151E+43;
        n[31] = 1.5914584739887E+24;
        n[32] = 3.77121605943324E+40;


        double h_star = 2300;
        double p_star = 99;
        double s_star = 4.4;
        double sigma = s / s_star;
        double eta = h / h_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((eta - 1.01) , I[num]) * Math.pow((sigma - 0.75) , J[num]);
        }
        x = x* p_star;
        return x;
    }

    private double h_s_to_p_2b(double h, double s) {
        int[] I ={0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 8, 12, 14};
        int[] J = {0, 1, 2, 4, 8, 0, 1, 2, 3, 5, 12, 1, 6, 18, 0, 1, 7, 12, 1, 16, 1, 12, 1, 8, 18, 1, 16, 1, 3, 14, 18, 10, 16};
        double[] n = new double[33];
        n[0] = 0.0801496989929495;
        n[1] = -0.543862807146111;
        n[2] = 0.337455597421283;
        n[3] = 8.9055545115745;
        n[4] = 313.840736431485;
        n[5] = 0.797367065977789;
        n[6] = -1.2161697355624;
        n[7] = 8.72803386937477;
        n[8] = -16.9769781757602;
        n[9] = -186.552827328416;
        n[10] = 95115.9274344237;
        n[11] = -18.9168510120494;
        n[12] = -4334.0703719484;
        n[13] = 543212633.012715;
        n[14] = 0.144793408386013;
        n[15] = 128.024559637516;
        n[16] = -67230.9534071268;
        n[17] = 33697238.0095287;
        n[18] = -586.63419676272;
        n[19] = -22140322476.9889;
        n[20] = 1716.06668708389;
        n[21] = -570817595.806302;
        n[22] = -3121.09693178482;
        n[23] = -2078413.8463301;
        n[24] = 3056059461577.86;
        n[25] = 3221.57004314333;
        n[26] = 326810259797.295;
        n[27] = -1441.04158934487;
        n[28] = 410.694867802691;
        n[29] = 109077066873.024;
        n[30] = -24796465425889.3;
        n[31] = 1888019068.65134;
        n[32] = -123651009018773.0;
        double h_star = 4100;
        double p_star = 100;
        double s_star = 7.9;
        double sigma = s / s_star;
        double eta = h / h_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((eta - 0.6) , I[num]) * Math.pow((sigma - 1.01) , J[num]);
        }
        x =Math.pow(x,4)* p_star;
        return x;
    }

    private double h_s_to_p_2a(double h, double s) {
        int[] I ={0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 4, 5, 5, 6, 7};
        int[] J ={1, 3, 6, 16, 20, 22, 0, 1, 2, 3, 5, 6, 10, 16, 20, 22, 3, 16, 20, 0, 2, 3, 6, 16, 16, 3, 16, 3, 1};
        double[] n = new double[29];
        n[0] = -0.0182575361923032;
        n[1] = -0.125229548799536;
        n[2] = 0.592290437320145;
        n[3] = 6.04769706185122;
        n[4] = 238.624965444474;
        n[5] = -298.639090222922;
        n[6] = 0.051225081304075;
        n[7] = -0.437266515606486;
        n[8] = 0.413336902999504;
        n[9] = -5.16468254574773;
        n[10] = -5.57014838445711;
        n[11] = 12.8555037824478;
        n[12] = 11.414410895329;
        n[13] = -119.504225652714;
        n[14] = -2847.7798596156;
        n[15] = 4317.57846408006;
        n[16] = 1.1289404080265;
        n[17] = 1974.09186206319;
        n[18] = 1516.12444706087;
        n[19] = 0.0141324451421235;
        n[20] = 0.585501282219601;
        n[21] = -2.97258075863012;
        n[22] = 5.94567314847319;
        n[23] = -6236.56565798905;
        n[24] = 9659.86235133332;
        n[25] = 6.81500934948134;
        n[26] = -6332.07286824489;
        n[27] = -5.5891922446576;
        n[28] = 0.0400645798472063;
        double h_star = 4200;
        double p_star = 4;
        double s_star = 12;
        double sigma = s / s_star;
        double eta = h / h_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((eta - 0.5) , I[num]) * Math.pow((sigma - 1.2) , J[num]);
        }
        x =Math.pow(x,4)* p_star;
        return x;
    }

    private double h_s_to_p_1(double h, double s) {
        int[] I ={0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 4, 4, 5};
        int[] J ={0, 1, 2, 4, 5, 6, 8, 14, 0, 1, 4, 6, 0, 1, 10, 4, 1, 4, 0};
        double[] n = new double[19];
        n[0] = -0.691997014660582;
        n[1] = -18.361254878756;
        n[2] = -9.28332409297335;
        n[3] = 65.9639569909906;
        n[4] = -16.2060388912024;
        n[5] = 450.620017338667;
        n[6] = 854.68067822417;
        n[7] = 6075.23214001162;
        n[8] = 32.6487682621856;
        n[9] = -26.9408844582931;
        n[10] = -319.9478483343;
        n[11] = -928.35430704332;
        n[12] = 30.3634537455249;
        n[13] = -65.0540422444146;
        n[14] = -4309.9131651613;
        n[15] = -747.512324096068;
        n[16] = 730.000345529245;
        n[17] = 1142.84032569021;
        n[18] = -436.407041874559;
        double h_star = 3400;
        double p_star = 100;
        double s_star = 7.6;
        double sigma = s / s_star;
        double eta = h / h_star;
        double x = 0;
        for (int num = 0; num < n.length; num++) {
            x = x+ n[num] * Math.pow((eta + 0.05) , I[num]) * Math.pow((sigma + 0.05) , J[num]);
        }
        x =x* p_star;
        return x;
    }

    public double t_hs(double h, double s){
        String areatype;
        double Temp=-1000;
        double p;
        double x;
        double t_hs;
        areatype = subregion_hs(h, s);
        if(areatype.equals("0")){
            t_hs=-1000;
        }
        if(areatype.equals("1")||areatype.equals("2a")||areatype.equals("2b")||areatype.equals("2c")||areatype.equals("3a")||areatype.equals("3b")){
            Temp = t_ph(p_hs(h, s), h);
        }

        if(areatype.equals("4")){
            if (s < 5.210887825){
                Temp = -1000;
            }
            else{
                Temp = Ts_hs(h, s);}
        }
        t_hs=Temp;
        return t_hs;
    }

    public double x_hs(double h, double s){
        String areatype;
        double Temp;
        double p;
        double x;
        double x_hs;
        double ws, ss;
        areatype = subregion_hs(h, s);
        if(areatype.equals("0")){
            x_hs=-1000;
        }
        if(!areatype.equals("4")){
            x_hs = -1000;
        }
        else{
            if (s < 5.210887825){
                x_hs = -1000;
            }
            else{
                Temp = Ts_hs(h, s);
                ws = s_pt(Ps(Temp), Temp, 0);
                ss = s_pt(Ps(Temp), Temp, 1);
                x_hs = (s - ws) / (ss - ws);
                if(x_hs>1){
                    x_hs=1;
                }
                if(x_hs<0){
                    x_hs=0;
                }
            }
        }
        return x_hs;
    }

    public double v_hs(double h, double s){
        String areatype;
        double Temp;
        double p;
        double x;
        double v_hs;
        areatype = subregion_hs(h, s);
        if(areatype.equals("0")){
            v_hs=-1000;
        }
        if(!areatype.equals("4")){
            p = p_hs(h, s);
            v_hs = v_ph(p, h);
        }

        else{
            if (s < 5.210887825){
                v_hs = -1000;
            }
            else{
                p = p_hs(h, s);
                x = x_hs(h, s);
                Temp = Ts(p);
                v_hs = vm(p, Temp, x);
            }
        }
        return v_hs;
    }
    public double den_hs(double h, double s){
        double a;
        double den_hs;
        a = v_hs(h, s);
        if(a ==Double.valueOf(0)||a ==Double.valueOf (-1000)){
                den_hs = -1000;}
        else{
                den_hs = 1 / a;}
        return den_hs;
    }

    public double u_hs(double h, double s){
        String areatype;
        double Temp;
        double p;
        double x;
        double u_hs;
        areatype = subregion_hs(h, s);
        if(areatype.equals("0")){
            u_hs=-1000;
        }
        if(!areatype.equals("4")){
            p = p_hs(h, s);
            u_hs = u_ph(p, h);
        }
        else{
            if (s < 5.210887825){
                u_hs = -1000;
            }
            else{
                u_hs = -1000;
            }
        }
        return u_hs;
    }

    public double cv_hs(double h, double s){
        String areatype;
        double Temp;
        double p;
        double x;
        double cv_hs;
        areatype = subregion_hs(h, s);
        if(areatype.equals("0")){
            cv_hs=-1000;
        }
        if(!areatype.equals("4")){
            p = p_hs(h, s);
            cv_hs = cv_ph(p, h);
        }

        else{
            if (s < 5.210887825){
                cv_hs = -1000;
            }
            else{
                cv_hs = -1000;
            }
        }
        return cv_hs;
    }

    public double cp_hs(double h, double s){
        String areatype;
        double Temp;
        double p;
        double x;
        double cp_hs;
        areatype = subregion_hs(h, s);
        if(areatype.equals("0")){
            cp_hs=-1000;
        }
        if(!areatype.equals("4")){
            p = p_hs(h, s);
            cp_hs = cp_ph(p, h);
        }

        else{
            if (s < 5.210887825){
                cp_hs = -1000;
            }
            else{
                cp_hs = -1000;
            }
        }
        return cp_hs;
    }

    public double w_hs(double h, double s){
        String areatype;
        double Temp;
        double p;
        double x;
        double w_hs;
        areatype = subregion_hs(h, s);
        if(areatype.equals("0")){
            w_hs=-1000;
        }
        if(!areatype.equals("4")){
            p = p_hs(h, s);
            w_hs = w_ph(p, h);
        }

        else{
            if (s < 5.210887825){
                w_hs = -1000;
            }
            else{
                w_hs = -1000;
            }
        }
        return w_hs;
    }

    public String m_type_PT(double p, double Temp){
        String areatype=subregion_pT(p,Temp);
        String m_type_PT;
        if (areatype.equals("1")){
                m_type_PT = "过冷水";}
        else if (areatype .equals("2")){
                m_type_PT = "过热汽";}
        else if (areatype.equals( "4")){
                m_type_PT = "饱和汽水";}
        else if (areatype.equals("5")){
                m_type_PT = "高温蒸汽";}
        else if (areatype.equals("0")){
                m_type_PT = "Over the range";}
        else if (areatype.equals("Critical point")){
                m_type_PT = "临界点";}
        else{
                m_type_PT = "临界/超临界汽水";}
        return  m_type_PT;
    }

    public String m_type_Ph(double p, double h){
        String areatype=subregion_ph(p,h);
        String m_type_PT;
        if (areatype.equals("1")){
            m_type_PT = "过冷水";}
        else if (areatype.equals("2a")||areatype .equals("2b")||areatype .equals("2c")){
            m_type_PT = "过热汽";}
        else if (areatype.equals( "4")){
            m_type_PT = "饱和汽水";}

        else if (areatype.equals("0")){
            m_type_PT = "Over the range";}

        else{
            m_type_PT = "临界/超临界汽水";}
        return  m_type_PT;
    }


    public String m_type_Ps(double mpa, double s) {
        String areatype=subregion_ps(mpa,s);
        String m_type_Ps;
        if (areatype.equals("1")){
            m_type_Ps = "过冷水";}
        else if (areatype.equals("2a")||areatype .equals("2b")||areatype .equals("2c")){
            m_type_Ps = "过热汽";}
        else if (areatype.equals( "4")){
            m_type_Ps = "饱和汽水";}

        else if (areatype.equals("0")){
            m_type_Ps = "Over the range";}

        else{
            m_type_Ps = "临界/超临界汽水";}
        return  m_type_Ps;
    }

    public String m_type_hs(double h, double s) {
        String areatype=subregion_hs(h,s);
        String m_type_hs;
        if (areatype.equals("1")){
            m_type_hs = "过冷水";}
        else if ((areatype.equals("2a")||areatype.equals("2b"))||areatype .equals("2c")){
            m_type_hs = "过热汽";}
        else if (areatype.equals( "4")){
            m_type_hs = "饱和汽水";}

        else if (areatype.equals("0")){
            m_type_hs = "Over the range";}

        else{
            m_type_hs = "临界/超临界汽水";}
        return  m_type_hs;
    }
}
