package com.pcjr.pcjr_oa.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Department;

import java.util.List;

/**
 * Created by mario on 2017/7/25.
 */
public class DepartmentAdapter extends BaseQuickAdapter<Department, BaseViewHolder> {


    public DepartmentAdapter() {
        super(R.layout.item_department);
    }

    @Override
    protected void convert(BaseViewHolder helper, Department item) {
        helper.setText(R.id.txt_department, item.getName());
        helper.setText(R.id.txt_number, String.valueOf(item.getNumber()));
    }
}
