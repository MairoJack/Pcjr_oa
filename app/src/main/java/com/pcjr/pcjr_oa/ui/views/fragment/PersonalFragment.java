package com.pcjr.pcjr_oa.ui.views.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseFragment;
import com.pcjr.pcjr_oa.ui.views.activity.AccountSafeActivity;
import com.pcjr.pcjr_oa.ui.views.activity.PersonalSettingActivity;
import com.pcjr.pcjr_oa.ui.views.activity.StaffDetailActivity;

import butterknife.BindView;

/**
 *  个人
 *  Created by Mario on 2017/7/20上午11:14
 */
public class PersonalFragment extends BaseFragment {

    public static final String TAG = PersonalFragment.class.getSimpleName();

    @BindView(R.id.txt_name) TextView name;
    @BindView(R.id.txt_job_name) TextView jobName;
    @BindView(R.id.txt_company) TextView company;

    @BindView(R.id.ll_pending_item) LinearLayout pendingItem;
    @BindView(R.id.ll_confirm_item) LinearLayout confirmItem;
    @BindView(R.id.ll_schedule) LinearLayout schedule;
    @BindView(R.id.ll_task) LinearLayout task;

    @BindView(R.id.ll_setting) LinearLayout setting;
    @BindView(R.id.ll_safe) LinearLayout safe;
    @BindView(R.id.ll_follow) LinearLayout follow;

    @BindView(R.id.btn_logout) Button logout;
    @BindView(R.id.txt_avatar) TextView txtAvatar;

    @Override
    protected int getLayoutId() {
        return R.layout.main_tab_personal_center;
    }

    @Override
    protected void initViews(LayoutInflater inflater,View self, Bundle savedInstanceState) {
    }

    @Override
    protected void initListeners() {
        setting.setOnClickListener(v->startActivity(new Intent(getContext(), PersonalSettingActivity.class)));
        safe.setOnClickListener(v->startActivity(new Intent(getContext(), AccountSafeActivity.class)));
        txtAvatar.setOnClickListener(v->startActivity(new Intent(getContext(), StaffDetailActivity.class)));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {

    }

    public static Fragment newInstance(String content) {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColor("#72B7F2").init();
    }
}
