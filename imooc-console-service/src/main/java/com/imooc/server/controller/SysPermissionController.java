package com.imooc.server.controller;

import com.imooc.server.common.BaseResponse;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.model.bo.SysPermission;
import com.imooc.server.model.dto.SysPermissionDTO;
import com.imooc.server.model.dto.SysRoleDTO;
import com.imooc.server.model.dto.SysRolePermissionDTO;
import com.imooc.server.model.vo.SysPermissionVO;
import com.imooc.server.service.SysPermissionService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/permission")
@Slf4j
public class SysPermissionController {

    @Autowired
    SysPermissionService sysPermissionService;

    /**
     * @Description: 分页查询
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    @RequestMapping(value = "/getListPage")
    HashMap<String, Object> getListPage(HttpServletRequest request) {
        SysPermissionVO sysPermissionVO = new SysPermissionVO();
        if(StringUtils.isNotBlank(request.getParameter("type"))){
            sysPermissionVO.setType(Integer.valueOf(request.getParameter("type")));
        }
        sysPermissionVO.setName(request.getParameter("name"));
        sysPermissionVO.setUri(request.getParameter("uri"));
        sysPermissionVO.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")));
        sysPermissionVO.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        sysPermissionVO.setSortField(request.getParameter("sortField"));
        sysPermissionVO.setSortOrder(request.getParameter("sortOrder"));
        HashMap<String, Object> listPage = sysPermissionService.getListPage(sysPermissionVO);
        return listPage;
    }

    ;

    /**
     * @Description: 根据用户名查询用户
     * @param: [username]
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    @RequestMapping(value = "/queryById/{id}")
    SysPermission queryById(@PathVariable("id") Integer id) {
        return sysPermissionService.queryById(id);
    }

    /**
     * @Description: 新增
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody SysPermissionVO sysPermissionVO,HttpServletRequest request) throws CommonServiceException {
        // 校验入参
        sysPermissionVO.checkParam();
        //获取Claims
        Claims claims = (Claims)request.getAttribute("user_claims");
        String loginUsername=claims.getSubject();//获取登录用户username
        //赋值
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(sysPermissionVO, sysPermission);

        boolean bool = sysPermissionService.saveOrUpdate(sysPermission,loginUsername);
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
        boolean bool = sysPermissionService.delete(id);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "删除失败！!"));
    }

    /**
     * @Description: 权限授权角色
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/permissionToRole")
    public BaseResponse permissionToRole(@RequestBody SysPermissionVO sysPermissionVO, HttpServletRequest request) throws CommonServiceException {
        // 校验入参
        sysPermissionVO.checkParam2();

        boolean bool = sysPermissionService.permissionToRole(sysPermissionVO);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "保存失败！!"));
    }

    /**
     * @Description: 根据权限ID获取(角色权限表数据)
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/queryRolePermissByPermissId/{id}")
    public BaseResponse queryRolePermissByPermissId(@PathVariable("id") Integer id) throws CommonServiceException {
        // 校验入参
        if (id==null) {
            throw new CommonServiceException(404, "出现异常,权限ID不能为空!");
        }
        List<SysRolePermissionDTO> list = sysPermissionService.queryRolePermissByPermissId(id);
        return BaseResponse.success(list);
    }

/**
 * @Description: 根据权限ID获取(角色权限表数据)
 * @return: boolean
 * @Author: XWL
 * @Date: 2020年05月29日
 */
    @RequestMapping(value = "/queryNoAuthRoleByPermissId/{id}")
    public BaseResponse queryNoAuthRoleByPermissId(@PathVariable("id") Integer id) throws CommonServiceException {
        // 校验入参
        if (id==null) {
            throw new CommonServiceException(404, "出现异常,权限ID不能为空!");
        }
        List<SysRoleDTO> list = sysPermissionService.queryNoAuthRoleByPermissId(id);
        return BaseResponse.success(list);
    }
}
