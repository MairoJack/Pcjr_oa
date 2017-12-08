package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Log;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */
public class LogAdapter extends BaseQuickAdapter<Log, BaseViewHolder> {

    public LogAdapter() {
        super(R.layout.item_simple_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Log item) {
        helper.setText(R.id.txt_title, item.getName());
        helper.setText(R.id.txt_content, item.getTitle());
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getTime(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
