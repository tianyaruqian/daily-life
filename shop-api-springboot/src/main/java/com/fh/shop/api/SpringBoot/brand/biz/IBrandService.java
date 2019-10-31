package com.hou.springboot.api.brand.hou.brand.biz;


import com.hou.springboot.api.brand.hou.brand.po.Brand;
import com.hou.springboot.api.brand.hou.common.ServerResult;
import com.hou.springboot.api.brand.hou.param.BrandParam;

import java.util.List;

public interface IBrandService {
    ServerResult queryList();

    ServerResult findAll();

    ServerResult addBrand(Brand brand);

    ServerResult deleteBrand(Long id);

    ServerResult selectBrand(Long id);

    ServerResult updateBrand(Brand brand);

    ServerResult deleteBatch(List<Integer> ids);

    ServerResult findBrandPageList(BrandParam brandParam);
}
