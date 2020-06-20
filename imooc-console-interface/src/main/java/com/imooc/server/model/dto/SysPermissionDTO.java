package com.imooc.server.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 权限表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysPermissionDTO implements Serializable {

    private Integer id;  //
    private Integer pid;  //父ID
    private Integer type;  //资源类型（1：菜单，2：按钮，3：操作）
    private String name;  //资源名称
    private String code;  //资源标识（或者叫权限字符串）
    private String uri;  //资源URI
    private Integer seq;  //序号
    private String createUser;  //
    private Date createTime;  //
    private String updateUser;  //
    private Date updateTime;  //
}
