package com.example.vo;

import com.example.config.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *  返回类型封装
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultJson<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> ResultJson<T> Success(){
        return new ResultJson<>(ResultEnum.SUCCESS.getResultCode(),ResultEnum.SUCCESS.getResultMsg(),null);
    }
    public static <T> ResultJson<T> Success(T data){
        return new ResultJson<>(ResultEnum.SUCCESS.getResultCode(),ResultEnum.SUCCESS.getResultMsg(),data);
    }

    public static <T> ResultJson<T> Error(){
        return new ResultJson<>(ResultEnum.ERROR.getResultCode(),ResultEnum.ERROR.getResultMsg(),null);
    }
    public static <T> ResultJson<T> Error(ResultEnum commonEnum){
        return new ResultJson<>(commonEnum.getResultCode(),commonEnum.getResultMsg(),null);
    }
    public static <T> ResultJson<T> Error(ServiceException serviceException){
        return new ResultJson<>(serviceException.getCode(),serviceException.getMessage(),null);
    }
    public static <T> ResultJson<T> Error(Exception exception){
        return new ResultJson<>("500",exception.getMessage(),null);
    }
}
