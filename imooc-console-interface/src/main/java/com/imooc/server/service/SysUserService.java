package com.imooc.server.service;

import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.model.bo.SysUser;
import com.imooc.server.model.vo.SysUserVO;
import java.util.HashMap;
import java.util.List;


public interface SysUserService {

    /**
     * @Description: 登录
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */

    /**
     * @Description: 分页查询
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    HashMap<String, Object> getListPage(SysUserVO sysUserVO);

    /**
     * @Description: 条件查询用户表集合
     * @param: SysUser
     * @return: List<SysUser>
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    public List<SysUser> querySysUserList(SysUser sysUser);

    /**
     * @Description: 新增用户表
     * @param: SysUser
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    boolean saveOrUpdate(SysUser sysUser, String loginUsername);

    /**
     * @Description: 根据id删除用户
     * @param: [username]
     * @return: com.imooc.security.server.entity.SysUser
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    boolean delete(Integer id);

    /**
     * @Description: 根据用户名查询用户
     * @param: [username]
     * @return: com.imooc.security.server.entity.SysUser
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    SysUser getByUsername(String username);

    /**
     * @Description: 根据用户名查询用户
     * @param: [username]
     * @return: com.imooc.security.server.entity.SysUser
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    SysUser queryById(Integer id);

    //验证用户名和密码是否正确
    SysUser checkUserLogin(String username, String password) throws CommonServiceException;
}