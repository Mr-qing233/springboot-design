package com.example.vo;

/**
 * <p>
 *  接口枚举返回码
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     * @return code
     */
    String getResultCode();

    /**
     * 错误信息
     * @return msg
     */
    String getResultMsg();
}
