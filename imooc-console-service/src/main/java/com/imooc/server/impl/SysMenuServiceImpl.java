package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.mapper.SysMenuMapper;
import com.imooc.server.mapper.SysRoleMenuMapper;
import com.imooc.server.model.bo.SysMenu;
import com.imooc.server.model.bo.SysRoleMenu;
import com.imooc.server.model.dto.SysUserDTO;
import com.imooc.server.model.vo.SysMenuVO;
import com.imooc.server.model.vo.SysRoleVO;
import com.imooc.server.service.SysMenuService;
import com.imooc.server.util.ColumnFieldUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Transactional
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public HashMap<String, Object> getListPage(SysMenuVO sysMenuVO) {
        IPage<SysMenu> ipage = new Page<>(sysMenuVO.getPageIndex(), sysMenuVO.getPageSize());
        //组装查询数据
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysMenuVO.getText())) {
            wrapper.like("text", sysMenuVO.getText());
        }
        if (sysMenuVO.getStatus()!=null) {
            wrapper.eq("status", sysMenuVO.getStatus());
        }
        wrapper.eq("parent_id", 0);
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

        //根据id查询所有子菜单循环删除
        List<SysMenuVO> list=getGridListById(id);
        for(SysMenuVO menu:list){
            this.deleteById(menu.getId());
            //删除角色菜单表的数据
            QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper();
            wrapper.eq("menu_id", menu.getId());
            sysRoleMenuMapper.delete(wrapper);
        }
        //根据ID删除当前ID菜单
        boolean b=this.deleteById(id);
        //删除角色菜单表的数据
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper();
        wrapper.eq("menu_id", id);
        sysRoleMenuMapper.delete(wrapper);
        return b;
    }

    @Override
    public SysMenu queryById(Integer id) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        return this.selectOne(wrapper);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~菜单表格start
    @Override
    public List<SysMenuVO> getGridListById(Integer id) {
        List<SysMenuVO> result = new ArrayList<>();
        List<SysMenuVO> list=getListById(id);
        if(list!=null&&list.size()>0){
            result=loopMenu(result,list);
        }

        return result;
    }

    private List<SysMenuVO> loopMenu(List<SysMenuVO> result, List<SysMenuVO> list) {

        for(SysMenuVO menu:list){
            result.add(menu);
            if(menu.getChildren().isEmpty()) {
                continue;
            }
            loopMenu(result,menu.getChildren());
        }
        return result;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~菜单表格end
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~菜单start
    @Override
    public List<SysMenuVO> getTreeById(Integer id) {
        return getListById(id);
    }

    public List<SysMenuVO> getListById(Integer id) {
        //组装查询数据
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        List<SysMenu> menuList = sysMenuMapper.selectList(wrapper);
        List<SysMenuVO> menus = getMenuListById(id, menuList);
        menus = loopSysMenu(menus,menuList);
        return menus;
    }

    /**
     * @param id 菜单的id
     * @param menuList 数据库查询到的所有菜单列表，未整理的
     *         List<SysMenuVO> menus = getMenuListById("0", menuList); 从根节点查询
     *         menus = loopSysMenu(menus,menuList);
     */
    private List<SysMenuVO> getMenuListById(Integer id,List<SysMenu> menuList){
        List<SysMenuVO> children = new ArrayList<>();
        for (SysMenu sysMenu : menuList) {
            if(id==sysMenu.getParentId()) {
                SysMenuVO sysMenuVO= new SysMenuVO();
                BeanUtils.copyProperties(sysMenu, sysMenuVO);
                // temp 用于封装各种属性
                children.add(sysMenuVO);
            }
        }
        return children;
    }
    /**
     * @param menus  某一级菜单的列表
     * @param menuList 数据库查询到的所有菜单列表，未整理的
     */
    private List<SysMenuVO> loopSysMenu(List<SysMenuVO> menus, List<SysMenu> menuList) {

        for (SysMenuVO menu : menus) {
            // 循环获取某个菜单的子菜单并set到属性里边
            menu.setChildren(this.getMenuListById(menu.getId(),menuList));
            // 如果某次返回的子菜单是空的，说明下边不在有子菜单了 所以跳出本次循环
            if(menu.getChildren().isEmpty()) {
                continue;
            }
            loopSysMenu(menu.getChildren(),menuList);
        }
        return menus;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~菜单end

    @Override
    public boolean menuToRole(SysRoleVO sysRoleVO) {
        if(sysRoleVO.getId()==null){
            new CommonServiceException(500, "保存失败！角色ID不能为空!");
        }
        //删除原所有角色菜单
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",sysRoleVO.getId());
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(wrapper);
        if(sysRoleMenus!=null&&sysRoleMenus.size()>0 ){
            for(SysRoleMenu sysRoleMenu:sysRoleMenus){
                sysRoleMenuMapper.deleteById(sysRoleMenu.getId());
            }
        }

        //新增角色菜单
        if(StringUtils.isNotEmpty(sysRoleVO.getMenus())){
            List<String> menus = Arrays.asList(sysRoleVO.getMenus().split(","));
            for(String menuId:menus){
                SysRoleMenu sysRoleMenu=new SysRoleMenu();
                sysRoleMenu.setRoleId(sysRoleVO.getId());
                sysRoleMenu.setMenuId(Integer.valueOf(menuId));
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
        return true;
    }

    @Override
    public List<SysMenuVO> queryMenuToRoleByRoleId(Integer id) {
        List<SysMenuVO> menuVOs=new ArrayList<>();
        QueryWrapper<SysMenu> wrapper1 = new QueryWrapper<>();
        List<SysMenu> menuList = sysMenuMapper.selectList(wrapper1);

        QueryWrapper<SysRoleMenu> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("role_id",id);
        List<SysRoleMenu> roleMenuList = sysRoleMenuMapper.selectList(wrapper2);
        if(menuList!=null&&menuList.size()>0){
            for(SysMenu menu:menuList){
                //赋值
                SysMenuVO menuVO=new SysMenuVO();
                BeanUtils.copyProperties(menu,menuVO);
                if(roleMenuList!=null&&roleMenuList.size()>0){
                    for(SysRoleMenu roleMenu:roleMenuList){
                        if(menu.getId().equals(roleMenu.getMenuId())){
                            menuVO.setChecked(true);
                        }
                    }
                }
                menuVOs.add(menuVO);
            }
        }
        return menuVOs;
    }

    @Override
    public List<SysMenuVO> getIndexMenuTreeByUserId(Integer userId) {
        //根据用户获取拥有的菜单数据
        SysUserDTO sysUserDTO=new SysUserDTO();
        sysUserDTO.setId(userId);
        List<SysMenu> sysMenus=sysMenuMapper.getMenuByUserId(sysUserDTO);
        //组装查询数据返回
        List<SysMenuVO> menus = getMenuListById(0, sysMenus);
        menus = loopSysMenu(menus,sysMenus);
        return menus;
    }
}