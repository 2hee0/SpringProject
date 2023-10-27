package com.onehundredmillion.library.service;

import com.onehundredmillion.library.domain.*;
import com.onehundredmillion.library.exception.NotEnoughStockException;
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
        Reservation reservation = reservationRepository.findOne(reservationId);
        reservation.cancel();
    }

}