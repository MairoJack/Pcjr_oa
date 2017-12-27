package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.utils.StringUtils;

/**
 * Created by mario on 2017/7/25.
 */
public class CustomerSelectAdapter extends BaseQuickAdapter<Customer, BaseViewHolder> {

    public CustomerSelectAdapter() {
        super(R.layout.item_contact_select);
    }

    @Override
    protected void convert(BaseViewHolder helper, Customer item) {
        String name = item.getName();
        if (item.getCustomerType() == 0) {
            helper.setText(R.id.txt_avatar, StringUtils.getLast2(name));
            helper.setBackgroundRes(R.id.txt_avatar, R.drawable.avatar_background_blue_48);
        } else {
            helper.setText(R.id.txt_avatar, "公司");
            helper.setBackgroundRes(R.id.txt_avatar, R.drawable.avatar_background_green_48);
        }
        helper.setText(R.id.txt_name, name);
        helper.setText(R.id.txt_manager, item.getManagerName());
        if(item.getIsSelected()) {
            helper.setImageResource(R.id.img_selected,R.mipmap.icon_selected);
        }else{
            helper.setImageResource(R.id.img_selected,R.mipmap.icon_unselected);
        }
    }

}
