package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 *  分页
 *  Created by Mario on 2017/12/13上午10:18
 */
@Data
public class Pager implements Serializable {
    /**
     * 第几页
     */
    private int index;
    /**
     * 页大小
     */
    private int  size;
    /**
     * 总条数
     */
    private int  total;
    /**
     * 最大页
     */
    @SerializedName("max_page")
    private int  maxPage;

}
