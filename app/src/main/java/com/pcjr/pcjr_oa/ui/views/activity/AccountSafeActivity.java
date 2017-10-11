package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.PlatformNotice;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.ViewUtil;

import butterknife.BindView;

/**
 *  账户与安全
 *  Created by Mario on 2017/9/1上午11:38
 */
public class AccountSafeActivity extends BaseToolbarActivity {
    @BindView(R.id.rl_change_password) RelativeLayout rlChangePassword;
    @BindView(R.id.rl_gesture) RelativeLayout rlGesture;
    @BindView(R.id.switch_gesture) SwitchCompat switchGesture;

    @Override
    protected int getLayoutId() {
        return R.layout.acount_safe;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("账户与安全");
    }

    @Override
    protected void initListeners() {
        rlChangePassword.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            startActivity(new Intent(this,ChangePasswordActivity.class));
        });

        switchGesture.setOnCheckedChangeListener((compoundButton,b)-> {
            if(b){
                rlGesture.setVisibility(View.VISIBLE);
            }else{
                rlGesture.setVisibility(View.GONE);
            }
        });

        rlGesture.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick())return;
            startActivity(new Intent(this,GestureEditActivity.class));
        });
    }

    @Override
    protected void initData() {

    }

}
