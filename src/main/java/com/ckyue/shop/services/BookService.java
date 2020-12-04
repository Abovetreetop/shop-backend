package com.ckyue.shop.services;

import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Review;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(String bid);

    Integer addReviewToBook(String bid, Integer rating, String comment);

    List<Review> getReviewByBook(String bid);

    List<Book> getBooksByCategory(String category);
}
