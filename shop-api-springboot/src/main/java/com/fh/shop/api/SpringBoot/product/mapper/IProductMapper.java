package com.hou.springboot.api.brand.hou.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hou.springboot.api.brand.hou.product.po.Product;
import org.apache.ibatis.annotations.Param;

public interface IProductMapper extends BaseMapper<Product> {
    Long updatePro(@Param("productId") Long productId, @Param("count") Long count);
}
