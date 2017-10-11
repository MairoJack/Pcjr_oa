package com.pcjr.pcjr_oa.ui.adapter;


import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Comment;
import com.pcjr.pcjr_oa.bean.Reply;
import com.pcjr.pcjr_oa.utils.DateUtils;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */

public class CommentAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public CommentAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_comment);
        addItemType(TYPE_LEVEL_1, R.layout.item_reply);

    }

    @Override
    protected void convert(BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                Comment comment = (Comment) item;
                holder.setText(R.id.txt_name, comment.getName());
                holder.setText(R.id.txt_content, comment.getContent());
                holder.setText(R.id.txt_time, DateUtils.longTimeToStr(comment.getDate(), DateUtils.YYYY_MM_DD_HH_MI));
                break;
            case TYPE_LEVEL_1:
                int p = getParentPosition(item);
                Comment parent = (Comment) getData().get(p);
                List<Reply> list = parent.getSubItems();
                int now = holder.getLayoutPosition() - p - 1;
                Reply reply = (Reply) item;

                if(list.size()  == 1){
                    holder.setText(R.id.txt_name, reply.getName());
                    holder.setText(R.id.txt_content, reply.getContent());
                    holder.setBackgroundRes(R.id.ll_reply, R.drawable.reply_background_singleton);
                } else if(list.size() == 2){
                    if(now == 0){
                        holder.setBackgroundRes(R.id.ll_reply, R.drawable.reply_background_top);
                    } else{
                        holder.setBackgroundRes(R.id.ll_reply, R.drawable.reply_background_bottom);
                    }
                    holder.setText(R.id.txt_name, reply.getName());
                    holder.setText(R.id.txt_content, reply.getContent());
                }else{
                    if(!reply.isMore()) {
                        holder.setText(R.id.txt_name, reply.getName());
                        holder.setText(R.id.txt_content, reply.getContent());
                    }else{
                        holder.setText(R.id.txt_name, "查看全部"+list.size()+"条回复>");
                        holder.addOnClickListener(R.id.txt_name);
                        holder.setVisible(R.id.txt_content,false);
                    }
                    if (now == 0) {
                        holder.setBackgroundRes(R.id.ll_reply, R.drawable.reply_background_top);
                    } else if (now == 1) {
                        holder.setBackgroundRes(R.id.ll_reply, R.drawable.reply_background_center);
                    } else if (now == 2){
                        holder.setBackgroundRes(R.id.ll_reply, R.drawable.reply_background_bottom);
                    } else {
                        holder.setVisible(R.id.ll_reply,false);
                    }
                }
                break;
        }
    }
}
