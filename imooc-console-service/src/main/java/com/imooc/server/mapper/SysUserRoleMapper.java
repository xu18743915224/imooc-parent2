package com.imooc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.server.model.bo.SysUserRole;
import com.imooc.server.model.dto.SysUserDTO;
import com.imooc.server.model.dto.SysUserRoleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 用户角色表——Mapper
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * @Description: 条件查询用户角色表集合
     * @param: SysUserRole
     * @return: List<SysUserRole>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    List<SysUserRole> querySysUserRoleList(SysUserRole sysUserRole);

    //根据角色ID获取(用户角色表数据)
    List<SysUserRoleDTO> queryUserRoleByRoleId(@Param("query")SysUserRoleDTO query);

    //根据角色ID获取(用户未授权数据)
    List<SysUserRoleDTO> queryNoAuthUserByRoleId(@Param("query")SysUserDTO query);
}
