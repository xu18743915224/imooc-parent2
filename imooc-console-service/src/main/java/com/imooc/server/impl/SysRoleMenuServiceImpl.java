package com.imooc.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.mapper.SysRoleMenuMapper;
import com.imooc.server.model.bo.SysRoleMenu;
import com.imooc.server.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 角色菜单
 * @Author: XWL
 * @CreateDate: 2020年05月29日
 * @Version: V1.0
 */
@Transactional
@Service
@ResponseBody
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {


}