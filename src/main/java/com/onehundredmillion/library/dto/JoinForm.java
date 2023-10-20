package com.onehundredmillion.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {
    private String userid;
    private String name;
    private String password;
    private String rePassword;
    private String rrn; //주민번호
    private String phoneNo;
    private String addr;
    private String addr_detail;
    private String addr_etc;
    private String extraAddr;
    private String zipcode;



}