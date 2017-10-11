package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 *
 *  Created by Mario on 2017/10/9下午2:07
 */
public class RepaymentInfoAdapter extends BaseQuickAdapter<Recharge, BaseViewHolder> {

    private double max;

    public void setMax(double max){
        this.max = max;
    }

    public RepaymentInfoAdapter() {
        super(R.layout.item_finance_info);
    }

    @Override
    protected void convert(BaseViewHolder helper, Recharge item) {
        double amount = Double.parseDouble(item.getAmount());
        int progress = (int)(amount/this.max * 100);
        helper.setProgress(R.id.progress,progress);
        helper.setText(R.id.txt_date, DateUtils.longTimeToStr(item.getDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
        helper.setText(R.id.txt_amount, item.getAmount());
    }
}
