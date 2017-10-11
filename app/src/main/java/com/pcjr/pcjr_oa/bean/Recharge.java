package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;

/**
 *  充值
 *  Created by Mario on 2017/10/9下午2:06
 */
public class Recharge implements Serializable {

    private String amount;
    private long date;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
