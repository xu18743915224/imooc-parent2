package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.mapper.SysRoleMapper;
import com.imooc.server.mapper.SysUserRoleMapper;
import com.imooc.server.model.bo.SysRole;
import com.imooc.server.model.bo.SysUserRole;
import com.imooc.server.model.dto.SysUserDTO;
import com.imooc.server.model.dto.SysUserRoleDTO;
import com.imooc.server.model.vo.SysRoleVO;
import com.imooc.server.model.vo.SysUserRoleVO;
import com.imooc.server.model.vo.SysUserVO;
import com.imooc.server.service.SysRoleService;
import com.imooc.server.util.ColumnFieldUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
@ResponseBody
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public HashMap<String, Object> getListPage(SysRoleVO sysRoleVO) {
        IPage<SysRole> ipage = new Page<SysRole>(sysRoleVO.getPageIndex(), sysRoleVO.getPageSize());
        //组装查询数据
        QueryWrapper<SysRole> wrapper = new QueryWrapper<SysRole>();
        if (StringUtils.isNotBlank(sysRoleVO.getRoleName())) {
            wrapper.like("username", sysRoleVO.getRoleName());
        }
        if (StringUtils.isNotBlank(sysRoleVO.getSortField()) && StringUtils.isNotBlank(sysRoleVO.getSortOrder())) {
            String column = ColumnFieldUtil.propertyToField(sysRoleVO.getSortField());
            if ("asc".equals(sysRoleVO.getSortOrder())) {
                wrapper.orderByAsc(column);//正序
            } else if ("desc".equals(sysRoleVO.getSortOrder())) {
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
    public SysRole queryById(Integer id) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        return this.selectOne(wrapper);
    }

    @Override
    public boolean saveOrUpdate(SysRole sysRole,String loginUsername) {
        if (sysRole.getId() == null) {
            sysRole.setCreateUser(loginUsername);
        }
        sysRole.setUpdateUser(loginUsername);
        if (sysRole.getId() == null) {
            sysRole.setCreateTime(new Date());
            sysRole.setUpdateTime(new Date());
            return this.insert(sysRole);
        }
        sysRole.setUpdateTime(new Date());
        return this.updateById(sysRole);
    }

    @Override
    public boolean delete(Integer id) {
        //根据权限ID删除所有(角色权限表)数据
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper();
        wrapper.eq("role_id", id);
        List<SysUserRole> list = sysUserRoleMapper.selectList(wrapper);
        if(list!=null&&list.size()>0){
            for(SysUserRole obj:list){
                sysUserRoleMapper.deleteById(obj.getId());
            }
        }
        //删除角色
        return this.deleteById(id);
    }

    @Override
    public List<SysUserRoleDTO> queryUserRoleByRoleId(Integer id) {
        SysUserRoleDTO query=new SysUserRoleDTO();
        query.setRoleId(id);
        List<SysUserRoleDTO> list=sysUserRoleMapper.queryUserRoleByRoleId(query);
        return list;
    }

    @Override
    public List<SysUserRoleDTO> queryNoAuthUserByRoleId(Integer id) {
        SysUserDTO query=new SysUserDTO();
        query.setRoleId(id);
        List<SysUserRoleDTO> list=sysUserRoleMapper.queryNoAuthUserByRoleId(query);
        return list;
    }

    @Override
    public boolean roleToUser(SysUserRoleVO sysUserRoleVO) {
        //删除原有
        QueryWrapper<SysUserRole> perQueryWrapper = new QueryWrapper();
        perQueryWrapper.eq("role_id", sysUserRoleVO.getRoleId());
        List<SysUserRole> list = sysUserRoleMapper.selectList(perQueryWrapper);
        if(list!=null&&list.size()>0){
            for(SysUserRole userRole:list){
                sysUserRoleMapper.deleteById(userRole.getId());
            }
        }
        //新增现有
        if(sysUserRoleVO.getUserList()!=null&&sysUserRoleVO.getUserList().size()>0){
            for(SysUserRoleVO userRole:sysUserRoleVO.getUserList()){
                SysUserRole sysUserRole=new SysUserRole();
                sysUserRole.setRoleId(sysUserRoleVO.getRoleId());
                sysUserRole.setUserId(userRole.getUserId());
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
        return true;
    }
}