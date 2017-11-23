package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 *  平台数据
 *  Created by Mario on 2017/11/23上午11:20
 */
@Data
public class PlatformData implements Serializable{

    /**
     * 可以余额
     */
    @SerializedName("available_balance")
    private String availableBalance;
    /**
     * 昨日余额
     */
    @SerializedName("yesterday_available_balance")
    private String yesterdayAvailableBalance;
    /**
     * 会员总数
     */
    @SerializedName("member_num")
    private String memberNum;
    /**
     * 昨日会员总数
     */
    @SerializedName("yesterday_member_num")
    private String yesterdayMemberNum;
    /**
     * 充值金额
     */
    @SerializedName("recharge_amount")
    private String rechargeAmount;
    /**
     * 昨日充值金额
     */
    @SerializedName("yesterday_recharge_amount")
    private String yesterdayRechargeAmount;
    /**
     * 提现金额
     */
    @SerializedName("withdraw_amount")
    private String withdrawAmount;
    /**
     * 昨日充值金额
     */
    @SerializedName("yesterday_withdraw_amount")
    private String yesterdayWithdrawAmount;


}
