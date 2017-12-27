package com.pcjr.pcjr_oa.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 客户 - 联系人
 * Created by Mario on 2017/9/20下午1:37
 */
@Data
public class CustomerContact implements Serializable {
    private String id;          //对应记录id
    private Integer type;       //类型：0 个人 1 公司 2 联系人

    @SerializedName("relationship")
    protected List<CustomerContactRelation> relationship;   //联系人、客户关系
}
