package com.store.bookstore.service;

import com.store.bookstore.entity.Book;
import com.store.bookstore.repos.BookRepository;
import com.store.bookstore.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Long bookId){
        Book getBook = bookRepository.getByBookId(bookId);
        if (getBook == null){
           return null;
        }return getBook;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Book createBook(BookRequest bookRequest) {
        Book newBook = new Book();
        newBook.setName(bookRequest.getName());
        newBook.setAuthor(bookRequest.getAuthor());
        newBook.setPrice(bookRequest.getPrice());
        newBook.setPicByte(bookRequest.getPicByte());
        return bookRepository.save(newBook);
    }

    public Book editBook(BookRequest bookRequest, Long bookId) {
        Book existingBook = bookRepository.getByBookId(bookId);
        existingBook.setName(bookRequest.getName());
        existingBook.setAuthor(bookRequest.getAuthor());
        existingBook.setPicByte(bookRequest.getPicByte());
        existingBook.setPrice(bookRequest.getPrice());
        return bookRepository.save(existingBook);
    }

    public void deleteBookById(Long bookId) {
       Book bookSelect = bookRepository.getByBookId(bookId);
       if (bookSelect != null){
           bookRepository.deleteById(bookId);
       }
    }
}
