package com.onehundredmillion.library.service;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.repository.RentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final RentRepository rentRepository;

    @Transactional
    public void savdBooks(Book book) {
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

}








