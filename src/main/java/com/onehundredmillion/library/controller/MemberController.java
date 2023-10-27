package com.onehundredmillion.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/join")
	public String createForm(Model model) {
		model.addAttribute("joinForm", new JoinForm());
		return "join/join";
	}

	@PostMapping("/join")
	public String processJoinForm(@Valid JoinForm joinForm, BindingResult result) {
		
		if (joinForm.getPasswordConfirm() == null) {
			result.rejectValue("passwordConfirm", "passwordConfirm", "비밀번호확인을 해주세요.");
		}
		
		if (!joinForm.isPasswordMatch()) {
			result.rejectValue("passwordConfirm", "passwordConfirm", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
		}
		
		if (result.hasErrors()) {
			return "join/join";
		}
		
		memberService.join(joinForm.toMember());

		return "redirect:/";
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

		HttpSession session = request.getSession();
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

		return "redirect:/";
	}

	@GetMapping("/join/mypage")
	public String myPage(Model model) {
		return "member/mypage";
	}

	@GetMapping("/logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();

		session.removeAttribute(SessionConst.LOGIN_MEMBER);
		session.removeAttribute("member.name");

		return "redirect:/";
	}

	@GetMapping("/userinfo")
	public String userInfo(Model model, HttpSession session) {
		Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
		if (loginMember != null) {
			// Fetch additional data for the logged-in user from the database
			Member userDataFromDatabase = memberService.findOne(loginMember.getId());
			model.addAttribute("loginMember", userDataFromDatabase);
		}
		return "member/userinfo";
	}

	@PostMapping("/updateUser/{id}")
	public String updateUser(@ModelAttribute("form") Member member, @PathVariable Long id) {

		memberService.updateUser(id, member);

		return "redirect:/userinfo";
	}
}