package com.pcjr.pcjr_oa.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;


import com.gyf.barlibrary.ImmersionBar;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.exception.LoginExpireException;
import com.pcjr.pcjr_oa.ui.views.activity.LoginActivity;
import com.pcjr.pcjr_oa.ui.views.activity.WebViewActivity;
import com.pcjr.pcjr_oa.utils.ToastUtils;

import butterknife.ButterKnife;

/**
 * 基础Activity
 * Created by Mario on 2016/7/22.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());

        ButterKnife.bind(this);
        this.initImmersionBar();
        this.initToolbar(savedInstanceState);
        this.initViews(savedInstanceState);
        this.initData();
        this.initListeners();
    }

    protected abstract int getLayoutId();


    protected abstract void initViews(Bundle savedInstanceState);


    protected abstract void initToolbar(Bundle savedInstanceState);


    protected abstract void initListeners();


    protected abstract void initData();

    protected void initImmersionBar(){
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    @Override
    public void finish() {
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        super.finish();
    }


    @Override
    protected void onDestroy() {
        mImmersionBar.destroy();
        super.onDestroy();
    }


    public void showToast(String msg) {
        this.showToast(msg, Toast.LENGTH_SHORT);
    }


    public void showToast(String msg, int duration) {
        if (msg == null) return;
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(this, msg, duration);
        } else {
            ToastUtils.show(this, msg, ToastUtils.LENGTH_SHORT);
        }
    }


    public void showToast(int resId) {
        this.showToast(resId, Toast.LENGTH_SHORT);
    }


    public void showToast(int resId, int duration) {
        if (duration == Toast.LENGTH_SHORT || duration == Toast.LENGTH_LONG) {
            ToastUtils.show(this, resId, duration);
        } else {
            ToastUtils.show(this, resId, ToastUtils.LENGTH_SHORT);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    /**
     * 跳转到WebView
     * @param title
     * @param url
     */
    protected void startWebViewActivity(String title,String url){
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }


    /**
     * 错误处理
     */
    protected void error(Throwable e){
        if(e instanceof LoginExpireException){
            showToast(getString(R.string.login_expire));
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            showToast(getString(R.string.network_error));
        }
    }
}
