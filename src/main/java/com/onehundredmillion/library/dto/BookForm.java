package com.onehundredmillion.library.dto;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class BookForm {

    private Long id;

    private String title;
    private String authors;
    private String isbn;
    private String contents;
    private String publisher;
//    private Datetime datetime;
    private String thumbnail;
    private int stockQuantity;

}