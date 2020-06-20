package com.imooc.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.server.mapper.SysDictDataMapper;
import com.imooc.server.mapper.SysDictTypeMapper;
import com.imooc.server.model.bo.SysDictData;
import com.imooc.server.model.bo.SysDictType;
import com.imooc.server.model.vo.SysDictTypeVO;
import com.imooc.server.service.SysDictTypeService;
import com.imooc.server.util.ColumnFieldUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Transactional
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {

    @Autowired
    SysDictTypeMapper sysDictTypeMapper;
    @Autowired
    SysDictDataMapper sysDictDataMapper;

    @Override
    public boolean saveOrUpdate(SysDictType sysDictType) {
        if (sysDictType.getId() == null) {
            sysDictType.setCreateTime(new Date());
            sysDictType.setUpdateTime(new Date());
            return this.insert(sysDictType);
        }
        sysDictType.setUpdateTime(new Date());
        return this.updateById(sysDictType);
    }

    @Override
    public boolean deleteType(Integer id) {
        SysDictType sysDictType = sysDictTypeMapper.selectById(id);
        //关联循环删除旗下所有字典数据
        QueryWrapper<SysDictData> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type",sysDictType.getDictType());
        List<SysDictData> sysDictDatas = sysDictDataMapper.selectList(wrapper);
        if(sysDictDatas!=null&&sysDictDatas.size()>0){
            for(SysDictData data:sysDictDatas){
                sysDictDataMapper.deleteById(data.getId());
            }
        }
        return this.deleteById(id);
    }

    @Override
    public HashMap<String, Object> getListPage(SysDictTypeVO sysDictTypeVO) {
        IPage<SysDictType> ipage = new Page<>(sysDictTypeVO.getPageIndex(), sysDictTypeVO.getPageSize());
        //组装查询数据
        QueryWrapper<SysDictType> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(sysDictTypeVO.getDictType())){
            wrapper.eq("dict_type",sysDictTypeVO.getDictType());
        }
        if(StringUtils.isNotBlank(sysDictTypeVO.getTypeName())){
            wrapper.like("type_name",sysDictTypeVO.getTypeName());
        }
        if(StringUtils.isNotBlank(sysDictTypeVO.getIsValid())){
            wrapper.eq("is_valid",sysDictTypeVO.getIsValid());
        }

        if (StringUtils.isNotBlank(sysDictTypeVO.getSortField()) && StringUtils.isNotBlank(sysDictTypeVO.getSortOrder())) {
            String column = ColumnFieldUtil.propertyToField(sysDictTypeVO.getSortField());
            if ("asc".equals(sysDictTypeVO.getSortOrder())) {
                wrapper.orderByAsc(column);//正序
            } else if ("desc".equals(sysDictTypeVO.getSortOrder())) {
                wrapper.orderByDesc(column);//倒序
            }
        }else{
            wrapper.orderByDesc("id");
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
    public SysDictType queryById(Integer id) {
        QueryWrapper<SysDictType> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        SysDictType sysDictType = this.selectOne(wrapper);
        return sysDictType;
    }

}