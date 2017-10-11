package com.pcjr.pcjr_oa.model;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.bean.Token;

import java.util.List;

import io.reactivex.Observable;

/**
 *
 *  Created by Mario on 2017/9/28上午10:54
 */
public interface IOAuthModel {
    Observable<BaseBean<List<StaffCompany>>> getStaffCompanyList();
}
