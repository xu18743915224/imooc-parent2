package com.imooc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.server.model.bo.SysRolePermission;
import com.imooc.server.model.dto.SysRoleDTO;
import com.imooc.server.model.dto.SysRolePermissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * @Description: 根据权限ID获取(角色权限表数据)
     * @param: [query]
     * @return: java.util.List<com.imooc.server.model.dto.SysRolePermissionDTO>
     * @Author: xwl
     * @Date: 2020-6-23 14:01
     */
    List<SysRolePermissionDTO> queryRolePermissByPermissId(@Param("query")SysRolePermissionDTO query);

    /**
     * @Description: 根据权限ID获取(角色未授权数据)
     * @param: [query]
     * @return: java.util.List<com.imooc.server.model.dto.SysRoleDTO>
     * @Author: xwl
     * @Date: 2020-6-23 18:09
     */
    List<SysRoleDTO> queryNoAuthRoleByPermissId(@Param("query")SysRoleDTO query);
}
