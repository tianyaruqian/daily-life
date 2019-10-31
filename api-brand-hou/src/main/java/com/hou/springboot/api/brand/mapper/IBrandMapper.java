package com.hou.springboot.api.brand.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hou.springboot.api.brand.po.Brand;
import com.hou.springboot.api.param.BrandParam;

import java.util.List;

public interface IBrandMapper extends BaseMapper<Brand> {
    Long findBrandCount(BrandParam brandParam);

    List<Brand> findPageList(BrandParam brandParam);
}
