package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.CategorySection;
import com.pcjr.pcjr_oa.bean.CategoryTask;
import com.pcjr.pcjr_oa.utils.DateUtils;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */

public class CategoryTaskAdapter extends BaseSectionQuickAdapter<CategorySection, BaseViewHolder> {


    public CategoryTaskAdapter(List<CategorySection> list) {
        super(R.layout.item_category1,R.layout.section_category,list);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, CategorySection item) {
        helper.setText(R.id.section_work_name,item.header);
    }
    @Override
    protected void convert(BaseViewHolder helper, CategorySection item) {
        CategoryTask object = item.t;
        helper.setText(R.id.txt_title, object.getTitle());
        helper.setText(R.id.txt_content, object.getContent());
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(object.getTime(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }


}
