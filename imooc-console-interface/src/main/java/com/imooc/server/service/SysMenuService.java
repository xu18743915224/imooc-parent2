package com.imooc.server.service;

import com.imooc.server.model.bo.SysMenu;
import com.imooc.server.model.vo.SysMenuVO;
import com.imooc.server.model.vo.SysRoleVO;

import java.util.HashMap;
import java.util.List;


public interface SysMenuService {

    //分页查询
    HashMap<String, Object> getListPage(SysMenuVO sysMenuVO);

    //新增_编辑菜单
    boolean saveOrUpdate(SysMenu sysMenu, String loginUsername);

    //根据id删除
    boolean delete(Integer id);

    //根据Id查询对象
    SysMenu queryById(Integer id);

    //根据ID查询菜单树列表
    List<SysMenuVO> getGridListById(Integer id);

    //根据ID查询菜单树
    List<SysMenuVO> getTreeById(Integer id);

    //菜单角色授权
    boolean menuToRole(SysRoleVO sysRoleVO);

    //根据角色ID查询角色菜单表数据
    List<SysMenuVO> queryMenuToRoleByRoleId(Integer id);

    //根据用户ID查询用户所拥有的菜单列表
    List<SysMenuVO> getIndexMenuTreeByUserId(Integer userId);
}