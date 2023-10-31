package com.onehundredmillion.library.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

	private Long id;

<<<<<<< HEAD
<<<<<<< HEAD
    private String title;
    private String author;
    private String isbn;
    private String image;
    private String publisher;
    private String pubdate;
    private String description;
    private int stockQuantity;

=======
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	private String title;
	private String authors;
	private String isbn;
	private String contents;
	private String publisher;
//    private Datetime datetime;
	private String thumbnail;
	private Integer stockQuantity;
	private LocalDateTime datetime;
<<<<<<< HEAD
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

}