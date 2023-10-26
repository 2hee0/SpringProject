package com.onehundredmillion.library.service;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
<<<<<<< HEAD
    public void saveBooks(Book book) {
=======
    public void savdBooks(Book book) {
>>>>>>> fed9438 (유저 정보 업데이트 최종완성)
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
<<<<<<< HEAD
    @Transactional
    public void updateBook(Long id, String name, int stockQuantity)
    {
        Book book = bookRepository.findOne(id);
        book.setName(name);
        book.setStockQuantity(stockQuantity);
    }
=======
	
>>>>>>> fed9438 (유저 정보 업데이트 최종완성)
}