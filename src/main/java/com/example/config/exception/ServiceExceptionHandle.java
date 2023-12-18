package com.example.config.exception;

import com.example.vo.ResultJson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *      自定义异常拦截器
 * </p>
 *
 * @author Gin
 * @since 2023-06-10
 */
@ControllerAdvice
public class ServiceExceptionHandle {
    /**
     * 如果抛出的是ServiceException则抛出该方法
     * @param serviceException 业务异常
     * @return Result.Error(serviceException)
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public <T> ResultJson<T> handle(ServiceException serviceException){
        return ResultJson.Error(serviceException);
    }
}
