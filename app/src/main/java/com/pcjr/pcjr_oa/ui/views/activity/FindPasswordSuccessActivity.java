package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;

/**
 *  找回密码 成功
 *  Created by Mario on 2017/7/26下午3:59
 */
public class FindPasswordSuccessActivity extends BaseToolbarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.find_password_success;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("重置密码");
        mToolbar.setNavigationIcon(R.mipmap.icon_left_gray);
    }

    @Override
    protected void initListeners() {

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
            startActivity(new Intent(FindPasswordSuccessActivity.this, LoginActivity.class));
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
