package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Contract;
import com.pcjr.pcjr_oa.utils.DateUtils;

/**
 * Created by mario on 2017/7/25.
 */
public class ContractAdapter extends BaseQuickAdapter<Contract, BaseViewHolder> {

    public ContractAdapter() {
        super(R.layout.item_contract);
    }

    @Override
    protected void convert(BaseViewHolder helper, Contract item) {
        helper.setText(R.id.txt_title, item.getContractNo());
        helper.setText(R.id.txt_name, item.getCreatePersonName());
        helper.setText(R.id.txt_time, DateUtils.longTimeToStr(item.getJoinDate(), DateUtils.DATE_FORMAT_YYYY_MM_DD));
    }

}
