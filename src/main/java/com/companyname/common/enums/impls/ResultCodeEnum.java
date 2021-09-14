package com.companyname.common.enums.impls;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
*
* @description:
* @param :  错误码枚举 - 简易
* @return :
* @author : Curtain
* @date : 2021/9/14
*/
@Getter
@RequiredArgsConstructor
public enum ResultCodeEnum {

    /**
     * 成功 200
     */
    SUCCESS(200,"操作成功"),
    /**
     * 失败 400
     */
    FAILURE(400,"操作失败"),
    /**
     * 资源不存在 404
     */
    UNFOUNDED(404,"资源不存在"),
    /**
     * 服务架构异常 500
     */
    INTERNAL_ERROR(500,"服务器内部异常");

    private final int code;
    private final String desc;
}
