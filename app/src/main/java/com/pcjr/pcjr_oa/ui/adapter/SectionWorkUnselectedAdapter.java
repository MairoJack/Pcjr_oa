package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.WorkItem;
import com.pcjr.pcjr_oa.bean.WorkSection;

import java.util.List;

/**
 *  Created by Mario on 2017/9/14下午2:14
 */
public class SectionWorkUnselectedAdapter extends BaseSectionQuickAdapter<WorkSection,BaseViewHolder> {

    public SectionWorkUnselectedAdapter(List<WorkSection> data) {
        super(R.layout.item_work_unselect, R.layout.section_work_unselected, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, WorkSection item) {
        helper.setText(R.id.section_work_name,item.header);
    }


    @Override
    protected void convert(BaseViewHolder helper, WorkSection item) {
        WorkItem workItem = item.t;
        if(workItem.isSelected()){
            helper.setImageResource(R.id.img_status,R.mipmap.icon_choose);
        }else{
            helper.setImageResource(R.id.img_status,R.mipmap.icon_increase);
        }
        helper.setText(R.id.txt_work_name,workItem.getName());
        helper.setImageResource(R.id.img_work,workItem.getImg());
    }


}
