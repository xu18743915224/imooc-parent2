package com.imooc.server.model.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("SYS_ROLE")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1590733820502L;

    @TableId(value = "ID")
    private Integer id;  //
    private String roleName;  //角色名称
    private String roleCode;  //
    private String roleDescription;  //角色描述
    private String createUser;  //
    private Date createTime;  //
    private String updateUser;  //
    private Date updateTime;  //
}
