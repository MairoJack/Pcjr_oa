package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  客户 - 公司
 *  Created by Mario on 2017/9/20下午1:37
 */
@Data
public class CustomerCompany extends Customer{

    private String business;                  //主营业务
    private String leader;                    //企业负责人
    private String regtime;                   //注册时间
    private Integer nature;                   //公司性质 0：金融服务机构  1：商圈管理者，
                                              //        2：政府机关/团体  3：实体企业  4：为其他

    @SerializedName("social_credit_code")
    private String socialCreditCode;          //社会信用代码
    @SerializedName("organization_code")
    private String organizationCode;          //组织机构代码
    @SerializedName("business_no")
    private String businessNo;                //营业执照号码
    @SerializedName("actual_leader")
    private String actualLeader;              //实际经营者

    public CustomerCompany(String name, String mobile, String actualLeader) {
        this.name = name;
        this.mobile = mobile;
        this.actualLeader = actualLeader;
    }

    public CustomerCompany() {
        super();
    }
}
