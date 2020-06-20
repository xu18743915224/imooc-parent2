package com.imooc.server.model.vo;

import com.imooc.server.common.BaseRequest;
import com.imooc.server.exception.CommonServiceException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 角色表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysRoleVO extends BaseRequest implements Serializable {

    private Integer id;  //
    private String roleName;  //角色名称
    private String roleCode;  //
    private String roleDescription;  //角色描述
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
        if (StringUtils.isEmpty(roleName)) {
            throw new CommonServiceException(404, "角色名称不能为空!");
        }
        if (StringUtils.isEmpty(roleCode)) {
            throw new CommonServiceException(404, "角色编码不能为空!");
        }
    }
}
