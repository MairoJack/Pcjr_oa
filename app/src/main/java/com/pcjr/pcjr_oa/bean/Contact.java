package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 *  联系人
 *  Created by Mario on 2017/10/30上午10:55
 */
public class Contact implements Serializable{
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String belongCustomer;
    @Expose
    private long date;
    @Expose
    private int img;

    public Contact(String name, String belongCustomer, long date) {
        this.name = name;
        this.belongCustomer = belongCustomer;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBelongCustomer() {
        return belongCustomer;
    }

    public void setBelongCustomer(String belongCustomer) {
        this.belongCustomer = belongCustomer;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
