package com.pcjr.pcjr_oa.bean;


import java.io.Serializable;

/**
 *  选择条目
 *  Created by Mario on 2017/9/12上午9:17
 */
public class SelectItem implements Serializable{

    private String name;

    private boolean isSelected = false;

    public SelectItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}
