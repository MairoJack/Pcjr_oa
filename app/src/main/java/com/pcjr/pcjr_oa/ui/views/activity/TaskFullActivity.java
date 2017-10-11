package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.widget.Button;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;


/**
 *  完善任务
 *  Created by Mario on 2017/8/4下午3:07
 */
public class TaskFullActivity extends TaskActivity {

    @BindView(R.id.btn_confirm) Button btnConfirm;

    @Override
    protected int getLayoutId() {
        return R.layout.task_full;
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

}
