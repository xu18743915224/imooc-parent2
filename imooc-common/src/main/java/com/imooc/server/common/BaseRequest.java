package com.imooc.server.common;


import com.imooc.server.exception.CommonServiceException;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.utils.common.vo
 * @description : 公共的请求对象
 **/
public abstract class BaseRequest {

    /**
     * @Description: 公共的参数验证方法
     * @Param: []
     * @return: void
     * @Author: jiangzh
     */
    public abstract void checkParam() throws CommonServiceException;

}
