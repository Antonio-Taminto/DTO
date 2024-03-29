package com.DTOexample.controllers;

import com.DTOexample.DTO.BookDTO;
import com.DTOexample.entities.Book;
import com.DTOexample.entities.enums.RecordStatusEnum;
import com.DTOexample.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping("/create")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){

        Book book = new Book();

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        BookDTO savedBookDTO = service.createBook(book);
        return ResponseEntity.ok().body(savedBookDTO);
    }
    @GetMapping("/findall")
    public ResponseEntity<List<BookDTO>> getAllBook(){
        List<BookDTO> bookDTOList = service.getAllBook();
        return ResponseEntity.ok().body(bookDTOList);
    }
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
        Optional<BookDTO> bookDTOOptional = service.getBookById(id);
        if(bookDTOOptional.isPresent()) {
            return ResponseEntity.ok().body(bookDTOOptional.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,@RequestBody BookDTO bookDTO){
        Book book = new Book();

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());

        Optional<BookDTO> updatedBookDTO = service.updateBook(id,book);

        if(updatedBookDTO.isPresent()) {
            return ResponseEntity.ok().body(updatedBookDTO.get());
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id){
        Optional<BookDTO> deletedBookDTO = service.deleteBook(id);
        if(deletedBookDTO.isPresent()){
            return ResponseEntity.ok().body(deletedBookDTO.get());
        }else {
            return ResponseEntity.badRequest().build();
        }

    }
}
