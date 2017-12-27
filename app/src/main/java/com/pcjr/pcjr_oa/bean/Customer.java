package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 客户
 * Created by Mario on 2017/9/20下午1:37
 */
@Data
public class Customer implements Serializable {

    protected String id;                      //ID
    protected String name;                    //客户名称
    protected String mobile;                  //手机
    protected String fax;                     //传真
    protected String address;                 //地址
    protected String email;                   //邮箱
    protected String website;                 //网站
    protected String remarks;                 //备注
    protected String manager;                 //客户经理ID

    @SerializedName("postal_code")
    protected String postalCode;              //邮编
    @SerializedName("card_no")
    protected String cardNo;                  //贷款卡号
    @SerializedName("manager_name")
    protected String managerName;             //客户经理
    @SerializedName("customer_type")
    protected Integer customerType;           //客户类型 0：个人  1：公司
    @SerializedName("credit_rating")
    protected String creditRating;            //信用等级
    @SerializedName("counter_guarantee")
    protected String counterGuarantee;        //反担保情
    @SerializedName("borrower_introduction")
    protected String borrowerIntroduction;    //借款人情况
    @SerializedName("credit_history")
    protected String creditHistory;           //信用记录
    @SerializedName("join_date")
    protected Long joinDate;                  //创建时间
    @SerializedName("is_selected")
    protected Boolean isSelected = false;               //是否被选中

    @Expose(serialize = false)
    protected transient boolean isUpdate = false;       //是否是更新 默认不是

    @SerializedName("relationship")
    protected List<CustomerContactRelation> relationship;   //联系人、客户关系

}
