package com.ckyue.shop.controllers;

import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Review;
import com.ckyue.shop.entities.User;
import com.ckyue.shop.services.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/catalog")
    @ResponseBody
    public ResponseEntity<List<Book>> catalog(@RequestParam String category) {
        if(category == null) {
            List<Book> books = bookService.getAllBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        List<Book> books = bookService.getBooksByCategory(category);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Book> findBookById(@RequestParam String bid) {
        Book book = bookService.getBookById(bid);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/review")
    @ResponseBody
    public ResponseEntity<List<Review>> getReview(@RequestParam String bid) {
        List<Review> reviews = bookService.getReviewByBook(bid);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/review/add")
    @ResponseBody
    public ResponseEntity<String> addReview(@RequestBody Map<String, Object> reviewMap) {
        String bid = (String) reviewMap.get("bid");
        Integer rating = (Integer) reviewMap.get("rating");
        String comment = (String) reviewMap.get("comment");
        Integer review_id = bookService.addReviewToBook(bid, rating, comment);
        String ret = "Added new review id: " + review_id.toString() + " to book bid: " + bid;
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }
}
