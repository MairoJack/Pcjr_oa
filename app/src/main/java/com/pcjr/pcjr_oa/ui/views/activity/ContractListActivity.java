package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.bean.Contract;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.mvp.BaseSwipeRefreshActivity;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.adapter.ContractAdapter;
import com.pcjr.pcjr_oa.ui.presenter.ContractListPresenter;
import com.pcjr.pcjr_oa.widget.PopTopDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  合同管理
 *  Created by Mario on 2017/12/25下午3:16
 */
public class ContractListActivity extends BaseSwipeRefreshActivity implements MvpView<BaseBean<List<Contract>>>{


    @BindView(R.id.btn_down) ImageView btnDown;

    private ContractListPresenter presenter;

    private PopTopDialog.Builder builder;

    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_dropdown_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        showBack();
        setTitle("合同库");

        adapter = new ContractAdapter();
        adapter.bindToRecyclerView(mRecyclerView);

        presenter = new ContractListPresenter();
        presenter.attachView(this);
    }


    @Override
    protected void initListeners() {

        btnDown.setOnClickListener(v->{
            backgroundAlpha(0.7f);
            builder.show();
        });

        adapter.setOnItemClickListener((a, view, position) -> {
//            Agreement agreement = (Agreement) a.getItem(position);
//            Intent intent = new Intent(this, AgreementEditActivity.class);
//            intent.putExtra("id", agreement.getId());
//            startActivity(intent);

            startActivity(new Intent(this, ContractEditActivity.class));
        });

        adapter.setOnLoadMoreListener(() -> {
            refresh = false;
            presenter.getContractList(++page, query);
        }, mRecyclerView);

    }

    @Override
    protected void initData() {
        List<ClassifySection> classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "状态");
        classifySectionList.add(cs);
        Classify c = new Classify("全部合同",0);
        c.setSelected(true);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我的合同",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我创建的",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我关注的",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("共享给我的",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("我下属的",0);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);

        builder = new PopTopDialog.Builder(this, PopTopDialog.TYPE.GRID);
        builder.setGridData(classifySectionList)
                .setPositions(new int[]{1})
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result->{
                    showToast(result);
                    backgroundAlpha(1f);
                })
                .create();

       super.initData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_add){
            startActivity(new Intent(this,ContractAddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        presenter.getContractList(1,query);
    }

    @Override
    public void onFailure(Throwable e) {
        super.error(e);
    }

    @Override
    public void onSuccess(BaseBean<List<Contract>> data) {
        super.success(data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Event.MessageEvent event) {
        onRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
