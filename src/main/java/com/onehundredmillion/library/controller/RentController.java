package com.onehundredmillion.library.controller;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.exception.NotEnoughStockException;
import com.onehundredmillion.library.service.RentService;
import com.onehundredmillion.library.sessioin.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
=======
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.onehundredmillion.library.dto.JoinForm;
import com.onehundredmillion.library.service.RentService;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

@Controller
@RequiredArgsConstructor
public class RentController {
	
	private final RentService rentService;
<<<<<<< HEAD
	// 대여하기
	@GetMapping(value = "/book/{bookId}/rent")
    public String rent(Model model, @PathVariable("bookId")Long bookId ,HttpServletRequest request) throws NotEnoughStockException {
        
		HttpSession session = request.getSession();
//		세션에서 현재 로그인된 멤버 조회.
		Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
		System.out.println("현재로그인된 멤버의 이름:"+member.getName());
	
		if(rentService.rent(member, bookId) != (long)0) {
			System.out.println("대여하기 성공");
			return "redirect:/books?success=renttrue";
		}
		else {
			return "redirect:/books?success=rentfalse";
		}
    }
	
	// 반납하기
    @GetMapping(value = "/book/{rentId}/cancel")
    public String returnBook(@PathVariable("rentId")Long rentId) {
    	rentService.returnBook(rentId);
		return "redirect:/mypage";
    }
=======
	
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	
}