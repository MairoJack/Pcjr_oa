package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.PlatformNotice;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.utils.DateUtils;
import com.pcjr.pcjr_oa.utils.ValidatorUtils;
import com.pcjr.pcjr_oa.widget.Dialog;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;

/**
 *  找回密码 第一步
 *  Created by Mario on 2017/7/26下午3:59
 */
public class FindPasswordStep1Activity extends BaseToolbarActivity {
    @BindView(R.id.txt_mobile) EditText txtMobile;
    @BindView(R.id.btn_cancel) ImageView btnCancel;
    @BindView(R.id.btn_next) Button btnNext;


    @Override
    protected int getLayoutId() {
        return R.layout.find_password_step1;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("找回密码");
        mToolbar.setNavigationIcon(R.mipmap.icon_left_gray);
    }

    @Override
    protected void initListeners() {
        txtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    btnCancel.setVisibility(View.VISIBLE);
                }else{
                    btnCancel.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnCancel.setOnClickListener(v->txtMobile.setText(""));
        btnNext.setOnClickListener(v->{
            String mobile = txtMobile.getText().toString().trim();
            if(!ValidatorUtils.isMobile(mobile)){
                Dialog.show(getString(R.string.mobile_error),this);
                return;
            }
            startActivity(new Intent(FindPasswordStep1Activity.this,FindPasswordStep2Activity.class));
        });
    }

    @Override
    protected void initData() {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }else if(item.getItemId() == R.id.btn_login) {
            finish();
            startActivity(new Intent(FindPasswordStep1Activity.this, LoginActivity.class));
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;

    }
}
