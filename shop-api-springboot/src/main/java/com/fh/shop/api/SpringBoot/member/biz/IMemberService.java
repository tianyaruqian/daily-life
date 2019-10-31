package com.hou.springboot.api.brand.hou.member.biz;


import com.hou.springboot.api.brand.hou.common.ServerResult;
import com.hou.springboot.api.brand.hou.member.po.Member;

public interface IMemberService  {

    ServerResult registerMember(Member member, String code);

    ServerResult findByName(String name);

    ServerResult findByPhone(String phoneNumber);

    ServerResult findByEmail(String email);

    ServerResult findMemberByName(Member member);
}
