package com.imooc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.server.model.bo.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 用户表——Mapper
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * @Description: 条件查询用户表集合
     * @param: SysUser
     * @return: List<SysUser>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysUser> querySysUserList(SysUser sysUser);
}
