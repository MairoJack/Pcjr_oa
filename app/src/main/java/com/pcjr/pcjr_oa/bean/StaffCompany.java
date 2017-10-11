package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 *  员工所属公司
 *  Created by Mario on 2017/7/27上午9:36
 */
public class StaffCompany implements Serializable {

    @Expose
    private int id;
    @Expose
    @SerializedName("Company")
    private String company;
    @Expose
    @SerializedName("ShortName")
    private String shortName;
    @Expose
    private List<StaffCompany> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<StaffCompany> getChildren() {
        return children;
    }

    public void setChildren(List<StaffCompany> children) {
        this.children = children;
    }
}
