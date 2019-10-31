package com.hou.springboot.api.brand.hou.member.controller;

import com.hou.springboot.api.brand.hou.common.Check;
import com.hou.springboot.api.brand.hou.common.ServerResult;
import com.hou.springboot.api.brand.hou.member.biz.IMemberService;
import com.hou.springboot.api.brand.hou.member.po.Member;
import com.hou.springboot.api.brand.hou.member.vo.MemberVo;
import com.hou.springboot.api.brand.hou.utils.KeyUtil;
import com.hou.springboot.api.brand.hou.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/members")
public class MemberApi {
    @Resource(name="memberService")
    private IMemberService memberService;
    @Autowired
    private HttpServletRequest request;


    @PostMapping
    public ServerResult registerMember(Member member , String code) {
        return memberService.registerMember(member,code);
    }

    @GetMapping
    public ServerResult findByName(String name){

        return memberService.findByName(name);
    }

    @GetMapping(value="phone")
    public ServerResult findByPhone(String phoneNumber){

        return memberService.findByPhone(phoneNumber);
    }

    @GetMapping(value = "email")
    public ServerResult findByEmail(String email){

        return memberService.findByEmail(email);
    }

    @GetMapping("login")

    public ServerResult findMemberByName(Member member){

        return memberService.findMemberByName(member);
    }

    @PostMapping("memberInfo")
    @Check
    public ServerResult findMemberInfo(){
        MemberVo member = (MemberVo)request.getAttribute("member");
        return ServerResult.success(member.getRealName());
    }

    //退出
    @PostMapping("loginOut")
    @Check
    public ServerResult loginOut(){
        MemberVo member = (MemberVo)request.getAttribute("member");
        Long mid = member.getId();
        String uid = member.getUid();
        String memberName = member.getMemberName();
        RedisUtil.delete(KeyUtil.buildMember(mid,memberName,uid));
        return ServerResult.success();
    }

}
