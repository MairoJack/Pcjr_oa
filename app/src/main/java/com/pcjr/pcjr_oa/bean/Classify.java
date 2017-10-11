package com.pcjr.pcjr_oa.bean;


import java.io.Serializable;

/**
 *  分类
 *  Created by Mario on 2017/7/31下午2:37
 */
public class Classify implements Serializable{

    private String name;

    private int type;

    private boolean isSelected = false;

    public Classify(String name, int type) {
        this.name = name;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
