package com.onehundredmillion.library.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.dto.JoinForm;
import com.onehundredmillion.library.exception.NotEnoughStockException;
import com.onehundredmillion.library.repository.RentRepository;
import com.onehundredmillion.library.service.BookService;
import com.onehundredmillion.library.service.MemberService;
import com.onehundredmillion.library.service.RentService;
import com.onehundredmillion.library.sessioin.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class RentController {
	
	private final RentService rentService;
	
	@GetMapping("/rent/{bookId}")
    public String rent(Model model, @PathVariable("bookId")Long bookId ,HttpServletRequest request) {
        
		HttpSession session = request.getSession();
//		세션에서 현재 로그인된 멤버 조회.
		Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
		System.out.println("현재로그인된 멤버의 이름:"+member.getName());
		try {
			rentService.rent(member,bookId);
			System.out.println("대여하기 성공");
		} catch (NotEnoughStockException e) {
			e.printStackTrace();
		}
		
        return "redirect:/";
    }
	
	
}

















