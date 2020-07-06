package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.mapper.SysPermissionMapper;
import com.imooc.server.mapper.SysRolePermissionMapper;
import com.imooc.server.mapper.SysUserRoleMapper;
import com.imooc.server.model.bo.SysPermission;
import com.imooc.server.model.bo.SysRolePermission;
import com.imooc.server.model.bo.SysUserRole;
import com.imooc.server.model.dto.SysRoleDTO;
import com.imooc.server.model.dto.SysRolePermissionDTO;
import com.imooc.server.model.vo.SysPermissionVO;
import com.imooc.server.model.vo.SysRoleVO;
import com.imooc.server.service.SysPermission1Service;
import com.imooc.server.util.ColumnFieldUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 权限表——SERVICEIMPL
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Transactional
@Service
@ResponseBody
public class SysPermission1ServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermission1Service {

    @Autowired
    SysPermissionMapper sysPermissionMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public HashMap<String, Object> getListPage(SysPermissionVO sysPermissionVO) {
        IPage<SysPermission> ipage = new Page<SysPermission>(sysPermissionVO.getPageIndex(), sysPermissionVO.getPageSize());
        //组装查询数据
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<SysPermission>();
        if (sysPermissionVO.getType()!=null) {
            wrapper.eq("type", sysPermissionVO.getType());
        }
        if (StringUtils.isNotBlank(sysPermissionVO.getName())) {
            wrapper.like("name", sysPermissionVO.getName());
        }
        if (StringUtils.isNotBlank(sysPermissionVO.getUri())) {
            wrapper.like("uri", sysPermissionVO.getUri());
        }
        if (StringUtils.isNotBlank(sysPermissionVO.getSortField()) && StringUtils.isNotBlank(sysPermissionVO.getSortOrder())) {
            String column = ColumnFieldUtil.propertyToField(sysPermissionVO.getSortField());
            if ("asc".equals(sysPermissionVO.getSortOrder())) {
                wrapper.orderByAsc(column);//正序
            } else if ("desc".equals(sysPermissionVO.getSortOrder())) {
                wrapper.orderByDesc(column);//倒序
            }
        }

        //数据库数据查询
        ipage = baseMapper.selectPage(ipage, wrapper);//super.page(page1, wrapper);
        HashMap<String, Object> result = new HashMap();
        result.put("data", ipage.getRecords());
        result.put("total", ipage.getRecords().size());
        // 返回grid需要的数据
        return result;
    }


}