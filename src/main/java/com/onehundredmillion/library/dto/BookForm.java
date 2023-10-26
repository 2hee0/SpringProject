package com.onehundredmillion.library.dto;

import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import java.time.chrono.ChronoLocalDateTime;

=======
>>>>>>> fed9438 (유저 정보 업데이트 최종완성)
@Getter @Setter
public class BookForm {

    private Long id;

<<<<<<< HEAD
    private String title;
    private String authors;
    private String isbn;
    private String contents;
    private String publisher;
//    private Datetime datetime;
    private String thumbnail;
    private int stockQuantity;


=======
    private String name;
    private String author;
    private String isbn;
    private String contents;
    private String company;
    private int stockQuantity;

>>>>>>> fed9438 (유저 정보 업데이트 최종완성)
}