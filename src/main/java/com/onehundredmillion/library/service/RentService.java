package com.onehundredmillion.library.service;

<<<<<<< HEAD
import com.onehundredmillion.library.domain.*;
import com.onehundredmillion.library.exception.NotEnoughStockException;
=======
import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.domain.Rent;
import com.onehundredmillion.library.domain.RentBook;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.repository.MemberRepository;
import com.onehundredmillion.library.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentService {
    private final MemberRepository memberRepository;
    private final RentRepository rentRepository;
    private final BookRepository bookRepository;

<<<<<<< HEAD

    public List<Rent> findAll(Member member) {
        return rentRepository.findAll(member.getId(), BookStatus.RENT);
    }
    
    // 대여하기 
   @Transactional
	public Long rent(Member member, Long bookId) throws NotEnoughStockException {
		Member saveMember = memberRepository.findOne(member.getId());
		Book book = bookRepository.findOne(bookId);
		RentBook rentBook = RentBook.createRentBook(book, 1);

		if(rentRepository.rentCheck(saveMember.getId(), book.getId()) == 0) {
			// 대여정보 생성
			Rent rent = Rent.createRent(saveMember, rentBook);
			// 대여정보 저장
			rentRepository.rent(rent);
			return (long)rent.getId();
		}
		else {
			return (long) 0;
		}
	}

   // 반납하기
    @Transactional
    public void returnBook(Long rentId) {
        //주문 엔티티 조회
        Rent rent = rentRepository.findOne(rentId);
=======
//   대여하기(오류나서 임시)
/*
   @Transactional
    public Long rent(Long memberId, Long bookId, int count) {
        Member member = memberRepository.findOne(memberId);
        Book book = bookRepository.findOne(bookId);


        //대여정보 생성
        Rent rent = Rent.createRent(member,book.getName(),count);
        //대여정보 저장
        rentRepository.save(rent);
        return rent.getId();
    }

*/


//반납하기

    @Transactional
    public void returnBook(Long bookId) {
        //주문 엔티티 조회
        Rent rent = rentRepository.findOne(bookId);
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
        //주문 취소
        rent.returnBook();
    }

}