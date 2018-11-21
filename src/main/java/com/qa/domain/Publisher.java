package com.qa.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

    @Entity(name = "Publisher")
    @Table(name = "publisher")
    public class Publisher {
        @Id
        @GeneratedValue
        private Long id;
        private String name;

        @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "publisher_id")
        private List<Book> books;

        public Publisher(String name, List<Book> books) {
            super();
            this.name = name;
            this.books = books;
        }

        public Publisher() {

        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Book> getBooks() {
            return books;
        }

        public void setBooks(List<Book> books) {
            this.books = books;
        }



}
