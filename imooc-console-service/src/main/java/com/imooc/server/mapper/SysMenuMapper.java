package com.imooc.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.server.model.bo.SysMenu;
import com.imooc.server.model.dto.SysUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> getMenuByUserId(@Param("query")SysUserDTO query);
}
