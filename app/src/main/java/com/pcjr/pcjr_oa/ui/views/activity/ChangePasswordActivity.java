package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.afollestad.materialdialogs.MaterialDialog;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;

/**
 *  更换密码
 *  Created by Mario on 2017/9/1上午11:38
 */
public class ChangePasswordActivity extends BaseToolbarActivity {
    @BindView(R.id.btn_save) Button btnSave;
    @BindView(R.id.txt_old_password) EditText txtOldPassword;
    @BindView(R.id.txt_password) EditText txtPassword;
    @BindView(R.id.txt_confirm_password) EditText txtConfirmPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.change_password;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("更换密码");
    }

    @Override
    protected void initListeners() {
        btnSave.setOnClickListener(v -> {
            if (ViewUtil.isFastDoubleClick()) return;
            change();
        });
    }

    @Override
    protected void initData() {


    }

    public void change() {
        String oldPassword = txtOldPassword.getText().toString().trim();
        String newPassword = txtPassword.getText().toString().trim();
        String confirm_password = txtConfirmPassword.getText().toString().trim();
        if (oldPassword.equals("")) {
            Dialog.show("原密码不能为空", this);
            return;
        }
        if (newPassword.equals("")) {
            Dialog.show("新密码不能为空", this);
            return;
        }
        if (confirm_password.equals("")) {
            Dialog.show("确认密码不能为空", this);
            return;
        }
        if (!newPassword.equals(confirm_password)) {
            Dialog.show("两次密码不相同", this);
            return;
        }
        Dialog.progress(this);
    }

}
