package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;

/**
 *  标签
 *  Created by Mario on 2017/8/7上午10:50
 */
public class Tag implements Serializable {

    private String name;
    private String typeName;
    private int type;
    private boolean selected = false;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(String typeName, int type) {
        this.typeName = typeName;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
