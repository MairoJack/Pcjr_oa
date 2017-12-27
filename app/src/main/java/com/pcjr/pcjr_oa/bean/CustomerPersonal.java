package com.pcjr.pcjr_oa.bean;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  客户 - 个人
 *  Created by Mario on 2017/9/20下午1:37
 */
@Data
public class CustomerPersonal extends Customer{

    private String identity;                //身份证
    private String company;                 //所在公司
    private String position;                //职位
    private Integer sex;                    //性别 0：男  1：女
    private Integer marriage;               //婚姻状况 0：未婚  1：已婚  2：离异

    public CustomerPersonal(String name, String mobile, String identity) {
        this.name = name;
        this.mobile = mobile;
        this.identity = identity;
    }

    public CustomerPersonal() {
    }
}
