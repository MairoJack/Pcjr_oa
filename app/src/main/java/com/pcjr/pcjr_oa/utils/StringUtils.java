package com.pcjr.pcjr_oa.utils;

import java.math.BigDecimal;

/**
 *
 *  Created by Mario on 2017/11/22上午10:52
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param text
     * @return
     */
    public static boolean validate(String text) {
        if(text != null && !text.equals(""))
            return true;
        return false;
    }

    /**
     * 截取字符串末两位
     * @param text
     * @return
     */
    public static String getLast2(String text) {
        if(text != null && text.length() > 2)
            return text.substring(text.length()-2);
        return text;
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