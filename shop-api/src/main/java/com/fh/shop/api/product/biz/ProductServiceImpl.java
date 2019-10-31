package com.fh.shop.api.product.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.product.mapper.IProductMapper;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.product.vo.ProductVo;
import com.fh.shop.api.utils.DateUtil;
import com.fh.shop.api.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements IProductService {
    @Resource
    private IProductMapper productMapper;

    /**
     * 查询
     * @return
     */
    public ServerResult findProductList(){
        String jsonProductList = RedisUtil.get("productList");
        if(StringUtils.isNotEmpty(jsonProductList)){
            //json字符串转java
            List<Product> products = JSONObject.parseArray(jsonProductList, Product.class);
            return ServerResult.success(products);
        }
        //redis缓存没有
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.eq("valid",1);
        List<Product> productList = productMapper.selectList(queryWrapper);
        List<ProductVo> voList= buildProductVo(productList);
        //java对象转化为json字符串，存入缓存中
        jsonProductList = JSONObject.toJSONString(voList);
        RedisUtil.set("productList",jsonProductList);
        return ServerResult.success(voList);
    }

    /**
     * po转vo
     * @param productList
     * @return
     */
    private List<ProductVo> buildProductVo(List<Product> productList){
        List<ProductVo> productVoList = new ArrayList<>();

        for (Product product : productList) {
            ProductVo productVo = new ProductVo();
            productVo.setPrice(product.getPrice());
            productVo.setId(product.getId());
            productVo.setImage(product.getImage());
            productVo.setProductName(product.getProductName());
            productVo.setProductTime(DateUtil.datetoStr(product.getProductTime(),DateUtil.Y_M_D));
            productVoList.add(productVo);
        }
        return productVoList;
    }
    
}
