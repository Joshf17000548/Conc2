package com.example.joshf.conc;

import android.support.v4.app.FragmentActivity;

/**
 * Created by user on 2016/09/12.
 */

public class HIA2 extends FragmentActivity {


    static int HIA2_Test2_Question1;
    static int HIA2_Test2_Question2;
    static int HIA2_Test2_Question3;
    static int HIA2_Test2_Question4;
    static int HIA2_Test2_Question5;
    static int HIA2_Test2_Question6;
    static int HIA2_Test2_Question7;
    static int HIA2_Test2_Question8;
    static int HIA2_Test2_Question9;
    static int HIA2_Test2_Question10;
    static int HIA2_Test2_Question11;
    static int HIA2_Test2_Question12;
    static int HIA2_Test2_Question13;
    static int HIA2_Test2_Question14;
    static int HIA2_Test2_Question15;
    static int HIA2_Test2_Question16;
    static int HIA2_Test2_Question17;
    static int HIA2_Test2_Question18;
    static int HIA2_Test2_Question19;
    static int HIA2_Test2_Question20;
    static int HIA2_Test2_Question21;
    static int HIA2_Test2_Question22;

    static String HIA2_Test3_Question1 = "NA";

    static int HIA2_Test4_Question1;
    static int HIA2_Test4_Question2;
    static int HIA2_Test4_Question3;
    static int HIA2_Test4_Question4;
    static int HIA2_Test4_Question5;
    static int HIA2_Test4_Question6;
    static int HIA2_Test4_Question7;

    static int HIA2_Test5_Question1;
    static int HIA2_Test5_Question2;

    static int HIA2_Test6_Question1;
    static String HIA2_Option_1;
    static String HIA2_Option_2;
    static String HIA2_Option_3;

    static int HIA2_Test7_Question1;

    static int HIA2_result;
    static boolean HIA2_Result_Chosen;

    static boolean HIA2_first =true;

    static String HIA1_result_string="/0";

    public HIA2() {
    }

    public static void clearHIA2(){


        HIA2_Test2_Question1=0;
        HIA2_Test2_Question2=0;
        HIA2_Test2_Question3=0;
        HIA2_Test2_Question4=0;
        HIA2_Test2_Question5=0;
        HIA2_Test2_Question6=0;
        HIA2_Test2_Question7=0;
        HIA2_Test2_Question8=0;
        HIA2_Test2_Question9=0;
        HIA2_Test2_Question10=0;
        HIA2_Test2_Question11=0;
        HIA2_Test2_Question12=0;
        HIA2_Test2_Question13=0;
        HIA2_Test2_Question14=0;
        HIA2_Test2_Question15=0;
        HIA2_Test2_Question16=0;
        HIA2_Test2_Question17=0;
        HIA2_Test2_Question18=0;
        HIA2_Test2_Question19=0;
        HIA2_Test2_Question20=0;
        HIA2_Test2_Question21=0;
        HIA2_Test2_Question22=0;

        HIA2_Test3_Question1 = "NA";

        HIA2_Test4_Question1=0;
        HIA2_Test4_Question2=0;
        HIA2_Test4_Question3=0;
        HIA2_Test4_Question4=0;
        HIA2_Test4_Question5=0;
        HIA2_Test4_Question6=0;
        HIA2_Test4_Question7=0;

        HIA2_Test5_Question1=0;
        HIA2_Test5_Question2=0;

        HIA2_Test6_Question1=0;
        HIA2_Option_1=null;
        HIA2_Option_2=null;
        HIA2_Option_3=null;

        HIA2_Test7_Question1=0;

        HIA2_result=0;
        HIA2_Result_Chosen = false;

        HIA2_first =true;

        HIA1_result_string="/0";

    }


