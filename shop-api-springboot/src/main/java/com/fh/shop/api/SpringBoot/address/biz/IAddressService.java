package com.hou.springboot.api.brand.hou.address.biz;

import com.hou.springboot.api.brand.hou.address.po.Address;
import com.hou.springboot.api.brand.hou.common.ServerResult;

public interface IAddressService {

    public ServerResult findAddress();

    public ServerResult insertAddress(Address address, Long memberVoId);


    public ServerResult deleteAddress(Long id);


    public ServerResult findById(Long id);

    public ServerResult updateAddres(Address address);


}
