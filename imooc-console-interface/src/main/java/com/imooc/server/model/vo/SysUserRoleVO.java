package com.imooc.server.model.vo;

import com.imooc.server.exception.CommonServiceException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用户角色表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysUserRoleVO implements Serializable {

    private Integer id;  //
    private Integer userId;  //用户ID
    private Integer roleId;  //角色ID
    private List<SysUserRoleVO> userList;

    public void checkParam2() throws CommonServiceException {
        if (roleId==null) {
            throw new CommonServiceException(404, "出现异常,角色ID不能为空!");
        }
    }
}
