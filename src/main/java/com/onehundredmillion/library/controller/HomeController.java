package com.onehundredmillion.library.controller;

<<<<<<< HEAD
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.dto.LoginForm;
import com.onehundredmillion.library.sessioin.SessionConst;
=======
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.sessioin.SessionConst;

>>>>>>> fed9438 (유저 정보 업데이트 최종완성)
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
<<<<<<< HEAD
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
=======
>>>>>>> fed9438 (유저 정보 업데이트 최종완성)

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

<<<<<<< HEAD
    @GetMapping("/")
    public String homeLogin(
    @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
    Member loginMember, Model model)

    {
        // 세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "home";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
=======
	@GetMapping("/")
	public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
			Model model)

	{
		if (loginMember != null) {
			model.addAttribute("loginMember", loginMember);
		}

		// 세션에 회원 데이터가 없으면 home
		if (loginMember == null) {
			return "home";
		}
		model.addAttribute("member", loginMember);
		return "home";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
>>>>>>> fed9438 (유저 정보 업데이트 최종완성)
}