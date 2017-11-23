package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import lombok.Data;

/**
 *  产品
 *  Created by Mario on 2017/11/22下午1:56
 */
@Data
public class Product implements Serializable{

    /**
     * 产品ID
     */
    private String id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 状态 0:待审核  1:发售中  2:已结束
     */
    private int status;
    /**
     * 产品金额
     */
    private String amount;
    /**
     * 产品期限
     */
    private String month;
    /**
     * 预期年化
     */
    @SerializedName("year_income")
    private String yearIncome;
    /**
     * 发售时间
     */
    @SerializedName("pub_date")
    private long pubDate;
    /**
     * 募集天数
     */
    @SerializedName("raise_date")
    private String raiseDate;
    /**
     * 回款日期
     */
    @SerializedName("repayment_date")
    private String repaymentDate;
    /**
     * 募集金额
     */
    @SerializedName("sale_amount")
    private String saleAmount;
    /**
     * 募集用时
     */
    @SerializedName("finish_time")
    private String finishTime;
    /**
     * 募集人数
     */
    @SerializedName("buyer_num")
    private String buyerNum;


}
