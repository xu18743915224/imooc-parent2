package com.imooc.server.exception;

import com.imooc.server.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.utils.exception
 * @description : 公共异常处理
 **/
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(CommonServiceException.class)
    @ResponseBody
    public BaseResponse serviceExceptionHandler(
            HttpServletRequest request, CommonServiceException e) {

        log.error("CommonServiceException, code:{}, message",
                e.getCode(), e.getMessage());

        return BaseResponse.serviceException(e);
    }

}
