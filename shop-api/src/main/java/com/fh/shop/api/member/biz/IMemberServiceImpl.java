package com.fh.shop.api.member.biz;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.shop.api.common.ResponseEnum;
import com.fh.shop.api.common.ServerResult;
import com.fh.shop.api.common.SystemConst;
import com.fh.shop.api.member.mapper.IMemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.utils.KeyUtil;
import com.fh.shop.api.utils.Md5Util;
import com.fh.shop.api.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("memberService")
public class IMemberServiceImpl implements IMemberService {
    @Resource
    private IMemberMapper memberMapper;


    @Override
    public ServerResult registerMember(Member member,String code) {
        //非空验证
        String memberName = member.getMemberName();
        String phoneNumber = member.getPhoneNumber();
        String email = member.getEmail();

        if(StringUtils.isEmpty(memberName)){
            return ServerResult.error(ResponseEnum.MEMBER_IS_NULL);
        }

        if(StringUtils.isEmpty(phoneNumber)){
            return ServerResult.error(ResponseEnum.PHONENUMBER_NULL);

        }
        if(StringUtils.isEmpty(email)){
            return ServerResult.error(ResponseEnum.EMAIL_IS_NULL);

        }
        //用户名唯一
        Member memberByName = getMemberByName(memberName);


        if(null!=memberByName){
            return ServerResult.error(ResponseEnum.MEMBERNAME_IS_EXIST);
        }
        //手机号唯一
        Member memberByPhone = getMemberByPhone(phoneNumber);
        if(null!=memberByPhone){
            return ServerResult.error(ResponseEnum.PHONE_IS_EXIST);
        }
        //邮箱唯一
        Member memByEmail = getMemberByEmail(email);
        if(null!=memByEmail){
            return ServerResult.error(ResponseEnum.EMAIL_IS_EXIST);
        }

        //验证码
        String PhoneJson = RedisUtil.get(phoneNumber);
        if(!code.equals(PhoneJson)){
            return ServerResult.error(ResponseEnum.CODE_ERROR);
        }
        //验证码错误
        String codesJson = RedisUtil.get(member.getPhoneNumber());
        if(!codesJson.equals(code)){
            return ServerResult.error(ResponseEnum.CODE_ERROR);
        }
        //密码
        String s = UUID.randomUUID().toString();
        member.setSalt(s);
        member.setMpassword(Md5Util.encodePassword(member.getMpassword(),s));
        memberMapper.insert(member);
        return ServerResult.success();
    }

    private Member getMemberByEmail(String email) {
        //邮箱唯一
        QueryWrapper queryEmailWrapper = new QueryWrapper();
        queryEmailWrapper.eq("email",email);
        return memberMapper.selectOne(queryEmailWrapper);
    }

    private Member getMemberByPhone(String phoneNumber) {
        //手机号码唯一
        QueryWrapper queryphoneWrapper = new QueryWrapper();
        queryphoneWrapper.eq("phoneNumber",phoneNumber);
        return memberMapper.selectOne(queryphoneWrapper);
    }

    private Member getMemberByName(String memberName) {
        //用户名唯一
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("memberName",memberName);
        return memberMapper.selectOne(queryWrapper);
    }

    @Override
    public ServerResult findByName(String name) {
        Member memByName = getMemberByName(name);
        if(null!=memByName){
            return ServerResult.error(ResponseEnum.MEMBERNAME_IS_EXIST);
        }
        return ServerResult.success();
    }

    @Override
    public ServerResult findByPhone(String phoneNumber) {
        Member memByPhone = getMemberByPhone(phoneNumber);
        if(null!=memByPhone){
            return ServerResult.error(ResponseEnum.PHONE_IS_EXIST);
        }
        return ServerResult.success();
    }

    @Override
    public ServerResult findByEmail(String email) {
        Member memberByEmail = getMemberByEmail(email);
        if(null!=memberByEmail){
            return ServerResult.error(ResponseEnum.EMAIL_IS_EXIST);
        }
        return ServerResult.success();
    }

    @Override
    public ServerResult findMemberByName(Member member) {
       String memberName = member.getMemberName();
        String mpassword = member.getMpassword();
        //非空验证
        if(StringUtils.isEmpty(memberName)){
            return ServerResult.error(ResponseEnum.MEMBER_IS_NULL);
        }
        if(StringUtils.isEmpty(mpassword)){
            return ServerResult.error(ResponseEnum.PASSWORD_IS_NULL);
        }
        //获取用户
        Member memberByName = getMemberByName(memberName);
        if(memberByName==null){
            return ServerResult.error(ResponseEnum.MEMBERNAME_IS_NULL);
        }
        //密码判断
        String salt = memberByName.getSalt();
        if(!Md5Util.encodePassword(mpassword, salt).equals(memberByName.getMpassword())){
            return ServerResult.error(ResponseEnum.PHONE_LENGTH_ERROR); 
        }
        Long mid = memberByName.getId();
        //构建memberVo
        String uuid = UUID.randomUUID().toString();
        MemberVo memberVo = new MemberVo();
        memberVo.setId(memberByName.getId());
        memberVo.setMemberName(memberName);
        memberVo.setRealName(memberByName.getRealName());
        memberVo.setUid(uuid);
       String memberJson = JSONObject.toJSONString(memberVo);
        //base64编码
        String encode = Base64.getEncoder().encodeToString(memberJson.getBytes());
        String sign = Md5Util.sign(encode,SystemConst.SECRETKEY);
        //再次编码
        String encodeSign = Base64.getEncoder().encodeToString(sign.getBytes());
        RedisUtil.setEx(KeyUtil.buildMember(mid,memberName,uuid),SystemConst.CODE_TIME_OUT,"2");
        return ServerResult.success(encode+"."+encodeSign);
    }

}
