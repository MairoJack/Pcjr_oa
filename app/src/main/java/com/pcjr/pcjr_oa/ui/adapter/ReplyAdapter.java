package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Reply;
import com.pcjr.pcjr_oa.utils.DateUtils;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */

public class ReplyAdapter extends BaseQuickAdapter<Reply, BaseViewHolder> {


    public ReplyAdapter(List<Reply> list) {
        super(R.layout.item_comment, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, Reply item) {
        holder.setText(R.id.txt_name, item.getName());
        holder.setText(R.id.txt_content, item.getContent());
        holder.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getDate(), DateUtils.YYYY_MM_DD_HH_MI));
        holder.addOnClickListener(R.id.txt_reply);
    }
}
