package com.pcjr.pcjr_oa.api;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.BusinessApproval;
import com.pcjr.pcjr_oa.bean.Contact;
import com.pcjr.pcjr_oa.bean.Contract;
import com.pcjr.pcjr_oa.bean.Customer;
import com.pcjr.pcjr_oa.bean.CustomerCompany;
import com.pcjr.pcjr_oa.bean.CustomerContact;
import com.pcjr.pcjr_oa.bean.CustomerContactRelation;
import com.pcjr.pcjr_oa.bean.CustomerPersonal;
import com.pcjr.pcjr_oa.bean.Member;
import com.pcjr.pcjr_oa.bean.Person;
import com.pcjr.pcjr_oa.bean.PlatformData;
import com.pcjr.pcjr_oa.bean.Product;
import com.pcjr.pcjr_oa.bean.ProductSummary;
import com.pcjr.pcjr_oa.bean.Recharge;
import com.pcjr.pcjr_oa.bean.RepaymentInfo;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.bean.Withdraw;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("pcjr/borrower/borrowerList")
    Observable<BaseBean<List<Customer>>> getBorrowerList(@Query("page") int page, @Query("query") String query, @Query("id") String id, @Query("type") int type);

    @POST("pcjr/borrower/addPerson")
    Observable<BaseBean<CustomerPersonal>> addPerson(@Body CustomerPersonal customer);

    @POST("pcjr/borrower/modifyPerson")
    Observable<BaseBean> modifyPerson(@Body CustomerPersonal customer);

    @GET("pcjr/borrower/getPersonDetail")
    Observable<BaseBean<CustomerPersonal>> getPersonDetail(@Query("id") String id);

    @POST("pcjr/borrower/addCompany")
    Observable<BaseBean<CustomerCompany>> addCompany(@Body CustomerCompany customer);

    @POST("pcjr/borrower/modifyCompany")
    Observable<BaseBean> modifyCompany(@Body CustomerCompany customer);

    @GET("pcjr/borrower/getCompanyDetail")
    Observable<BaseBean<CustomerCompany>> getCompanyDetail(@Query("id") String id);

    @GET("pcjr/borrower/getManagerList")
    Observable<BaseBean<List<Person>>> getManagerList();

    @GET("pcjr/contact/contactList")
    Observable<BaseBean<List<Contact>>> getContactList(@Query("page") int page, @Query("query") String query, @Query("id") String id, @Query("type") int type);

    @POST("pcjr/contact/addContact")
    Observable<BaseBean<Contact>> addContact(@Body Contact contact);

    @POST("pcjr/contact/modifyContact")
    Observable<BaseBean> modifyContact(@Body Contact contact);

    @GET("pcjr/contact/contactDetail")
    Observable<BaseBean<Contact>> getContactDetail(@Query("id") String id);

    @POST("pcjr/contact/modifyRelationship")
    Observable<BaseBean> modifyRelationship(@Body CustomerContact customerContact);

    @GET("pcjr/contact/getRelationshipList")
    Observable<BaseBean<List<CustomerContactRelation>>> getRelationshipList(@Query("id") String id, @Query("type") int type);

    @GET("pcjr/contract/contractList")
    Observable<BaseBean<List<Contract>>> getContractList(@Query("page") int page, @Query("query") String query);

    @POST("pcjr/contract/addContract")
    Observable<BaseBean<Contract>> addContract(@Body Contract contract);

    @POST("pcjr/contract/modifyContract")
    Observable<BaseBean> modifyContract(@Body Contract contract);

    @GET("pcjr/contract/contractDetail")
    Observable<BaseBean<Contract>> getContractDetail(@Query("id") String id);

    @GET("pcjr/approve/businessApproveList")
    Observable<BaseBean<List<BusinessApproval>>> getBusinessApproveList(@Query("page") int page, @Query("query") String query);

    @POST("pcjr/approve/addBusinessApprove")
    Observable<BaseBean<BusinessApproval>> addBusinessApprove(@Body BusinessApproval businessApproval);

    @POST("pcjr/approve/modifyBusinessApprove")
    Observable<BaseBean> modifyBusinessApprove(@Body BusinessApproval businessApproval);

    @GET("pcjr/approve/deleteBusinessApprove")
    Observable<BaseBean> deleteBusinessApprove(@Query("id") String id);

}
