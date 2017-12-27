package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 * 合同
 * Created by Mario on 2017/12/25下午3:18
 */
@Data
public class Contract implements Serializable {

    @SerializedName("id")
    private String id;                          //合同ID
    @SerializedName("contract_no")
    protected String contractNo;               //合同编号
    @SerializedName("approve_id")
    protected String approveId;                //审批记录ID
    @SerializedName("borrower_id")
    protected String borrowerId;               //借款人ID
    @SerializedName("borrower_name")
    protected String borrowerName;             //实际借款人姓名
    @SerializedName("borrower_type")
    protected Integer borrowerType;            //客户类型 0:个人客户 1:公司客户
    @SerializedName("borrow_amount")
    protected String borrowAmount;             //借款金额
    @SerializedName("product_num")
    protected Integer productNum;              //拆分产品笔数
    @SerializedName("create_person_id")
    protected String createPersonId;          //创建人ID
    @SerializedName("create_person_name")
    protected String createPersonName;        //创建人姓名
    @SerializedName("year_income")
    protected String yearIncome;               //年化收益
    @SerializedName("deadline")
    protected Long deadline;                    //项目结束日期
    @SerializedName("value_date")
    protected Long valueDate;                  //起息日期
    @SerializedName("product_days")
    protected Integer productDays;             //产品期限
    @SerializedName("repayment")
    protected Integer repayment;                //还款方式 0：一次性还本付息 1：按月付息 到期还本 2：等额本息 3：先息后本（按季付息）
    @SerializedName("remarks")
    protected String remarks;                   //备注
    @SerializedName("join_date")
    protected Long joinDate;                    //创建时间

}
