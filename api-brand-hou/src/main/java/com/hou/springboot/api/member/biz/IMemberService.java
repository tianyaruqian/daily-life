package com.hou.springboot.api.member.biz;


import com.hou.springboot.api.common.ServerResult;
import com.hou.springboot.api.member.po.Member;

public interface IMemberService  {

    ServerResult registerMember(Member member, String code);

    ServerResult findByName(String name);

    ServerResult findByPhone(String phoneNumber);

    ServerResult findByEmail(String email);

    ServerResult findMemberByName(Member member);
}
