package com.ckyue.shop.dao;

import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Review;

import java.util.List;

public interface BookDao {
    List<Book> getAllBooks();

    Book getBookById(String bid);

    List<Book> getBooksByCategory(String category);

    Integer AddReviewToBook(String bid, Integer rating, String comment);

    List<Review> getReviewByBook(String bid);
}
