package com.imooc.server.service;


import com.imooc.server.model.bo.SysDictData;
import com.imooc.server.model.vo.SysDictDataVO;

import java.util.HashMap;
import java.util.List;

public interface SysDictDataService {

    //保存or更新
    boolean saveOrUpdate(SysDictData sysDictData);

    //根据id删除
    boolean delete(Integer id);

    //获取分页pageList
    HashMap<String, Object> getListPage(SysDictDataVO sysDictDataVO);

    //根据ID查询dictData
    SysDictData queryById(Integer id);

    //根据dictType查询dictData
    List<SysDictData> getSysDictDataByType(String dictType);
}