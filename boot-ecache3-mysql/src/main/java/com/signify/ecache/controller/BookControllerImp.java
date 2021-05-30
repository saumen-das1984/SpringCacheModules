package com.signify.ecache.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.signify.ecache.model.Book;
import com.signify.ecache.service.BookService;

//import lombok.extern.log4j.Log4j2;

//@Log4j2
@RestController
@RequestMapping(value = { "/book" })
public class BookControllerImp implements BookController{
	
	@Autowired
    private BookService bookService;

	@Override
	@GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book getBookById(@RequestParam(name = "book_id", defaultValue = "0") int bookId) {
		return bookService.findById(bookId);
	}

	@Override
	@GetMapping(value = "/getByTitle", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book getBookByTitle(@RequestParam(name = "book_title", defaultValue = "NA") String bookTitle) {
		return bookService.findByTitle(bookTitle);
	}

	@Override
	@GetMapping(value = "/getAllBooks", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getAllBooks() {
		List<Book> book_list = new ArrayList<>();
		try {
			book_list = bookService.getAllBooks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book_list;
	}

	@Override
	@PostMapping(value = "/insertBook", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book insertBook(@RequestBody Book book) {
		return bookService.insertBook(book);
	}

	@Override
	@PutMapping(value = "/updateBook", produces = MediaType.APPLICATION_JSON_VALUE)
	public int updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}

	@Override
	@PutMapping(value = "/deleteBook", produces = MediaType.APPLICATION_JSON_VALUE)
	public int deleteBook(@RequestParam(name = "book_id", defaultValue = "0") int bookId) {
		return bookService.deleteBook(bookId);
	}

}
