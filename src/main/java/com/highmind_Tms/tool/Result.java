package com.highmind_Tms.tool;

public class Result<T> {
    private String error;
    private int status;
    private T data;

    private Result(T data) {
        this.status = 1;
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.status = cm.getStatus();
        this.error = cm.getError();
    }

    /**
     * 成功时候的调用
     *
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功，不需要传入参数
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success() {
        return (Result<T>) success("");
    }

    /**
     * 失败时候的调用
     *
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }

    /**
     * 失败时候的调用,扩展消息参数
     *
     * @param cm
     * @param msg
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm, String msg) {
        cm.setError(cm.getError() + "--" + msg);
        return new Result<T>(cm);
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

}
