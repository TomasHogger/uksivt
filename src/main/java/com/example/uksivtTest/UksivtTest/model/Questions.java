package com.example.uksivtTest.UksivtTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questions {

    private String q1_1;
    private String q1_2;
    private String q2;
    private String q2_text;
    private String q3;
    private String q4;
    private String q5;
    private String q6;
    private String q7;
    private String q8;
    private List<String> q9;
    private String q9_text;
    private List<String> q10;
    private String q10_text;
    private String q11;
    private String q12_1;
    private String q12_2;
    private String q12_3;
    private String q12_4;
    private String q12_5;
    private String q13;
    private String q14;
    private List<String> q15;
    private String q15_text;
    private String q16;
    private String q17_1;
    private String q17_2;
    private String q18;
    private String q19;
    private String q20;
    private String q21;
    private String q22;
    private List<String> q23;
    private String q23_text;
    private String q24;
    private String q25;
    private String q25_text;
    private String q26;
    private String q27;
    private List<String> q28;
    private List<String> q29;
    private List<String> q30;
    private List<String> q31;
    private List<String> q32;
    private List<String> q33;
    private String q34;
    private String q35;
    private List<String> q36;
    private List<String> q37;
    private String q38;
    private String q39;
    private String q40;
    private String q41;
    private String q42;
    private String q43;
    private List<String> q44;
    private String q45;
    private String q46;
    private String q47;
    private List<String> q48;
    private String q48_text;
    private List<String> q49;
    private String q50;
    private String q51;
    private String q52;
    private String q53;
    private String q54;
    private List<String> q55;
    private String q56;
    private String q57;
    private String q58;
    private String q59;
    private String q60;


    public void setQ9(Object q9) {
        this.q9 = convertStringToArray(q9);
    }

    public void setQ10(Object q10) {
        this.q10 = convertStringToArray(q10);
    }

    public void setQ15(Object q15) {
        this.q15 = convertStringToArray(q15);
    }

    public void setQ23(Object q23) {
        this.q23 = convertStringToArray(q23);
    }

    public void setQ28(Object q28) {
        this.q28 = convertStringToArray(q28);
    }

    public void setQ29(Object q29) {
        this.q29 = convertStringToArray(q29);
    }

    public void setQ30(Object q30) {
        this.q30 = convertStringToArray(q30);
    }

    public void setQ31(Object q31) {
        this.q31 = convertStringToArray(q31);
    }

    public void setQ32(Object q32) {
        this.q32 = convertStringToArray(q32);
    }

    public void setQ33(Object q33) {
        this.q33 = convertStringToArray(q33);
    }

    public void setQ36(Object q36) {
        this.q36 = convertStringToArray(q36);
    }

    public void setQ37(Object q37) {
        this.q37 = convertStringToArray(q37);
    }

    public void setQ44(Object q44) {
        this.q44 = convertStringToArray(q44);
    }

    public void setQ48(Object q48) {
        this.q48 = convertStringToArray(q48);
    }
    public void setQ49(Object q49) {
        this.q49 = convertStringToArray(q49);
    }

    public void setQ55(Object q55) {
        this.q55 = convertStringToArray(q55);
    }


    private List<String> convertStringToArray(Object str) {
        List<String> arr;
        if (str instanceof String) {
            arr = new ArrayList<>();
            arr.add((String) str);
        } else {
            arr = new ArrayList<String>((List) str);
        }
        return arr;
    }
}
