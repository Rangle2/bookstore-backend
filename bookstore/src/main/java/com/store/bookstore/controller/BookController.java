package com.store.bookstore.controller;

import com.store.bookstore.entity.Book;
import com.store.bookstore.repos.BookRepository;
import com.store.bookstore.request.BookRequest;
import com.store.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get/{bookId}")
    public Book getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/get")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody BookRequest bookRequest){
        return bookService.createBook(bookRequest);
    }

    @PutMapping("/edit/{bookId}")
    public Book editBook(@RequestBody BookRequest bookRequest, @PathVariable Long bookId){
        return bookService.editBook(bookRequest, bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        bookService.deleteBookById(bookId);
    }
}
