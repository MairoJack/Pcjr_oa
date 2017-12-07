package com.pcjr.pcjr_oa.bean;


import java.io.Serializable;

import lombok.Data;

/**
 *  传递 Intent 选择
 *  Created by Mario on 2017/12/7下午3:39
 */
@Data
public class IntentSelect implements Serializable{

    private String title;
    private String select;
    private String[] data;
    private boolean isSetting = false;

    public IntentSelect(String title, String select, String[] data) {
        this.title = title;
        this.select = select;
        this.data = data;
    }

    public IntentSelect(String title, String select, String[] data, boolean isSetting) {
        this.title = title;
        this.select = select;
        this.data = data;
        this.isSetting = isSetting;
    }
}
