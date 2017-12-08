package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.widget.BottomMenuDialog;
import com.pcjr.pcjr_oa.widget.Dialog;

import butterknife.BindView;

/**
 *  自由退回
 *  Created by Mario on 2017/12/8下午3:17
 */
public class FreeReturnActivity extends BaseToolbarActivity {

    @BindView(R.id.rl_return_node) RelativeLayout rlReturnNode;
    @BindView(R.id.txt_return_node) TextView txtReturnNode;
    @BindView(R.id.txt_content) EditText txtContent;

    private BottomMenuDialog pmd;

    private String[] nodes = new String[]{"提交节点", "1级审批", "2级审批"};

    private boolean n = false;
    @Override
    protected int getLayoutId() {
        return R.layout.free_return;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("自由退回");
    }


    @Override
    protected void initListeners() {

        rlReturnNode.setOnClickListener(v->{
            pmd = new BottomMenuDialog.Builder(FreeReturnActivity.this)
                    .setMenus(nodes)
                    .setOnItemClickListener((dialog,position)-> {
                        txtReturnNode.setText(nodes[position]);
                        n = true;
                    }).create();
            pmd.show();
        });
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
            if(!n){
                Dialog.show("您还未选择退回节点",this);
                return true;
            }
            if(item.getItemId() == R.id.btn_ok){
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
