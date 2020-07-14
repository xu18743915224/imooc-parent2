package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.exception.CommonServiceException;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
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
        wrapper.eq("pid", "0");
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

    @Override
    public boolean saveOrUpdate(SysPermission sysPermission, String loginUsername) {
        if (sysPermission.getId() == null) {
            sysPermission.setCreateUser(loginUsername);
        }
        sysPermission.setUpdateUser(loginUsername);
        if (sysPermission.getId() == null) {
            sysPermission.setCreateTime(new Date());
            sysPermission.setUpdateTime(new Date());
            return this.insert(sysPermission);
        }
        sysPermission.setUpdateTime(new Date());
        return this.updateById(sysPermission);
    }

    @Override
    public boolean delete(Integer id) {
        //根据权限ID删除所有(角色权限表)数据
        QueryWrapper<SysRolePermission> perQueryWrapper = new QueryWrapper();
        perQueryWrapper.eq("permission_id", id);
        List<SysRolePermission> rolePermissionList = sysRolePermissionMapper.selectList(perQueryWrapper);
        if(rolePermissionList!=null&&rolePermissionList.size()>0){
            for(SysRolePermission rolePermission:rolePermissionList){
                sysRolePermissionMapper.deleteById(rolePermission.getId());
            }
        }
        //删除权限
        return this.deleteById(id);
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~权限表格grid树start
    @Override
    public List<SysPermissionVO> getGridListById(Integer id) {
        List<SysPermissionVO> result = new ArrayList<>();
        List<SysPermissionVO> list=getListById(id);
        if(list!=null&&list.size()>0){
            result=loopPermission(result,list);
        }

        return result;
    }

    private List<SysPermissionVO> loopPermission(List<SysPermissionVO> result, List<SysPermissionVO> list) {

        for(SysPermissionVO per:list){
            result.add(per);
            if(per.getChildren().isEmpty()) {
                continue;
            }
            loopPermission(result,per.getChildren());
        }
        return result;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~权限表格grid树end
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~权限start
    public List<SysPermissionVO> getListById(Integer id) {
        //组装查询数据
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        List<SysPermission> permissionList = sysPermissionMapper.selectList(wrapper);
        List<SysPermissionVO> permissions = getPermissionListById(id, permissionList);
        permissions = loopSysPermission(permissions,permissionList);
        return permissions;
    }

    /**
     * @param id 权限的id
     * @param permissionList 数据库查询到的所有权限列表，未整理的 "0"从根节点查询
     */
    private List<SysPermissionVO> getPermissionListById(Integer id,List<SysPermission> permissionList){
        List<SysPermissionVO> children = new ArrayList<>();
        for (SysPermission sysPermission : permissionList) {
            if(id==sysPermission.getPid()) {
                SysPermissionVO sysPermissionVO= new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                // temp 用于封装各种属性
                children.add(sysPermissionVO);
            }
        }
        return children;
    }
    /**
     * @param permissionVOs  某一级权限的列表
     * @param permissionList 数据库查询到的所有权限列表，未整理的
     */
    private List<SysPermissionVO> loopSysPermission(List<SysPermissionVO> permissionVOs, List<SysPermission> permissionList) {

        for (SysPermissionVO permissionVO : permissionVOs) {
            // 循环获取某个权限的子权限并set到属性里边
            permissionVO.setChildren(this.getPermissionListById(permissionVO.getId(),permissionList));
            // 如果某次返回的子权限是空的，说明下边不在有子权限了 所以跳出本次循环
            if(permissionVO.getChildren().isEmpty()) {
                continue;
            }
            loopSysPermission(permissionVO.getChildren(),permissionList);
        }
        return permissionVOs;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~权限end

    @Override
    public List<SysPermissionVO> queryPermissionToRoleByRoleId(Integer id) {
        List<SysPermissionVO> permissionVOs=new ArrayList<>();
        QueryWrapper<SysPermission> wrapper1 = new QueryWrapper<>();
        List<SysPermission> permissionList = sysPermissionMapper.selectList(wrapper1);

        QueryWrapper<SysRolePermission> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("role_id",id);
        List<SysRolePermission> rolePermissionList = sysRolePermissionMapper.selectList(wrapper2);
        if(permissionList!=null&&permissionList.size()>0){
            for(SysPermission permission:permissionList){
                //赋值
                SysPermissionVO permissionVO=new SysPermissionVO();
                BeanUtils.copyProperties(permission,permissionVO);
                if(rolePermissionList!=null&&rolePermissionList.size()>0){
                    for(SysRolePermission rolePermission:rolePermissionList){
                        if(permission.getId().equals(rolePermission.getPermissionId())){
                            permissionVO.setChecked(true);
                        }
                    }
                }
                permissionVOs.add(permissionVO);
            }
        }
        return permissionVOs;
    }

    @Override
    public boolean permission1_savePermissionToRole(SysRoleVO sysRoleVO) {
        if(sysRoleVO.getId()==null||sysRoleVO.getPermissionId()==null){
            new CommonServiceException(500, "保存失败！角色ID或权限ID不能为空!");
        }
        QueryWrapper<SysPermission> wrapper1 = new QueryWrapper<>();
        List<SysPermission> permissionList = sysPermissionMapper.selectList(wrapper1);

        List<Integer> idList=new ArrayList<>();
        idList.add(sysRoleVO.getPermissionId());

        //根据选中节点向下看是否有子节点如果有全部选中
        loopDownEach(sysRoleVO.getPermissionId(),idList,permissionList);

        //判断是选中框赋权 还是取消框赋权(取消赋权不需要关联向上查询父)
        if("true".equals(sysRoleVO.getFlag())){
            //根据选中节点向上看是否有父节点如果有全部选中(如果父节点存在既不操作)
            QueryWrapper<SysPermission> wrapper2 = new QueryWrapper<>();
            SysPermission permission = sysPermissionMapper.selectById(sysRoleVO.getPermissionId());
            loopUpEach(permission.getPid(),idList,permissionList);
        }

        //根据role获取数据库所已经拥有的权限
        QueryWrapper<SysRolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",sysRoleVO.getId());
        List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectList(wrapper);
        if(sysRolePermissions!=null&&sysRolePermissions.size()>0 ) {
            List<Integer> deleteIdList=new ArrayList<>();
            for(int i=0;i<idList.size();i++){
                Integer permissionId=idList.get(i);
                for (SysRolePermission sysRolePermission : sysRolePermissions) {
                    //取消赋权
                    if("false".equals(sysRoleVO.getFlag())){
                        if(permissionId==sysRolePermission.getPermissionId()){
                            sysRolePermissionMapper.deleteById(sysRolePermission.getId());
                        }
                    }
                    if("true".equals(sysRoleVO.getFlag())){
                        if (permissionId == sysRolePermission.getPermissionId()) {
                            deleteIdList.add(permissionId);
                        }
                    }
                }
            }
            //选中赋权
            if(deleteIdList!=null&&deleteIdList.size()>0){
                for(Integer i:deleteIdList){
                    idList.remove(i);
                }
            }
            if("true".equals(sysRoleVO.getFlag())){
                for (Integer permissionId : idList) {
                    SysRolePermission rolePermission=new SysRolePermission();
                    rolePermission.setRoleId(sysRoleVO.getId());
                    rolePermission.setPermissionId(permissionId);
                    sysRolePermissionMapper.insert(rolePermission);
                }
            }
            //没有曾经赋值过权限新增
        }else{
            for(Integer permissionId:idList){
                //选中赋权
                if("true".equals(sysRoleVO.getFlag())){
                    SysRolePermission rolePermission=new SysRolePermission();
                    rolePermission.setRoleId(sysRoleVO.getId());
                    rolePermission.setPermissionId(permissionId);
                    sysRolePermissionMapper.insert(rolePermission);
                }
            }
        }
        return true;
    }

    private void loopDownEach(Integer permissionId,List<Integer> idList,List<SysPermission> lists){
        for(SysPermission permission:lists){
            if(permission.getPid().equals(permissionId)){
                idList.add(permission.getId());
            }else{
                continue;
            }
            loopDownEach(permission.getId(),idList,lists);
        }
    }

    private void loopUpEach(Integer pid,List<Integer> idList,List<SysPermission> lists){
        for(SysPermission permission:lists){
            if(permission.getId().equals(pid)){
                idList.add(permission.getId());
            }else{
                continue;
            }
            loopUpEach(permission.getPid(),idList,lists);
        }
    }
}