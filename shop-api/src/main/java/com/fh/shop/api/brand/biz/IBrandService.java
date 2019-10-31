package com.fh.shop.api.brand.biz;

import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.param.BrandParam;

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
