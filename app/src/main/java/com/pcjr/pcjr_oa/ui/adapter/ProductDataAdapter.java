package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Product;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 *
 *  Created by Mario on 2017/10/9下午2:07
 */
public class ProductDataAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {


    public ProductDataAdapter() {
        super(R.layout.item_today_product);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        switch (item.getStatus()){
            case 0: helper.setText(R.id.txt_status, "募集完成");break;
            case 1: helper.setText(R.id.txt_status, "正在募集");break;
        }
        helper.setText(R.id.txt_product_name, item.getProductName());
        helper.setText(R.id.txt_product_month, item.getMonth());
        helper.setText(R.id.txt_collect_amount, item.getAmount());
        helper.setText(R.id.txt_expected_rate, item.getRate());
        helper.setText(R.id.txt_collect_days, item.getDays());
        helper.setText(R.id.txt_collect_time, item.getTime());
        helper.setText(R.id.txt_collect_number, item.getNumber()+"人");
        helper.setText(R.id.txt_public_date, DateUtils.longTimeToStr(item.getDate(), DateUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS));
    }
}
