package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.exception.CommonServiceException;
import com.imooc.server.mapper.SysDictDataMapper;
import com.imooc.server.mapper.SysUserMapper;
import com.imooc.server.model.bo.SysDictData;
import com.imooc.server.model.bo.SysUser;
import com.imooc.server.model.vo.SysDictDataVO;
import com.imooc.server.model.vo.SysUserVO;
import com.imooc.server.service.SysDictDataService;
import com.imooc.server.service.SysUserService;
import com.imooc.server.util.ColumnFieldUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {

    @Autowired
    SysDictDataMapper sysDictDataMapper;

    @Override
    public boolean saveOrUpdate(SysDictData sysDictData) {
        if (sysDictData.getId() == null) {
            sysDictData.setCreateTime(new Date());
            sysDictData.setUpdateTime(new Date());
            return this.insert(sysDictData);
        }
        sysDictData.setUpdateTime(new Date());
        return this.updateById(sysDictData);
    }

    @Override
    public boolean delete(Integer id) {
        return this.deleteById(id);
    }

    @Override
    public HashMap<String, Object> getListPage(SysDictDataVO sysDictDataVO) {
        IPage<SysDictData> ipage = new Page<>(sysDictDataVO.getPageIndex(), sysDictDataVO.getPageSize());
        //根据type类型获取
        QueryWrapper<SysDictData> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(sysDictDataVO.getDictType())){
            wrapper.eq("dict_type",sysDictDataVO.getDictType());
        }
        if(StringUtils.isNotBlank(sysDictDataVO.getDictCode())){
            wrapper.eq("dict_code",sysDictDataVO.getDictCode());
        }
        if(StringUtils.isNotBlank(sysDictDataVO.getDictName())){
            wrapper.like("dict_name",sysDictDataVO.getDictName());
        }
        if(StringUtils.isNotBlank(sysDictDataVO.getIsValid())){
            wrapper.eq("is_valid",sysDictDataVO.getIsValid());
        }

        if (StringUtils.isNotBlank(sysDictDataVO.getSortField()) && StringUtils.isNotBlank(sysDictDataVO.getSortOrder())) {
            String column = ColumnFieldUtil.propertyToField(sysDictDataVO.getSortField());
            if ("asc".equals(sysDictDataVO.getSortOrder())) {
                wrapper.orderByAsc(column);//正序
            } else if ("desc".equals(sysDictDataVO.getSortOrder())) {
                wrapper.orderByDesc(column);//倒序
            }
        }

        //数据库数据查询
        ipage = baseMapper.selectPage(ipage, wrapper);//super.page(page1, wrapper);
        HashMap<String, Object> result = new HashMap();
        result.put("data", ipage.getRecords());
        result.put("total", ipage.getRecords().size());
        // 返回grid需要的数据
        return result;
    }

    @Override
    public SysDictData queryById(Integer id) {
        QueryWrapper<SysDictData> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        SysDictData sysDictData = this.selectOne(wrapper);
        return sysDictData;
    }

    @Override
    public List<SysDictData> getSysDictDataByType(String dictType) {
        QueryWrapper<SysDictData> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(dictType)) {
            wrapper.eq("dict_type", dictType);
            wrapper.eq("is_valid", '1');
        }
        wrapper.orderByAsc("no");
        List<SysDictData> list = baseMapper.selectList(wrapper);
        return list;
    }


}