package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.core.BaseDropDownActivity;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.adapter.CustomerAdapter;
import com.pcjr.pcjr_oa.ui.presenter.CustomerListPresenter;
import com.pcjr.pcjr_oa.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  客户管理
 *  Created by Mario on 2017/9/20下午1:41
 */
public class CustomerListActivity extends BaseDropDownActivity implements SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener,SearchView.OnCloseListener,MvpView<BaseBean<List<Customer>>>{


    @BindView(R.id.swipeLayout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    
    private SearchView searchView;
    
    private CustomerAdapter adapter;
    private CustomerListPresenter presenter;
    private View empty;
    private boolean refresh = true;
    private int page = 1;
    private String query = "";
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        empty = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);

        adapter = new CustomerAdapter();
        adapter.bindToRecyclerView(mRecyclerView);



        presenter = new CustomerListPresenter();
        presenter.attachView(this);
    }


    @Override
    protected void initListeners() {
        mPopTop.setOnDismissListener(() -> {
            showToast(closeGridPop());
        });

        adapter.setOnItemClickListener((a, view, position) -> {
            Customer customer = adapter.getItem(position);
            Intent intent;
            if(customer.getCustomerType() == 0){
                intent = new Intent(this, CustomerPersonalInfoActivity.class);
            } else {
                intent = new Intent(this, CustomerCompanyInfoActivity.class);
            }
            intent.putExtra("id", customer.getId());
            startActivity(intent);
        });

        adapter.setOnLoadMoreListener(() -> {
            refresh = false;
            presenter.getBorrowerList(++page, query);
        }, mRecyclerView);

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

        positions = new int[]{1,10};
        initGridPopData();

        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索客户");
        searchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.query = query;
        mSwipeRefreshLayout.post(()->mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onClose() {
        if(StringUtils.validate(query)) {
            query = "";
            mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
            onRefresh();
        }
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
        Intent intent;
        if(item.getOrder() == 0){
            intent = new Intent(this,CustomerPersonalAddActivity.class);
        } else {
            intent = new Intent(this,CustomerCompanyAddActivity.class);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onRefresh() {
        adapter.setEnableLoadMore(false);
        page = 1;
        refresh = true;
        presenter.getBorrowerList(1,query);
    }


    @Override
    public void onFailure(Throwable e) {
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        if(!refresh) adapter.loadMoreFail();
        error(e);
    }

    @Override
    public void onSuccess(BaseBean<List<Customer>> data) {
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        List<Customer> list = data.getData();
        if(refresh) {
            if (list.size() == 0) {
                adapter.setNewData(null);
                adapter.setEmptyView(empty);
            } else {
                adapter.setNewData(list);
                adapter.disableLoadMoreIfNotFullPage();
            }
        } else {
            adapter.addData(list);
            if (page >= data.getPager().getMaxPage()){
                adapter.loadMoreEnd();
            } else {
                adapter.loadMoreComplete();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }



}
