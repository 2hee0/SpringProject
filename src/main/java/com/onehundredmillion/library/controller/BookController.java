package com.onehundredmillion.library.controller;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.dto.BookForm;
import com.onehundredmillion.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
  /*책 추가 관리자용(추후에,,)
  @GetMapping(value = "/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }
    @PostMapping(value = "/items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        bookService.savdBooks(book);
        return "redirect:/items";
    }*/


    @GetMapping(value = "/books")
    public String list(Model model) {
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "book/bookList";
    }
    
    @GetMapping(value = "/book/{bookId}")
    public String bookDetail(Model model, @PathVariable("bookId")Long bookId) {
        Book book = bookService.findOne(bookId);
        model.addAttribute("book", book);
        return "book/detail";
    }
    
    
    
    


}