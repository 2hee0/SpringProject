package com.onehundredmillion.library.service;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.domain.Rent;
import com.onehundredmillion.library.domain.RentBook;
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.repository.MemberRepository;
import com.onehundredmillion.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentService {
    private final MemberRepository memberRepository;
    private final RentRepository rentRepository;
    private final BookRepository bookRepository;

  /*
//   대여하기(오류나서 임시)
   @Transactional
    public Long rent(Long memberId, Long bookId, int count) {
        Member member = memberRepository.findOne(memberId);
        Book book = bookRepository.findOne(bookId);


        //대여정보 생성
        Rent rent = Rent.createRent(member,book.getName(),count);
        //대여정보 저장
        rentRepository.save(rent);
        return rent.getId();
    }*/

    /**
     * 반납하기
     */
    @Transactional
    public void returnBook(Long bookId) {
        //주문 엔티티 조회
        Rent rent = rentRepository.findOne(bookId);
        //주문 취소
        rent.returnBook();
    }
}