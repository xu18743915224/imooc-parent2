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


    /**
     * @Description: 新增
     * @param: SysUser
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    boolean saveOrUpdate(SysPermission sysPermission,String loginUsername);

    /**
     * @Description: 根据id删除
     * @param: [id]
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    boolean delete(Integer id);

    //根据ID查询权限树列表
    List<SysPermissionVO> getGridListById(Integer id);
}