package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 *  产品汇总
 *  Created by Mario on 2017/10/10上午10:30
 */
@Data
public class ProductSummary implements Serializable{
    /**
     * 平均用时
     */
    @SerializedName("avg_sale_time")
    private String avgSaleTime;
    /**
     * 产品平均年化
     */
    @SerializedName("avg_year_income")
    private String avgYearIncome;
    /**
     * 产品数量
     */
    @SerializedName("product_num")
    private String productNum;
    /**
     * 成交总额
     */
    @SerializedName("total_trading_amount")
    private String totalTradingAmount;
    /**
     * 成交总笔数
     */
    @SerializedName("total_deal_num")
    private String totalDealNum;
    /**
     * 参与投资总人数
     */
    @SerializedName("total_member_num")
    private String totalMemberNum;
   
}
