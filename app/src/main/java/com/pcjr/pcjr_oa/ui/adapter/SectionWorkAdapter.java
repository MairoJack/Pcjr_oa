package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.WorkItem;
import com.pcjr.pcjr_oa.bean.WorkSection;

import java.util.List;

/**
 * Created by mario on 2017/7/26.
 */

public class SectionWorkAdapter extends BaseSectionQuickAdapter<WorkSection,BaseViewHolder> {

    public SectionWorkAdapter(List<WorkSection> data) {
        super(R.layout.item_work, R.layout.section_work, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, WorkSection item) {
        helper.setText(R.id.section_work_name,item.header);
    }


    @Override
    protected void convert(BaseViewHolder helper, WorkSection item) {
        WorkItem workItem = item.t;
        helper.setText(R.id.txt_work_name,workItem.getName());
        helper.setImageResource(R.id.img_work,workItem.getImg());
    }


}
