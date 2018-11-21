package com.qa.repository;

import com.qa.domain.Publisher;

import javax.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.List;

@Transactional(SUPPORTS)
public class PublisherRepository {

    @PersistenceContext(unitName = "ibdbPU")
    private EntityManager manager;

    public Long publisherCount() {
        TypedQuery<Long> query = manager.createQuery("SELECT COUNT(p) FROM Publisher p", Long.class);
        return query.getSingleResult();
    }

    @Transactional(REQUIRED)
    public Publisher createPublisher(Publisher publisher) {
        manager.persist(publisher);
        return publisher;
    }

    public List<Publisher> getAllPublishers() {
        TypedQuery<Publisher> query = manager.createQuery("SELECT p FROM Publisher p ORDER BY p.title DESC",
                Publisher.class);
        return query.getResultList();
    }

    public Publisher findPublisher(Long id) {
        return manager.find(Publisher.class, id);
    }

    @Transactional(REQUIRED)
    public void deletePublisher(Long id) {

        manager.remove(manager.getReference(Publisher.class, id));
    }

}
