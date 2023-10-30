package com.onehundredmillion.library.domain;

import com.onehundredmillion.library.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "BOOK_ID", nullable = false)
    private Long id;
    private String isbn;
    private String title;
    private String image;
    private String author;
    private String description;
    private String publisher;
    private String pubdate;
    private int stockQuantity;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories = new ArrayList<Category>();

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    @OneToMany(mappedBy = "book")
    List<ReservationBook> reservationBooks = new ArrayList<>();
    
    @OneToMany(mappedBy = "book")
    List<RentBook> rentBooks = new ArrayList<>();


    public void removeStock(int quantity) throws NotEnoughStockException {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("잔여 도서가 없습니다.");
        }
        this.stockQuantity = restStock;
    }
}