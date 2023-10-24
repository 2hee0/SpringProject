package com.onehundredmillion.library.controller;


import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.dto.JoinForm;
import com.onehundredmillion.library.dto.LoginForm;
import com.onehundredmillion.library.service.MemberService;
import com.onehundredmillion.library.sessioin.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/join")
    public String createForm(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "join/join";
    }


    @PostMapping("/join")
    public String processJoinForm(@Valid JoinForm joinForm, BindingResult result) {
        if (result.hasErrors()) {
            return "join/join";
        }

        memberService.join(joinForm.toMember());

        return "redirect:/";
    }

    @GetMapping("/joinForm/{userid}/exists")
    public ResponseEntity<Boolean> checkIdDuplicate(@PathVariable String userid) {
        return ResponseEntity.ok(memberService.checkIdDuplicate(userid));
    }


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {

        return "login/login";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
            @RequestParam(defaultValue = "/home") String redirectURL,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/login";
        }
        Member loginMember = memberService.login(loginForm.getLoginId(),
                loginForm.getPassword());
        log.info("login? {}", loginMember);
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/login";
        }
        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        //redirectURL 적용
        return "redirect:" + redirectURL;

    }

    @GetMapping("/join/mypage")
    public String myPage(Model model) {
        return "member/mypage";
    }
}