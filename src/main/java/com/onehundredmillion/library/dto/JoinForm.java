package com.onehundredmillion.library.dto;

import com.onehundredmillion.library.domain.Address;
import com.onehundredmillion.library.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {
    @NotEmpty(message = "아이디를 입력해주세요.")
    @Size(min = 5, message = "아이디는 5자 이상으로 입력해주세요.")
    private String userId;

    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=!.]).*$", message = "비밀번호는 숫자,영어,특수문자를 포함해야 합니다.")
    private String password;

    @NotEmpty(message = "비밀번호확인을 해주세요.")
    private String passwordConfirm;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;

    private String Rrn1; //주민번호
    private String Rrn2; //주민번호

    @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자로만 입력해주세요.")
    @Size(min = 10, max = 11, message = "전화번호는 10자 또는 11자여야 합니다.")
    private String phoneNo;

    private String zipcode;
    private String addr;
    private String addr_detail;
    private String addr_etc;
    private String extraAddr;

    public boolean isPasswordMatch() {
        return this.password != null && this.password.equals(passwordConfirm);
    }

    public Member toMember() {
        Member member = new Member();
        member.setUserId(this.getUserId());
        member.setName(this.getName());
        member.setPassword(this.getPassword());
        member.setPasswordConfirm(this.getPasswordConfirm());
        member.setRrn1(this.getRrn1());
        member.setRrn2(this.getRrn2());
        member.setPhoneNo(this.getPhoneNo());
        member.setAddress(new Address(zipcode, addr, addr_etc, addr_detail));
        return member;
    }

}