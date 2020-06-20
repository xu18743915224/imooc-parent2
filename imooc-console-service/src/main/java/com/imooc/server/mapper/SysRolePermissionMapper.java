package com.imooc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.server.model.bo.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 角色权限表——Mapper
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    /**
     * @Description: 条件查询角色权限表集合
     * @param: SysRolePermission
     * @return: List<SysRolePermission>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysRolePermission> querySysRolePermissionList(SysRolePermission sysRolePermission);

}
