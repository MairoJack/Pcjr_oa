package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *  回款信息
 *  Created by Mario on 2017/10/10下午4:42
 */
@Data
public class RepaymentInfo implements Serializable {

    /**
     * 今日回款金额
     */
    @SerializedName("today_repayment_amount")
    private String todayRepaymentAmount;
    /**
     * 当天
     */
    private String today;
    /**
     * 金额
     */
    private String amount;
    /**
     * 列表
     */
    private List<Repayment> list;

}
