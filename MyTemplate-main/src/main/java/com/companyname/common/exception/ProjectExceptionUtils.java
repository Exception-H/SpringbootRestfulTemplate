package com.companyname.common.exception;



/**
*
* @description: 异常统一处理工具类
* @author : Curtain
* @date : 2021/9/14
*/
public final class ProjectExceptionUtils {

    /**
     * 返回一个新的异常，统一构建，方便统一处理
     *
     * @param msg 消息
     * @param t   异常信息
     * @return 返回异常
     */
    public static BizException mpe(String msg, Throwable t, Object... params) {
        return new BizException(String.format(msg, params), t);
    }

    /**
     * 重载的方法
     *
     * @param msg 消息
     * @return 返回异常
     */
    public static BizException mpe(String msg, Object... params) {
        return new BizException(String.format(msg, params));
    }

    public static void throwMpe(boolean condition, String msg, Object... params) {
        if (condition) {
            throw mpe(msg, params);
        }
    }

}
