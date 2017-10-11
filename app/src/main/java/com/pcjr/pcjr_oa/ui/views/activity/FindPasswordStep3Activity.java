package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.utils.ValidatorUtils;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;

/**
 *  找回密码 第三步
 *  Created by Mario on 2017/7/26下午3:59
 */
public class FindPasswordStep3Activity extends BaseToolbarActivity {
    @BindView(R.id.btn_eye) CheckBox btnEye;
    @BindView(R.id.txt_password) EditText txtPassword;
    @BindView(R.id.btn_confirm) Button btnConfirm;


    @Override
    protected int getLayoutId() {
        return R.layout.find_password_step3;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("重置密码");
        mToolbar.setNavigationIcon(R.mipmap.icon_left_gray);
    }

    @Override
    protected void initListeners() {
        btnEye.setOnCheckedChangeListener((buttonView,isChecked)->{
            if(isChecked) {
                txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnEye.setBackgroundResource(R.mipmap.icon_open_eye_gray);
            }
            else {
                txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnEye.setBackgroundResource(R.mipmap.icon_close_eye_gray);
            }

        });
        btnConfirm.setOnClickListener(v->{
            String password = txtPassword.getText().toString().trim();
            if(!ValidatorUtils.isPassword(password)){
                Dialog.show(getString(R.string.password_error),this);
                return;
            }
            startActivity(new Intent(FindPasswordStep3Activity.this,FindPasswordSuccessActivity.class));
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
            startActivity(new Intent(FindPasswordStep3Activity.this, LoginActivity.class));
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
