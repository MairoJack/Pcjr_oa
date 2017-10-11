package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 *  审批
 *  Created by Mario on 2017/9/13上午9:30
 */
public class Approval implements Serializable{
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
    private int status;

    private boolean isSelected = false;

    public Approval(String title) {
        this.title = title;
    }

    public Approval(String title, String name, long time, int status) {
        this.title = title;
        this.name = name;
        this.time = time;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean select) {
        isSelected = select;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
