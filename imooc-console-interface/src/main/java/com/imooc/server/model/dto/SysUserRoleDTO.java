package com.imooc.server.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 用户角色表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysUserRoleDTO implements Serializable {

    private Integer id;  //
    private Integer userId;  //用户ID
    private Integer roleId;  //角色ID

    private String username; //用户名
    private String nickname; //昵称
}
