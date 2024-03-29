package com.DTOexample.services;

import com.DTOexample.DTO.BookDTO;
import com.DTOexample.entities.Book;
import com.DTOexample.entities.enums.RecordStatusEnum;
import com.DTOexample.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public BookDTO createBook(Book book){
        Book booksaved = repository.save(book);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        return bookDTO;
    }

    public List<BookDTO> getAllBook(){
        List<Book> bookList = repository.findAllActive();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for(Book book : bookList){
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setIsbn(book.getIsbn());
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }
    public Optional<BookDTO> getBookById(Long id){
        Optional<Book> bookOptional = repository.findById(id);
        if(bookOptional.isPresent()) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(bookOptional.get().getId());
            bookDTO.setTitle(bookOptional.get().getTitle());
            bookDTO.setAuthor(bookOptional.get().getAuthor());
            bookDTO.setIsbn(bookOptional.get().getIsbn());
            return Optional.of(bookDTO);
        }else {
            return Optional.empty();
        }
    }
    public Optional<BookDTO> updateBook(Long id,Book book){
        Optional<Book> bookOptional = repository.findById(id);
        if(bookOptional.isPresent()){
            bookOptional.get().setTitle(book.getTitle());
            bookOptional.get().setAuthor(book.getAuthor());
            bookOptional.get().setIsbn(book.getIsbn());



            Book savedBook = repository.save(bookOptional.get());

            BookDTO bookDTO = new BookDTO();

            bookDTO.setId(savedBook.getId());
            bookDTO.setTitle(savedBook.getTitle());
            bookDTO.setAuthor(savedBook.getAuthor());
            bookDTO.setIsbn(savedBook.getIsbn());

            return Optional.of(bookDTO);
        }else {
            return Optional.empty();
        }
    }
    public Optional<BookDTO> deleteBook(Long id){
        Optional<Book> bookOptional = repository.findById(id);
        if(bookOptional.isPresent()){
            bookOptional.get().setRecordStatusEnum(RecordStatusEnum.D);

            Book savedBook = repository.save(bookOptional.get());

            BookDTO bookDTO = new BookDTO();

            bookDTO.setId(savedBook.getId());
            bookDTO.setTitle(savedBook.getTitle());
            bookDTO.setAuthor(savedBook.getAuthor());
            bookDTO.setIsbn(savedBook.getIsbn());

            return Optional.of(bookDTO);
        }else {
            return Optional.empty();
        }

    }



}
