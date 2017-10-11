package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;

/**
 *  找回密码 第二步
 *  Created by Mario on 2017/7/26下午3:59
 */
public class FindPasswordStep2Activity extends BaseToolbarActivity {
    @BindView(R.id.txt_verify) EditText txtVerify;
    @BindView(R.id.btn_verify) TextView btnVerify;
    @BindView(R.id.btn_next) Button btnNext;

    private TimeCount time;
    @Override
    protected int getLayoutId() {
        return R.layout.find_password_step2;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("找回密码");
        mToolbar.setNavigationIcon(R.mipmap.icon_left_gray);
        time = new TimeCount(60000, 1000);
    }

    @Override
    protected void initListeners() {
        btnNext.setOnClickListener(v->{
            String verify = txtVerify.getText().toString().trim();
            if(verify.equals("")){
                Dialog.show(getString(R.string.verify_error),this);
                return;
            }
            startActivity(new Intent(FindPasswordStep2Activity.this,FindPasswordStep3Activity.class));
        });

        btnVerify.setOnClickListener(v->{
            btnVerify.setClickable(false);
            time.start();
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
            startActivity(new Intent(FindPasswordStep2Activity.this, LoginActivity.class));
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

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            btnVerify.setText("发送验证码");
            btnVerify.setClickable(true);
            btnVerify.setTextColor(Color.parseColor("#f54246"));
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnVerify.setTextColor(Color.parseColor("#a6a6a6"));
            btnVerify.setText("重新发送（"+millisUntilFinished / 1000 + "）");
        }
    }
}
