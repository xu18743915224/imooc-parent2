package com.imooc.server.controller;

import com.imooc.server.common.BaseResponse;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.model.bo.SysDictData;
import com.imooc.server.model.bo.SysDictType;
import com.imooc.server.model.vo.SysDictDataVO;
import com.imooc.server.model.vo.SysDictTypeVO;
import com.imooc.server.service.SysDictDataService;
import com.imooc.server.service.SysDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dict")
@Slf4j
public class SysDictController {

    @Autowired
    SysDictDataService sysDictDataService;
    @Autowired
    SysDictTypeService sysDictTypeService;

    /**
     * @Description: 分页查询类型
     * @param: [request]
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @Author: xwl
     * @Date: 2020-6-4 16:58
     */
    @RequestMapping(value = "/getListPageType")
    HashMap<String, Object> getListPageType(HttpServletRequest request) {
        SysDictTypeVO sysDictTypeVO = new SysDictTypeVO();
        sysDictTypeVO.setDictType(request.getParameter("dictType"));
        sysDictTypeVO.setTypeName(request.getParameter("typeName"));
        sysDictTypeVO.setIsValid(request.getParameter("isValid"));
        sysDictTypeVO.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")));
        sysDictTypeVO.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        sysDictTypeVO.setSortField(request.getParameter("sortField"));
        sysDictTypeVO.setSortOrder(request.getParameter("sortOrder"));
        HashMap<String, Object> listPage = sysDictTypeService.getListPage(sysDictTypeVO);
        return listPage;
    }

    //根据字典类型type获取字典数据分页list
    @RequestMapping(value = "/getListPageData")
    HashMap<String, Object> getListPageData(HttpServletRequest request) {
        SysDictDataVO sysDictDataVO = new SysDictDataVO();
        sysDictDataVO.setDictType(request.getParameter("dictType"));
        sysDictDataVO.setDictCode(request.getParameter("dictCode"));
        sysDictDataVO.setDictName(request.getParameter("dictName"));
        sysDictDataVO.setIsValid(request.getParameter("isValid"));
        sysDictDataVO.setPageIndex(Integer.parseInt(request.getParameter("pageIndex")));
        sysDictDataVO.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        sysDictDataVO.setSortField(request.getParameter("sortField"));
        sysDictDataVO.setSortOrder(request.getParameter("sortOrder"));
        HashMap<String, Object> listPage = sysDictDataService.getListPage(sysDictDataVO);
        return listPage;
    }

    //新增or保存字典类型
    @RequestMapping(value = "/saveOrUpdateType")
    public BaseResponse saveOrUpdateType(@RequestBody SysDictTypeVO sysDictTypeVO,HttpServletRequest request) throws CommonServiceException {
        //校验入参
        sysDictTypeVO.checkParam();

        //赋值
        SysDictType sysDictType = new SysDictType();
        BeanUtils.copyProperties(sysDictTypeVO, sysDictType);
        boolean bool = sysDictTypeService.saveOrUpdate(sysDictType);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "保存失败！!"));
    }

    //新增or保存字典数据
    @RequestMapping(value = "/saveOrUpdateData")
    public BaseResponse saveOrUpdateData(@RequestBody SysDictDataVO sysDictDataVO,HttpServletRequest request) throws CommonServiceException {
        //校验入参
        sysDictDataVO.checkParam();

        //赋值
        SysDictData sysDictData = new SysDictData();
        BeanUtils.copyProperties(sysDictDataVO, sysDictData);
        boolean bool = sysDictDataService.saveOrUpdate(sysDictData);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "保存失败！!"));
    }

    //删除字典类型 关联删除旗下所有字典数据
    @RequestMapping(value = "/deleteType/{id}")
    public BaseResponse delete(@PathVariable("id") Integer id) throws CommonServiceException {
        boolean bool = sysDictTypeService.deleteType(id);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "删除失败！!"));
    }

    //根据id删除字典数据
    @RequestMapping(value = "/deleteData/{id}")
    public BaseResponse deleteData(@PathVariable("id") Integer id) throws CommonServiceException {
        boolean bool = sysDictDataService.delete(id);
        if (bool) {
            return BaseResponse.success();
        }
        return BaseResponse.serviceException(new CommonServiceException(500, "删除失败！!"));
    }

    //根据ID查询dictType
    @RequestMapping(value = "/queryDictTypeById/{id}")
    SysDictType queryDictTypeById(@PathVariable("id") Integer id) {
        return sysDictTypeService.queryById(id);
    }

    //根据ID查询dictData
    @RequestMapping(value = "/queryDictDataById/{id}")
    SysDictData queryDictDataById(@PathVariable("id") Integer id) {
        return sysDictDataService.queryById(id);
    }

    //根据字典类型type获取字典数据分页list
    @RequestMapping(value = "/getSysDictDataByType/{dictType}")
    List<SysDictData> getSysDictDataByType(@PathVariable("dictType") String dictType) {
        List<SysDictData> bySysDictData = sysDictDataService.getSysDictDataByType(dictType);
        return bySysDictData;
    }
}
