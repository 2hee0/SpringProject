package com.onehundredmillion.library.controller;

import com.onehundredmillion.library.domain.*;
import com.onehundredmillion.library.service.LikeService;
import com.onehundredmillion.library.service.RentService;
import com.onehundredmillion.library.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.onehundredmillion.library.dto.JoinForm;
import com.onehundredmillion.library.dto.LoginForm;
import com.onehundredmillion.library.dto.MemberUpdateForm;
import com.onehundredmillion.library.service.MemberService;
import com.onehundredmillion.library.sessioin.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final RentService rentService;
    private final ReservationService reservationService;
    private final LikeService likeService;

    @GetMapping(value = "/join")
    public String createForm(Model model) {
        model.addAttribute("joinForm", new JoinForm());
        return "join/join";
    }

    @PostMapping("/join")
    public String processJoinForm(@Valid JoinForm joinForm, BindingResult result) {

        if (memberService.checkIdDuplicate(joinForm.getUserId())) {
            result.rejectValue("userId", "userId", "사용중인 아이디입니다.");
        }
        if (joinForm.getPasswordConfirm() == null) {
            result.rejectValue("passwordConfirm", "passwordConfirm", "비밀번호 재입력을 해주세요.");
        } else if (!joinForm.isPasswordMatch()) {
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

        // 로그인 성공 처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String myBook(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            Member saveMember = memberService.findOne(loginMember.getId());
            List<Rent> rents = rentService.findAll(saveMember);
            model.addAttribute("rents", rents);
            List<Reservation> reservations = reservationService.findAll(saveMember);
            model.addAttribute("reservations", reservations);
            List<Like> likes = likeService.findAll(saveMember);
            model.addAttribute("likes", likes);
        }
        return "member/mypage";
    }

    @GetMapping("/userinfoForm")
    public String userInfo(Model model, HttpSession session) {
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            // Fetch additional data for the logged-in user from the database
            Member userDataFromDatabase = memberService.findOne(loginMember.getId());
            model.addAttribute("loginMember", userDataFromDatabase);
        }
        return "member/userinfo";
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