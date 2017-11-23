package com.pcjr.pcjr_oa.api;


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

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mario on 2017/9/29上午10:56
 */
public interface OAuthService {
    @GET("mapi/company/select")
    Observable<BaseBean<List<StaffCompany>>> getStaffCompanyList();

    @GET("data/api/productDurationInfo")
    Observable<BaseBean<ProductSummary>> getProductDurationInfo(@Query("start_date") long startDate, @Query("end_date") long endDate);

    @GET("data/api/todayProductInfo")
    Observable<BaseBean<List<Product>>> getTodayProductInfo();

    @GET("data/api/repaymentDurationInfo")
    Observable<BaseBean<RepaymentInfo>> getRepaymentDurationInfo(@Query("start_date") long startDate, @Query("end_date") long endDate);

    @GET("data/api/oneDayRepaymentDetail")
    Observable<BaseBean<RepaymentInfo>> getOneDayRepaymentDetail(@Query("one_day") String oneDay);

    @GET("data/api/rechargeDurationInfo")
    Observable<BaseBean<Recharge>> getRechargeDurationInfo(@Query("start_date") long startDate, @Query("end_date") long endDate);

    @GET("data/api/withdrawDurationInfo")
    Observable<BaseBean<Withdraw>> getWithdrawDurationInfo(@Query("start_date") long startDate, @Query("end_date") long endDate);

    @GET("data/api/memberNumDurationInfo")
    Observable<BaseBean<Member>> getMemberNumDurationInfo(@Query("start_date") long startDate, @Query("end_date") long endDate);

    @GET("data/api/effectiveMemberNumDurationInfo")
    Observable<BaseBean<Member>> getEffectiveMemberNumDurationInfo(@Query("start_date") long startDate, @Query("end_date") long endDate);

    @GET("data/api/platformData")
    Observable<BaseBean<PlatformData>> getPlatformData();
}
