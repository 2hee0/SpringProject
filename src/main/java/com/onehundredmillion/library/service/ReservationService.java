package com.onehundredmillion.library.service;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.domain.Reservation;
import com.onehundredmillion.library.domain.ReservationBook;
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.repository.MemberRepository;
import com.onehundredmillion.library.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;

    @Transactional
    public Long reservation(Long memberId, Long bookId) {
        Member member = memberRepository.findOne(memberId);
        Book book = bookRepository.findOne(bookId);

        ReservationBook reservationBook = ReservationBook.createReservationBook(book);
        Reservation reservation = Reservation.createReservation(member, reservationBook);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    //예약 취소
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findOne(reservationId);
        reservation.cancel();
    }


}