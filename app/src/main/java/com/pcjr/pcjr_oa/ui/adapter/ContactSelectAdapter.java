package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.StringUtils;

/**
 * Created by mario on 2017/7/25.
 */
public class ContactSelectAdapter extends BaseQuickAdapter<Contact, BaseViewHolder> {

    public ContactSelectAdapter() {
        super(R.layout.item_contact_select);
    }

    @Override
    protected void convert(BaseViewHolder helper, Contact item) {
        String name = item.getName();
        helper.setText(R.id.txt_name, name);
        helper.setText(R.id.txt_avatar, StringUtils.getLast2(name));
        if(item.getIsSelected()) {
            helper.setImageResource(R.id.img_selected,R.mipmap.icon_selected);
        }else{
            helper.setImageResource(R.id.img_selected,R.mipmap.icon_unselected);
        }
    }

}
