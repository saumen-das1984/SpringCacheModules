package com.signify.cacheresolver.controller;

import java.util.List;

import com.signify.cacheresolver.model.Book;

public interface BookController {
	public Book getBookById(int bookId);
	public Book getBookByTitle(String bookTitle);
	public List<Book> getAllBooks();
	public Book insertBook(Book book);
	public int updateBook(Book book);
	public int deleteBook(int bookId);
}
