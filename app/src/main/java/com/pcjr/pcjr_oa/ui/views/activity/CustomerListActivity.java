package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.mvp.BaseSwipeRefreshActivity;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.adapter.ContactAdapter;
import com.pcjr.pcjr_oa.ui.adapter.CustomerAdapter;
import com.pcjr.pcjr_oa.ui.presenter.CustomerListPresenter;
import com.pcjr.pcjr_oa.widget.PopTopDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  客户管理
 *  Created by Mario on 2017/9/20下午1:41
 */
public class CustomerListActivity extends BaseSwipeRefreshActivity implements MvpView<BaseBean<List<Customer>>>{


    @BindView(R.id.btn_down) ImageView btnDown;


    private CustomerListPresenter presenter;

    private PopTopDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_dropdown_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        showBack();
        setTitle("客户管理");

        adapter = new CustomerAdapter();
        adapter.bindToRecyclerView(mRecyclerView);

        presenter = new CustomerListPresenter();
        presenter.attachView(this);
    }


    @Override
    protected void initListeners() {

        btnDown.setOnClickListener(v->{
            backgroundAlpha(0.7f);
            builder.show();
        });

        adapter.setOnItemClickListener((a, view, position) -> {
            Customer customer = (Customer) a.getItem(position);
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
        List<ClassifySection> classifySectionList = new ArrayList<>();
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

        builder = new PopTopDialog.Builder(this, PopTopDialog.TYPE.GRID);
        builder.setGridData(classifySectionList)
                .setPositions(new int[]{1,10})
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result->{
                    showToast(result);
                    backgroundAlpha(1f);
                })
                .create();

       super.initData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        super.onCreateOptionsMenu(menu);
        return true;
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
        super.onRefresh();
        presenter.getBorrowerList(1,query);
    }


    @Override
    public void onFailure(Throwable e) {
        super.error(e);
    }

    @Override
    public void onSuccess(BaseBean<List<Customer>> data) {
        super.success(data);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
