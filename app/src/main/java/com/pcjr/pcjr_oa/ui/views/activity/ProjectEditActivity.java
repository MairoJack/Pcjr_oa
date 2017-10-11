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
import com.pcjr.pcjr_oa.bean.Urgency;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;


/**
 *  编辑项目
 *  Created by Mario on 2017/8/18上午9:22
 */
public class ProjectEditActivity extends ProjectActivity {

    @BindView(R.id.btn_confirm) Button btnConfirm;

    @BindView(R.id.ll_feedback) LinearLayout llFeedback;
    @BindView(R.id.ll_urge) LinearLayout llUrge;
    @BindView(R.id.ll_follow) LinearLayout llFollow;
    @BindView(R.id.ll_finish) LinearLayout llFinish;
    @BindView(R.id.ll_more) LinearLayout llMore;

    private BottomSheetDialog moreBottomSheetDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.project_edit;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        initMoreBottomSheetDialog();

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        btnConfirm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            finish();
        });

        llUrge.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
            startActivity(new Intent(this,UrgeActivity.class));
        });

        llMore.setOnClickListener(v->{
            moreBottomSheetDialog.show();
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

    private void initMoreBottomSheetDialog(){
        moreBottomSheetDialog = new BottomSheetDialog(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_bottom_menu_grid, (ViewGroup) findViewById(R.id.dialog));
        ImageView btnCopy = view.findViewById(R.id.btn_copy);
        ImageView btnDelete = view.findViewById(R.id.btn_delete);
        TextView btnCancel = view.findViewById(R.id.btn_cancel);
        moreBottomSheetDialog.setContentView(view);
        moreBottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                .setBackgroundResource(android.R.color.transparent);


        btnCopy.setOnClickListener(v->{
            showToast("copy");
        });

        btnDelete.setOnClickListener(v->{
            showToast("delete");
        });

        btnCancel.setOnClickListener(v->{
            moreBottomSheetDialog.cancel();
        });
    }
}
