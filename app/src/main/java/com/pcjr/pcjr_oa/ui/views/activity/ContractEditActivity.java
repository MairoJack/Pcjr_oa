package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;


/**
 *  编辑合同
 *  Created by Mario on 2017/12/25下午4:25
 */
public class ContractEditActivity extends ContractActivity {

    @BindView(R.id.btn_confirm) Button btnConfirm;

    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_query_records) LinearLayout llQueryRecords;
    @BindView(R.id.ll_log) LinearLayout llLog;
    @BindView(R.id.ll_delete) LinearLayout llDelete;

    @Override
    protected int getLayoutId() {
        return R.layout.contract_edit;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        btnConfirm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
            }
        }
    }

}
