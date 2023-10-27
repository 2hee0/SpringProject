package com.onehundredmillion.library.domain;

import com.onehundredmillion.library.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@Setter
public class ReservationBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RESERVATION_BOOK")
    private Long id;

    @ColumnDefault("1")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;


    @ManyToOne
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    public static ReservationBook createReservationBook(Book book, int count) throws NotEnoughStockException {
        ReservationBook reservationBook = new ReservationBook();
        reservationBook.setBook(book);
        reservationBook.setCount(count);

        book.removeStock(count);
        return reservationBook;
    }

    public void cancel() {
        reservation.cancel();
    }
}