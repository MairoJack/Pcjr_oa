package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Days;

/**
 *
 *  Created by Mario on 2017/10/9下午2:07
 */
public class UserCountAdapter extends BaseQuickAdapter<Days, BaseViewHolder> {

    private double max;

    public void setMax(double max){
        this.max = max;
    }

    public UserCountAdapter() {
        super(R.layout.item_user_count);
    }

    @Override
    protected void convert(BaseViewHolder helper, Days item) {
        double num = Double.parseDouble(item.getNum());
        int progress = (int)(num/this.max * 100);
        helper.setProgress(R.id.progress,progress);
        helper.setText(R.id.txt_date, item.getDays());
        helper.setText(R.id.txt_num, item.getNum());
    }
}
