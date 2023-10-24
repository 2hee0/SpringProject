package com.onehundredmillion.library.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookLike {

	private long memberid;
	private long bookid;
	
	public BookLike(long memberid, long bookid) {
		this.memberid = memberid;
		this.bookid = bookid;
	}
	
	
}
