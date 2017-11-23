package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 *  回款
 *  Created by Mario on 2017/10/10下午4:42
 */
@Data
public class Repayment implements Serializable {

    /**
     * 日期
     */
    private String days;
    /**
     * 金额
     */
    private String amount;
    /**
     * 产品名称
     */
    @SerializedName("product_name")
    private String productName;
    /**
     * 应还本金
     */
    @SerializedName("estimated_capital")
    private String estimatedCapital;
    /**
     * 应还利息
     */
    @SerializedName("estimated_interest")
    private String estimatedInterest;

}
