package com.onehundredmillion.library.controller;

<<<<<<< HEAD
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.service.ReservationService;
import com.onehundredmillion.library.sessioin.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

=======
import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.domain.Reservation;
import com.onehundredmillion.library.domain.ReservationSearch;
import com.onehundredmillion.library.exception.NotEnoughStockException;
import com.onehundredmillion.library.service.BookService;
import com.onehundredmillion.library.service.MemberService;
import com.onehundredmillion.library.service.ReservationService;
import lombok.RequiredArgsConstructor;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
<<<<<<< HEAD

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


=======
    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping(value = "/reserv")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Book> books = bookService.findBooks();
        model.addAttribute("members", members);
        model.addAttribute("items", books);
        return "/mypage";   //v??? ?
    }
    @PostMapping(value = "/reserv")
    public String reservation(@RequestParam("memberId") Long memberId,
                        @RequestParam("bookId") Long bookId, @RequestParam("count") int count) throws NotEnoughStockException {
        reservationService.reservation(memberId, bookId,count);
        return "redirect:/bookList";
    }

    @GetMapping(value = "/reservs")
    public String bookList(@ModelAttribute("reservationSearch") Reservation
                                    reservation, Model model) {
        List<Reservation> reservations = reservationService.findAll(reservation);
        model.addAttribute("reservation", reservations);
        return "book/BookList";
    }

    @PostMapping(value = "/books/{reservId}/cancel")
    public String cancelReserv(@PathVariable("reservId") Long reservId) {
        reservationService.cancelReservation(reservId);
        return "redirect:/bookList";
    }
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
}