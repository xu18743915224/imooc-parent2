package com.imooc.server.service;

import com.imooc.server.model.bo.SysMenu;
import com.imooc.server.model.vo.SysMenuVO;
import java.util.HashMap;


public interface SysMenuService {

    //分页查询
    HashMap<String, Object> getListPage(SysMenuVO sysMenuVO);

    //新增_编辑菜单
    boolean saveOrUpdate(SysMenu sysMenu, String loginUsername);

    //根据id删除
    boolean delete(Integer id);

}