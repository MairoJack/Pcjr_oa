package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.StringUtils;

/**
 * Created by Mario on 2017/10/30上午10:57
 */
public class ContactAdapter extends BaseQuickAdapter<Contact, BaseViewHolder> {

    public ContactAdapter() {
        super(R.layout.item_customer_contact);
    }

    @Override
    protected void convert(BaseViewHolder helper, Contact item) {
        String name = item.getName();
        helper.setText(R.id.txt_name, name);
        helper.setText(R.id.txt_avatar, StringUtils.getLast2(name));
        helper.setBackgroundRes(R.id.txt_avatar, R.drawable.avatar_background_blue_48);
        helper.addOnClickListener(R.id.btn_telephone);
    }

}
