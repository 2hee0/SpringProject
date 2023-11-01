package com.onehundredmillion.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.BookStatus;
import com.onehundredmillion.library.domain.Like;
import com.onehundredmillion.library.domain.LikeBook;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.repository.LikeRepository;
import com.onehundredmillion.library.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {

	private final MemberRepository memberRepository;
	private final LikeRepository likeRepository;
	private final BookRepository bookRepository;

	public List<Like> findAll(Member member) {
		return likeRepository.findAll(member.getId(),BookStatus.LIKE);
	}

	@Transactional
	public Long like(Member member, Long bookId) {
		Member saveMember = memberRepository.findOne(member.getId());
		Book book = bookRepository.findOne(bookId);
		LikeBook likeBook = LikeBook.createLikeBook(book, 1);

		if(likeRepository.likeCheck(saveMember.getId(), book.getId()) == 0) {
			Like like = Like.createLike(saveMember,likeBook);
			likeRepository.like(like);
			return (long)like.getId();
		}
		else {
			return (long)0;
		}
	}

	//예약 취소
	@Transactional
	public void dislikeBook(Long likeId) {
		Like like = likeRepository.findOne(likeId);
		like.dislike();
	}
}