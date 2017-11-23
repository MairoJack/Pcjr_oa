package com.pcjr.pcjr_oa.model.impl;


import com.pcjr.pcjr_oa.api.RetrofitManager;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Member;
import com.pcjr.pcjr_oa.bean.PlatformData;
import com.pcjr.pcjr_oa.bean.Product;
import com.pcjr.pcjr_oa.bean.ProductSummary;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.bean.Repayment;
import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.bean.Withdraw;
import com.pcjr.pcjr_oa.model.IOAuthModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mario on 2016/7/25.
 */
public class OAuthModel implements IOAuthModel {

    private static final OAuthModel mInstance = new OAuthModel();


    public static OAuthModel getInstance() {
        return mInstance;
    }


    private OAuthModel() {
    }


    @Override
    public Observable<BaseBean<List<StaffCompany>>> getStaffCompanyList() {
        return RetrofitManager.getInstance().getAuthService().getStaffCompanyList();
    }

    @Override
    public Observable<BaseBean<ProductSummary>> getProductDurationInfo(long startDate, long endDate) {
        return RetrofitManager.getInstance().getAuthService().getProductDurationInfo(startDate, endDate);
    }

    @Override
    public Observable<BaseBean<List<Product>>> getTodayProductInfo() {
        return RetrofitManager.getInstance().getAuthService().getTodayProductInfo();
    }

    @Override
    public Observable<BaseBean<RepaymentInfo>> getRepaymentDurationInfo(long startDate, long endDate) {
        return RetrofitManager.getInstance().getAuthService().getRepaymentDurationInfo(startDate, endDate);
    }

    @Override
    public Observable<BaseBean<RepaymentInfo>> getOneDayRepaymentDetail(String oneDay) {
        return RetrofitManager.getInstance().getAuthService().getOneDayRepaymentDetail(oneDay);
    }

    @Override
    public Observable<BaseBean<Recharge>> getRechargeDurationInfo(long startDate, long endDate) {
        return RetrofitManager.getInstance().getAuthService().getRechargeDurationInfo(startDate, endDate);
    }

    @Override
    public Observable<BaseBean<Withdraw>> getWithdrawDurationInfo(long startDate, long endDate) {
        return RetrofitManager.getInstance().getAuthService().getWithdrawDurationInfo(startDate, endDate);
    }

    @Override
    public Observable<BaseBean<Member>> getMemberNumDurationInfo(long startDate, long endDate) {
        return RetrofitManager.getInstance().getAuthService().getMemberNumDurationInfo(startDate, endDate);
    }

    @Override
    public Observable<BaseBean<Member>> getEffectiveMemberNumDurationInfo(long startDate, long endDate) {
        return RetrofitManager.getInstance().getAuthService().getEffectiveMemberNumDurationInfo(startDate, endDate);
    }

    @Override
    public Observable<BaseBean<PlatformData>> getPlatformData() {
        return RetrofitManager.getInstance().getAuthService().getPlatformData();
    }


}
