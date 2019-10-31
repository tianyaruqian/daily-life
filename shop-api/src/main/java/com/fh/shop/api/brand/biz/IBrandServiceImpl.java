package com.fh.shop.api.brand.biz;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.brand.mapper.IBrandMapper;
import com.fh.shop.api.brand.po.Brand;
import com.fh.shop.api.common.DataTable;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.param.BrandParam;
import com.fh.shop.api.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("brandService")
public class IBrandServiceImpl implements IBrandService {
    @Resource
    private IBrandMapper brandMapper;

    /**
     * 查询
     */

    public ServerResult queryList(){
        String jsonHotBrand = RedisUtil.get("HotBrand");
        if(StringUtils.isNotEmpty(jsonHotBrand)){
            //json字符串转为java对象
            List<Brand> HotBrandList = JSONObject.parseArray(jsonHotBrand, Brand.class);
            return ServerResult.success(HotBrandList);
        }
        //缓存没有
        QueryWrapper<Brand> brandQueryWrapper = new QueryWrapper<>();
        brandQueryWrapper.eq("status",1);
        brandQueryWrapper.orderByAsc("vieworder");
        List<Brand> brands = brandMapper.selectList(brandQueryWrapper);
        //先存缓存，java对象转换为json字符串
        jsonHotBrand = JSONObject.toJSONString(brands);
        RedisUtil.set("HotBrand",jsonHotBrand);
        return ServerResult.success(brands);
    }

    @Override
    public ServerResult findAll() {
        List<Brand> brands = brandMapper.selectList(null);
        return ServerResult.success(brands);
    }

    @Override
    public ServerResult addBrand(Brand brand) {
        brandMapper.insert(brand);
        return ServerResult.success();
    }

    @Override
    public ServerResult deleteBrand(Long id) {
        brandMapper.deleteById(id);
        return ServerResult.success();
    }

    @Override
    public ServerResult selectBrand(Long id) {
        Brand brand = brandMapper.selectById(id);
        if(brand==null){
            return ServerResult.error(ResponseEnum.BRAND_IS_NULL);
        }
        return ServerResult.success(brand);
    }

    @Override
    public ServerResult updateBrand(Brand brand) {
        brandMapper.updateById(brand);
        return ServerResult.success();
    }

    @Override
    public ServerResult deleteBatch(List<Integer> ids) {
        brandMapper.deleteBatchIds(ids);
        return ServerResult.success();
    }

    @Override
    public ServerResult findBrandPageList(BrandParam brandParam) {
        //总条数
        Long Count = brandMapper.findBrandCount(brandParam);
        //分页列表
        List<Brand> brandList = brandMapper.findPageList(brandParam);
        DataTable dataTable = new DataTable(brandParam.getDraw(),Count,Count,brandList);
        return ServerResult.success(dataTable);
    }





}
