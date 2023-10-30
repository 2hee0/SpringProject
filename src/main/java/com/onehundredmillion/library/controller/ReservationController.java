package com.onehundredmillion.library.controller;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.domain.Reservation;
import com.onehundredmillion.library.domain.ReservationSearch;
import com.onehundredmillion.library.exception.NotEnoughStockException;
import com.onehundredmillion.library.service.BookService;
import com.onehundredmillion.library.service.MemberService;
import com.onehundredmillion.library.service.RentService;
import com.onehundredmillion.library.service.ReservationService;
import com.onehundredmillion.library.sessioin.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;
    private final BookService bookService;

 /*   @GetMapping(value = "/reserv")
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
    }*/



    @GetMapping(value = "/reservs")
    public String bookList(@ModelAttribute("reservationSearch") Reservation
                                    reservation, Model model) {
        List<Reservation> reservations = reservationService.findAll(reservation);
        model.addAttribute("reservation", reservations);
        return "book/BookList";
    }

    @GetMapping("/reserv/{bookId}")
    public String reservation(Model model, @PathVariable("bookId")Long bookId , HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        System.out.println("현재로그인된 멤버의 이름:"+member.getName());
        try {
            reservationService.reservation(member,bookId);
            System.out.println("예약 완료");
        } catch (NotEnoughStockException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }
}