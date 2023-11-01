package com.onehundredmillion.library.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateForm {
	@NotEmpty(message = "이름을 입력해주세요")
	private String name;
	private String addr;
	private String zipcode;
	private String addr_etc;
	private String addr_detail;
	@NotEmpty(message = "비밀번호확인을 해주세요.")
	private String passwordConfirm;
	@NotEmpty(message = "비밀번호을 입력해주세요")
	@Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자로만 입력해주세요.")
	@Size(min = 10, max = 11, message = "전화번호는 10자 또는 11자여야 합니다.")
	private String phoneNo;
	private String Rrn1; // 주민번호
	@NotEmpty(message = "비밀번호을 입력해주세요")
	@Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=]).*$", message = "비밀번호는 숫자,  특수문자를 포함해야 합니다.")
	private String password;

	public boolean isPasswordMatch() {
		return password != null && password.equals(passwordConfirm);
	}
}
