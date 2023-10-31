package com.onehundredmillion.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;

	@Transactional
	public void saveBooks(Book book) {
		bookRepository.save(book);
	}

	public List<Book> findBooks() {
		return bookRepository.findAll();
	}

	public Book findOne(Long bookId) {
		return bookRepository.findOne(bookId);
	}

	public Book reserve() {
		return bookRepository.reserve();
	}

	public Book rent() {
		return bookRepository.rent();
	}

	@Transactional
<<<<<<< HEAD
<<<<<<< HEAD
	public void updateBook(Long id, String title, int stockQuantity) {
		Book book = bookRepository.findOne(id);
		book.setTitle(title);
		book.setStockQuantity(stockQuantity);
	}

	public void updateBook2(Long bookId, String description, int stockQuantity) {
		Book book = bookRepository.findOne(bookId);
		book.setStockQuantity(stockQuantity);
		book.setDescription(description);

	}
=======
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	public void updateBook(Long id, String contents, int stockQuantity) {
		Book book = bookRepository.findOne(id);
		book.setContents(contents);
		book.setStockQuantity(stockQuantity);
	}
<<<<<<< HEAD
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
}