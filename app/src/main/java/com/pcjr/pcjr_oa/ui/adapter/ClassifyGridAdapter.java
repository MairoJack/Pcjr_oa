package com.pcjr.pcjr_oa.ui.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */

public class ClassifyGridAdapter extends BaseSectionQuickAdapter<ClassifySection, BaseViewHolder> {


    public ClassifyGridAdapter(List<ClassifySection> list) {
        super(R.layout.item_classify_grid,R.layout.classify_section,list);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ClassifySection item) {
        helper.setText(R.id.section,item.header);
    }
    @Override
    protected void convert(BaseViewHolder helper, ClassifySection item) {
        Classify object = item.t;
        if(object.isSelected()) {
            helper.setBackgroundRes(R.id.txt_classify,R.drawable.corner_red_5dp);
            helper.setTextColor(R.id.txt_classify, Color.parseColor("#FFFFFF"));
        }else{
            helper.setBackgroundRes(R.id.txt_classify,R.drawable.corner_gray_5dp);
            helper.setTextColor(R.id.txt_classify, Color.parseColor("#606060"));
        }
        helper.setText(R.id.txt_classify, object.getName());
    }


}
