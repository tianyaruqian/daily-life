package com.fh.shop.api.type.biz;


import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.type.po.Type;

import java.util.List;

public interface ITypeService {
    ServerResult findList(Long id);

    List<Type> findAllList();

    ServerResult addType(Type type);

    ServerResult updateType(Type type);

    ServerResult deleteType(List<Integer> ids);
}
