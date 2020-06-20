package com.imooc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.server.model.bo.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 权限表——Mapper
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * @Description: 条件查询权限表集合
     * @param: SysPermission
     * @return: List<SysPermission>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysPermission> querySysPermissionList(SysPermission sysPermission);

}
