package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.utils.StringUtils;

/**
 * Created by mario on 2017/7/25.
 */
public class RelationshipAdapter extends BaseQuickAdapter<CustomerContactRelation, BaseViewHolder> {

    private int type;

    public RelationshipAdapter() {
        super(R.layout.item_contact_setting);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerContactRelation item) {
        String name;
        if (this.type == 2) {
            name = item.getContactName();
            helper.setText(R.id.txt_content, item.getCustomerName());
        } else {
            name = item.getCustomerName();
            helper.setText(R.id.txt_content, item.getContactName());
        }
        helper.setText(R.id.txt_title, name);
        helper.setText(R.id.txt_avatar, StringUtils.getLast2(name));
        helper.setText(R.id.txt_role, item.getRoleRelationship());
        helper.addOnClickListener(R.id.img_setting);
    }

    public void setType(int type) {
        this.type = type;
    }

}
