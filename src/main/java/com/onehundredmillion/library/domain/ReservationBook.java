package com.onehundredmillion.library.domain;

<<<<<<< HEAD
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
=======
import com.onehundredmillion.library.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

@Entity
@Getter
@Setter
public class ReservationBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
<<<<<<< HEAD
    @Column(name = "RESERVATION_BOOK", nullable = false)
    private Long id;
    
=======
    @Column(name = "RESERVATION_BOOK")
    private Long id;

>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
    @ColumnDefault("1")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    public static ReservationBook createReservationBook(Book book, int count){
=======

    @ManyToOne
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    public static ReservationBook createReservationBook(Book book, int count) throws NotEnoughStockException {
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
        ReservationBook reservationBook = new ReservationBook();
        reservationBook.setBook(book);
        reservationBook.setCount(count);

<<<<<<< HEAD
=======
        book.removeStock(count);
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
        return reservationBook;
    }

    public void cancel() {
<<<<<<< HEAD
    	count = 0;
=======
        reservation.cancel();
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
    }
}