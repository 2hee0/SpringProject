package com.onehundredmillion.library.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.onehundredmillion.library.exception.NotEnoughStockException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "BOOK_ID", nullable = false)
	private Long id;
	private String name;
	private String author;
	private String isbn;
	private int stockQuantity;
	private String publisher;
	private LocalDateTime datetime;
	private String contents;
	private String thumbnail;

	@ManyToMany(mappedBy = "books")
	private List<Category> categories = new ArrayList<Category>();

	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	@OneToMany(mappedBy = "book")
	List<ReservationBook> reservationBooks = new ArrayList<>();

	public void removeStock(int quantity) throws NotEnoughStockException {
		int restStock = this.stockQuantity - quantity;
		if (restStock < 0) {
			throw new NotEnoughStockException("잔여 도서가 없습니다.");
		}
		this.stockQuantity = restStock;
	}
}