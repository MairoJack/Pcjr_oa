package com.pcjr.pcjr_oa.model.impl;


import com.pcjr.pcjr_oa.api.RetrofitManager;
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

    @Override
    public Observable<BaseBean<List<Customer>>> getBorrowerList(int page, String query, String id, int type) {
        return RetrofitManager.getInstance().getAuthService().getBorrowerList(page, query, id, type);
    }

    @Override
    public Observable<BaseBean<CustomerPersonal>> addPerson(CustomerPersonal customer) {
        return RetrofitManager.getInstance().getAuthService().addPerson(customer);
    }

    @Override
    public Observable<BaseBean> modifyPerson(CustomerPersonal customer) {
        return RetrofitManager.getInstance().getAuthService().modifyPerson(customer);
    }

    @Override
    public Observable<BaseBean<CustomerPersonal>> getPersonDetail(String id) {
        return RetrofitManager.getInstance().getAuthService().getPersonDetail(id);
    }

    @Override
    public Observable<BaseBean<CustomerCompany>> addCompany(CustomerCompany customer) {
        return RetrofitManager.getInstance().getAuthService().addCompany(customer);
    }

    @Override
    public Observable<BaseBean> modifyCompany(CustomerCompany customer) {
        return RetrofitManager.getInstance().getAuthService().modifyCompany(customer);
    }

    @Override
    public Observable<BaseBean<CustomerCompany>> getCompanyDetail(String id) {
        return RetrofitManager.getInstance().getAuthService().getCompanyDetail(id);
    }

    @Override
    public Observable<BaseBean<List<Person>>> getManagerList() {
        return RetrofitManager.getInstance().getAuthService().getManagerList();
    }

    @Override
    public Observable<BaseBean<List<Contact>>> getContactList(int page, String query, String id, int type) {
        return RetrofitManager.getInstance().getAuthService().getContactList(page, query, id, type);
    }

    @Override
    public Observable<BaseBean<Contact>> addContact(Contact contact) {
        return RetrofitManager.getInstance().getAuthService().addContact(contact);
    }

    @Override
    public Observable<BaseBean> modifyContact(Contact contact) {
        return RetrofitManager.getInstance().getAuthService().modifyContact(contact);
    }

    @Override
    public Observable<BaseBean<Contact>> getContactDetail(String id) {
        return RetrofitManager.getInstance().getAuthService().getContactDetail(id);
    }

    @Override
    public Observable<BaseBean> modifyRelationship(CustomerContact customerContact) {
        return RetrofitManager.getInstance().getAuthService().modifyRelationship(customerContact);
    }

    @Override
    public Observable<BaseBean<List<CustomerContactRelation>>> getRelationshipList(String id, int type) {
        return RetrofitManager.getInstance().getAuthService().getRelationshipList(id, type);
    }

    @Override
    public Observable<BaseBean<List<Contract>>> getContractList(int page, String query) {
        return RetrofitManager.getInstance().getAuthService().getContractList(page,query);
    }

    @Override
    public Observable<BaseBean<Contract>> addContract(Contract contract) {
        return RetrofitManager.getInstance().getAuthService().addContract(contract);
    }

    @Override
    public Observable<BaseBean> modifyContract(Contract contract) {
        return RetrofitManager.getInstance().getAuthService().modifyContract(contract);
    }

    @Override
    public Observable<BaseBean<Contract>> getContractDetail(String id) {
        return RetrofitManager.getInstance().getAuthService().getContractDetail(id);
    }

    @Override
    public Observable<BaseBean<List<BusinessApproval>>> getBusinessApproveList(int page, String query) {
        return RetrofitManager.getInstance().getAuthService().getBusinessApproveList(page,query);
    }

    @Override
    public Observable<BaseBean<BusinessApproval>> addBusinessApprove(BusinessApproval businessApproval) {
        return RetrofitManager.getInstance().getAuthService().addBusinessApprove(businessApproval);
    }

    @Override
    public Observable<BaseBean> modifyBusinessApprove(BusinessApproval businessApproval) {
        return RetrofitManager.getInstance().getAuthService().modifyBusinessApprove(businessApproval);
    }

    @Override
    public Observable<BaseBean> deleteBusinessApprove(String id) {
        return RetrofitManager.getInstance().getAuthService().deleteBusinessApprove(id);
    }

}
