package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Days;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.utils.DateUtils;

import java.math.BigDecimal;

/**
 *
 *  Created by Mario on 2017/10/9下午2:07
 */
public class FinanceInfoAdapter extends BaseQuickAdapter<Days, BaseViewHolder> {

    private double max;

    public void setMax(double max){
        this.max = max;
    }

    public FinanceInfoAdapter() {
        super(R.layout.item_finance_info);
    }

    @Override
    protected void convert(BaseViewHolder helper, Days item) {
        double amount = Double.parseDouble(item.getAmount());
        int progress = (int)(amount/this.max * 100);
        helper.setProgress(R.id.progress,progress);
        helper.setText(R.id.txt_date, item.getDays());
        helper.setText(R.id.txt_amount, item.getAmount());
    }
}
