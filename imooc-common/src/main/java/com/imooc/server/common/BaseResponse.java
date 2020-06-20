package com.imooc.server.common;


import com.imooc.server.exception.CommonServiceException;
import lombok.Data;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.utils.common.vo
 * @description : 表现层公共返回
 **/
@Data
public class BaseResponse<M> {

    private Integer code;   // 业务编号
    private String message; // 异常信息
    private M data;         // 业务数据返回

    private BaseResponse() {
    }

    // 成功但是无参数
    public static BaseResponse success() {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("");
        return response;
    }

    // 成功有参数
    public static <M> BaseResponse success(M data) {
        BaseResponse response = new BaseResponse();
        response.setCode(200);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    // 未登录异常
    public static <M> BaseResponse noLogin() {
        BaseResponse response = new BaseResponse();
        response.setCode(401);
        response.setMessage("请登录");
        return response;
    }

    // 登录超时
    public static <M> BaseResponse timeOutLogin() {
        BaseResponse response = new BaseResponse();
        response.setCode(408);
        response.setMessage("登录超时,请重新登录!");
        return response;
    }

    // 出现业务异常
    public static <M> BaseResponse serviceException(CommonServiceException e) {
        BaseResponse response = new BaseResponse();
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }

}
