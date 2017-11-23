package com.pcjr.pcjr_oa.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *  充值
 *  Created by Mario on 2017/10/9下午2:06
 */
@Data
public class Recharge implements Serializable {

    private String today_recharge_amount;

    private List<Days> list;
}
