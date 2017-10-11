package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.SelectItem;

import java.util.List;

/**
 *
 *  Created by Mario on 2017/8/16下午4:20
 */
public class SimpleSelectAdapter extends BaseQuickAdapter<SelectItem, BaseViewHolder> {


    public SimpleSelectAdapter(List<SelectItem> list) {
        super(R.layout.item_simple_select, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectItem item) {
        helper.setText(R.id.txt_name, item.getName());
        if(item.isSelected()) {
            helper.setImageResource(R.id.img_selected,R.mipmap.icon_selected);
        }else{
            helper.setImageResource(R.id.img_selected,R.mipmap.icon_unselected);
        }
    }
}
