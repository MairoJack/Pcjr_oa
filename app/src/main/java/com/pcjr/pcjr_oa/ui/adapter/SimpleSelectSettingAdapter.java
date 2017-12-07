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
public class SimpleSelectSettingAdapter extends BaseQuickAdapter<SelectItem, BaseViewHolder> {


    public SimpleSelectSettingAdapter(List<SelectItem> list) {
        super(R.layout.item_simple_select_setting, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectItem item) {
        helper.setText(R.id.txt_name, item.getName());
        helper.addOnClickListener(R.id.img_remove);
    }
}
