package com.imooc.server.controller;

import com.imooc.server.common.BaseResponse;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.model.bo.SysUser;
import com.imooc.server.model.vo.SysUserVO;
import com.imooc.server.service.SysUserService;
import com.imooc.server.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponse login(@RequestBody SysUserVO sysUserVO) throws CommonServiceException {
        //参数校验
        sysUserVO.checkParam();
        //验证用户名和密码是否正确
        SysUser sysUser = sysUserService.checkUserLogin(sysUserVO.getUsername(),sysUserVO.getPassword());
        //登录成功，签发token
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        Map<String, Object> map = new HashMap<>(16);
        map.put("id", sysUser.getId());
        map.put("username", sysUser.getUsername());
        map.put("nickname", sysUser.getNickname());
        //获取角色apis
        String apis=sysUserService.getPermissionByUserId(sysUser.getId());
        map.put("apis",apis);
        String token = jwtTokenUtil.createJwt(sysUser.getId()+"",sysUser.getUsername(),map);
        return BaseResponse.success(token);
    }

    /**
     * @Description: 分页查询
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    @RequestMapping(value = "/getListPage")
    HashMap<String, Object> getListPage(HttpServletRequest request) {
        SysUserVO sysUserVO = new SysUserVO();
        sysUserVO.setUsername(request.getParameter("username"));
        sysUserVO.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")));
        sysUserVO.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        sysUserVO.setSortField(request.getParameter("sortField"));
        sysUserVO.setSortOrder(request.getParameter("sortOrder"));
        HashMap<String, Object> listPage = sysUserService.getListPage(sysUserVO);
        return listPage;
    }

    /**
     * @Description: 新增用户表
     * @param: SysUser
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/saveOrUpdate")
    public BaseResponse saveOrUpdate(@RequestBody SysUserVO sysUserVO,HttpServletRequest request) throws CommonServiceException {
        //校验入参
        sysUserVO.checkParam();
        //获取Claims
        Claims claims = (Claims)request.getAttribute("user_claims");
        String loginUsername=claims.getSubject();//获取登录用户username
        //赋值
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVO, sysUser);
        boolean bool = sysUserService.saveOrUpdate(sysUser,loginUsername);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "保存失败！!"));
    }

    /**
     * @Description: 新增用户表
     * @param: SysUser
     * @return: boolean
     * @Author: XWL
     * @Date: 2020年05月29日
     */
    @RequestMapping(value = "/delete/{id}")
    public BaseResponse delete(@PathVariable("id") Integer id) throws CommonServiceException {
        boolean bool = sysUserService.delete(id);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "删除失败！!"));
    }

    /**
     * @Description: 根据用户名查询用户
     * @param: [username]
     * @return: com.imooc.security.server.entity.SysUser
     * @Author: xwl
     * @Date: 2020-5-29 15:05
     */
    @RequestMapping(value = "/queryById/{id}")
    SysUser queryById(@PathVariable("id") Integer id) {
        return sysUserService.queryById(id);
    }
}
