package com.onehundredmillion.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id;

    private String name;
    private String author;
    private int isbn;
    private String contents;
    private String company;

}