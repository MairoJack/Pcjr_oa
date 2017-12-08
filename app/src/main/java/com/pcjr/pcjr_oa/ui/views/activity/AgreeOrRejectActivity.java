package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;

import butterknife.BindView;

/**
 *  同意或驳回
 *  Created by Mario on 2017/12/8下午3:11
 */
public class AgreeOrRejectActivity extends BaseToolbarActivity {

    @BindView(R.id.txt_content) EditText content;

    @Override
    protected int getLayoutId() {
        return R.layout.agree_or_reject;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ok,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_ok){
            showToast("ok");
        }
        return super.onOptionsItemSelected(item);
    }
}
