package com.imooc.server.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 角色菜单表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysRoleMenuVO implements Serializable {

    private Integer id;  //
    private Integer roleId;  //角色ID
    private Integer menuId;  //菜单ID

}
