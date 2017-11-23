package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *  提现
 *  Created by Mario on 2017/11/23上午9:27
 */
@Data
public class Withdraw implements Serializable {

    /**
     * 今日提现金额
     */
    private String today_withdraw_amount;

    private List<Days> list;
}

