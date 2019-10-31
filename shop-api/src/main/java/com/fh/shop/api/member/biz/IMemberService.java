package com.fh.shop.api.member.biz;

import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.member.po.Member;

public interface IMemberService  {

    ServerResult registerMember(Member member,String code);

    ServerResult findByName(String name);

    ServerResult findByPhone(String phoneNumber);

    ServerResult findByEmail(String email);

    ServerResult findMemberByName(Member member);
}
