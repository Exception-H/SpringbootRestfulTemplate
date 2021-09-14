package com.companyname.common.enums.impls;

import com.companyname.common.enums.interfaces.BaseErrorInfoInterface;
import lombok.Getter;

/**
 * @className: CommonEnum
 * @description: 错误码枚举
 * @author: Curtain
 * @date: 2021-08-19
 **/
@Getter
public enum CommonEnum implements BaseErrorInfoInterface {
    // 数据操作错误定义
    SUCCESS("200", "操作成功"),
    BODY_NOT_MATCH("400","操作失败"),
    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
    NOT_FOUND("404", "资源不存在!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503","服务器正忙，请稍后再试!")
    ;
    /** 错误码 */
    private final String resultCode;

    /** 错误描述 */
    private final String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
