package com.qa.persistence.repository;

import com.qa.domain.Book;
import com.qa.domain.Publisher;
import com.qa.repository.BookRepository;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookRepositoryTest {

    private static Long bookId;

    @Inject
    private BookRepository bookRepository;

    @Deployment
    public static Archive<?> createDeploymentPackage() {

        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Book.class)
                .addClass(Publisher.class)
                .addClass(BookRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }

    @Test
    @InSequence(1)
    public void deploymentTest() {

        assertNotNull(bookRepository);
    }

    @Test
    @InSequence(2)
    public void getAllBookTest() {

        assertEquals(Long.valueOf(0), bookRepository.bookCount());

        assertEquals(0, bookRepository.getAllBooks().size());
    }

    @Test
    @InSequence(3)
    public void CreateBookTest() {

        List<Book> publishersBooks = new ArrayList<Book>();
        Publisher publisher = new Publisher("name", publishersBooks);
        Book book = new Book("isbn", "title", publisher.getId(), "author");

        assertTrue(bookRepository.createBook(book));

        bookId = book.getId();

    }

    @Test
    @InSequence(4)
    public void findBookTest() {

        Book bookFound = bookRepository.findBook(bookId);

        assertNotNull(bookFound.getId());
        assertEquals("title", bookFound.getTitle());
    }

    @Test
    @InSequence(5)
    public void updateBookTest() {
        Book bookFound = bookRepository.findBook(bookId);
        bookFound.setTitle("test");
        Book bookUpdated = bookRepository.updateBook(bookFound);
        assertEquals("test", bookUpdated.getTitle());
    }

    @Test
    @InSequence(6)
    public void CountBooksTest() {

        assertEquals(Long.valueOf(1), bookRepository.bookCount());

        assertEquals(1, bookRepository.getAllBooks().size());
    }

    @Test
    @InSequence(7)
    public void deleteBookTest() {

        assertFalse(bookRepository.deleteBook(bookId));

        Book bookDeleted = bookRepository.findBook(bookId);

    }

    @Test
    @InSequence(8)
    public void getAllTest() {

        assertEquals(Long.valueOf(0), bookRepository.bookCount());

        assertEquals(0, bookRepository.getAllBooks().size());
    }


}