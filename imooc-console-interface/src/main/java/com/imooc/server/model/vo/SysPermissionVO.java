package com.imooc.server.model.vo;

import com.imooc.server.common.BaseRequest;
import com.imooc.server.exception.CommonServiceException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description: 权限表
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
public class SysPermissionVO extends BaseRequest implements Serializable {

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
    private List<SysRoleVO> roleList;

    //分页信息
    private int pageSize = 20;
    private int pageIndex = 0;
    private String sortField;
    private String sortOrder;

    @Override
    public void checkParam() throws CommonServiceException {
        if (StringUtils.isEmpty(name)) {
            throw new CommonServiceException(404, "权限名称不能为空!");
        }
        if (type==null) {
            throw new CommonServiceException(404, "权限类型不能为空!");
        }
    }

    public void checkParam2() throws CommonServiceException {
        if (id==null) {
            throw new CommonServiceException(404, "出现异常,权限ID不能为空!");
        }
    }
}
