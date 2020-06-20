package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.mapper.SysRolePermissionMapper;
import com.imooc.server.model.bo.SysRolePermission;
import com.imooc.server.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 角色权限表——SERVICEIMPL
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Transactional
@Service
@ResponseBody
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public boolean save(SysRolePermission sysRolePermission) {
        return this.insert(sysRolePermission);
    }

    @Override
    public List<SysRolePermission> getSysRolePermissionListPage(Integer pageNum, Integer pageSize) {
        QueryWrapper<SysRolePermission> wrapper = new QueryWrapper();
        Page<SysRolePermission> ipage = new Page<>(pageNum, pageSize);
        IPage<SysRolePermission> SysRolePermissionIPage = this.selectPage(ipage, wrapper);
        System.out.println("总页数" + SysRolePermissionIPage.getPages());
        System.out.println("总记录数" + SysRolePermissionIPage.getTotal());
        List<SysRolePermission> SysRolePermissions = SysRolePermissionIPage.getRecords();
        return SysRolePermissions;
    }

    @Override
    public List<SysRolePermission> querySysRolePermissionList(SysRolePermission sysRolePermission) {
        return sysRolePermissionMapper.querySysRolePermissionList(sysRolePermission);
    }
}