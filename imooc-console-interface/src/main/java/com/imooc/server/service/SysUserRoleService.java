package com.imooc.server.service;

import com.imooc.server.model.bo.SysUserRole;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin
@RequestMapping("/sysUserRoleService")
public interface SysUserRoleService {

    /**
     * @Description: 分页查询用户角色表
     * @param: [pages, size]
     * @return: List<SysUserRole>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysUserRole> getSysUserRoleListPage(Integer pageNum, Integer pageSize);

    /**
     * @Description: 条件查询用户角色表集合
     * @param: SysUserRole
     * @return: List<SysUserRole>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysUserRole> querySysUserRoleList(SysUserRole sysUserRole);

    /**
     * @Description: 新增用户角色表
     * @param: SysUserRole
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    boolean save(SysUserRole sysUserRole);
}