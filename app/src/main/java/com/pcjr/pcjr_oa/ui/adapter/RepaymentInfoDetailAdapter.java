package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Repayment;

/**
 * Created by Mario on 2017/10/9下午2:07
 */
public class RepaymentInfoDetailAdapter extends BaseQuickAdapter<Repayment, BaseViewHolder> {


    public RepaymentInfoDetailAdapter() {
        super(R.layout.item_repayment);
    }

    @Override
    protected void convert(BaseViewHolder helper, Repayment item) {
        helper.setText(R.id.txt_product_name, item.getProductName());
        helper.setText(R.id.txt_actual_capital, item.getEstimatedCapital() + "元");
        helper.setText(R.id.txt_actual_interest, item.getEstimatedInterest() + "元");
    }
}
