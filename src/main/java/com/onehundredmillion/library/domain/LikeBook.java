package com.onehundredmillion.library.domain;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class LikeBook {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LIKE_BOOK", nullable = false)
    private Long id;
    
    @ColumnDefault("1")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIKE_ID")
    private Like like;

    public static LikeBook createLikeBook(Book book, int count){
    	LikeBook likeBook = new LikeBook();
    	likeBook.setBook(book);
    	likeBook.setCount(count);

        return likeBook;
    }

    public void dislike() {
    	count = 0;
    }
	
}
