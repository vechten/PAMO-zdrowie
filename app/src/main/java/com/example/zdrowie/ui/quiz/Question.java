package com.example.zdrowie.ui.quiz;

public class Question {

    private final String que;
    private final String opt1;
    private final String opt2;
    private final String opt3;
    private final String rightOpt;

    public Question(String que, String opt1, String opt2, String opt3, String rightOpt) {
        this.que = que;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.rightOpt = rightOpt;
    }

    public String getQue() {
        return que;
    }

    public String getOpt1() {
        return opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public String getRightOpt() {
        return rightOpt;
    }
}