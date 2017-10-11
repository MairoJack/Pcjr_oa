package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import java.util.List;

/**
 *
 *  Created by Mario on 2017/9/29下午2:46
 */
public interface WorkView extends MvpView {

    void onStaffCompanyListSuccess(BaseBean<List<StaffCompany>> data);

}
