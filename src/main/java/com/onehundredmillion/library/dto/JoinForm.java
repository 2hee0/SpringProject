package com.onehundredmillion.library.dto;

import com.onehundredmillion.library.domain.Address;
import com.onehundredmillion.library.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {
    private String userid;

    private String password;
    private String passwordConfirm;
    private String Rrn; //주민번호
    private String name;
    @NotEmpty(message = "회원 전화번호는 필수입니다.")
    private String phoneNo;
    private String zipcode;
    private String addr;
    private String addr_detail;
    private String addr_etc;
    private String extraAddr;

    public Member toMember() {
        Member member = new Member();
        member.setUserid(this.getUserid());
        member.setName(this.getName());
        member.setPassword(this.getPassword());
        member.setPasswordConfirm(this.getPasswordConfirm());
        member.setRrn(this.getRrn());
        member.setPhoneNo(this.getPhoneNo());
        member.setAddress(new Address(zipcode,addr,addr_etc,addr_detail));
        return member;
    }

}