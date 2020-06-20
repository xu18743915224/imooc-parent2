package com.imooc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.server.model.bo.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

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

}
