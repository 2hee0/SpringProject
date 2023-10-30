package com.onehundredmillion.library.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter @Setter
public class BookForm {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String contents;
    private String publisher;
    private int stockQuantity;
    private String link;
    private String image;
    private String pubdate;
    private String description;
}