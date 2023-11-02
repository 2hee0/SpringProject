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

    //관리자로 로그인 시 책 추가
    @GetMapping(value = "/adminmain")
    public String addBook(Model model) {
        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";


        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("query1", "자바스크립트");
        queryMap.put("query2", "홈페이지");
        queryMap.put("query3", "JAVA");
        queryMap.put("query4", "IT");


        String query1 = queryMap.get("query1");
        String query2 = queryMap.get("query2");
        String query3 = queryMap.get("query3");
        String query4 = queryMap.get("query4");

        ResponseEntity<String> resp1 = booksearch.callNaverApi(clientId, clientSecret, query1);
        ResponseEntity<String> resp2 = booksearch.callNaverApi(clientId, clientSecret, query2);
        ResponseEntity<String> resp3 = booksearch.callNaverApi(clientId, clientSecret, query3);
        ResponseEntity<String> resp4 = booksearch.callNaverApi(clientId, clientSecret, query4);


        booksearch.resultBook(resp1.getBody());
        booksearch.resultBook(resp2.getBody());
        booksearch.resultBook(resp3.getBody());
        booksearch.resultBook(resp4.getBody());
        return "admin/admin_main";
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

    @GetMapping(value = "book/{bookId}/detail")
    public String bookDetail(Model model, @PathVariable("bookId") Long bookId) {
        Book book = bookService.findOne(bookId);
        model.addAttribute("book", book);
        return "book/detail";
    }

}