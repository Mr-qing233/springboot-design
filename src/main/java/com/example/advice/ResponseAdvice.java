package com.example.advice;

import com.example.vo.ResultEnum;
import com.example.vo.ResultJson;
import io.micrometer.common.lang.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.net.BindException;


/**
 * <p>
 *  切面统一封装返回值
 * </p>
 *
 * @author Gin
 * @since 2023-06-11
 */
@RestControllerAdvice(value = "com.example.controller")
@Slf4j
public class ResponseAdvice implements  ResponseBodyAdvice<Object> {
    /**
     * 直接返回true,对所有handler的responseBody都调用beforeBodyWrite方法
     * @param returnType 返回类型
     * @param converterType 输入类型
     * @return true
     */
    @Override
    public boolean supports(@NonNull MethodParameter returnType,@NonNull Class converterType) {
        return true;
    }

    /**
     * 统一包装
     * @param body 切片数据
     * @param returnType 返回类型
     * @param selectedContentType 选择内容类型
     * @param selectedConverterType 选择转换类型
     * @param request 任务
     * @param response 返回
     * @return ResultJson.Success(body)
     */
    @Override
    public Object beforeBodyWrite(Object body,@NonNull MethodParameter returnType,@NonNull MediaType selectedContentType,@NonNull Class selectedConverterType,@NonNull ServerHttpRequest request,@NonNull ServerHttpResponse response) {
        if(body instanceof ResultJson<?>){
            return body;
        }
        return ResultJson.Success(body);
    }

    /**
     * 系统内部异常捕获
     *
     * @param exception 服务异常
     * @return ResultJson.Error
     * @param <T> serviceException
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public <T> ResultJson<T> serviceExceptionHandler(Exception exception){
        log.error("系统内部异常:",exception);
        return ResultJson.Error(ResultEnum.ERROR);
    }

    /**
     * 忽略参数异常处理器
     * 出发例子:带有@RequestParam注解的参数未传入形参
     *
     * @param missingServletRequestParameterException 错误
     * @return ResultJson.Error
     * @param <T> ResultEnum.SERVLETREQUESTPARAMETER
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public <T> ResultJson<T> parameterMissingExceptionHandler(MissingServletRequestParameterException missingServletRequestParameterException){
        log.error("缺少Servlet请求参数异常:",missingServletRequestParameterException);
        return ResultJson.Error(ResultEnum.MISSINGSERVLETREQUESTPARAMETER);
    }

    /**
     * 缺少请求体异常处理器
     * 触发例子:不给请求体参数
     *
     * @param httpMessageNotReadableException 缺少请求体异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> ResultJson<T> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException httpMessageNotReadableException) {
        log.error("参数请求体异常", httpMessageNotReadableException);
        return ResultJson.Error(ResultEnum.MISSINGPARAMTERBODY);
    }


    /**
     * 统一处理请求参数绑定错误(实体对象传参);
     *
     * @param bindException BindException
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Object validExceptionHandler(BindException bindException) {
        log.error("方法参数绑定错误(实体对象传参)", bindException);
        return ResultJson.Error(ResultEnum.BINDENTITYERROR);
    }

    /**
     * 统一处理请求参数绑定错误(实体对象请求体传参);
     *
     * @param methodArgumentNotValidException 参数验证异常
     * @return ResponseResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object parameterExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("方法参数无效异常(实体对象请求体传参)", methodArgumentNotValidException);
        return ResultJson.Error(ResultEnum.METHODARGUMENTERROR);
    }
}
