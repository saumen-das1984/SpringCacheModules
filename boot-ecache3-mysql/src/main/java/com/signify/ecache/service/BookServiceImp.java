package com.signify.ecache.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.signify.ecache.config.BookQueryConfig;
import com.signify.ecache.model.Book;
import com.signify.ecache.model.mapper.BookMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class BookServiceImp implements BookService {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	BookQueryConfig bookQueryConfig;

	@SuppressWarnings("deprecation")
	@Override
	@Cacheable(cacheNames = "books_id", key = "#bookId")
	public Book findById(int bookId) {
		String fetchByBookIdQuery = bookQueryConfig.getSelectbyId();
		log.info("fetchByBookIdQuery : {}",fetchByBookIdQuery);
		Book book = jdbcTemplate.queryForObject(fetchByBookIdQuery, new Object[]{bookId}, new BookMapper());
		return book;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Cacheable(cacheNames ="books_title", key = "#bookTitle")
	public Book findByTitle(String bookTitle) {
		String fetchByBookTitleQuery = bookQueryConfig.getSelectbyTitle();
		log.info("fetchByBookTitleQuery : {}",fetchByBookTitleQuery);
		Book book = jdbcTemplate.queryForObject(fetchByBookTitleQuery, new Object[]{bookTitle}, new BookMapper());
		return book;
	}

	@Override
	@Cacheable(cacheNames = "books", key = "#root.methodName")
	public List<Book> getAllBooks() {
		String fetchAllBooksQuery = bookQueryConfig.getSelect();
		log.info("fetchAllBooksQuery : {}",fetchAllBooksQuery);
		List<Book> book_list = jdbcTemplate.query(fetchAllBooksQuery,  new BookMapper());
		return book_list;
	}

	@Override
	public Book insertBook(Book book) {
		KeyHolder holder = new GeneratedKeyHolder();
		String insertBookQuery = bookQueryConfig.getInsert();
		log.info("insertBookQuery : {}",insertBookQuery);
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(insertBookQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, book.getTitle());
				ps.setString(2, book.getDescription());
				return ps;
			}
		}, holder);

		int bookId = holder.getKey().intValue();
		book.setId(bookId);
		return book;
	}

	@Override
	public int updateBook(Book book) {
		int updCpunt = 0;
		String updateBookQuery = bookQueryConfig.getUpdate();
		log.info("updateBookQuery : {}",updateBookQuery);
		updCpunt =  jdbcTemplate.update(updateBookQuery,new Object[]{book.getTitle() ,book.getDescription(), book.getId()});
		return updCpunt;
	}

	@Override
	public int deleteBook(int bookId) {
		int updCpunt = 0;
		String deleteBookQuery = bookQueryConfig.getDelete();
		log.info("deleteBookQuery : {}",deleteBookQuery);
		updCpunt =  jdbcTemplate.update(deleteBookQuery,new Object[]{bookId});
		return updCpunt;
	}

}
