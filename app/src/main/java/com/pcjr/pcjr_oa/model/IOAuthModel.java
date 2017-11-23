package com.pcjr.pcjr_oa.model;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Member;
import com.pcjr.pcjr_oa.bean.PlatformData;
import com.pcjr.pcjr_oa.bean.Product;
import com.pcjr.pcjr_oa.bean.ProductSummary;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.bean.Repayment;
import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.bean.Token;
import com.pcjr.pcjr_oa.bean.Withdraw;

import java.util.List;

import io.reactivex.Observable;

/**
 *
 *  Created by Mario on 2017/9/28上午10:54
 */
public interface IOAuthModel {
    Observable<BaseBean<List<StaffCompany>>> getStaffCompanyList();
    Observable<BaseBean<ProductSummary>> getProductDurationInfo(long startDate,long endDate);
    Observable<BaseBean<List<Product>>> getTodayProductInfo();
    Observable<BaseBean<RepaymentInfo>> getRepaymentDurationInfo(long startDate, long endDate);
    Observable<BaseBean<RepaymentInfo>> getOneDayRepaymentDetail(String oneDay);
    Observable<BaseBean<Recharge>> getRechargeDurationInfo(long startDate, long endDate);
    Observable<BaseBean<Withdraw>> getWithdrawDurationInfo(long startDate, long endDate);
    Observable<BaseBean<Member>> getMemberNumDurationInfo(long startDate, long endDate);
    Observable<BaseBean<Member>> getEffectiveMemberNumDurationInfo(long startDate, long endDate);
    Observable<BaseBean<PlatformData>> getPlatformData();

}
