package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
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
        String name = item.getName();
        if (item.getCustomerType() == 0) {
            if (name.length() < 2) {
                helper.setText(R.id.txt_avatar, name);
            } else {
                helper.setText(R.id.txt_avatar, name.substring(name.length() - 2));
                helper.setBackgroundRes(R.id.txt_avatar, R.drawable.avatar_background_blue_48);
            }
        } else {
            helper.setText(R.id.txt_avatar, "公司");
            helper.setBackgroundRes(R.id.txt_avatar, R.drawable.avatar_background_green_48);
        }
        helper.setText(R.id.txt_name, name);
        helper.setText(R.id.txt_manager, item.getManagerName());
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getJoinDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
