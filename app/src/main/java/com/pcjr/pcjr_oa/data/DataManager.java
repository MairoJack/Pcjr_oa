package com.pcjr.pcjr_oa.data;

import com.pcjr.pcjr_oa.App;
import com.pcjr.pcjr_oa.api.ApiConstant;
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
import com.pcjr.pcjr_oa.bean.Token;
import com.pcjr.pcjr_oa.bean.Withdraw;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.exception.LoginExpireException;
import com.pcjr.pcjr_oa.exception.UnauthorizedException;
import com.pcjr.pcjr_oa.model.impl.ApiModel;
import com.pcjr.pcjr_oa.model.impl.OAuthModel;
import com.pcjr.pcjr_oa.utils.RxUtils;
import com.pcjr.pcjr_oa.utils.SPUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Mario on 2017/9/28下午3:08
 */
public class DataManager {

    private static String TAG = DataManager.class.getSimpleName();

    private static DataManager dataManager;

    private ApiModel apiModel;
    private OAuthModel oAuthModel;

    public synchronized static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    /*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
     * -------------------------- ApiModel Start------------------------------
     */
    public Observable<BaseBean<Token>> login(String username, String password, String key) {
        return this.apiModel.login(username, password, key)
                .compose(RxUtils.applyIOToMainThreadSchedulers());
    }

    public Observable<BaseBean<Token>> refreshToken() {
        return this.apiModel.refreshToken()
                .compose(RxUtils.applyIOToMainThreadSchedulers());
    }

    /*
     * -------------------------- ApiModel Over ------------------------------
     */

