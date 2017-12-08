package com.pcjr.pcjr_oa.ui.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.QueryRecord;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */
public class QueryRecordAdapter extends BaseQuickAdapter<QueryRecord, BaseViewHolder> {

    public QueryRecordAdapter() {
        super(R.layout.item_simple_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryRecord item) {
        helper.setText(R.id.txt_title, item.getName());
        if(item.isQuery()){
            helper.setTextColor(R.id.txt_content, Color.parseColor("#606060"));
            helper.setText(R.id.txt_content, "已查阅");
        } else {
            helper.setTextColor(R.id.txt_content, Color.parseColor("#ff4341"));
            helper.setText(R.id.txt_content, "未查阅");
        }
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getTime(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
