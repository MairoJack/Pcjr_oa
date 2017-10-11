package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;

/**
 *  职位
 *  Created by Mario on 2017/8/9上午11:13
 */
public class Job implements Serializable {

    private String name;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
