package com.onehundredmillion.library.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Like {
	@Id @GeneratedValue
	@Column(name = "LIKE_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@OneToMany(mappedBy = "like", cascade = CascadeType.ALL)
	private List<LikeBook> likeBooks = new ArrayList<>();

	///////////// 연관관계 메서드 /////////////////
	public void setMember(Member member) {
		this.member = member;
		member.getLike().add(this);
	}

	public void addLikeBook(LikeBook likeBook) {
		likeBooks.add(likeBook);
		likeBook.setLike(this);
	}
	

}
