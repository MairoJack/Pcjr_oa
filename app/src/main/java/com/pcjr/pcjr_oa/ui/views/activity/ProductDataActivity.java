package com.pcjr.pcjr_oa.ui.views.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.pcjr.pcjr_oa.R;
import com.pcjr.pcjr_oa.bean.Product;
import com.pcjr.pcjr_oa.bean.ProductSummary;
import com.pcjr.pcjr_oa.constant.Constant;
import com.pcjr.pcjr_oa.core.BaseToolbarActivity;
import com.pcjr.pcjr_oa.ui.adapter.ProductDataAdapter;
import com.pcjr.pcjr_oa.ui.decorator.RecycleViewDivider;
import com.pcjr.pcjr_oa.ui.presenter.ProductDataPresenter;
import com.pcjr.pcjr_oa.ui.presenter.ivview.ProductDataView;
import com.pcjr.pcjr_oa.utils.DateUtils;

import java.util.List;

import butterknife.BindView;

/**
 * 产品数据
 * Created by Mario on 2017/10/10上午10:26
 */
public class ProductDataActivity extends BaseToolbarActivity implements ProductDataView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_product_number)
    TextView txtProductNumber;
    @BindView(R.id.txt_product_number_today)
    TextView txtProductNumberToday;
    @BindView(R.id.txt_deal_number)
    TextView txtDealNumber;
    @BindView(R.id.txt_deal_number_today)
    TextView txtDealNumberToday;
    @BindView(R.id.txt_average_time)
    TextView txtAverageTime;
    @BindView(R.id.txt_average_today)
    TextView txtAverageToday;
    @BindView(R.id.txt_deal_amount)
    TextView txtDealAmount;
    @BindView(R.id.txt_deal_amount_today)
    TextView txtDealAmountToday;
    @BindView(R.id.txt_average_rate)
    TextView txtAverageRate;
    @BindView(R.id.txt_average_rate_today)
    TextView txtAverageRateToday;
    @BindView(R.id.txt_invest_number)
    TextView txtInvestNumber;
    @BindView(R.id.txt_invest_number_today)
    TextView txtInvestNumberToday;
    @BindView(R.id.txt_today_product)
    TextView txtTodayProduct;
    private ProductDataAdapter adapter;
    private ProductDataPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.product_data;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setTitle("产品数据");
        showBack();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, 1, 20, Color.parseColor("#f7f8f9")));

        adapter = new ProductDataAdapter();
        mRecyclerView.setAdapter(adapter);

        presenter = new ProductDataPresenter();
        presenter.attachView(this);
    }


    @Override
    protected void initListeners() {

    }


    @Override
    protected void initData() {

        long start = System.currentTimeMillis() / 1000;
        long end = start + 10 * 24 * 3600;

        txtTitle.setText(DateUtils.longTimeToStr(start, DateUtils.DATE_FORMAT_YYYY_MM_DD)
                + "至" + DateUtils.longTimeToStr(end, DateUtils.DATE_FORMAT_YYYY_MM_DD)
                + "日产品数据");
        presenter.getProductDurationInfo(start, end);
        presenter.getTodayProductInfo();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_date, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btn_date) {
            startActivityForResult(new Intent(this, CountDateSettingActivity.class), Constant.REQUEST_COUNT_DATE);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onFailure(Throwable e) {
        showToast(getString(R.string.network_error));
    }

    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onProductDurationInfoSuccess(ProductSummary data) {
        txtProductNumber.setText(data.getProductNum());
        txtDealNumber.setText(data.getTotalDealNum());
        txtAverageTime.setText(data.getAvgSaleTime());
        txtDealAmount.setText(data.getTotalTradingAmount());
        txtAverageRate.setText(data.getAvgYearIncome());
        txtInvestNumber.setText(data.getTotalMemberNum());
    }

    @Override
    public void onTodayProductInfoSuccess(List<Product> data) {
        txtTodayProduct.setText("今日产品（" + data.size() + "）");
        adapter.setNewData(data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_COUNT_DATE:
                    String startDate = data.getStringExtra("startDate");
                    String endDate = data.getStringExtra("endDate");
                    txtTitle.setText(startDate + "至" + endDate + "日产品数据");
                    presenter.getProductDurationInfo(DateUtils.getMillisOfDate(startDate), DateUtils.getMillisOfDate(endDate));
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
