package com.imooc.server.model.vo;

import com.imooc.server.common.BaseRequest;
import com.imooc.server.exception.CommonServiceException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysUserVO extends BaseRequest implements Serializable {

    private Integer id;  //
    private String username;  //账号
    private String password;  //密码
    private String nickname;  //昵称
    private String email;  //邮箱
    private Integer status;  //状态（0：锁定，1：解锁）
    private String createUser;  //
    private Date createTime;  //
    private String updateUser;  //
    private Date updateTime;  //

    //分页信息
    private int pageSize = 20;
    private int pageIndex = 0;
    private String sortField;
    private String sortOrder;

    @Override
    public void checkParam() throws CommonServiceException {
        if (StringUtils.isEmpty(username)) {
            throw new CommonServiceException(404, "用户名不能为空!");
        }
        if (StringUtils.isEmpty(password)) {
            throw new CommonServiceException(404, "密码不能为空!");
        }
    }
}
