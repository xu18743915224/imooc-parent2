package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.mapper.SysMenuMapper;
import com.imooc.server.model.bo.SysMenu;
import com.imooc.server.model.vo.SysMenuVO;
import com.imooc.server.service.SysMenuService;
import com.imooc.server.util.ColumnFieldUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Transactional
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;


    @Override
    public HashMap<String, Object> getListPage(SysMenuVO sysMenuVO) {
        IPage<SysMenu> ipage = new Page<SysMenu>(sysMenuVO.getPageIndex(), sysMenuVO.getPageSize());
        //组装查询数据
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<SysMenu>();
        if (StringUtils.isNotBlank(sysMenuVO.getText())) {
            wrapper.like("text", sysMenuVO.getText());
        }
        if (sysMenuVO.getStatus()!=null) {
            wrapper.eq("status", sysMenuVO.getStatus());
        }
        if (StringUtils.isNotBlank(sysMenuVO.getSortField()) && StringUtils.isNotBlank(sysMenuVO.getSortOrder())) {
            String column = ColumnFieldUtil.propertyToField(sysMenuVO.getSortField());
            if ("asc".equals(sysMenuVO.getSortOrder())) {
                wrapper.orderByAsc(column);//正序
            } else if ("desc".equals(sysMenuVO.getSortOrder())) {
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

    @Override
    public boolean saveOrUpdate(SysMenu sysMenu, String loginUsername) {
        if (sysMenu.getId() == null) {
            sysMenu.setCreateUser(loginUsername);
        }
        sysMenu.setUpdateUser(loginUsername);
        if (sysMenu.getId() == null) {
            sysMenu.setCreateTime(new Date());
            sysMenu.setUpdateTime(new Date());
            return this.insert(sysMenu);
        }
        sysMenu.setUpdateTime(new Date());
        return this.updateById(sysMenu);
    }

    @Override
    public boolean delete(Integer id) {
        return this.deleteById(id);
    }
}