package com.example.config.exception;

import com.example.vo.ResultEnum;
import lombok.Getter;

/**
 * <p>
 *  自定义异常
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;
    private String msg;

    public ServiceException(ResultEnum resultEnum){
        super(resultEnum.getResultMsg());
        this.code = resultEnum.getResultCode();
        this.msg = resultEnum.getResultMsg();
    }
}
