package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Product;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.FinanceInfoRechargeAdapter;
import com.pcjr.pcjr_oa.ui.adapter.ProductDataAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  产品数据
 *  Created by Mario on 2017/10/10上午10:26
 */
public class ProductDataActivity extends BaseToolbarActivity {

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private List<Product> list;
    private ProductDataAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.product_data;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("产品数据");
        showBack();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this,1,20, Color.parseColor("#f7f8f9")));

        adapter = new ProductDataAdapter();
        mRecyclerView.setAdapter(adapter);

    }


    @Override
    protected void initListeners() {

    }


    @Override
    protected void initData() {
        list = new ArrayList<>();

        Product product = new Product();
        product.setProductName("大城小爱NO.972-2018");
        product.setMonth("365天");
        product.setAmount("100万元");
        product.setRate("7%");
        product.setDays("4天");
        product.setTime("1分18秒");
        product.setNumber(24);
        product.setDate(1483203661);
        product.setStatus(0);
        list.add(product);

        product = new Product();
        product.setProductName("国泰民安NO.972-2018");
        product.setMonth("12个月");
        product.setAmount("50万元");
        product.setRate("6.5%");
        product.setDays("3天");
        product.setTime("18秒");
        product.setNumber(12);
        product.setDate(1483203661);
        product.setStatus(1);
        list.add(product);

        adapter.setNewData(list);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_date,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_date){
            startActivity(new Intent(this,CountDateSettingActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }

}
