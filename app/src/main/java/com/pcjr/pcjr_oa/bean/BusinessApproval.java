package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

import lombok.Data;

/**
 *  业务审批
 *  Created by Mario on 2017/12/27上午11:03
 */
@Data
public class BusinessApproval implements Serializable{
    private String id;                        //ID
    @SerializedName("approve_no")
    private String approveNo;                 //审批编号
    @SerializedName("title")
    private String title;                     //标题
    @SerializedName("actual_borrower_id")
    private String actualBorrowerId;          //实际借款人ID
    @SerializedName("actual_borrower_name")
    private String actualBorrowerName;        //实际借款人姓名
    @SerializedName("actual_borrower_type")
    private Integer actualBorrowerType;       //客户类型 0:个人客户 1:公司客户
    @SerializedName("form_borrower_id")
    private String formBorrowerId;            //出面借款人ID
    @SerializedName("form_borrower_name")
    private String formBorrowerName;          //出面借款人姓名
    @SerializedName("form_borrower_type")
    private Integer formBorrowerType;         //客户类型 0:个人客户 1:公司客户
    @SerializedName("manager")
    private String manager;                   //客户经理ID
    @SerializedName("manager_name")
    private String managerName;               //客户经理名字
    @SerializedName("apply_person_id")
    private String applyPersonId;             //申请人ID
    @SerializedName("apply_person_name")
    private String applyPersonName;           //申请人姓名
    @SerializedName("project_source")
    private String projectSource;             //项目来源
    @SerializedName("project_type")
    private Integer projectType;              //项目类型
    @SerializedName("want_amount")
    private String wantAmount;                //意向金额
    @SerializedName("approve_amount")
    private String approveAmount;             //审批金额
    @SerializedName("guarantee_type")
    private String guaranteeType;             //担保方式
    @SerializedName("intro")
    private String intro;                     //借款用途
    @SerializedName("main_risk")
    private String mainRisk;                  //主要风险点
    @SerializedName("precautions")
    private String precautions;               //防范措施
    @SerializedName("guarantee_fee_amount")
    private String guaranteeFeeAmount;        //担保费金额
    @SerializedName("guarantee_fee_pay_type")
    private String guaranteeFeePayType;       //担保费缴费形式
    @SerializedName("service_fee_amount")
    private String serviceFeeAmount;          //服务费金额
    @SerializedName("service_fee_pay_type")
    private String serviceFeePayType;         //服务费缴费形式
    @SerializedName("security_deposit_amount")
    private String securityDepositAmount;     //保证金金额
    @SerializedName("security_deposit_pay_type")
    private String securityDepositPayType;    //保证金缴费方式
    @SerializedName("status")
    private Integer status;                   //审批状态
    @SerializedName("join_date")
    private Long joinDate;                    //创建时间
    
    private transient boolean update = false; //是否更新

}
