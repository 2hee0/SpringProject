package com.onehundredmillion.library.controller;

import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.service.ReservationService;
import com.onehundredmillion.library.sessioin.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    // 예약하기
    @GetMapping(value = "/book/{bookId}/reserve")
    public String reserve(Model model, @PathVariable("bookId")Long bookId , HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        System.out.println("현재로그인된 멤버의 이름:"+member.getName());
        
		if(reservationService.reserve(member, bookId) != (long)0) {
			System.out.println("예약하기 성공");
			return "redirect:/books?success=reservetrue";
		}else {
			return "redirect:/books?success=reservefalse";
		}

    }
    
	// 예약취소
    @GetMapping(value = "/book/cancel")
    public String cancelBook(@RequestParam("reservationId") Long reservationId) {
        // reservationId를 받아와서 처리
        reservationService.cancelBook(reservationId);
        return "redirect:/mypage";
    }


}