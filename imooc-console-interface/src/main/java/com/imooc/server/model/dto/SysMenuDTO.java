package com.imooc.server.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 菜单表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysMenuDTO implements Serializable {
    private Integer id;          //菜单ID
    private Integer parentId;    //菜单父ID
    private String iconCls;     //菜单图标
    private String text;        //菜单名
    private String url;         //菜单url
    private Integer status;     //状态（0：无效，1：有效）
    private Integer no;			//顺序
    private String createUser;  //
    private Date createTime;    //
    private String updateUser;  //
    private Date updateTime;    //
}
