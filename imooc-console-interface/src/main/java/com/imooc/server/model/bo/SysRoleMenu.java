package com.imooc.server.model.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 角色菜单表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
@TableName("SYS_ROLE_MENU")
public class SysRoleMenu implements Serializable {
    @TableId(value = "ID" )
    private Integer id;  //
    private Integer roleId;  //角色ID
    private Integer menuId;  //菜单ID
}
