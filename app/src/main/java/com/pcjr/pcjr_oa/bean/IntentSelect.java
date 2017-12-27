package com.pcjr.pcjr_oa.bean;


import java.io.Serializable;

import lombok.Data;

/**
 *  传递 Intent 选择
 *  Created by Mario on 2017/12/7下午3:39
 */
@Data
public class IntentSelect implements Serializable{

    private String title;                          //选择项
    private String select;                         //当前选择项
    private String[] data;                         //选择项数据
    private boolean isSetting = false;             //是否需要设置

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
