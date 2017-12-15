package com.pcjr.pcjr_oa.ui.adapter;

import android.annotation.SuppressLint;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Flow;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */
public class FlowAdapter extends BaseQuickAdapter<Flow, BaseViewHolder> {

    public FlowAdapter() {
        super(R.layout.item_approval_flow);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, Flow item) {
        switch (item.getStatus()) {
            case 0 :
                helper.setImageResource(R.id.img_icon,R.mipmap.icon_flow_submit);
                helper.setTextColor(R.id.txt_title,R.color.color_explain);
                helper.setBackgroundRes(R.id.ll_background,R.drawable.left_border_green);
                helper.setVisible(R.id.ll_content,true);
                break;
            case 1:
                helper.setImageResource(R.id.img_icon,R.mipmap.icon_flow_ok);
                helper.setTextColor(R.id.txt_title,R.color.color_explain);
                helper.setBackgroundRes(R.id.ll_background,R.drawable.left_border_green);
                helper.setVisible(R.id.ll_content,true);
                break;
            case 2 :
                helper.setImageResource(R.id.img_icon,R.mipmap.icon_flow_pending);
                helper.setTextColor(R.id.txt_title,R.color.color_yellow);
                helper.setBackgroundRes(R.id.ll_background,R.drawable.left_border_yellow);
                helper.setVisible(R.id.ll_content,true);
                break;
            case 3 :
                helper.setImageResource(R.id.img_icon,R.mipmap.icon_flow_return);
                helper.setTextColor(R.id.txt_title,R.color.color_intro);
                helper.setBackgroundRes(R.id.ll_background,R.drawable.left_border_red);
                helper.setVisible(R.id.ll_content,true);
                break;
            case 4 :
                helper.setImageResource(R.id.img_icon,R.mipmap.icon_flow_pending_gray);
                helper.setTextColor(R.id.txt_title,R.color.color_explain);
                helper.setBackgroundRes(R.id.ll_background,R.drawable.left_border_gray);
                helper.setVisible(R.id.ll_content,true);
                break;
            case 5 :
                helper.setImageResource(R.id.img_icon,R.mipmap.icon_flow_finish);
                helper.setVisible(R.id.ll_content,false);
                break;
        }
        helper.setText(R.id.txt_title, item.getName() + "    " + item.getTitle());
        if(item.getContent().equals("")){
            helper.setText(R.id.txt_content, item.getTitle());
            helper.setVisible(R.id.txt_content,true);
        } else {
            helper.setText(R.id.txt_content, "");
            helper.setVisible(R.id.txt_content,false);
        }

        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getTime(), DateUtils.YYYY_MM_DD_HH_MI));
    }

}
