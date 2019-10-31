package com.hou.springboot.api.type.biz;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.type.mapper.ITypeMapper;
import com.hou.springboot.api.type.po.Type;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("typeService")
public class ITypeServiceImpl implements ITypeService {
    @Resource
    private ITypeMapper typeMapper;

    @Override
    public ServerResult findList(Long id) {
        QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.eq("parentId",id);
        List<Type> types = typeMapper.selectList(typeQueryWrapper);
        return ServerResult.success(types);
    }

    @Override
    public List<Type> findAllList() {
        List<Type> typeList= typeMapper.selectList(null);

        return  typeList;
    }

    @Override
    public ServerResult addType(Type type) {
        typeMapper.insert(type);
        return ServerResult.success();
    }

    @Override
    public ServerResult updateType(Type type) {
        typeMapper.updateById(type);
        return ServerResult.success();
    }

    @Override
    public ServerResult deleteType(List<Integer> ids) {
        typeMapper.deleteBatchIds(ids);
        return ServerResult.success();
    }


}
