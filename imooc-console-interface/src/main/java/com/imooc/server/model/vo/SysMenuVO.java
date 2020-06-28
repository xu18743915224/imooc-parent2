package com.imooc.server.model.vo;

import com.imooc.server.common.BaseRequest;
import com.imooc.server.exception.CommonServiceException;
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
public class SysMenuVO extends BaseRequest implements Serializable {
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

    //分页信息
    private int pageSize = 20;
    private int pageIndex = 0;
    private String sortField;
    private String sortOrder;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
