package com.fh.shop.api.brand.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.param.BrandParam;

import java.util.List;

public interface IBrandMapper extends BaseMapper<Brand> {
    Long findBrandCount(BrandParam brandParam);

    List<Brand> findPageList(BrandParam brandParam);
}
