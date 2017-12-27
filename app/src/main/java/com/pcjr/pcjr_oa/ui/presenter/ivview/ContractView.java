package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Contract;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

/**
 *
 *  Created by Mario on 2017/12/19下午2:51
 */
public interface ContractView extends MvpView {

    void onAddContractSuccess(BaseBean<Contract> data);

    void onModifyContractSuccess(BaseBean data);

    void onGetContractDetailSuccess(Contract data);
}
