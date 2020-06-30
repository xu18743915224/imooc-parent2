package com.imooc.server.service;

import com.imooc.server.model.bo.SysMenu;
import com.imooc.server.model.vo.SysMenuVO;
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
}