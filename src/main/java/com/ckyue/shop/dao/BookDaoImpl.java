package com.ckyue.shop.dao;

import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Review;
import com.ckyue.shop.exceptions.ShopAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{
    private static final String SQL_ALL = "SELECT * FROM BOOK";
    private static final String SQL_GET_BY_BID = "SELECT * FROM BOOK WHERE BID = ?";
    private static final String SQL_GET_BY_CATEGORY = "SELECT * FROM BOOK WHERE CATEGORY = ?";
    private static final String SQL_GET_REVIEW_BY_BID = "SELECT * FROM REVIEW WHERE BID = ?";
    private static final String SQL_ADD_REVIEW_TO_BID = "INSERT INTO REVIEW (ID, BID, RATING, COMMENT) VALUES(NEXTVAL('REVIEW_SEQ'), ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SQL_ALL, bookRowMapper);
    }

    @Override
    public Book getBookById(String bid) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_BID, new Object[]{bid}, bookRowMapper);
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return jdbcTemplate.query(SQL_GET_BY_CATEGORY, new Object[]{category}, bookRowMapper);
    }

    @Override
    public Integer AddReviewToBook(String bid, Integer rating, String comment) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_ADD_REVIEW_TO_BID, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, bid);
                ps.setInt(2, rating);
                ps.setString(3, comment);
                System.out.println(ps);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID");
        }catch (Exception e) {
            throw new ShopAuthException(e.getMessage());
        }
    }

    @Override
    public List<Review> getReviewByBook(String bid) {
        return jdbcTemplate.query(SQL_GET_REVIEW_BY_BID, new Object[]{bid}, reviewRowMapper);
    }

    private RowMapper<Book> bookRowMapper = ((rs, rowNum) -> {
        return new Book(rs.getString("BID"),
                rs.getString("TITLE"),
                rs.getInt("PRICE"),
                rs.getString("CATEGORY"));
    });

    private RowMapper<Review> reviewRowMapper = ((rs, rowNum) -> {
        return new Review(rs.getInt("ID"),
                rs.getString("BID"),
                rs.getInt("RATING"),
                rs.getString("COMMENT"));
    });
}
