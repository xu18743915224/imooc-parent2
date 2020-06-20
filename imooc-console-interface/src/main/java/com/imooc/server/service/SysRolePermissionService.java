package com.imooc.server.service;

import com.imooc.server.model.bo.SysRolePermission;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@RequestMapping("/sysRolePermissionService")
public interface SysRolePermissionService {

    /**
     * @Description: 分页查询角色权限表
     * @param: [pages, size]
     * @return: List<SysRolePermission>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysRolePermission> getSysRolePermissionListPage(Integer pageNum, Integer pageSize);

    /**
     * @Description: 条件查询角色权限表集合
     * @param: SysRolePermission
     * @return: List<SysRolePermission>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysRolePermission> querySysRolePermissionList(SysRolePermission sysRolePermission);

    /**
     * @Description: 新增角色权限表
     * @param: SysRolePermission
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    boolean save(SysRolePermission sysRolePermission);
}