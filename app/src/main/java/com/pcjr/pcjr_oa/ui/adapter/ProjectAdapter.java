package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Project;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */

public class ProjectAdapter extends BaseQuickAdapter<Project, BaseViewHolder> {

    public ProjectAdapter() {
        super(R.layout.item_project);
    }

    @Override
    protected void convert(BaseViewHolder helper, Project item) {
        helper.setText(R.id.txt_title, item.getTitle());
        helper.setText(R.id.txt_content, item.getContent());
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getTime(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
