package com.onehundredmillion.library.service;

import com.onehundredmillion.library.domain.*;
<<<<<<< HEAD
=======
import com.onehundredmillion.library.exception.NotEnoughStockException;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.repository.MemberRepository;
import com.onehundredmillion.library.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;

<<<<<<< HEAD
    public List<Reservation> findAll(Member member) {
        return reservationRepository.findAll(member.getId(),BookStatus.RESERVATION);
    }
    
	@Transactional
    public Long reserve(Member member, Long bookId) {
        Member saveMember = memberRepository.findOne(member.getId());
        Book book = bookRepository.findOne(bookId);
        ReservationBook reservationBook = ReservationBook.createReservationBook(book, 1);

        if(reservationRepository.reservationCheck(saveMember.getId(), book.getId()) == 0) {
        	Reservation reservation = Reservation.createReservation(saveMember,reservationBook);
            reservationRepository.reserve(reservation);
            return (long)reservation.getId();
        }
        else {
        	return (long)0;
        }
        		


    }
    
    //예약 취소
    @Transactional
    public void cancelBook(Long reservationId) {
=======
    @Transactional
    public Long reservation(Long memberId, Long bookId, int count) throws NotEnoughStockException {
        Member member = memberRepository.findOne(memberId);
        Book book = bookRepository.findOne(bookId);

        ReservationBook reservationBook = ReservationBook.createReservationBook(book, count);
        Reservation reservation = Reservation.createReservation(member, reservationBook);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    public List<Reservation> findAll(Reservation reservation) {
        return reservationRepository.findAll();
    }

    //예약 취소

    @Transactional
    public void cancelReservation(Long reservationId) {
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
        Reservation reservation = reservationRepository.findOne(reservationId);
        reservation.cancel();
    }

}