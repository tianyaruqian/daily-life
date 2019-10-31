package com.fh.shop.api.address.biz;

import com.fh.shop.api.address.po.Address;
import com.fh.shop.api.common.ServerResult;

public interface IAddressService {

    public ServerResult findAddress();

    public ServerResult insertAddress(Address address,Long memberVoId);


    public ServerResult deleteAddress(Long id);


    public ServerResult findById(Long id);

    public ServerResult updateAddres(Address address);


}
