package com.imooc.server.service;


import com.imooc.server.model.bo.SysDictType;
import com.imooc.server.model.vo.SysDictTypeVO;

import java.util.HashMap;

public interface SysDictTypeService {

    //保存or更新
    boolean saveOrUpdate(SysDictType sysDictType);

    //根据id删除
    boolean deleteType(Integer id);

    //获取分页pageList
    HashMap<String, Object> getListPage(SysDictTypeVO sysDictTypeVO);

    //根据ID查询dictType
    SysDictType queryById(Integer id);
}