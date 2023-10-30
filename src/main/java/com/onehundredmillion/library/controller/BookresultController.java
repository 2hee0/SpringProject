package com.onehundredmillion.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onehundredmillion.library.dto.Bookresult;
import com.onehundredmillion.library.dto.NaverResult;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Controller
public class BookresultController {
    @GetMapping("/book/list")
    public String list(String text, Model model) {

    String clientId = "dGv6cZfFAFF4fYpxYN2X";
    String clientSecret = "VXAzS1syXt";

    //String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // JSON 결과
    URI uri = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com")
            .path("/v1/search/book.json")
            .queryParam("query", "스프링부트")
//            .queryParam("query", "IT")
            .queryParam("display", 10)
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

        try {
        result = om.readValue(resp.getBody(), NaverResult.class);
    } catch (JsonMappingException e) {
        e.printStackTrace();
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }

    List<Bookresult> books =result.getItems();	// books를 list.html에 출력 -> model 선언
        model.addAttribute("books", books);

		return "/book/list";
}


}