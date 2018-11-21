package com.qa.repository;


import com.qa.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;


@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager manager;


    public Book findBook(Long id) {
        return manager.find(Book.class, id);
    }

    public List<Book> getAllBooks() {
        TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long bookCount() {
        TypedQuery<Long> query = manager.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

    @Transactional(REQUIRED)
    public boolean createBook(Book book) {
        manager.persist(book);
        return manager.contains(book);

    }

    @Transactional(REQUIRED)
    public boolean deleteBook(Long id) {
        manager.remove(manager.getReference(Book.class, id));

        return manager.contains(manager.getReference(Book.class, id));
    }

    @Transactional(REQUIRED)
    public Book updateBook(Book book) {
        Book bookInDb = findBook(book.getId());
        bookInDb.setAuthor(book.getAuthor());
        bookInDb.setTitle(book.getTitle());
        bookInDb.setPublisherId(book.getPublisherId());
        return bookInDb;
    }
}