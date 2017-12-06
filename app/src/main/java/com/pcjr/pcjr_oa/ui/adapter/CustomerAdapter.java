package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Approval;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */
public class CustomerAdapter extends BaseQuickAdapter<Customer, BaseViewHolder> {

    public CustomerAdapter() {
        super(R.layout.item_customer);
    }

    @Override
    protected void convert(BaseViewHolder helper, Customer item) {
        helper.setText(R.id.txt_title, item.getTitle());
        helper.setText(R.id.txt_name, item.getName());
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getTime(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
