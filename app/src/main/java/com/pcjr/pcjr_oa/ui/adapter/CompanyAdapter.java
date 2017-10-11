package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Company;


import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */
public class CompanyAdapter extends BaseQuickAdapter<Company, BaseViewHolder> {


    public CompanyAdapter(List<Company> list) {
        super(R.layout.item_company, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Company item) {
        helper.setText(R.id.txt_company, item.getName());
    }
}
