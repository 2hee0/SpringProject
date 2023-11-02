package com.onehundredmillion.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Booksearch;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.service.BookService;
import com.onehundredmillion.library.service.MemberService;
import com.onehundredmillion.library.sessioin.SessionConst;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final Booksearch booksearch;
    private final MemberService memberService;

    @GetMapping(value = "/books")
    public String list(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            Member userDataFromDatabase = memberService.findOne(loginMember.getId());
            model.addAttribute("loginMember", userDataFromDatabase);
            List<Book> books = bookService.findBooks();
            model.addAttribute("books", books);
            return "/book/booklist";
        }

        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "/book/booklist";
    }

    @GetMapping(value = "/bookfront")
    public String listfront(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            Member userDataFromDatabase = memberService.findOne(loginMember.getId());
            model.addAttribute("loginMember", userDataFromDatabase);
            List<Book> books = bookService.findBooks();
            model.addAttribute("books", books);
            return "/book/booklistfront";
        }
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "/book/booklistfront";
    }

    @GetMapping(value = "/bookback")
    public String listback(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            Member userDataFromDatabase = memberService.findOne(loginMember.getId());
            model.addAttribute("loginMember", userDataFromDatabase);
            List<Book> books = bookService.findBooks();
            model.addAttribute("books", books);
            return "/book/booklistback";

        }
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "/book/booklistback";
    }

    @GetMapping(value = "/bookit")
    public String listit(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            Member userDataFromDatabase = memberService.findOne(loginMember.getId());
            model.addAttribute("loginMember", userDataFromDatabase);
            List<Book> books = bookService.findBooks();
            model.addAttribute("books", books);
            return "/book/booklistit";
        }
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "/book/booklistit";
    }

    // 관리자로 로그인 시 책 추가
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
    public String bookDetail(Model model, @PathVariable("bookId") Long bookId, HttpSession session) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            Member userDataFromDatabase = memberService.findOne(loginMember.getId());
            model.addAttribute("loginMember", userDataFromDatabase);
            Book book = bookService.findOne(bookId);
            model.addAttribute("book", book);
            return "book/detail";
        }
        Book book = bookService.findOne(bookId);
        model.addAttribute("book", book);
        return "book/detail";
    }

}