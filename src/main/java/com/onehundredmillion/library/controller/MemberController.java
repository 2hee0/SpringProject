package com.onehundredmillion.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.onehundredmillion.library.domain.Booksearch;
import com.onehundredmillion.library.domain.Like;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.domain.Rent;
import com.onehundredmillion.library.domain.Reservation;
import com.onehundredmillion.library.dto.JoinForm;
import com.onehundredmillion.library.dto.LoginForm;
import com.onehundredmillion.library.dto.MemberUpdateForm;
import com.onehundredmillion.library.service.LikeService;
import com.onehundredmillion.library.service.MemberService;
import com.onehundredmillion.library.service.RentService;
import com.onehundredmillion.library.service.ReservationService;
import com.onehundredmillion.library.sessioin.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final RentService rentService;
    private final ReservationService reservationService;
    private final LikeService likeService;
    private final Booksearch booksearch;

    @GetMapping(value = "/join")
    public String createForm(Model model) {
        model.addAttribute("joinForm", new JoinForm());

        String clientId = "dGv6cZfFAFF4fYpxYN2X";
        String clientSecret = "VXAzS1syXt";

        Map<String, String> queryMap = new HashMap<>();

        queryMap.put("query1", "모던 자바스크립트 Deep Dive");
        queryMap.put("query2", "데이터베이스 개론 (제3판)");
        queryMap.put("query3", "만들면서 배우는 생성 AI");
        queryMap.put("query4", "면접을 위한 CS 전공지식 노트");
        queryMap.put("query5", "웹디자인기능사 실기 7일 완성");
        queryMap.put("query6", "2023 이기적 정보처리기능사 필기 기출문제집");
        queryMap.put("query7", "레트로의 유니티 게임 프로그래밍 에센스 (C#으로 배우는 입문부터 4가지 게임 제작까지)");
        queryMap.put("query8", "피, 땀, 픽셀");
        queryMap.put("query9", "진짜 쓰는 프리미어 프로 영상 편집");
        queryMap.put("query10", "디버깅을 통해 배우는 리눅스 커널의 구조와 원리(2)");
        queryMap.put("query11", "모두를 위한 클라우드 컴퓨팅");
        queryMap.put("query12", "IT 엔지니어를 위한 네트워크 입문");
        queryMap.put("query13", "세상에서 가장 쉬운 코딩책 (6개월 만에 비전공자에서 개발자가 된 위캔코딩의 기초 코딩 수업)");
        queryMap.put("query14", "혼자 공부하는 컴퓨터구조");
        queryMap.put("query15", "진짜 챗GPT 활용법 (엑셀 활용법부터 블로그 자동화, 유튜브 콘텐츠 생성, 미드저니와 ChatGPT API 사용법까지)");
        queryMap.put("query16", "누구나 가능한 유닉스&리눅스 2편");
        queryMap.put("query17", "웹 개발자 로드맵 (수치와 데이터로 증명하는 가이드북)");

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
        return "join/join";
    }

    @PostMapping("/join")
    public String processJoinForm(@Valid JoinForm joinForm, BindingResult result) {

        if (memberService.checkIdDuplicate(joinForm.getUserId())) {
            result.rejectValue("userId", "userId", "사용중인 아이디입니다.");
        }
        if (joinForm.getPasswordConfirm() == null) {
            result.rejectValue("passwordConfirm", "passwordConfirm", "비밀번호 재입력을 해주세요.");
        }
        if (!joinForm.isPasswordMatch()) {
            result.rejectValue("passwordConfirm", "passwordConfirm", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        if (result.hasErrors()) {
            return "join/join";
        }

        memberService.join(joinForm.toMember());
        return "redirect:/login";
    }

    @GetMapping("/joinForm/{userId}/exists")
    public ResponseEntity<Boolean> checkIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.checkIdDuplicate(userId));
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {

        return "login/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/login";
        }
        Member loginMember = memberService.login(loginForm.getUserId(), loginForm.getPassword());
        log.info("login? {}", loginMember);
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/login";
        }
        if ("admin".equals(loginMember.getUserId())) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
            return "redirect:/adminmain";
        }

        // 로그인 성공 처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myBook(Model model, HttpSession session, HttpServletRequest request) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            Member userDataFromDatabase = memberService.findOne(loginMember.getId());
            model.addAttribute("loginMember", userDataFromDatabase);
            Member saveMember = memberService.findOne(loginMember.getId());
            List<Rent> rents = rentService.findAll(saveMember);
            model.addAttribute("rents", rents);
            List<Reservation> reservations = reservationService.findAll(saveMember);
            model.addAttribute("reservations", reservations);
            List<Like> likes = likeService.findAll(saveMember);
            model.addAttribute("likes", likes);

            return "member/mypage";
        }
        return "redirect:/login";

    }

    @GetMapping("/userinfoForm")
    public String userInfo(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            // Fetch additional data for the logged-in user from the database
            Member userDataFromDatabase = memberService.findOne(loginMember.getId());
            model.addAttribute("loginMember", userDataFromDatabase);
            return "member/userinfo";
        }
        return "redirect:/login";
    }

    @PostMapping("/userinfo/{Id}")
    public String updateMember(@PathVariable Long Id,
                               @ModelAttribute("loginMember") @Valid MemberUpdateForm loginMember, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (!loginMember.isPasswordMatch()) {
                bindingResult.rejectValue("passwordConfirm", "passwordConfirm", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                return "/userinfoForm";
            }
        }
        if (bindingResult.hasErrors()) {
            return "/userinfoForm";
        }

        memberService.updateMember(Id, loginMember);
        return "redirect:/userinfoForm";
    }

    @GetMapping("/explain")
    public String explainPage() {

        return "explain/explain";
    }
}