package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.mapper.SysUserRoleMapper;
import com.imooc.server.model.bo.SysUserRole;
import com.imooc.server.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 用户角色表——SERVICEIMPL
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Transactional
@Service
@ResponseBody
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public boolean save(SysUserRole sysUserRole) {
        return this.insert(sysUserRole);
    }

    @Override
    public List<SysUserRole> getSysUserRoleListPage(Integer pageNum, Integer pageSize) {
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper();
        Page<SysUserRole> ipage = new Page<>(pageNum, pageSize);
        IPage<SysUserRole> SysUserRoleIPage = this.selectPage(ipage, wrapper);
        System.out.println("总页数" + SysUserRoleIPage.getPages());
        System.out.println("总记录数" + SysUserRoleIPage.getTotal());
        List<SysUserRole> SysUserRoles = SysUserRoleIPage.getRecords();
        return SysUserRoles;
    }

    @Override
    public List<SysUserRole> querySysUserRoleList(SysUserRole sysUserRole) {
        return sysUserRoleMapper.querySysUserRoleList(sysUserRole);
    }
}