package com.onehundredmillion.library.controller;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.service.BookService;
=======
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.onehundredmillion.library.domain.Member;
<<<<<<< HEAD
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
import com.onehundredmillion.library.sessioin.SessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

<<<<<<< HEAD
<<<<<<< HEAD
	private final BookService bookService;

=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	@GetMapping("/")
	public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
			Model model)

<<<<<<< HEAD
=======
	@GetMapping("/")
	public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
			Model model)

>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	{
		if (loginMember != null) {
			model.addAttribute("loginMember", loginMember);
		} else {
			// 세션에 회원 데이터가 없으면 home
			return "home";
		}
<<<<<<< HEAD
<<<<<<< HEAD
		List<Book> books = bookService.findBooks();
		model.addAttribute("books", books);
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
		model.addAttribute("member", loginMember);
		return "home";
	}

<<<<<<< HEAD
<<<<<<< HEAD
	@GetMapping("/logout")
=======
	@PostMapping("/logout")
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
	@PostMapping("/logout")
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
<<<<<<< HEAD
<<<<<<< HEAD
		return "home";
=======
		return "redirect:/";
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
		return "redirect:/";
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	}
}