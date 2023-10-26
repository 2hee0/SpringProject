package com.onehundredmillion.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateForm {

	private String name;
	private String addr;
	private String zipcode;
	private String addr_etc;
	private String addr_detail;
	private String passwordConfirm;
	private String phoneNo;
	private String Rrn1; // 주민번호
}
