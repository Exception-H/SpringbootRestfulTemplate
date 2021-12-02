package com.companyname.common.utils;

import com.alibaba.fastjson.JSONObject;
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
public class ResultBody {
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
    private Object result;

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
    public static ResultBody success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static ResultBody success(Object data) {
        ResultBody rb = new ResultBody();
        rb.setCode(ResultCodeEnum.SUCCESS.getResultCode());
        rb.setMessage(ResultCodeEnum.SUCCESS.getResultMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(BaseErrorInfoInterface errorInfo) {
        ResultBody rb = new ResultBody();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error(String code, String message) {
        ResultBody rb = new ResultBody();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    /**
     * 失败
     */
    public static ResultBody error( String message) {
        ResultBody rb = new ResultBody();
        rb.setCode("-1");
        rb.setMessage(message);
        rb.setResult(null);
        return rb;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
