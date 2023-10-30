package com.onehundredmillion.library.admin;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.dto.BookForm;
import com.onehundredmillion.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AddBookController {

    private final BookService bookService;

    @GetMapping(value = "/new")
    public String createForm(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "admin/createBookForm";
    }

    @PostMapping(value = "/new")
    public String create(BookForm bookForm) {

        Book book = new Book();
        book.setTitle(bookForm.getTitle());
        book.setAuthor(bookForm.getAuthor());
//        book.setCategories(.);  //category 어떻게?
        book.setIsbn(bookForm.getIsbn());
        book.setStockQuantity(bookForm.getStockQuantity());

        bookService.saveBooks(book);

        return "redirect:/bookList";    //북 리스트로 경로 리다이렉트

    }
}