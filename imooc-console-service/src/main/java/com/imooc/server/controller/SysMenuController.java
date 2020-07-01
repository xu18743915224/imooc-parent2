package com.imooc.server.controller;

import com.imooc.server.common.BaseResponse;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.model.bo.SysMenu;
import com.imooc.server.model.bo.SysUser;
import com.imooc.server.model.vo.SysMenuVO;
import com.imooc.server.model.vo.SysRoleVO;
import com.imooc.server.model.vo.SysUserVO;
import com.imooc.server.service.SysMenuService;
import com.imooc.server.service.SysUserService;
import com.imooc.server.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
@Slf4j
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;


    /**
     * @Description: 分页查询
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    @RequestMapping(value = "/getListPage")
    HashMap<String, Object> getListPage(HttpServletRequest request) {
        SysMenuVO sysMenuVO = new SysMenuVO();
        sysMenuVO.setText(request.getParameter("text"));
        sysMenuVO.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")));
        sysMenuVO.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        sysMenuVO.setSortField(request.getParameter("sortField"));
        sysMenuVO.setSortOrder(request.getParameter("sortOrder"));
        HashMap<String, Object> listPage = sysMenuService.getListPage(sysMenuVO);
        return listPage;
    }

    /**
     * @Description: 根据ID查询子列表
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    @RequestMapping(value = "/getGridListById/{id}")
    List<SysMenuVO> getListById(@PathVariable("id") Integer id){
        List<SysMenuVO> list=sysMenuService.getGridListById(id);
        return list;
    }

    /**
     * @Description: 根据ID查询子列表
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    @RequestMapping(value = "/getTreeById/{id}")
    List<SysMenuVO> getTreeById(@PathVariable("id") Integer id){
        List<SysMenuVO> list=sysMenuService.getTreeById(id);
        return list;
    }

    /**
     * @Description: 新增
     * @param: SysUser
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody SysMenuVO sysMenuVO,HttpServletRequest request) throws CommonServiceException {
        //校验入参
        sysMenuVO.checkParam();
        //获取Claims
        Claims claims = (Claims)request.getAttribute("user_claims");
        String loginUsername=claims.getSubject();//获取登录用户username
        //赋值
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuVO, sysMenu);
        boolean bool = sysMenuService.saveOrUpdate(sysMenu,loginUsername);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "保存失败！!"));
    }

    /**
     * @Description: 删除
     * @param: SysMenu
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/delete/{id}")
    public BaseResponse delete(@PathVariable("id") Integer id) throws CommonServiceException {
        boolean bool = sysMenuService.delete(id);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "删除失败！!"));
    }

    /**
     * @Description: 根据Id查询对象
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    @RequestMapping(value = "/queryById/{id}")
    SysMenu queryById(@PathVariable("id") Integer id) {
        return sysMenuService.queryById(id);
    }

    /**
     * @Description: 菜单角色授权
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/menuToRole")
    public BaseResponse menuToRole(@RequestBody SysRoleVO sysRoleVO) throws CommonServiceException {
        //校验入参
        sysRoleVO.menuToRoleCheckParam();

        boolean bool = sysMenuService.menuToRole(sysRoleVO);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "保存失败！!"));
    }

    /**
     * @Description: 根据角色ID查询角色菜单表数据
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    @RequestMapping(value = "/queryMenuToRoleByRoleId/{id}")
    List<SysMenuVO> queryMenuToRoleByRoleId(@PathVariable("id") Integer id){
        List<SysMenuVO> list=sysMenuService.queryMenuToRoleByRoleId(id);
        return list;
    }

    /**
     * @Description: 根据用户ID查询用户所拥有的菜单列表
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    @RequestMapping(value = "/getIndexMenuTreeByUserId")
    List<SysMenuVO> getIndexMenuTreeByUserId(HttpServletRequest request){
        //获取Claims
        Claims claims = (Claims)request.getAttribute("user_claims");
        Integer userId = (Integer) claims.get("id");
        List<SysMenuVO> list=sysMenuService.getIndexMenuTreeByUserId(userId);
        return list;
    }



}
