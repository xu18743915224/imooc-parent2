package com.imooc.server.model.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("SYS_PERMISSION")
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 1590733820502L;

    @TableId(value = "ID")
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
