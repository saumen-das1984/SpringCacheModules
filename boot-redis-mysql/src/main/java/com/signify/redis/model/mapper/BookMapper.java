package com.signify.redis.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.signify.redis.model.Book;

@Component
public class BookMapper implements RowMapper<Book> {
	
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		book.setDescription(rs.getString("description"));
	    return book;
	}
}
