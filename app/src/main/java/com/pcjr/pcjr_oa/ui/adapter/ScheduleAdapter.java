package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Schedule;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 *  日程
 *  Created by Mario on 2017/8/10上午11:09
 */
public class ScheduleAdapter extends BaseQuickAdapter<Schedule, BaseViewHolder> {


    public ScheduleAdapter() {
        super(R.layout.item_schedule);
    }

    @Override
    protected void convert(BaseViewHolder helper, Schedule item) {
        helper.setText(R.id.txt_title, item.getTitle());
        helper.setText(R.id.txt_content, item.getContent());
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getTime(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
