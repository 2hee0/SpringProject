package com.onehundredmillion.library.domain;

<<<<<<< HEAD
import org.hibernate.annotations.ColumnDefault;

=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
import com.onehundredmillion.library.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD
=======
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

@Entity
@Getter
@Setter
public class RentBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RENTBOOK_ID", nullable = false)
    private Long id;

    @ColumnDefault("1")
    private int count;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RENT_ID")
    private Rent rent;
    
    public static RentBook createRentBook(Book book,  int
            count) throws NotEnoughStockException {
=======
    public static RentBook createRentBook(Book book, int count) throws NotEnoughStockException {
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
        RentBook rentBook = new RentBook();
        rentBook.setBook(book);
        rentBook.setCount(count);

        book.removeStock(count);

        return rentBook;
    }

<<<<<<< HEAD
// 주문 취소
    public void returnBook() {
       getBook().addStock(count);
=======

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RENT_ID")
    private Rent rent;


    public void returnBook() {
        getBook().addStock(count);
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
    }

    public void add(RentBook rentBook) {
    }
}