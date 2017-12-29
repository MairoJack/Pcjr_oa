package com.pcjr.pcjr_oa.constant;

/**
 * Created by mario on 2017/9/1.
 */

public class Constant {

    //手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0;     // 正常状态
    public static final int POINT_STATE_SELECTED = 1;   // 按下状态
    public static final int POINT_STATE_WRONG = 2;      // 错误状态

    public final static int REQUEST_UNION_ITEM = 101;          //关联审批
    public final static int REQUEST_UNION_PRODUCT = 102;       //关联产品
    public final static int REQUEST_UNION_SCHEDULE = 103;      //关联日程
    public final static int REQUEST_UNION_DOCUMENT = 104;      //关联文档
    public final static int REQUEST_UNION_CONTRACT = 105;      //关联合同
    public final static int REQUEST_UNION_CUSTOMER = 106;      //关联客户
    public final static int REQUEST_UNION_CONTACT = 107;       //关联联系人
    public final static int REQUEST_UNION_APPROVE = 108;       //关联审批
    public final static int REQUEST_UNION_DATA = 109;          //关联数据
    public final static int REQUEST_UNION_FORM = 110;          //关联表单
    public final static int REQUEST_UNION_SHARE = 111;         //关联分享
    public final static int REQUEST_UNION_ACTIVITY = 112;       //关联活动

    public final static int REQUEST_REPEAT_RESULT = 201;            //设置重复
    public final static int REQUEST_REMIND_SETTING = 202;           //设置提醒
    public final static int REQUEST_PARTICIPANTS = 203;             //设置参与人
    public final static int REQUEST_SHAREER = 204;                  //设置共享人
    public final static int REQUEST_OPENNESS = 205;                 //设置公开程度
    public final static int REQUEST_TAG = 206;                      //设置标签
    public final static int REQUEST_ATTACHMENT = 207;               //设置附件
    public final static int REQUEST_LEAVE_TYPE = 208;               //设置请假类型
    public final static int REQUEST_MANAGER = 209;                  //设置负责人
    public final static int REQUEST_URGENCY = 210;                  //设置紧急程度
    public final static int REQUEST_PARENT_TASK = 211;              //设置上级任务
    public final static int REQUEST_SUB_TASK = 212;                 //设置子任务
    public final static int REQUEST_COMPANY_NATURE = 213;           //设置公司性质
    public final static int REQUEST_CUSTOMER_CREDIT_LEVEL = 214;    //设置客户信用等级
    public final static int REQUEST_SEX = 215;                      //设置性别
    public final static int REQUEST_MARITAL_STATUS = 216;           //设置婚姻状况
    public final static int REQUEST_ROLE = 217;                     //设置角色关系
    public final static int REQUEST_INTIMACY = 218;                 //设置亲密度
    public final static int REQUEST_COUNT_DATE = 219;               //设置统计起止日期
    public final static int REQUEST_ACTUAL_CUSTOMER = 220;          //设置实际客户
    public final static int REQUEST_SHOW_CUSTOMER = 221;            //设置出面客户
    public final static int REQUEST_CUSTOMER_CONTACT = 222;         //设置客户联系人
    public final static int REQUEST_RELATIONSHIP = 223;             //设置客户联系人关系
    public final static int REQUEST_REPAYMENT_METHOD = 224;         //设置还款方式
    public final static int REQUEST_PROJECT_TYPE = 226;             //设置项目类型

    public final static String[] SELECT_LEAVE_TYPE = new String[]{"事假", "病假", "年假", "调休", "婚假", "产假", "其他"};
    public final static String[] SELECT_OPENNESS = new String[]{"上级可见(默认)", "仅自己可见", "公开"};
    public final static String[] SELECT_COMPANY_NATURE = new String[]{"金融服务机构", "商圈管理者", "政府机关/团体", "实体企业", "其他"};
    public final static String[] SELECT_CUSTOMER_CREDIT_LEVEL = new String[]{"A级", "B级", "C级"};
    public final static String[] SELECT_SEX = new String[]{"男", "女"};
    public final static String[] SELECT_MARITAL_STATUS = new String[]{"未婚", "已婚", "离异"};
    public final static String[] SELECT_ROLE = new String[]{"父女", "父子", "母女", "母子", "配偶", "朋友", "家属", "同事"};
    public final static String[] SELECT_INTIMACY = new String[]{"初相识", "一般关系", "朋友关系"};
    public final static String[] SELECT_REPAYMENT_METHOD = new String[]{"一次还本付息", "先息后本(按月付息)", "先息后本(按季付息)","等额本息,按月付款"};
    public final static String[] SELECT_PROJECT_TYPE = new String[]{"大城小爱", "国泰明安", "珠联璧合","商通宝盈"};
    public final static String[] SELECT_APPROVAL_STATUS = new String[]{"审批中", "已完成"};

    public final static String KEY = "key";
    public final static String ACCESS_TOKEN = "access_token";
    public final static String REFRESH_TOKEN = "refresh_token";


//    public final static String COLOR_RED = "#F43F4A";
//    public final static String COLOR_GREEN = "#63CA91";
//    public final static String COLOR_GRAY = "#A6A6A6";
//    public final static String COLOR_YELLOW = "#E7BC23";
//    public final static String COLOR_DARK = "#606060";
}
