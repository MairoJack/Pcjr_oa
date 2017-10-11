package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;

/**
 *  回款
 *  Created by Mario on 2017/10/10下午4:42
 */
public class Repayment implements Serializable {

    private String title;
    private String actualCapital;
    private String actualInterest;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActualCapital() {
        return actualCapital;
    }

    public void setActualCapital(String actualCapital) {
        this.actualCapital = actualCapital;
    }

    public String getActualInterest() {
        return actualInterest;
    }

    public void setActualInterest(String actualInterest) {
        this.actualInterest = actualInterest;
    }
}
