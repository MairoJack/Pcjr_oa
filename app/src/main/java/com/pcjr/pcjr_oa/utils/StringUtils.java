package com.pcjr.pcjr_oa.utils;

import java.math.BigDecimal;

/**
 *
 *  Created by Mario on 2017/11/22上午10:52
 */
public class StringUtils {


    public static boolean validate(String text) {
        if(text != null && !text.equals(""))
            return true;
        return false;
    }

    public static String tenThousand(String amount) {
        BigDecimal bdAmount= new BigDecimal(amount);
        BigDecimal bdTenThousand = new BigDecimal("10000");
        if(bdAmount.compareTo(bdTenThousand) >= 0) {
            amount = bdAmount.divide(bdTenThousand,BigDecimal.ROUND_DOWN).toString() + "万元";
        }
        return amount + "元";
    }
}