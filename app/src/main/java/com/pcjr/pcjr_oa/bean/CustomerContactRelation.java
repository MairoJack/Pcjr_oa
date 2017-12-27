package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 * 联系人 客户 关系
 * Created by Mario on 2017/12/21下午2:10
 */
@Data
public class CustomerContactRelation implements Serializable {
    private String id;                     //ID
    private String intimacy;               //亲密度

    @SerializedName("contact_id")
    private String contactId;              //联系人ID
    @SerializedName("contact_name")
    private String contactName;            //联系人名称
    @SerializedName("customer_id")
    private String customerId;             //客户ID
    @SerializedName("customer_name")
    private String customerName;           //客户名称
    @SerializedName("contact_mobile")
    private String contactMobile;          //联系电话
    @SerializedName("customer_type")
    private Integer customerType;          //客户类型 0：个人  1：公司
    @SerializedName("role_relationship")
    private String roleRelationship;       //角色关系
    @SerializedName("join_date")
    protected Integer joinDate;            //创建时间

}
