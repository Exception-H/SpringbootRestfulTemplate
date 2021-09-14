package com.companyname.common.enums.interfaces;

/**
 * @className: BaseErrorInfoInterface
 * @description: 自定义的错误描述枚举类父接口
 * @author: Curtain
 * @date: 2021-08-19
 **/
public interface BaseErrorInfoInterface {
    /** 错误码*/
    String getResultCode();

    /** 错误描述*/
    String getResultMsg();
}
