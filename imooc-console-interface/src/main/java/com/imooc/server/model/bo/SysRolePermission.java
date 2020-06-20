package com.imooc.server.model.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("SYS_ROLE_PERMISSION")
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 1590733820502L;

    @TableId(value = "ID" )
    private Integer id;  //
    private Integer roleId;  //角色ID
    private Integer permissionId;  //权限ID
}
