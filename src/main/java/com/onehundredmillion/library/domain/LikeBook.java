package com.onehundredmillion.library.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class LikeBook {
	@Id @GeneratedValue
	@Column(name ="LIKE_BOOK_ID")
	private Long id;
	
	// 좋아요 상태(LIKE,DISLIKE)
	@Enumerated(EnumType.STRING)
	private RentStatus status;
	
	@ManyToOne
	@JoinColumn(name="LIKE_ID")
	private Like like;
	
	@ManyToOne
	@JoinColumn(name = "BOOK_ID")
	private Book book;
	
}
