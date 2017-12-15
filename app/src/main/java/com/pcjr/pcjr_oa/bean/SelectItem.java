package com.pcjr.pcjr_oa.bean;


import java.io.Serializable;

import lombok.Data;

/**
 *  选择条目
 *  Created by Mario on 2017/9/12上午9:17
 */
@Data
public class SelectItem implements Serializable{

    private String name;                    //选择条目名称
    private int type;                       //选择条目类型
    private boolean isSelected = false;     //是否选中

    public SelectItem(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public SelectItem(String name) {
        this.name = name;
    }
}
