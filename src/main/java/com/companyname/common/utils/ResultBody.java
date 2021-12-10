package com.companyname.common.utils;

import cn.hutool.json.JSONObject;
import com.companyname.common.enums.impls.ResultCodeEnum;
import com.companyname.common.enums.interfaces.BaseErrorInfoInterface;
import lombok.Getter;
import lombok.Setter;

/**
 * @className: ResultBody
 * @description: 数据返回类
 * @author: Curtain
 * @date: 2021-08-19
 **/

@Setter
@Getter
public class ResultBody<T> {
    /**
     *  响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private T result;

    public ResultBody() {

    }

    public ResultBody(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }
    /**
     * 成功
     *
     * @return
     */
    public static <T> ResultBody<T> success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static <T> ResultBody<T> success(T data) {
        ResultBody<T> rb = new ResultBody<T>();
        rb.setCode(ResultCodeEnum.SUCCESS.getResultCode());
        rb.setMessage(ResultCodeEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static <T> ResultBody<T> error(BaseErrorInfoInterface errorInfo) {
        ResultBody<T> rb = new ResultBody<T>();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static <T> ResultBody<T> error(String code, String message) {
        ResultBody<T> rb = new ResultBody<T>();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static <T> ResultBody<T> error( String message) {
        ResultBody<T> rb = new ResultBody<T>();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return JacksonUtil.objToJson(this);
    }
}
