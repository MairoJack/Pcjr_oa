package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 *  项目
 *  Created by Mario on 2017/8/17下午2:50
 */
public class Project implements Serializable{
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

    private boolean isSelected = false;

    public Project(String title, long time, String content) {
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public Project(String title) {
        this.title = title;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean select) {
        isSelected = select;
    }
}
