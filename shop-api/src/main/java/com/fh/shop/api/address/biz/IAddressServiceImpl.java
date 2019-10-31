package com.fh.shop.api.address.biz;

import com.fh.shop.api.address.mapper.IAddressMapper;
import com.fh.shop.api.address.po.Address;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.member.vo.MemberVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("addressService")
public class IAddressServiceImpl implements IAddressService {
    @Resource
    private IAddressMapper addressMapper;

    @Override
    public ServerResult findAddress() {
        return null;
    }

    @Override
    public ServerResult insertAddress(Address address,Long memberVoId) {
        address.setMemberId(memberVoId);
        addressMapper.insert(address);
        return ServerResult.success();
    }

    @Override
    public ServerResult deleteAddress(Long id) {
        addressMapper.deleteById(id);
        return ServerResult.success();
    }

    @Override
    public ServerResult findById(Long id) {
        Address address = addressMapper.selectById(id);
        return ServerResult.success(address);
    }

    @Override
    public ServerResult updateAddres(Address address) {
        addressMapper.updateById(address);
        return ServerResult.success();
    }
}
