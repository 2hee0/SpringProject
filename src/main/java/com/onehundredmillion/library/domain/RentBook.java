package com.onehundredmillion.library.domain;

import com.onehundredmillion.library.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RENTBOOK_ID", nullable = false)
    private Long id;

    private int count;

    public static RentBook createRentBook(Book book,  int
            count) throws NotEnoughStockException {
        RentBook rentBook = new RentBook();
        rentBook.setBook(book);
        rentBook.setCount(count);

        book.removeStock(count);

        return rentBook;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RENT_ID")
    private Rent rent;

    /** 주문 취소 */
    public void returnBook() {
       getBook().addStock(count);
    }

    public void add(RentBook rentBook) {
    }
}