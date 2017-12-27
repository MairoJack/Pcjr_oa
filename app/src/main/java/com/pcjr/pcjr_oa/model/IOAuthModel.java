package com.pcjr.pcjr_oa.model;


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


/**
 * Created by Mario on 2017/9/28上午10:54
 */
public interface IOAuthModel {
    Observable<BaseBean<List<StaffCompany>>> getStaffCompanyList();

    Observable<BaseBean<ProductSummary>> getProductDurationInfo(long startDate, long endDate);

    Observable<BaseBean<List<Product>>> getTodayProductInfo();

    Observable<BaseBean<RepaymentInfo>> getRepaymentDurationInfo(long startDate, long endDate);

    Observable<BaseBean<RepaymentInfo>> getOneDayRepaymentDetail(String oneDay);

    Observable<BaseBean<Recharge>> getRechargeDurationInfo(long startDate, long endDate);

    Observable<BaseBean<Withdraw>> getWithdrawDurationInfo(long startDate, long endDate);

    Observable<BaseBean<Member>> getMemberNumDurationInfo(long startDate, long endDate);

    Observable<BaseBean<Member>> getEffectiveMemberNumDurationInfo(long startDate, long endDate);

    Observable<BaseBean<PlatformData>> getPlatformData();

    Observable<BaseBean<List<Customer>>> getBorrowerList(int page, String query, String id, int type);

    Observable<BaseBean<CustomerPersonal>> addPerson(CustomerPersonal customer);

    Observable<BaseBean> modifyPerson(CustomerPersonal customer);

    Observable<BaseBean<CustomerPersonal>> getPersonDetail(String id);

    Observable<BaseBean<CustomerCompany>> addCompany(CustomerCompany customer);

    Observable<BaseBean> modifyCompany(CustomerCompany customer);

    Observable<BaseBean<CustomerCompany>> getCompanyDetail(String id);

    Observable<BaseBean<List<Person>>> getManagerList();

    Observable<BaseBean<List<Contact>>> getContactList(int page, String query, String id, int type);

    Observable<BaseBean<Contact>> addContact(Contact contact);

    Observable<BaseBean> modifyContact(Contact contact);

    Observable<BaseBean<Contact>> getContactDetail(String id);

    Observable<BaseBean> modifyRelationship(CustomerContact customerContact);

    Observable<BaseBean<List<CustomerContactRelation>>> getRelationshipList(String id, int type);

    Observable<BaseBean<List<Contract>>> getContractList(int page, String query);

    Observable<BaseBean<Contract>> addContract(Contract contract);

    Observable<BaseBean> modifyContract(Contract contract);

    Observable<BaseBean<Contract>> getContractDetail(String id);

    Observable<BaseBean<List<BusinessApproval>>> getBusinessApproveList(int page, String query);

    Observable<BaseBean<BusinessApproval>> addBusinessApprove(BusinessApproval businessApproval);

    Observable<BaseBean> modifyBusinessApprove(BusinessApproval businessApproval);

    Observable<BaseBean> deleteBusinessApprove(String id);

}
