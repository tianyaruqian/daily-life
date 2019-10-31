package com.hou.springboot.api.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hou.springboot.api.product.po.Product;
import org.apache.ibatis.annotations.Param;

public interface IProductMapper extends BaseMapper<Product> {
    Long updatePro(@Param("productId") Long productId, @Param("count") Long count);
}
