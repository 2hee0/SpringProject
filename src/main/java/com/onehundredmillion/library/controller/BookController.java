package com.onehundredmillion.library.controller;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Booksearch;
import com.onehundredmillion.library.dto.BookForm;
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final Booksearch booksearch;

    @GetMapping(value = "/books")
    public String list(Model model) {
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "/book/booklist";
    }

    @GetMapping(value = "/list/back")
    public String back(Model model, String query) {
        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";
        query = "JAVA";

        ResponseEntity<String> resp = booksearch.callNaverApi(clientId, clientSecret, query);

        booksearch.resultBook(resp.getBody());

        return "/book/booklist";
    }

    @GetMapping(value = "/list/front")
    public String front(Model model) {
        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";


        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("query1", "javascript");
        queryMap.put("query2", "홈페이지");

        String query1 = queryMap.get("query1");
        String query2 = queryMap.get("query2");

        ResponseEntity<String> resp1 = booksearch.callNaverApi(clientId, clientSecret, query1);
        ResponseEntity<String> resp2 = booksearch.callNaverApi(clientId, clientSecret, query2);


        booksearch.resultBook(resp1.getBody());
        booksearch.resultBook(resp2.getBody());
        return "/book/booklist";
    }

    @GetMapping(value = "/list/it")
    public String IT(Model model, String query) {
        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";
        query = "IT";

        ResponseEntity<String> resp = booksearch.callNaverApi(clientId, clientSecret, query);

        booksearch.resultBook(resp.getBody());

        return "/book/booklist";
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
        bookForm.setIsbn(item.getIsbn());
        bookForm.setTitle(item.getTitle());
        bookForm.setImage(item.getImage());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setPublisher(item.getPublisher());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setDescription(item.getDescription());

        model.addAttribute("bookForm", bookForm);
        return "admin/updateItemForm";
    }

    @PostMapping(value = "/book/{bookId}/edit")
    public String updateItem(@PathVariable Long bookId, @ModelAttribute("bookForm")
    BookForm bookForm) {
        bookService.updateBook(bookId, bookForm.getTitle(), bookForm.getStockQuantity());
        return "redirect:/booklist";
    }


    @GetMapping(value = "book/{bookId}/detail")
    public String bookDetail(Model model, @PathVariable("bookId") Long bookId) {
        Book book = bookService.findOne(bookId);
        model.addAttribute("book", book);
        return "book/detail";
    }


}