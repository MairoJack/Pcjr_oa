package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import lombok.Data;

/**
 *  流程
 *  Created by Mario on 2017/12/8下午2:47
 */
@Data
public class Flow implements Serializable{
    @Expose
    private String name;
    @Expose
    private String title;
    @Expose
    private long time;
    @Expose
    private int status;
    @Expose
    private String content;

    public Flow(String name, String title,String content,int status,long time) {
        this.name = name;
        this.title = title;
        this.time = time;
        this.status = status;
        this.content = content;
    }
}
