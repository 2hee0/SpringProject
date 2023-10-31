package com.onehundredmillion.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.service.LikeService;
import com.onehundredmillion.library.sessioin.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LikeController {
	private final LikeService likeService;
	
	// 찜하기
    @GetMapping(value = "/book/{bookId}/like")
    public String like(Model model, @PathVariable("bookId")Long bookId , HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        System.out.println("현재로그인된 멤버의 이름:"+member.getName());
        
		if(likeService.like(member, bookId) != (long)0) {
			System.out.println("찜하기 성공");
			return "redirect:/books?success=liketrue";
		}else {
			return "redirect:/books?success=likefalse";
		}

    }
    
	// 찜취소
    @GetMapping(value = "/book/{likeId}/dislike")
    public String dislikeBook(@PathVariable("likeId") Long likeId) {
        // likeId를 받아와서 처리
    	likeService.dislikeBook(likeId);
        return "redirect:/mypage";
    }
}
