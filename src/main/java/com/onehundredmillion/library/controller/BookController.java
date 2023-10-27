package com.onehundredmillion.library.controller;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.dto.BookForm;
import com.onehundredmillion.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    //  책 추가 관리자용
    @GetMapping(value = "/admin/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "admin/createItemForm";
    }

    @PostMapping(value = "/admin/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getTitle());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthors());
        book.setIsbn(form.getIsbn());
        bookService.saveBooks(book);
        return "redirect:/items";
    }


    @GetMapping(value = "/books")
    public String list(Model model) {
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "book/bookList";
    }

    @GetMapping(value = "/books/reserve")
    public String reserve(Model model) {
        Book book = bookService.reserve();
        model.addAttribute("book", book);
        return "book/reserve";
    }

    @GetMapping(value = "/books/rent")
    public String rent(Model model) {
        Book book = bookService.rent();
        model.addAttribute("book", book);
        return "book/rent";
    }


    @GetMapping(value = "/book/{bookId}/edit")
    public String updateBookForm(@PathVariable("bookId") Long bookId, Model
            model) {
        Book item = (Book) bookService.findOne(bookId);
        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setTitle(item.getName());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setAuthors(item.getAuthor());
        bookForm.setIsbn(item.getIsbn());
        model.addAttribute("bookForm", bookForm);
        return "admin/updateItemForm";
    }

    @PostMapping(value = "/book/{bookId}/edit")
    public String updateItem(@PathVariable Long bookId, @ModelAttribute("bookForm")
    BookForm bookForm) {
        bookService.updateBook(bookId, bookForm.getTitle(), bookForm.getStockQuantity());
        return "redirect:/booklist";
    }

}