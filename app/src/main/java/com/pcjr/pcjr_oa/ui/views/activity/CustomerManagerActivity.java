package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.core.BaseDropDownActivity;
import com.pcjr.pcjr_oa.ui.adapter.CustomerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  客户管理
 *  Created by Mario on 2017/9/20下午1:41
 */
public class CustomerManagerActivity extends BaseDropDownActivity implements SwipeRefreshLayout.OnRefreshListener,SearchView.OnQueryTextListener{


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    
    private SearchView searchView;
    
    private CustomerAdapter adapter;
    private List<Customer> list;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_dropdown_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showBack();
        setTitle("客户管理");

        initGridPop();

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomerAdapter();
        mRecyclerView.setAdapter(adapter);
        
    }


    @Override
    protected void initListeners() {
        mPopTop.setOnDismissListener(() -> {
            showToast(closeGridPop());
        });

        adapter.setOnItemClickListener((adapter,view,position)-> {
            startActivity(new Intent(this,CustomerInfoActivity.class));
        });

    }

    @Override
    protected void initData() {
        classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "客户分类");
        classifySectionList.add(cs);
        Classify c = new Classify("全部客户",0);
        c.setSelected(true);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我负责的客户",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我参与的客户",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我关注的客户",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("下属的客户",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("分享给我的客户",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我创建的客户",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("参与评论的客户",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);

        cs = new ClassifySection(true, "客户排序");
        classifySectionList.add(cs);
        c = new Classify("按创建时间",2);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按客户名称",2);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按更新时间",2);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);


        positions = new int[]{1,10,18};
        initGridPopData();

        list = new ArrayList<>();
        Customer p = new Customer("客户名称客户名称客户名称客户名","杜拉拉",1501055624,0);
        list.add(p);
        p = new Customer("名字显示客户表申请人","杜拉拉",1501055624,1);
        list.add(p);
        p = new Customer("客户名称客户名称客户名称客户名","杜拉拉",1501055624,0);
        list.add(p);
        p= new Customer("名字显示客户表申请人","杜拉拉",1501055624,0);
        list.add(p);
        p= new Customer("客户名称客户名称客户名称客户名","杜拉拉",1501055624,0);
        list.add(p);
        p= new Customer("名字显示客户表申请人","杜拉拉",1501055624,0);
        list.add(p);
        p= new Customer("客户名称客户名称客户名称客户名","杜拉拉",1501055624,0);
        list.add(p);
        p= new Customer("名字显示客户表申请人","杜拉拉",1501055624,1);
        list.add(p);
        p= new Customer("客户名称客户名称客户名称客户名","杜拉拉",1501055624,0);
        list.add(p);
        p= new Customer("名字显示客户表申请人","杜拉拉",1501055624,1);
        list.add(p);
        adapter.setNewData(list);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer,menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索客户");
        searchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
            return true;
        }
        if (item.getItemId() == R.id.btn_search) {
            return true;
        }
        Intent intent = new Intent(this,CustomerAddActivity.class);
        intent.putExtra("type",item.getOrder());
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }


}
