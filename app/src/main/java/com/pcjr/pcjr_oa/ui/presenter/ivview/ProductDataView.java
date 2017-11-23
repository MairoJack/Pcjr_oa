package com.pcjr.pcjr_oa.ui.presenter.ivview;


import com.pcjr.pcjr_oa.bean.BaseBean;
import com.pcjr.pcjr_oa.bean.Product;
import com.pcjr.pcjr_oa.bean.ProductSummary;
import com.pcjr.pcjr_oa.bean.StaffCompany;
import com.pcjr.pcjr_oa.core.mvp.MvpView;

import java.util.List;

/**
 *
 *  Created by Mario on 2017/9/29下午2:46
 */
public interface ProductDataView extends MvpView {

    void onProductDurationInfoSuccess(ProductSummary data);

    void onTodayProductInfoSuccess(List<Product> data);
}
