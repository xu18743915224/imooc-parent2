package com.imooc.server.controller;

import com.imooc.server.common.BaseResponse;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.model.bo.SysPermission;
import com.imooc.server.model.dto.SysRoleDTO;
import com.imooc.server.model.dto.SysRolePermissionDTO;
import com.imooc.server.model.vo.SysPermissionVO;
import com.imooc.server.service.SysPermission1Service;
import com.imooc.server.service.SysPermissionService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/permission1")
@Slf4j
public class SysPermission1Controller {

    @Autowired
    SysPermission1Service sysPermission1Service;

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
        HashMap<String, Object> listPage = sysPermission1Service.getListPage(sysPermissionVO);
        return listPage;
    }


}
