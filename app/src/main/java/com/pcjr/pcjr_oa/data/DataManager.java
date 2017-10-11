package com.pcjr.pcjr_oa.data;

import com.pcjr.pcjr_oa.App;
import com.pcjr.pcjr_oa.api.ApiConstant;
import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.bean.Token;
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
