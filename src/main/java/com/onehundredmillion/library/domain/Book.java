package com.onehundredmillion.library.domain;

import com.onehundredmillion.library.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String name;
    private String authors;
    private String isbn;
    private String contents;
    private String publisher;
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
    
    @OneToMany(mappedBy = "book")
    List<LikeBook> likeBooks = new ArrayList<>();


    public void removeStock(int quantity) throws NotEnoughStockException {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("잔여 도서가 없습니다.");
        }
        this.stockQuantity = restStock;
    }
}