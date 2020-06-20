package com.imooc.server.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 角色权限表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysRolePermissionVO implements Serializable {

    private Integer id;  //
    private Integer roleId;  //角色ID
    private Integer permissionId;  //权限ID
}
