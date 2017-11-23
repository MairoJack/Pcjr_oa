package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *  用户
 *  Created by Mario on 2017/11/23上午9:27
 */
@Data
public class Member implements Serializable {

    /**
     * 今日注册人数
     */
    private String today_member_num;
    /**
     * 今日实名人数
     */
    private String today_effective_member_num;

    private List<Days> list;
}