    /*public int getHIA2_Test2_Question1() {
        return HIA2_Test2_Question1;
    }
    public int getHIA2_Test2_Question2() {
        return HIA2_Test2_Question2;
    }
    public int getHIA2_Test2_Question3() {
        return HIA2_Test2_Question3;
    }
    public int getHIA2_Test2_Question4() {
        return HIA2_Test2_Question4;
    }
    public int getHIA2_Test2_Question5() {
        return HIA2_Test2_Question5;
    }
    public int getHIA2_Test2_Question6() {
        return HIA2_Test2_Question6;
    }
    public int getHIA2_Test2_Question7() {
        return HIA2_Test2_Question7;
    }
    public int getHIA2_Test2_Question8() {
        return HIA2_Test2_Question8;
    }
    public int getHIA2_Test2_Question9() {
        return HIA2_Test2_Question9;
    }
    public int getHIA2_Test2_Question10() {
        return HIA2_Test2_Question10;
    }
    public int getHIA2_Test2_Question11() {
        return HIA2_Test2_Question11;
    }
    public int getHIA2_Test2_Question12() {
        return HIA2_Test2_Question12;
    }
    public int getHIA2_Test2_Question13() {
        return HIA2_Test2_Question13;
    }
    public int getHIA2_Test2_Question14() {
        return HIA2_Test2_Question14;
    }
    public int getHIA2_Test2_Question15() {
        return HIA2_Test2_Question15;
    }
    public int getHIA2_Test2_Question16() {
        return HIA2_Test2_Question16;
    }
    public int getHIA2_Test2_Question17() {
        return HIA2_Test2_Question17;
    }
    public int getHIA2_Test2_Question18() {
        return HIA2_Test2_Question18;
    }
    public int getHIA2_Test2_Question19() {
        return HIA2_Test2_Question19;
    }
    public int getHIA2_Test2_Question20() {
        return HIA2_Test2_Question20;
    }
    public int getHIA2_Test2_Question21() {
        return HIA2_Test2_Question21;
    }
    public int getHIA2_Test2_Question22() {
        return HIA2_Test2_Question22;
    }

    public String getHIA2_Test3_Question1() { return HIA2_Test3_Question1; }

    public int getHIA2_Test4_Question1() {
        return HIA2_Test4_Question1;
    }
    public int getHIA2_Test4_Question2() {
        return HIA2_Test4_Question2;
    }
    public int getHIA2_Test4_Question3() {
        return HIA2_Test4_Question3;
    }
    public int getHIA2_Test4_Question4() {
        return HIA2_Test4_Question4;
    }
    public int getHIA2_Test4_Question5() {
        return HIA2_Test4_Question5;
    }
    public int getHIA2_Test4_Question6() {
        return HIA2_Test4_Question6;
    }
    public int getHIA2_Test4_Question7() {
        return HIA2_Test4_Question7;
    }

    public int getHIA2_Test5_Question1() {
        return HIA2_Test5_Question1;
    }
    public int getHIA2_Test5_Question2() {
        return HIA2_Test5_Question2;
    }

    public int getHIA2_Test6_Question1() { return HIA2_Test6_Question1; }

    public int getHIA2_Test7_Question1() {
        return HIA2_Test7_Question1;
    }

    public void setHIA2_Test2_Question1(int HIA2_Test2_Question1) {this.HIA2_Test2_Question1 = HIA2_Test2_Question1;}
    public void setHIA2_Test2_Question2(int HIA2_Test2_Question2) {this.HIA2_Test2_Question2 = HIA2_Test2_Question2;}
    public void setHIA2_Test2_Question3(int HIA2_Test2_Question3) {this.HIA2_Test2_Question3 = HIA2_Test2_Question3;}
    public void setHIA2_Test2_Question4(int HIA2_Test2_Question4) {this.HIA2_Test2_Question4 = HIA2_Test2_Question4;}
    public void setHIA2_Test2_Question5(int HIA2_Test2_Question5) {this.HIA2_Test2_Question5 = HIA2_Test2_Question5;}
    public void setHIA2_Test2_Question6(int HIA2_Test2_Question6) {this.HIA2_Test2_Question6 = HIA2_Test2_Question6;}
    public void setHIA2_Test2_Question7(int HIA2_Test2_Question7) {this.HIA2_Test2_Question7 = HIA2_Test2_Question7;}
    public void setHIA2_Test2_Question8(int HIA2_Test2_Question8) {this.HIA2_Test2_Question8 = HIA2_Test2_Question8;}
    public void setHIA2_Test2_Question9(int HIA2_Test2_Question9) {this.HIA2_Test2_Question9 = HIA2_Test2_Question9;}
    public void setHIA2_Test2_Question10(int HIA2_Test2_Question10) {this.HIA2_Test2_Question10 = HIA2_Test2_Question10;}
    public void setHIA2_Test2_Question11(int HIA2_Test2_Question11) {this.HIA2_Test2_Question11 = HIA2_Test2_Question11;}
    public void setHIA2_Test2_Question12(int HIA2_Test2_Question12) {this.HIA2_Test2_Question12 = HIA2_Test2_Question12;}
    public void setHIA2_Test2_Question13(int HIA2_Test2_Question13) {this.HIA2_Test2_Question13 = HIA2_Test2_Question13;}
    public void setHIA2_Test2_Question14(int HIA2_Test2_Question14) {this.HIA2_Test2_Question14 = HIA2_Test2_Question14;}
    public void setHIA2_Test2_Question15(int HIA2_Test2_Question15) {this.HIA2_Test2_Question15 = HIA2_Test2_Question15;}
    public void setHIA2_Test2_Question16(int HIA2_Test2_Question16) {this.HIA2_Test2_Question16 = HIA2_Test2_Question16;}
    public void setHIA2_Test2_Question17(int HIA2_Test2_Question17) {this.HIA2_Test2_Question17 = HIA2_Test2_Question17;}
    public void setHIA2_Test2_Question18(int HIA2_Test2_Question18) {this.HIA2_Test2_Question18 = HIA2_Test2_Question18;}
    public void setHIA2_Test2_Question19(int HIA2_Test2_Question19) {this.HIA2_Test2_Question19 = HIA2_Test2_Question19;}
    public void setHIA2_Test2_Question20(int HIA2_Test2_Question20) {this.HIA2_Test2_Question20 = HIA2_Test2_Question20;}
    public void setHIA2_Test2_Question21(int HIA2_Test2_Question21) {this.HIA2_Test2_Question21 = HIA2_Test2_Question21;}
    public void setHIA2_Test2_Question22(int HIA2_Test2_Question22) {this.HIA2_Test2_Question22 = HIA2_Test2_Question22;}

    public void setHIA2_Test3_Question1(String HIA2_Test3_Question1) {this.HIA2_Test3_Question1 = HIA2_Test3_Question1;}

    public void setHIA2_Test4_Question1(int HIA2_Test4_Question1) {this.HIA2_Test4_Question1 = HIA2_Test4_Question1;}
    public void setHIA2_Test4_Question2(int HIA2_Test4_Question2) {this.HIA2_Test4_Question2 = HIA2_Test4_Question2;}
    public void setHIA2_Test4_Question3(int HIA2_Test4_Question3) {this.HIA2_Test4_Question3 = HIA2_Test4_Question3;}
    public void setHIA2_Test4_Question4(int HIA2_Test4_Question4) {this.HIA2_Test4_Question4 = HIA2_Test4_Question4;}
    public void setHIA2_Test4_Question5(int HIA2_Test4_Question5) {this.HIA2_Test4_Question5 = HIA2_Test4_Question5;}
    public void setHIA2_Test4_Question6(int HIA2_Test4_Question6) {this.HIA2_Test4_Question6 = HIA2_Test4_Question6;}
    public void setHIA2_Test4_Question7(int HIA2_Test4_Question7) {this.HIA2_Test4_Question7 = HIA2_Test4_Question7;}

    public void setHIA2_Test5_Question1(int HIA2_Test5_Question1) {this.HIA2_Test5_Question1 = HIA2_Test5_Question1;}
    public void setHIA2_Test5_Question2(int HIA2_Test5_Question2) {this.HIA2_Test5_Question2 = HIA2_Test5_Question2;}

    public void setHIA2_Test6_Question1(int HIA2_Test6_Question1) {this.HIA2_Test6_Question1 = HIA2_Test6_Question1;}

    public void setHIA2_Test7_Question1(int HIA2_Test7_Question1) {this.HIA2_Test7_Question1 = HIA2_Test7_Question1;}*/

}

