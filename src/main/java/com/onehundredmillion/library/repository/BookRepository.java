package com.onehundredmillion.library.repository;

import com.onehundredmillion.library.domain.Book;
import jakarta.persistence.EntityManager;
<<<<<<< HEAD
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
=======
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
<<<<<<< HEAD

    @PersistenceContext
    private final EntityManager em;

    @Transactional
=======
    private final EntityManager em;

>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
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

<<<<<<< HEAD
    public Book reserve() {
        // TODO Auto-generated method stub
        return null;
    }

    public Book rent() {
        // TODO Auto-generated method stub
        return null;
    }
=======
	public Book reserve() {
		// TODO Auto-generated method stub
		return null;
	}

	public Book rent() {
		// TODO Auto-generated method stub
		return null;
	}
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
}