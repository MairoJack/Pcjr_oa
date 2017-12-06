package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;


/**
 *  编辑客户 - 个人
 *  Created by Mario on 2017/9/21上午8:52
 */
public class CustomerEditPersonalActivity extends CustomerActivity {

    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.btn_confirm) Button btnConfirm;

    @Override
    protected int getLayoutId() {
        return R.layout.customer_full_personal;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        txtTitle.setText("编辑客户");
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
