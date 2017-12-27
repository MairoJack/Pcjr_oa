package com.pcjr.pcjr_oa.ui.views.activity;

import android.os.Bundle;
import android.widget.Button;
import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contract;
import com.pcjr.pcjr_oa.constant.Event;
import com.pcjr.pcjr_oa.ui.presenter.ContractPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ContractView;
import com.pcjr.pcjr_oa.utils.ViewUtil;
import com.pcjr.pcjr_oa.widget.Dialog;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;


/**
 *  新建合同
 *  Created by Mario on 2017/12/25下午4:21
 */
public class ContractAddActivity extends ContractActivity implements ContractView{

    @BindView(R.id.btn_confirm) Button btnConfirm;

    private ContractPresenter presenter;
    @Override
    protected int getLayoutId() {
        return R.layout.contract_add;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        presenter = new ContractPresenter();
        presenter.attachView(this);
    }



    @Override
    protected void initListeners() {
        super.initListeners();
        btnConfirm.setOnClickListener(v->{
            if(ViewUtil.isFastDoubleClick()) return;
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onAddContractSuccess(BaseBean<Contract> data) {
        if (data.isSuccess()) {
            showToast(data.getMsg());
            EventBus.getDefault().post(new Event.MessageEvent());
            finish();
        } else {
            Dialog.show(data.getMsg(), this);
        }
    }

    @Override
    public void onFailure(Throwable e) {
        error(e);
    }

    @Override
    public void onSuccess(Object data) {}
    @Override
    public void onModifyContractSuccess(BaseBean data) {}
    @Override
    public void onGetContractDetailSuccess(Contract data) {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
