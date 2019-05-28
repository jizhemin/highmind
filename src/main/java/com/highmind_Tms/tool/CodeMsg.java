package com.highmind_Tms.tool;



public class CodeMsg {
    private int status;
    private String error;
    // 按照模块定义CodeMsg
    // 通用异常
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(0,"服务端异常");
    public static CodeMsg INSERT_ERROR = new CodeMsg(0,"插入异常");
    public static CodeMsg UPDATE_ERROR = new CodeMsg(0,"更新异常");
    public static CodeMsg DELETE_ERROR = new CodeMsg(0,"删除异常");
    public static CodeMsg IMPORT_ERROR = new CodeMsg(0,"导入异常");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(0,"输入参数为空");
    public static CodeMsg PAGING_PARAMETERS_ERROR = new CodeMsg(0,"分页参数异常");
    // 业务异常
    public static CodeMsg USER_NOT_EXSIST = new CodeMsg(0,"用户不存在");
    public static CodeMsg USER_EXSIST = new CodeMsg(0,"用户存在");
    public static CodeMsg ILLEGAL_REQUEST = new CodeMsg(0,"非法请求");
    public static CodeMsg SESSION_NOT_EXSIST =  new CodeMsg(0,"用户未登录");
    public static CodeMsg NOT_FIND_DATA = new CodeMsg(0,"查找不到对应数据");
    //状态异常，这个是被捕获的固定状态
    public static CodeMsg CASCADE_DELETE = new CodeMsg(3,"级联删除异常");
    public CodeMsg(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
