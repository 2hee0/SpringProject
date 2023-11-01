package com.onehundredmillion.library.domain;

import com.onehundredmillion.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class Booksearch {
    private final BookRepository bookRepository;

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
                int maxLength = 255;

                if (description.length() > maxLength) {
                    description = description.substring(0, maxLength); // 최대 길이까지 자르기
                }
                Book book = new Book();
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setImage(image);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setPubdate(pubdate);
                book.setStockQuantity(10);
                book.setDescription(description);

                bookRepository.save(book);

                System.out.println("result : " + obj);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
/*
    public ResponseEntity<String> existData(String clientId, String clientSecret, String query) {
        if (!bookRepository.existQuery(query)) {
            ResponseEntity<String> response = callNaverApi(clientId, clientSecret, query);
            resultBook(response.getBody());
            bookRepository.existQuery(query);
            return response;
        }
        return null;
    }
*/



    public ResponseEntity<String> callNaverApi(String clientId, String clientSecret, String query) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", query)
                .queryParam("display", 30)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(req, String.class);
    }
}