package com.onehundredmillion.library.repository;

import com.onehundredmillion.library.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Transactional
    public void save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    public Book findOne(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    public Book reserve() {
        // TODO Auto-generated method stub
        return null;
    }

    public Book rent() {
        // TODO Auto-generated method stub
        return null;
    }

/*    @Transactional
    public boolean existQuery(String query) {
        Query checkQuery = em.createNativeQuery("SELECT COUNT(*) FROM Book WHERE Book = :BookId");
        checkQuery.setParameter("query", query);
        long count = (long) checkQuery.getSingleResult();

        if (count == 0) {
            em.createNativeQuery("INSERT INTO query (query) VALUES (:query)")
                    .setParameter("query", query)
                    .executeUpdate();
            return false;
        } else {
            return true;
        }
    }*/

}