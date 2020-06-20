package com.imooc.server.model.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
@TableName("SYS_USER")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1590733820502L;

    @TableId(value = "ID")
    private Integer id;  //
    private String username;  //账号
    private String password;  //密码
    private String nickname;  //昵称
    private String email;  //邮箱
    private Integer status;  //状态（0：锁定，1：解锁）
    private String createUser;  //
    private Date createTime;  //
    private String updateUser;  //
    private Date updateTime;  //
}
