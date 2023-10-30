package com.onehundredmillion.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.dto.BookForm;
import com.onehundredmillion.library.dto.NaverResult;
import com.onehundredmillion.library.repository.BookRepository;
import com.onehundredmillion.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;

/*    @GetMapping(value = "/books")
    public String list(Model model) {
        List<Book> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "book/bookList";
    }*/

    @GetMapping(value = "/list/back")
    public String back(Model model) {
        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";


        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", "JAVA")
                .queryParam("display", 30)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        // Spring 요청 제공 클래스
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();

        // Spring 제공 restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NaverResult result = null;

        resultBook(resp.getBody());
        return "/book/booklist";
    }

    @GetMapping(value = "/list/front")
    public String front(Model model) {
        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";


        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", "프론트엔드")
                .queryParam("display", 30)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        // Spring 요청 제공 클래스
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();

        // Spring 제공 restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NaverResult result = null;

        resultBook(resp.getBody());
        return "/book/booklist";
    }

    @GetMapping(value = "/list/it")
    public String IT(Model model) {
        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";


        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", "IT")
                .queryParam("display", 30)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        // Spring 요청 제공 클래스
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();

        // Spring 제공 restTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NaverResult result = null;

        resultBook(resp.getBody());
        return "/book/booklist";
    }

    public void resultBook(String body) {
        try {

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(body);
            System.out.println("resp: " + body);
            JSONArray items = (JSONArray) obj.get("items");
            for (int i = 0; i < items.size(); i++) {
                obj = (JSONObject) items.get(i);
                String title = (String) obj.get("title");
                String isbn = (String) obj.get("isbn");
                String image = (String) obj.get("image");
                String author = (String) obj.get("author");
                String publisher = (String) obj.get("publisher");
                String pubdate = (String) obj.get("pubdate");
                String description = (String) obj.get("description");
                int maxLength = 200;

                if (description.length() > maxLength) {
                    description = description.substring(0, maxLength); // 최대 길이까지 자르기
                }
                Book book = new Book();
                book.setStockQuantity(10);
                book.setTitle(title);
                book.setIsbn(isbn);
                book.setImage(image);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setPubdate(pubdate);
                book.setDescription(description);

                bookRepository.save(book);

                System.out.println("result : " + obj);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        bookForm.setTitle(item.getTitle());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setIsbn(item.getIsbn());
        bookForm.setPublisher(item.getPublisher());
        bookForm.setImage(item.getImage());
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


    @GetMapping(value = "/book/{bookId}")
    public String bookDetail(Model model, @PathVariable("bookId") Long bookId) {
        Book book = bookService.findOne(bookId);
        model.addAttribute("book", book);
        return "book/detail";
    }


}