package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Approval;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */

public class ApprovalAdapter extends BaseQuickAdapter<Approval, BaseViewHolder> {

    public ApprovalAdapter() {
        super(R.layout.item_approval);
    }

    @Override
    protected void convert(BaseViewHolder helper, Approval item) {
        helper.setText(R.id.txt_title, item.getTitle());
        helper.setText(R.id.txt_name, item.getName());
        switch (item.getStatus()){
            case 0:helper.setText(R.id.txt_status,"审批中");break;
            case 1:helper.setText(R.id.txt_status,"已完成");break;
        }
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getTime(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