    /*
     * -------------------------- OAuthModel Start------------------------------
     */
    public Observable<BaseBean<List<StaffCompany>>> getStaffCompanyList() {
        return this.oAuthModel.getStaffCompanyList()
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<ProductSummary>> getProductDurationInfo(long startDate, long endDate) {
        return this.oAuthModel.getProductDurationInfo(startDate, endDate)
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<List<Product>>> getTodayProductInfo() {
        return this.oAuthModel.getTodayProductInfo()
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<RepaymentInfo>> getRepaymentDurationInfo(long startDate, long endDate) {
        return this.oAuthModel.getRepaymentDurationInfo(startDate, endDate)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<RepaymentInfo>> getOneDayRepaymentDetail(String oneDay) {
        return this.oAuthModel.getOneDayRepaymentDetail(oneDay)
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<Recharge>> getRechargeDurationInfo(long startDate, long endDate) {
        return this.oAuthModel.getRechargeDurationInfo(startDate, endDate)
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<Withdraw>> getWithdrawDurationInfo(long startDate, long endDate) {
        return this.oAuthModel.getWithdrawDurationInfo(startDate, endDate)
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<Member>> getMemberNumDurationInfo(long startDate, long endDate) {
        return this.oAuthModel.getMemberNumDurationInfo(startDate, endDate)
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<Member>> getEffectiveMemberNumDurationInfo(long startDate, long endDate) {
        return this.oAuthModel.getEffectiveMemberNumDurationInfo(startDate, endDate)
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<PlatformData>> getPlatformData() {
        return this.oAuthModel.getPlatformData()
                .compose(RxUtils.applyIOToMainThreadSchedulers());

    }

    public Observable<BaseBean<List<Customer>>> getBorrowerList(int page, String query, String id, int type) {
        return this.oAuthModel.getBorrowerList(page, query, id, type)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<CustomerPersonal>> addPerson(CustomerPersonal customer) {
        return this.oAuthModel.addPerson(customer)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean> modifyPerson(CustomerPersonal customer) {
        return this.oAuthModel.modifyPerson(customer)
                .flatMap(new CheckAuthNoData())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<CustomerPersonal>> getPersonDetail(String id) {
        return this.oAuthModel.getPersonDetail(id)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<CustomerCompany>> addCompany(CustomerCompany customer) {
        return this.oAuthModel.addCompany(customer)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean> modifyCompany(CustomerCompany customer) {
        return this.oAuthModel.modifyCompany(customer)
                .flatMap(new CheckAuthNoData())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<CustomerCompany>> getCompanyDetail(String id) {
        return this.oAuthModel.getCompanyDetail(id)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }


    public Observable<BaseBean<List<Person>>> getManagerList() {
        return this.oAuthModel.getManagerList()
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<List<Contact>>> getContactList(int page, String query, String id, int type) {
        return this.oAuthModel.getContactList(page, query, id, type)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<Contact>> addContact(Contact contact) {
        return this.oAuthModel.addContact(contact)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean> modifyContact(Contact contact) {
        return this.oAuthModel.modifyContact(contact)
                .flatMap(new CheckAuthNoData())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<Contact>> getContactDetail(String id) {
        return this.oAuthModel.getContactDetail(id)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean> modifyRelationship(CustomerContact customerContact) {
        return this.oAuthModel.modifyRelationship(customerContact)
                .flatMap(new CheckAuthNoData())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<List<CustomerContactRelation>>> getRelationshipList(String id, int type) {
        return this.oAuthModel.getRelationshipList(id, type)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<List<Contract>>> getContractList(int page, String query) {
        return this.oAuthModel.getContractList(page, query)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<Contract>> addContract(Contract contract) {
        return this.oAuthModel.addContract(contract)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean> modifyContract(Contract contract) {
        return this.oAuthModel.modifyContract(contract)
                .flatMap(new CheckAuthNoData())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<Contract>> getContractDetail(String id) {
        return this.oAuthModel.getContractDetail(id)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<List<BusinessApproval>>> getBusinessApproveList(int page, String query) {
        return this.oAuthModel.getBusinessApproveList(page, query)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean<BusinessApproval>> addBusinessApprove(BusinessApproval businessApproval) {
        return this.oAuthModel.addBusinessApprove(businessApproval)
                .flatMap(new CheckAuth<>())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean> modifyBusinessApprove(BusinessApproval businessApproval) {
        return this.oAuthModel.modifyBusinessApprove(businessApproval)
                .flatMap(new CheckAuthNoData())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    public Observable<BaseBean> deleteBusinessApprove(String id) {
        return this.oAuthModel.deleteBusinessApprove(id)
                .flatMap(new CheckAuthNoData())
                .compose(RxUtils.applyIOToMainThreadSchedulers())
                .retryWhen(new RetryWithUnAuth());

    }

    /*
     * -------------------------- OAuthModel Over ------------------------------
     */

    private DataManager() {
        this.apiModel = ApiModel.getInstance();
        this.oAuthModel = OAuthModel.getInstance();
    }

    private class CheckAuth<T> implements Function<BaseBean<T>, ObservableSource<BaseBean<T>>> {

        @Override
        public ObservableSource<BaseBean<T>> apply(@NonNull BaseBean<T> result) throws Exception {
            if (!result.isSuccess()) {
                switch (result.getCode()) {
                    case ApiConstant.TOKEN_EXPIRE:
                        return Observable.error(new UnauthorizedException());
                    case ApiConstant.LOGIN_EXPIRE:
                        return Observable.error(new LoginExpireException(result.getMsg()));
                }
            }
            return Observable.just(result);
        }
    }

    private class CheckAuthNoData implements Function<BaseBean, ObservableSource<BaseBean>> {

        @Override
        public ObservableSource<BaseBean> apply(@NonNull BaseBean result) throws Exception {
            if (!result.isSuccess()) {
                switch (result.getCode()) {
                    case ApiConstant.TOKEN_EXPIRE:
                        return Observable.error(new UnauthorizedException());
                    case ApiConstant.LOGIN_EXPIRE:
                        return Observable.error(new LoginExpireException(result.getMsg()));
                }
            }
            return Observable.just(result);
        }
    }

    private class RetryWithUnAuth implements Function<Observable<Throwable>, ObservableSource<?>> {

        @Override
        public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) {
            return throwableObservable.flatMap(throwable -> {
                if (throwable instanceof UnauthorizedException) {
                    return refreshToken()
                            .doOnNext(data -> {
                                Token result = data.getData();
                                if (result.isSuccess()) {
                                    SPUtils.put(App.getContext(), Constant.ACCESS_TOKEN, result.getAccessToken());
                                    SPUtils.put(App.getContext(), Constant.REFRESH_TOKEN, result.getRefreshToken());
                                } else {
                                    SPUtils.clear(App.getContext());
                                }
                            });
                }
                return Observable.error(throwable);
            });
        }
    }
}
