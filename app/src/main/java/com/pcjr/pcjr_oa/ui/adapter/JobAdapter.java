package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Job;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */
public class JobAdapter extends BaseQuickAdapter<Job, BaseViewHolder> {


    public JobAdapter() {
        super(R.layout.item_job);
    }

    @Override
    protected void convert(BaseViewHolder helper, Job item) {
        helper.setText(R.id.txt_job_name, item.getName());
        helper.setText(R.id.txt_number, String.valueOf(item.getNumber()));
    }
}
