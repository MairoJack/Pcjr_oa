package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Token;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseAppCompatActivity;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.presenter.LoginPresenter;
import com.pcjr.pcjr_oa.utils.MD5Util;
import com.pcjr.pcjr_oa.utils.SPUtils;
import com.pcjr.pcjr_oa.utils.ValidatorUtils;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;


public class LoginActivity extends BaseAppCompatActivity implements MvpView<BaseBean<Token>> {

    @BindView(R.id.btn_eye) CheckBox btnEye;
    @BindView(R.id.btn_forget) Button btnForget;
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.txt_username) TextView txtUsername;
    @BindView(R.id.txt_password) TextView txtPassword;

    private long exitTime = 0;
    private LoginPresenter presenter;
    private MaterialDialog dialog;
    @Override
    protected int getLayoutId() {
        return R.layout.login;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mImmersionBar.statusBarColor("#9CCAFC").init();
        dialog = Dialog.progress(this,"正在登陆...");
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {

    }

    @Override
    protected void initListeners() {
        btnEye.setOnCheckedChangeListener((buttonView,isChecked)->{
            if(isChecked) {
                txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btnEye.setBackgroundResource(R.mipmap.icon_open_eye);
            }
            else {
                txtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                btnEye.setBackgroundResource(R.mipmap.icon_close_eye);
            }

        });

        btnLogin.setOnClickListener(v->{
            String username = txtUsername.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
//            if(!ValidatorUtils.isMobile(username)){
//                Dialog.show(getString(R.string.mobile_error),this);
//                return;
//            }
            if(!ValidatorUtils.isPassword(password)){
                Dialog.show(getString(R.string.password_error),this);
                return;
            }
            dialog.show();
            String key = MD5Util.encrypt(username+password+System.currentTimeMillis());
            SPUtils.put(this, Constant.KEY,key);
            presenter.login(username,password,key);

        });

        btnForget.setOnClickListener(v->startActivity(new Intent(LoginActivity.this,FindPasswordStep1Activity.class)));
    }

    @Override
    protected void initData() {
        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onFailure(Throwable e) {
        if(dialog.isShowing()) dialog.dismiss();
        showToast(e.getMessage());
    }

    @Override
    public void onSuccess(BaseBean<Token> data) {
        if(dialog.isShowing()) dialog.dismiss();
        Token result = data.getData();
        if(result.isSuccess()){
            SPUtils.put(this,Constant.ACCESS_TOKEN,result.getAccessToken());
            SPUtils.put(this,Constant.REFRESH_TOKEN,result.getRefreshToken());
            startActivity(new Intent(this,MainActivity.class));
        }else{
            Dialog.show(result.getMsg(),this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
