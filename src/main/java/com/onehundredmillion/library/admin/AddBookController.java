package com.onehundredmillion.library.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.dto.BookForm;
import com.onehundredmillion.library.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AddBookController {

    private final BookService bookService;

    @RequestMapping("/adminmain")
    public String adminForm() {
        return "admin/admin_main";
    }

    @GetMapping("/bookList")
    public String adminList(Model model) {
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "admin/admin_bookList";
    }

    @GetMapping(value = "/new")
    public String createForm(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "admin/admin_bookRegist";
    }

    @PostMapping(value = "/new")
    public String create(BookForm bookForm) {

        Book book = new Book();
        book.setTitle(bookForm.getTitle());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setPublisher(bookForm.getPublisher());
        book.setPubdate(bookForm.getPubdate());
        book.setImage(bookForm.getImage());
        book.setDescription(bookForm.getDescription());

        bookService.saveBooks(book);

        return "redirect:/admin/bookList";

    }

    @GetMapping(value = "/update/{bookId}")
    public String updatemainForm(Model model, @PathVariable Long bookId) {
        Book books = bookService.findOne(bookId);
        model.addAttribute("book", books);

        return "admin/admin_book";
    }

    @GetMapping(value = "/updateadmin/{bookId}")
    public String updateForm(Model model, @PathVariable Long bookId) {
        Book books = bookService.findOne(bookId);
        model.addAttribute("book", books);
        return "admin/admin_bookEdit";
    }

    @PostMapping(value = "/updateadmin/{bookId}")
    public String updatebook(@PathVariable Long bookId, @ModelAttribute Book book, Model model) {
        bookService.updateBook(bookId, book.getDescription(), book.getStockQuantity());
        Book books = bookService.findOne(bookId);
        model.addAttribute("book", books);
        return "admin/admin_book";
    }
}