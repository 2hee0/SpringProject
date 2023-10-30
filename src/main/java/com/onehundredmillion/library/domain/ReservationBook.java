package com.onehundredmillion.library.domain;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ReservationBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RESERVATION_BOOK", nullable = false)
    private Long id;
    
    @ColumnDefault("1")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    public static ReservationBook createReservationBook(Book book, int count){
        ReservationBook reservationBook = new ReservationBook();
        reservationBook.setBook(book);
        reservationBook.setCount(count);

        return reservationBook;
    }

    public void cancel() {
    	count = 0;
    }
}