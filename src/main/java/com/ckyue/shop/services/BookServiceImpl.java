package com.ckyue.shop.services;

import com.ckyue.shop.dao.BookDao;
import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService{
    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public Book getBookById(String bid) {
        return bookDao.getBookById(bid);
    }

    @Override
    public Integer addReviewToBook(String bid, Integer rating, String comment) {
        return bookDao.AddReviewToBook(bid, rating, comment);
    }

    @Override
    public List<Review> getReviewByBook(String bid) {
        return bookDao.getReviewByBook(bid);
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return bookDao.getBooksByCategory(category);
    }
}
