package com.pcjr.pcjr_oa.ui.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Classify;

import java.util.List;

/**
 *  下列列表菜单
 *  Created by Mario on 2017/8/15下午4:22
 */
public class ClassifyListAdapter extends BaseQuickAdapter<Classify, BaseViewHolder> {


    public ClassifyListAdapter(List<Classify> list) {
        super(R.layout.item_classify_list, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Classify item) {
        helper.setText(R.id.txt_name, item.getName());
        if(item.isSelected()){
            helper.setVisible(R.id.img_selected,true);
            helper.setTextColor(R.id.txt_name, Color.parseColor("#ff4341"));
        }else{
            helper.setVisible(R.id.img_selected,false);
            helper.setTextColor(R.id.txt_name, Color.parseColor("#606060"));
        }
    }
}
