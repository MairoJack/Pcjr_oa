package com.pcjr.pcjr_oa.bean;


import java.io.Serializable;

/**
 *  紧急程度
 *  Created by Mario on 2017/8/16下午4:19
 */
public class Urgency implements Serializable{

    private String name;

    private int level;

    private boolean isSelected = false;

    public Urgency(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
