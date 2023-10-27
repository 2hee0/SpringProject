package com.onehundredmillion.library.service;

import com.onehundredmillion.library.domain.*;
import com.onehundredmillion.library.exception.NotEnoughStockException;
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.repository.MemberRepository;
import com.onehundredmillion.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentService {
    private final MemberRepository memberRepository;
    private final RentRepository rentRepository;
    private final BookRepository bookRepository;

// 대여하기
   @Transactional  
   public Long rent(Member member, Long bookId) throws NotEnoughStockException {
	   	Member member2 = memberRepository.findOne(member.getId());
		Book book = bookRepository.findOne(bookId);
		RentBook rentBook = RentBook.createRentBook(book, 1);
		
		//대여정보 생성
		Rent rent = Rent.createRent(member2, rentBook);
		
        //대여정보 저장
		rentRepository.save(rent);
		
		return rent.getId(); 
	}

    public List<Rent> findAll(Rent rent) {
        return rentRepository.findAll();
    }

// 반납하기
    @Transactional
    public void returnBook(Long bookId) {
        //주문 엔티티 조회
        Rent rent = rentRepository.findOne(bookId);
        //주문 취소
        rent.returnBook();
    }

}