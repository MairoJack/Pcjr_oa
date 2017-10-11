package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Urgency;

import java.util.List;

/**
 *
 *  Created by Mario on 2017/8/16下午4:20
 */
public class UrgencyAdapter extends BaseQuickAdapter<Urgency, BaseViewHolder> {


    public UrgencyAdapter(List<Urgency> list) {
        super(R.layout.item_urgency, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Urgency item) {
        switch (item.getLevel()){
            case 1:helper.setBackgroundRes(R.id.img_level,R.drawable.dot_level1);break;
            case 2:helper.setBackgroundRes(R.id.img_level,R.drawable.dot_level2);break;
            case 3:helper.setBackgroundRes(R.id.img_level,R.drawable.dot_level3);break;
        }
        helper.setText(R.id.txt_name, item.getName());
        if(item.isSelected()) {
            helper.setImageResource(R.id.img_selected,R.mipmap.icon_selected);
        }else{
            helper.setImageResource(R.id.img_selected,R.mipmap.icon_unselected);
        }
    }
}
