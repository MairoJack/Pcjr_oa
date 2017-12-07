package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  编辑联系人
 *  Created by Mario on 2017/12/7下午2:33
 */
public class ContactEditActivity extends ContactActivity {

    @BindView(R.id.btn_finish) Button btnFinish;
    @BindView(R.id.txt_title) TextView txtTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.contact_full;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        txtTitle.setText("编辑联系人");
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        btnFinish.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

    }

    @Override
    protected void initData() {

    }

}
