package com.onehundredmillion.library.repository;

import com.onehundredmillion.library.domain.BookStatus;
import com.onehundredmillion.library.domain.Rent;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentRepository {
    private final EntityManager em;

    public void save(Rent rent) {
        em.persist(rent);
    }

    public Rent findOne(Long id) {
        return em.find(Rent.class, id);
    }

    public List<Rent> findAll(Long id, BookStatus status){
        return em.createQuery("select r from Rent r where r.member.id = :memberId and r.status = :status", Rent.class)
        		.setParameter("memberId", id)
        		.setParameter("status", status)
        		.getResultList();
    }
    
	public Long rentCheck(Long memberId, Long bookId) {
	    Long count = em.createQuery("SELECT COUNT(r) FROM Rent r " +
                "JOIN r.rentBookList rentBook " +
                "WHERE r.member.id = :memberId " +
                "AND rentBook.book.id = :bookId " +
                "AND r.status = :status", Long.class)
		.setParameter("memberId", memberId)
		.setParameter("bookId", bookId)
		.setParameter("status", BookStatus.RENT)
		.getSingleResult();
		return count;
    }
	
	public void rent(Rent rent) {
        em.persist(rent);
    }
    
}