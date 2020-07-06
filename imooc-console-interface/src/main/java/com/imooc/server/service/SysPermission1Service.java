package com.imooc.server.service;

import com.imooc.server.model.bo.SysPermission;
import com.imooc.server.model.dto.SysRoleDTO;
import com.imooc.server.model.dto.SysRolePermissionDTO;
import com.imooc.server.model.vo.SysPermissionVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RequestMapping("/sysPermission1Service")
public interface SysPermission1Service {

    /**
     * @Description: 分页查询
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    HashMap<String, Object> getListPage(SysPermissionVO sysPermissionVO);


}