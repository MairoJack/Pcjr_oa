package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;

import lombok.Data;

/**
 *  人
 *  Created by Mario on 2017/8/3下午4:29
 */
@Data
public class Person implements Serializable {

    private String id;
    private String name;
    private String department;
    private String job;
    private String avatar;
    private String Realname;
    private boolean selected = false;

}
