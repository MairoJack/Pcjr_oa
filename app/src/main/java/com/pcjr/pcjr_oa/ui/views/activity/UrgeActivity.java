package com.pcjr.pcjr_oa.ui.views.activity;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.ParticipantAdapter;
import com.pcjr.pcjr_oa.widget.Dialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  催办
 *  Created by Mario on 2017/8/25上午10:23
 */
public class UrgeActivity extends BaseToolbarActivity{


    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @BindView(R.id.check_manager) CheckBox checkManager;
    @BindView(R.id.check_participants) CheckBox checkParticipants;
    @BindView(R.id.check_create) CheckBox checkCreate;

    private List<Person> list;
    private ParticipantAdapter adapter;
    private boolean m = false,p = false ,c = false ,l = false;
    private Person selectedItem;
    @Override
    protected int getLayoutId() {
        return R.layout.urge;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("催办");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((adapter,view,position)-> {
            Person p = list.get(position);
            if(p.isSelected()){
                p.setSelected(false);
            }else{
                p.setSelected(true);
            }
            adapter.notifyItemChanged(position,p);
        });

        Drawable drawable = ContextCompat.getDrawable(this,R.mipmap.icon_selected);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(), drawable.getMinimumHeight());

        checkManager.setOnCheckedChangeListener((cb,b)-> {
           if(b){
               checkManager.setCompoundDrawables(null,null,drawable,null);
           }else{
               checkManager.setCompoundDrawables(null,null,null,null);
           }
           m = !m;
        });

        checkParticipants.setOnCheckedChangeListener((cb,b)-> {
            if(b){
                checkParticipants.setCompoundDrawables(null,null,drawable,null);
            }else{
                checkParticipants.setCompoundDrawables(null,null,null,null);
            }
            p = !p;
        });

        checkCreate.setOnCheckedChangeListener((cb,b)-> {
            if(b){
                checkCreate.setCompoundDrawables(null,null,drawable,null);
            }else{
                checkCreate.setCompoundDrawables(null,null,null,null);
            }
            c = !c;
        });
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();

        Person p = new Person();
        p.setName("杜拉拉");
        p.setDepartment("部门111111");
        p.setJob("职位11");
        list.add(p);

        p = new Person();
        p.setName("萧拉拉");
        p.setDepartment("部门2222111");
        p.setJob("职位22");
        list.add(p);
        adapter = new ParticipantAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ok,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_ok){
            if(!(m||c||p)){
                Dialog.show("至少选择一项",this);
                return true;
            }
            if(item.getItemId() == R.id.btn_ok){
                Dialog.show("催办成功",this);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
