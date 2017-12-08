package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import lombok.Data;

/**
 *  操作日志
 *  Created by Mario on 2017/12/8下午2:47
 */
@Data
public class Log implements Serializable{
    @Expose
    private String id;
    @Expose
    private String title;
    @Expose
    private String name;
    @Expose
    private long time;
    @Expose
    private int img;
    @Expose
    private String content;

    public Log(String name, String title, long time) {
        this.name = name;
        this.title = title;
        this.time = time;
    }

}
