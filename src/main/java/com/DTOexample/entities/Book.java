package com.DTOexample.entities;


import com.DTOexample.entities.enums.RecordStatusEnum;
import jakarta.persistence.*;


@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "record_status")
    private RecordStatusEnum recordStatusEnum = RecordStatusEnum.A;

    public Book(Long id, String title, String author, String isbn,RecordStatusEnum recordStatusEnum) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.recordStatusEnum = recordStatusEnum;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public RecordStatusEnum getRecordStatusEnum() {
        return recordStatusEnum;
    }

    public void setRecordStatusEnum(RecordStatusEnum recordStatusEnum) {
        this.recordStatusEnum = recordStatusEnum;
    }
}
