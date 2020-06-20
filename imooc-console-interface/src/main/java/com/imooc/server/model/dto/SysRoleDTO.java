package com.imooc.server.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

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
public class SysRoleDTO implements Serializable {

    private Integer id;  //
    private String roleName;  //角色名称
    private String roleCode;  //
    private String roleDescription;  //角色描述
    private String createUser;  //
    private Date createTime;  //
    private String updateUser;  //
    private Date updateTime;  //
}
