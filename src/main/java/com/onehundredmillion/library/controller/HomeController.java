package com.onehundredmillion.library.controller;

import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.dto.LoginForm;
import com.onehundredmillion.library.sessioin.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String homeLogin(
    @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
    Member loginMember, Model model)

    {
    	if (loginMember != null) {
			model.addAttribute("loginMember", loginMember);
		}
     /*   if (loginMember == null) {
            return "home";
        }*/
        model.addAttribute("member", loginMember);
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