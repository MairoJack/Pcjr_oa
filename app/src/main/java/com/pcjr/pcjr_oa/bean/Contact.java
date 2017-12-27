package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *  联系人
 *  Created by Mario on 2017/10/30上午10:55
 */
@Data
public class Contact implements Serializable{
    protected String id;                      //ID
    protected String name;                    //联系人名称
    protected String mobile;                  //手机号码
    protected String address;                 //居住地址
    protected String email;                   //邮箱
    private String company;                   //工作单位
    private String position;                  //单位职位
    private String remark;                    //备注
    private Integer sex;                      //性别 0：男  1：女

    @SerializedName("relationship_id")
    protected String relationshipId;            //客户联系人关系ID
    @SerializedName("join_date")
    protected Long joinDate;                    //创建时间
    @SerializedName("is_selected")
    protected Boolean isSelected;               //是否被选中
    @SerializedName("relationship")
    protected List<CustomerContactRelation> relationship;   //联系人、客户关系

    @Expose(serialize = false)
    protected transient boolean isUpdate = false;  //是否是更新 默认不是


}
