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
@RequestMapping("/sysPermissionService")
public interface SysPermissionService {

    /**
     * @Description: 分页查询
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    HashMap<String, Object> getListPage(SysPermissionVO sysPermissionVO);

    /**
     * @Description: 根据ID查询
     * @param: [username]
     * @return: com.imooc.security.server.entity.SysUser
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    SysPermission queryById(Integer id);

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


    /**
     * @Description: 根据userId查询所有权限
     * @param: []
     * @return: java.util.List<com.imooc.security.server.entity.SysPermission>
     * @Author: xwl
     * @Date: 2020-5-29 15:08
     */
    List<SysPermission> findByUserId(Integer userId);

    /**
     * @Description: 权限授权角色
     * @param: [sysPermissionVO]
     * @return: boolean
     * @Author: xwl
     * @Date: 2020-6-23 13:20
     */
    boolean permissionToRole(SysPermissionVO sysPermissionVO);

    /**
     * @Description: 根据权限ID获取(角色权限表数据)
     * @param: [id]
     * @return: java.util.List<com.imooc.server.model.dto.SysRolePermissionDTO>
     * @Author: xwl
     * @Date: 2020-6-23 13:51
     */
    List<SysRolePermissionDTO> queryRolePermissByPermissId(Integer id);

    /**
     * @Description: 根据权限ID获取(角色未授权数据)
     * @param: [id]
     * @return: java.util.List<com.imooc.server.model.dto.SysRoleDTO>
     * @Author: xwl
     * @Date: 2020-6-23 18:03
     */
    List<SysRoleDTO> queryNoAuthRoleByPermissId(Integer id);
}