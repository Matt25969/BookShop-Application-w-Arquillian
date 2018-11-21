package com.qa.domain;


import javax.persistence.*;


import java.util.Date;


@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String isbn;
    private String title;

    @Column(name = "publisher_id")
    private Long publisherId;

    private String author;

    public Book() {
    }


    public Book(String isbn, String title, Long publisherId, String author) {
        super();

        this.isbn = isbn;
        this.title = title;
        this.publisherId = publisherId;
        this.author = author;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        author = author;
    }



}
