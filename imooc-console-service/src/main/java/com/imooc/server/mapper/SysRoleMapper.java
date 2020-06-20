package com.imooc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.server.model.bo.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 角色表——Mapper
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * @Description: 条件查询角色表集合
     * @param: SysRole
     * @return: List<SysRole>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysRole> querySysRoleList(SysRole sysRole);
}
