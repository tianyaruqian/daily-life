package com.hou.springboot.api.type.biz;


import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.type.po.Type;

import java.util.List;

public interface ITypeService {
    ServerResult findList(Long id);

    List<Type> findAllList();

    ServerResult addType(Type type);

    ServerResult updateType(Type type);

    ServerResult deleteType(List<Integer> ids);
}
