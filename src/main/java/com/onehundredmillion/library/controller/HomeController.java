package com.onehundredmillion.library.controller;

import com.onehundredmillion.library.domain.Booksearch;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.dto.LoginForm;
import com.onehundredmillion.library.sessioin.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final Booksearch booksearch;

    @GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
            Member loginMember, Model model) {
        if (loginMember != null) {
            model.addAttribute("loginMember", loginMember);
            return "home";
        }
        model.addAttribute("member", loginMember);


        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";


        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("query1", "진짜 쓰는 실무 엑셀");
        queryMap.put("query2", "모던 자바 스크립트 Deep dive");
        queryMap.put("query3", "데이터베이스 개론");
        queryMap.put("query4", "만들면서 배우는 생성 AI");
        queryMap.put("query5", "CS 전공지식 노트");
        queryMap.put("query6", "정보처리기사");
        queryMap.put("query7", "유니티 게임 프로그래밍");
        queryMap.put("query8", "피, 땀, 픽셀");
        queryMap.put("query9", "진짜 쓰는 프리미어 프로 영상 편집");
        queryMap.put("query10", "리눅스 커널의 구조와 원리");
        queryMap.put("query11", "모두를 위한 클라우드 컴퓨팅");
        queryMap.put("query12", "IT 엔지니어를 위한");
        queryMap.put("query13", "세상에서 가장 쉬운 코딩책");
        queryMap.put("query14", "IT 지식 입문서");
        queryMap.put("query15", "챗GPT");
        queryMap.put("query16", "유닉스 & 리눅스");
        queryMap.put("query17", "웹 개발자 로드맵");

        String query1 = queryMap.get("query1");
        String query2 = queryMap.get("query2");
        String query3 = queryMap.get("query3");
        String query4 = queryMap.get("query4");
        String query5 = queryMap.get("query5");
        String query6 = queryMap.get("query6");
        String query7 = queryMap.get("query7");
        String query8 = queryMap.get("query8");
        String query9 = queryMap.get("query9");
        String query10 = queryMap.get("query10");
        String query11 = queryMap.get("query11");
        String query12 = queryMap.get("query12");
        String query13 = queryMap.get("query13");
        String query14 = queryMap.get("query14");
        String query15 = queryMap.get("query15");
        String query16 = queryMap.get("query16");
        String query17 = queryMap.get("query17");


        ResponseEntity<String> resp1 = booksearch.callNaverApi(clientId, clientSecret, query1);
        ResponseEntity<String> resp2 = booksearch.callNaverApi(clientId, clientSecret, query2);
        ResponseEntity<String> resp3 = booksearch.callNaverApi(clientId, clientSecret, query3);
        ResponseEntity<String> resp4 = booksearch.callNaverApi(clientId, clientSecret, query4);
        ResponseEntity<String> resp5 = booksearch.callNaverApi(clientId, clientSecret, query5);
        ResponseEntity<String> resp6 = booksearch.callNaverApi(clientId, clientSecret, query6);
        ResponseEntity<String> resp7 = booksearch.callNaverApi(clientId, clientSecret, query7);
        ResponseEntity<String> resp8 = booksearch.callNaverApi(clientId, clientSecret, query8);
        ResponseEntity<String> resp9 = booksearch.callNaverApi(clientId, clientSecret, query9);
        ResponseEntity<String> resp10 = booksearch.callNaverApi(clientId, clientSecret, query10);
        ResponseEntity<String> resp11 = booksearch.callNaverApi(clientId, clientSecret, query11);
        ResponseEntity<String> resp12 = booksearch.callNaverApi(clientId, clientSecret, query12);
        ResponseEntity<String> resp13 = booksearch.callNaverApi(clientId, clientSecret, query13);
        ResponseEntity<String> resp14 = booksearch.callNaverApi(clientId, clientSecret, query14);
        ResponseEntity<String> resp15 = booksearch.callNaverApi(clientId, clientSecret, query15);
        ResponseEntity<String> resp16 = booksearch.callNaverApi(clientId, clientSecret, query16);
        ResponseEntity<String> resp17 = booksearch.callNaverApi(clientId, clientSecret, query17);


        booksearch.resultBook(resp1.getBody());
        booksearch.resultBook(resp2.getBody());
        booksearch.resultBook(resp3.getBody());
        booksearch.resultBook(resp4.getBody());
        booksearch.resultBook(resp5.getBody());
        booksearch.resultBook(resp6.getBody());
        booksearch.resultBook(resp7.getBody());
        booksearch.resultBook(resp8.getBody());
        booksearch.resultBook(resp9.getBody());
        booksearch.resultBook(resp10.getBody());
        booksearch.resultBook(resp11.getBody());
        booksearch.resultBook(resp12.getBody());
        booksearch.resultBook(resp13.getBody());
        booksearch.resultBook(resp14.getBody());
        booksearch.resultBook(resp15.getBody());
        booksearch.resultBook(resp16.getBody());
        booksearch.resultBook(resp17.getBody());
        return "home";

    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "home";
    }
}