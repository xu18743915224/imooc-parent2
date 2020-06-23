package com.imooc.server.controller;

import com.imooc.server.common.BaseResponse;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.model.bo.SysRole;
import com.imooc.server.model.bo.SysUserRole;
import com.imooc.server.model.dto.SysUserDTO;
import com.imooc.server.model.dto.SysUserRoleDTO;
import com.imooc.server.model.vo.SysRoleVO;
import com.imooc.server.service.SysRoleService;
import com.imooc.server.service.SysUserRoleService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
@Slf4j
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleServicei;

    /**
     * @Description: 分页查询
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    @RequestMapping(value = "/getListPage")
    HashMap<String, Object> getListPage(HttpServletRequest request) {
        SysRoleVO sysRoleVO = new SysRoleVO();
        sysRoleVO.setRoleName(request.getParameter("roleName"));
        sysRoleVO.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")));
        sysRoleVO.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        sysRoleVO.setSortField(request.getParameter("sortField"));
        sysRoleVO.setSortOrder(request.getParameter("sortOrder"));
        HashMap<String, Object> listPage = sysRoleServicei.getListPage(sysRoleVO);
        return listPage;
    }

    ;

    /**
     * @Description: 根据用户名查询用户
     * @param: [username]
     * @return: com.imooc.security.server.entity.SysUser
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    @RequestMapping(value = "/queryById/{id}")
    SysRole queryById(@PathVariable("id") Integer id) {
        return sysRoleServicei.queryById(id);
    }

    /**
     * @Description: 新增
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody SysRoleVO sysRoleVO,HttpServletRequest request) throws CommonServiceException {
        // 校验入参
        sysRoleVO.checkParam();
        //获取Claims
        Claims claims = (Claims)request.getAttribute("user_claims");
        String loginUsername=claims.getSubject();//获取登录用户username
        //赋值
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleVO, sysRole);

        boolean bool = sysRoleServicei.saveOrUpdate(sysRole,loginUsername);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "保存失败！!"));
    }

    /**
     * @Description: 删除
     * @return: BaseResponse
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/delete/{id}")
    public BaseResponse delete(@PathVariable("id") Integer id) throws CommonServiceException {
        boolean bool = sysRoleServicei.delete(id);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "删除失败！!"));
    }

    /**
     * @Description: 角色授权用户
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/roleToUser")
    public BaseResponse roleToUser(@RequestBody SysRoleVO sysRoleVO) throws CommonServiceException {
        // 校验入参
        sysRoleVO.checkParam2();

        boolean bool = sysRoleServicei.roleToUser(sysRoleVO);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "保存失败！!"));
    }

    /**
     * @Description: 根据角色ID获取(用户角色表数据)
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/queryUserRoleByRoleId/{id}")
    public BaseResponse queryUserRoleByRoleId(@PathVariable("id") Integer id) throws CommonServiceException {
        // 校验入参
        if (id==null) {
            throw new CommonServiceException(404, "出现异常,角色ID不能为空!");
        }
        List<SysUserRoleDTO> list = sysRoleServicei.queryUserRoleByRoleId(id);
        return BaseResponse.success(list);
    }

    /**
     * @Description: 根据角色ID获取(没有授权用户表数据)
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/queryNoAuthUserByRoleId/{id}")
    public BaseResponse queryNoAuthUserByRoleId(@PathVariable("id") Integer id) throws CommonServiceException {
        // 校验入参
        if (id==null) {
            throw new CommonServiceException(404, "出现异常,角色ID不能为空!");
        }
        List<SysUserRoleDTO> list = sysRoleServicei.queryNoAuthUserByRoleId(id);
        return BaseResponse.success(list);
    }

}
