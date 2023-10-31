package com.onehundredmillion.library.admin;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onehundredmillion.library.domain.Book;
import com.onehundredmillion.library.dto.BookForm;
import com.onehundredmillion.library.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AddBookController {

	private final BookService bookService;

<<<<<<< HEAD
	@RequestMapping("/adminmain")
=======
	@GetMapping("/adminmain")
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	public String adminForm() {
		return "admin/admin_main";
	}

<<<<<<< HEAD
	@GetMapping("/bookList")
	public String adminList(Model model) {
		List<Book> books = bookService.findBooks();
		model.addAttribute("books", books);
		return "admin/admin_bookList";
	}

=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	@GetMapping(value = "/new")
	public String createForm(Model model) {
		model.addAttribute("bookForm", new BookForm());
		return "admin/admin_bookRegist";
	}

	@PostMapping(value = "/new")
	public String create(BookForm bookForm) {

		Book book = new Book();
<<<<<<< HEAD
		book.setTitle(bookForm.getTitle());
		book.setAuthor(bookForm.getAuthor());
=======
		book.setName(bookForm.getTitle());
		book.setAuthor(bookForm.getAuthors());
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
//        book.setCategories(.);  //category 어떻게?
		book.setIsbn(bookForm.getIsbn());
		book.setStockQuantity(bookForm.getStockQuantity());
		book.setPublisher(bookForm.getPublisher());
<<<<<<< HEAD
<<<<<<< HEAD
		book.setPubdate(bookForm.getPubdate());
		book.setImage(bookForm.getImage());
		book.setDescription(bookForm.getDescription());

		bookService.saveBooks(book);

		return "/admin/bookList"; // 북 리스트로 경로 리다이렉트

	}

	@GetMapping(value = "/update/{bookId}")
	public String updatemainForm(Model model, @PathVariable Long bookId) {
		Book books = bookService.findOne(bookId);
		model.addAttribute("book", books);
		return "admin/admin_book";
	}

=======
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
//		book.setDatetime(bookForm.getDatetime());
		book.setContents(bookForm.getContents());
		book.setThumbnail(bookForm.getThumbnail());

		bookService.saveBooks(book);

		return "redirect:/adminmain"; // 북 리스트로 경로 리다이렉트

	}

<<<<<<< HEAD
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	@GetMapping(value = "/updateadmin/{bookId}")
	public String updateForm(Model model, @PathVariable Long bookId) {
		Book books = bookService.findOne(bookId);
		model.addAttribute("book", books);
		return "admin/admin_bookEdit";
	}

	@PostMapping(value = "/updateadmin/{bookId}")
<<<<<<< HEAD
<<<<<<< HEAD
	public String updatebook2(@PathVariable Long bookId, @ModelAttribute Book book, Model model) {
		bookService.updateBook2(bookId, book.getDescription(), book.getStockQuantity());
		Book books = bookService.findOne(bookId);
		model.addAttribute("book", books);
		return "admin/admin_book";
=======
	public String updatebook(@PathVariable Long bookId, @ModelAttribute Book book) {
		bookService.updateBook(bookId, book.getContents(), book.getStockQuantity());
		return "redirect:/admin_bookEdi";
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
=======
	public String updatebook(@PathVariable Long bookId, @ModelAttribute Book book) {
		bookService.updateBook(bookId, book.getContents(), book.getStockQuantity());
		return "redirect:/admin_bookEdi";
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	}
}