package com.fh.shop.api.cart.biz;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.fh.shop.api.cart.vo.CartItem;
import com.fh.shop.api.cart.vo.CartVo;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.common.SystemConst;
import com.fh.shop.api.product.mapper.IProductMapper;
import com.fh.shop.api.product.po.Product;
import com.fh.shop.api.utils.BigDecimalUtil;
import com.fh.shop.api.utils.KeyUtil;
import com.fh.shop.api.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ICartBizImpl implements ICartBiz {

    @Resource
    private IProductMapper productMapper;

    @Override
    public ServerResult addCart(Long productId, Long count, Long mid) {
        //  商品是否为空
        Product product = productMapper.selectById(productId);
        if(product == null){
            return  ServerResult.error(ResponseEnum.PRODUCT_IS_NULL);
        }
        //  商品是否上下架
        Integer valid = product.getValid();
        if(valid != SystemConst.PRODUCT_VALID){
            return ServerResult.error(ResponseEnum.PRODUCT_IS_DOWN);
        }
        //  购物车是否存在
        String cartJson = RedisUtil.Hget(SystemConst.HASH_REDIS, KeyUtil.buildCartKey(mid));
        //  没有购物车创建购物车
        if(StringUtils.isEmpty(cartJson)){
            // 创建购物车
            CartVo cartVo = new CartVo();
            // 构建商品
            CartItem cartItem = buildCartItem(productId, count, product);
            // 加入购物车
            cartVo.getList().add(cartItem);
            // 更新购物车
            updateCart(cartVo);
            // 放入redis中
            String listJson = JSONObject.toJSONString(cartVo);
            RedisUtil.Hset(SystemConst.HASH_REDIS,KeyUtil.buildCartKey(mid),listJson);
            // 返回数据
            return  ServerResult.success();
        }
        //  购物车是否有商品信息
        CartVo cartVo = JSONObject.parseObject(cartJson, CartVo.class);
        List<CartItem> list = cartVo.getList();
        CartItem cartItem = getProduct(productId, list);
        // 如果商品不存在，则加入购物车中
        if(cartItem == null){
            CartItem cartItem2 = buildCartItem(productId, count, product);
            // 加入购物车
            list.add(cartItem2);
            // 更新购物车
            updateCart(cartVo);
            // 放入redis中
            String listJson = JSONObject.toJSONString(cartVo);
            RedisUtil.Hset(SystemConst.HASH_REDIS,KeyUtil.buildCartKey(mid),listJson);
            // 返回数据
            return  ServerResult.success();
        }
        // 如果商品存在，则更新商品数量及小计
        long count1 = cartItem.getCount() + count;
        if(count1 == 0){
            deleteProductFromCartSum(cartVo,cartItem);
        }
        // 判断购物车是否有商品
        if(cartVo.getList().size() == 0){
            // 删除整个购物车
            RedisUtil.hdel(SystemConst.HASH_REDIS,KeyUtil.buildCartKey(mid));
        }else{
            cartItem.setCount(count1);
            String subTotalPrice = BigDecimalUtil.mul(cartItem.getProductPrice(), cartItem.getCount().toString()).toString();
            cartItem.setSubtotal(subTotalPrice);
            // 更新整个购物车
            updateCart(cartVo);
            // 放入redis中
            String listJson = JSONObject.toJSONString(cartVo);
            RedisUtil.Hset(SystemConst.HASH_REDIS,KeyUtil.buildCartKey(mid),listJson);
        }
        return ServerResult.success();
    }

    @Override
    public ServerResult findCart(Long id) {
        String hget = RedisUtil.Hget(SystemConst.HASH_REDIS, KeyUtil.buildCartKey(id));
        if(StringUtils.isEmpty(hget)){
            return ServerResult.error(ResponseEnum.CART_IS_NULL);
        }
        CartVo cartVo = JSONObject.parseObject(hget, CartVo.class);
        return ServerResult.success(cartVo);
    }

    @Override
    public ServerResult deleteCart(Long userVipVoId, Long productId) {
        //  购物车是否存在
        String cartJson = RedisUtil.Hget(SystemConst.HASH_REDIS, KeyUtil.buildCartKey(userVipVoId));
        if(StringUtils.isEmpty(cartJson)){
            return ServerResult.error(ResponseEnum.CART_IS_NULL);
        }
        //  购物车有商品
        CartVo cartVo = JSONObject.parseObject(cartJson, CartVo.class);
        boolean deleteSuccess = deleteProductFromCart(cartVo, productId);
        if(!deleteSuccess){
            return ServerResult.error(ResponseEnum.PRODUCT_IS_NULL);
        }
        // 判断购物车是否有商品
        if(cartVo.getList().size() == 0){
            // 删除整个购物车
            RedisUtil.hdel(SystemConst.HASH_REDIS,KeyUtil.buildCartKey(userVipVoId));
        }else{
            //  更新购物车
            updateCart(cartVo);
            //  存入reds
            String listJson = JSONObject.toJSONString(cartVo);
            RedisUtil.Hset(SystemConst.HASH_REDIS,KeyUtil.buildCartKey(userVipVoId),listJson);
        }
        return ServerResult.success();
    }



    private void deleteProductFromCartSum(CartVo carVo, CartItem cartItrmVo) {
        List<CartItem> cartItemVoList = carVo.getList();
        for (CartItem itemVo : cartItemVoList) {
            if(itemVo.getId() == cartItrmVo.getId()){
                cartItemVoList.remove(itemVo);
                break;
            }
        }
    }


    private boolean deleteProductFromCart(CartVo cartVo,Long productId){
        boolean deleteScuuess = false;
        List<CartItem> list = cartVo.getList();
        for (CartItem item : list) {
            if(item.getId() == productId){
                list.remove(item);
                deleteScuuess = true;
                break;
            }
        }
        return deleteScuuess;
    }





    private void updateCart(CartVo cartVo) {
        Long totalCount = 0L;
        BigDecimal totalPrice = new BigDecimal(0);
        List<CartItem> list = cartVo.getList();
        for (CartItem item : list) {
            totalCount  += item.getCount();
            String itemPrice = BigDecimalUtil.mul(item.getProductPrice(), item.getCount().toString()).toString();
            totalPrice = BigDecimalUtil.add(totalPrice.toString(),itemPrice);
        }
        cartVo.setTotalCount(totalCount);
        cartVo.setTotalPrice(totalPrice.toString());
    }

    private CartItem buildCartItem(Long productId, Long count, Product product) {
        // 构建商品
        CartItem cartItem = new CartItem();
        // 商品Id
        cartItem.setId(productId);
        // 商品名称
        cartItem.setProductName(product.getProductName());
        // 商品图片
        cartItem.setImage(product.getImage());
        // 商品价格
        String productPrice = product.getPrice().toString();
        cartItem.setProductPrice(productPrice);
        // 商品个数
        cartItem.setCount(count);
        // 小计
        String price = cartItem.getProductPrice();
        String subtotal = BigDecimalUtil.mul(price, String.valueOf(count)).toString();
        cartItem.setSubtotal(subtotal);
        return cartItem;
    }

    private CartItem getProduct(Long productId, List<CartItem> list) {
        CartItem product = null;
        for (CartItem cartItem : list) {
            if(cartItem.getId() == productId){
                product = cartItem;
                break;
            }
        }
        return product;
    }
}
