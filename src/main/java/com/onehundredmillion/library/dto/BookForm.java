package com.onehundredmillion.library.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.chrono.ChronoLocalDateTime;

@Getter @Setter
public class BookForm {

    private Long id;

    private String title;
    private String author;
    private String isbn;
    private String image;
    private String publisher;
    private String pubdate;
    private String description;
    private int stockQuantity;


}