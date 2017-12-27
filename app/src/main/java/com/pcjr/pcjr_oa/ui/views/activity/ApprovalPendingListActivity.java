package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.bean.Classify;
import com.pcjr.pcjr_oa.bean.ClassifySection;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.core.mvp.BaseSwipeRefreshActivity;
import com.pcjr.pcjr_oa.core.mvp.MvpView;
import com.pcjr.pcjr_oa.ui.adapter.ApprovalAdapter;
import com.pcjr.pcjr_oa.ui.presenter.ApprovalBusinessListPresenter;
import com.pcjr.pcjr_oa.widget.PopTopDialog;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  待办审批/下属待办审批
 *  Created by Mario on 2017/9/13上午9:35
 */
public class ApprovalPendingListActivity extends BaseSwipeRefreshActivity implements MvpView<BaseBean<List<BusinessApproval>>>{

    @BindView(R.id.btn_down) ImageView btnDown;
    private PopTopDialog.Builder builder;

    private ApprovalBusinessListPresenter presenter;
    @Override
    protected int getLayoutId() {
        return R.layout.simple_swipe_dropdown_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        showBack();
        setTitle("待办审批");

        adapter = new ApprovalAdapter();
        adapter.bindToRecyclerView(mRecyclerView);

        presenter = new ApprovalBusinessListPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void initListeners() {
        btnDown.setOnClickListener(v->{
            builder.show();
            backgroundAlpha(0.7f);
        });

        adapter.setOnItemClickListener((adapter,view,position)-> {
            startActivity(new Intent(this,ApprovalPendingDetailActivity.class));
        });

    }

    @Override
    protected void initData() {
        List<ClassifySection> classifySectionList = new ArrayList<>();
        ClassifySection cs = new ClassifySection(true, "类型");
        classifySectionList.add(cs);
        Classify c = new Classify("全部审批",0);
        c.setSelected(true);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("业务审批",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("上线审批",0);
        cs= new ClassifySection(c);
        classifySectionList.add(cs);

        cs = new ClassifySection(true, "审批排序");
        classifySectionList.add(cs);
        c = new Classify("按到期时间",1);
        c.setSelected(true);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按反馈时间",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按申请时间",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);
        c = new Classify("按申请人",1);
        cs = new ClassifySection(c);
        classifySectionList.add(cs);

        builder = new PopTopDialog.Builder(this, PopTopDialog.TYPE.GRID)
                .setGridData(classifySectionList)
                .setPositions(new int[]{1,5})
                .setDropDownBtn(btnDown)
                .setOnCloseListener(result -> {
                    showToast(result);
                    backgroundAlpha(1f);
                }).create();

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        presenter.getBusinessApproveList(page,query);
    }

    @Override
    public void onFailure(Throwable e) {
        super.error(e);
    }

    @Override
    public void onSuccess(BaseBean<List<BusinessApproval>> data) {
        super.success(data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Event.MessageEvent event) {
        onRefresh();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

}
