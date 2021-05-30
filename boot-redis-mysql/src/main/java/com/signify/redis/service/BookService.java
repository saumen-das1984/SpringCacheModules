package com.signify.redis.service;

import java.util.List;

import com.signify.redis.model.Book;

public interface BookService {
	public Book findById(int bookId);
	public Book findByTitle(String bookTitle);
	public List<Book> getAllBooks();
	public Book insertBook(Book book);
	public int updateBook(Book book);
	public int deleteBook(int bookId);
}
