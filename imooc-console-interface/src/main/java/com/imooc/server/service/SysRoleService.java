package com.imooc.server.service;

import com.imooc.server.model.bo.SysRole;
import com.imooc.server.model.dto.SysUserDTO;
import com.imooc.server.model.dto.SysUserRoleDTO;
import com.imooc.server.model.vo.SysRoleVO;
import java.util.HashMap;
import java.util.List;

public interface SysRoleService {

    /**
     * @Description: 分页查询
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    HashMap<String, Object> getListPage(SysRoleVO sysRoleVO);

    /**
     * @Description: 根据ID查询
     * @param: [username]
     * @return: com.imooc.security.server.entity.SysUser
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    SysRole queryById(Integer id);

    /**
     * @Description: 新增
     * @param: SysUser
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    boolean saveOrUpdate(SysRole sysRole,String loginUsername);

    /**
     * @Description: 根据id删除
     * @param: [id]
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    boolean delete(Integer id);

    //根据角色ID获取(用户角色表数据)
    List<SysUserRoleDTO> queryUserRoleByRoleId(Integer id);

    //根据角色ID获取(没有授权用户表数据)
    List<SysUserRoleDTO> queryNoAuthUserByRoleId(Integer id);

    //角色授权用户
    boolean roleToUser(SysRoleVO sysRoleVO);
}