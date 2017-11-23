package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by Mario on 2017/10/30上午10:57
 */
public class ContactAdapter extends BaseQuickAdapter<Contact, BaseViewHolder> {

    public ContactAdapter() {
        super(R.layout.item_customer_contact);
    }

    @Override
    protected void convert(BaseViewHolder helper, Contact item) {
        helper.setText(R.id.txt_name, item.getName());
        helper.setText(R.id.txt_belong_customer, item.getBelongCustomer());
        helper.setText(R.id.txt_date, DateUtils.longTimeToStr(item.getDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
