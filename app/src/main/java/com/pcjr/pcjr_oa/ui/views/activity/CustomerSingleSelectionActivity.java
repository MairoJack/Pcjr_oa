package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.core.mvp.BaseSwipeRefreshActivity;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.adapter.CustomerSelectAdapter;
import com.pcjr.pcjr_oa.ui.presenter.CustomerListPresenter;

import java.util.List;

/**
 * 客户单选
 * Created by Mario on 2017/12/14下午4:28
 */
public class CustomerSingleSelectionActivity extends BaseSwipeRefreshActivity implements MvpView<BaseBean<List<Customer>>> {


    private int lastPosition;
    private Customer selectedItem;
    private CustomerListPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        showBack();
        setTitle("客户选择");

        adapter = new CustomerSelectAdapter();
        adapter.bindToRecyclerView(mRecyclerView);

        presenter = new CustomerListPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void initListeners() {
        adapter.setOnItemClickListener((a, view, position) -> {
            if (lastPosition != -1) {
                Customer lastItem = (Customer) adapter.getItem(lastPosition);
                lastItem.setIsSelected(false);
                adapter.notifyItemChanged(lastPosition, lastItem);
            }
            selectedItem = (Customer) adapter.getItem(position);
            selectedItem.setIsSelected(true);
            adapter.notifyItemChanged(position, selectedItem);
            lastPosition = position;
        });

        adapter.setOnLoadMoreListener(() -> {
            refresh = false;
            presenter.getBorrowerList(++page, query);
        }, mRecyclerView);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        presenter.getBorrowerList(page, query);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_ok, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btn_ok) {
            Intent intent = new Intent();
            intent.putExtra("result", selectedItem);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFailure(Throwable e) {
        super.error(e);
    }

    @Override
    public void onSuccess(BaseBean<List<Customer>> data) {
        lastPosition = -1;
        super.success(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
