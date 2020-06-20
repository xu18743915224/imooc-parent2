package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.mapper.SysUserMapper;
import com.imooc.server.model.bo.SysUser;
import com.imooc.server.model.vo.SysUserVO;
import com.imooc.server.service.SysUserService;
import com.imooc.server.util.ColumnFieldUtil;
import com.imooc.server.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 用户表——SERVICEIMPL
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public boolean saveOrUpdate(SysUser sysUser,String loginUsername) {
        if (sysUser.getId() == null) {
            sysUser.setCreateUser(loginUsername);
        }
        sysUser.setUpdateUser(loginUsername);
        if (sysUser.getId() == null) {
            sysUser.setCreateTime(new Date());
            sysUser.setUpdateTime(new Date());
            return this.insert(sysUser);
        }
        sysUser.setUpdateTime(new Date());
        return this.updateById(sysUser);
    }

    @Override
    public boolean delete(Integer id) {

        return this.deleteById(id);
    }

    @Override
    public SysUser getByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        return sysUser;
    }

    @Override
    public SysUser queryById(Integer id) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        return sysUser;
    }

    @Override
    public SysUser checkUserLogin(String username, String password) throws CommonServiceException {
        // 根据用户名获取用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);

        // 避免数据出现问题
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if(sysUser==null){
            throw new CommonServiceException(404, "用户名输入有误");
        }

        if(!password.equals(sysUser.getPassword())){
            throw new CommonServiceException(500,"用户密码输入有误");
        }else{
            return sysUser;
        }
    }


    @Override
    public HashMap<String, Object> getListPage(SysUserVO sysUserVO) {
        IPage<SysUser> ipage = new Page<SysUser>(sysUserVO.getPageIndex(), sysUserVO.getPageSize());
        //组装查询数据
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        if (StringUtils.isNotBlank(sysUserVO.getUsername())) {
            wrapper.like("username", sysUserVO.getUsername());
        }
        //wrapper.eq("status", "1");//有效标志 1解锁
        if (StringUtils.isNotBlank(sysUserVO.getSortField()) && StringUtils.isNotBlank(sysUserVO.getSortOrder())) {
            String column = ColumnFieldUtil.propertyToField(sysUserVO.getSortField());
            if ("asc".equals(sysUserVO.getSortOrder())) {
                wrapper.orderByAsc(column);//正序
            } else if ("desc".equals(sysUserVO.getSortOrder())) {
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
    public List<SysUser> querySysUserList(SysUser sysUser) {
        return sysUserMapper.querySysUserList(sysUser);
    }
}