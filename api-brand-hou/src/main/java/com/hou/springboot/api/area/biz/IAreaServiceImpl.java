package com.hou.springboot.api.area.biz;


import com.alibaba.fastjson.JSONObject;
import com.hou.springboot.api.area.mapper.IAreaMapper;
import com.hou.springboot.api.area.po.Area;
import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("areaService")
public class IAreaServiceImpl implements IAreaService {
    @Resource
    private IAreaMapper areaMapper;


    @Override
    public ServerResult findAreaList(Long id) {
        String areaListJson = RedisUtil.get("areaList");
        if(!StringUtils.isEmpty(areaListJson)){
            //如果存在
            List<Area> list = JSONObject.parseArray(areaListJson,Area.class);
            List<Area> childList = findChildList(list,id);
             return ServerResult.success(childList);

        }

        //redis不存在
        List<Area> areaList = areaMapper.selectList(null);
        String areas = JSONObject.toJSONString(areaList);
        RedisUtil.set("areaList",areas);
        List<Area> childList = findChildList(areaList,id);
        return ServerResult.success(childList);
    }



        private List<Area> findChildList(List<Area> list ,Long id){
            List<Area> areaList = new ArrayList<>();
            for (Area area : list) {
                if(area.getParentId().equals(id)){
                    areaList.add(area);
                }
            }
            return areaList;
        }


}
