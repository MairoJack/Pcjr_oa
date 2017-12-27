package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */

public class ApprovalAdapter extends BaseQuickAdapter<BusinessApproval, BaseViewHolder> {

    public ApprovalAdapter() {
        super(R.layout.item_approval);
    }

    @Override
    protected void convert(BaseViewHolder helper, BusinessApproval item) {
        helper.setText(R.id.txt_title, item.getTitle());
        helper.setText(R.id.txt_name, item.getTitle());
        if(item.getStatus() == -1){
            helper.setVisible(R.id.txt_status,false);
        } else {
            helper.setVisible(R.id.txt_status,true);
            switch (item.getStatus()){
                case 0:helper.setText(R.id.txt_status,"审批中");break;
                case 1:helper.setText(R.id.txt_status,"已完成");break;
            }
        }
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getJoinDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
