package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import lombok.Data;

/**
 *  查询记录
 *  Created by Mario on 2017/12/8下午2:47
 */
@Data
public class QueryRecord implements Serializable{
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
    private boolean isQuery;

    public QueryRecord(String name, boolean isQuery, long time) {
        this.name = name;
        this.isQuery = isQuery;
        this.time = time;
    }

}
