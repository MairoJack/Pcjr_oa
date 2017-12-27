package com.pcjr.pcjr_oa.core.mvp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * 基础下拉刷新 / 搜索
 * Created by Mario on 2017/9/20下午1:41
 */
public abstract class BaseSwipeRefreshActivity extends BaseToolbarActivity implements SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {


    @BindView(R.id.swipeLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter adapter;
    protected SearchView searchView;
    protected View empty;
    protected int page = 1;
    protected boolean refresh = true;
    protected String query = "";

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        empty = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
    }

    @Override
    protected void initData() {

        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.btn_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("搜索");
        searchView.setIconifiedByDefault(true);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.query = query;
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
        onRefresh();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onClose() {
        if (StringUtils.validate(query)) {
            query = "";
            mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
            onRefresh();
        }
        return false;
    }

    @Override
    public void onRefresh() {
        page = 1;
        refresh = true;
        adapter.setEnableLoadMore(false);
    }

    @Override
    public void error(Throwable e) {
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        if (!refresh) adapter.loadMoreFail();
        super.error(e);
    }

    @SuppressWarnings("unchecked")
    public <T> void success(BaseBean<List<T>> data) {
        if (mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
        if (refresh) {
            if (data.getData().size() == 0) {
                adapter.setNewData(null);
                adapter.setEmptyView(empty);
            } else {
                adapter.setNewData(data.getData());
                adapter.disableLoadMoreIfNotFullPage();
            }
        } else {
            adapter.addData(data.getData());
            if (page >= data.getPager().getMaxPage()) {
                adapter.loadMoreEnd();
            } else {
                adapter.loadMoreComplete();
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Event.MessageEvent event) {
        onRefresh();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
