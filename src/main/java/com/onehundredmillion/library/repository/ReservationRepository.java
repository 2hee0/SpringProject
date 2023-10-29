package com.onehundredmillion.library.repository;

import com.onehundredmillion.library.domain.BookStatus;
import com.onehundredmillion.library.domain.Reservation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {
	private final EntityManager em;

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public Reservation findOne(Long id) {
        return em.find(Reservation.class, id);
    }

    public List<Reservation> findAll(Long id, BookStatus status) {
        return em.createQuery("select r from Reservation r where r.member.id = :memberId and r.status = :status", Reservation.class)
        		.setParameter("memberId", id)
        		.setParameter("status", status)
        		.getResultList();
    }
    
	public Long reservationCheck(Long memberId, Long bookId) {
	    Long count = em.createQuery("SELECT COUNT(r) FROM Reservation r " +
                "JOIN r.reservationBooks ReservationBook " +
                "WHERE r.member.id = :memberId " +
                "AND ReservationBook.book.id = :bookId " +
                "AND r.status = :status", Long.class)
		.setParameter("memberId", memberId)
		.setParameter("bookId", bookId)
		.setParameter("status", BookStatus.RESERVATION)
		.getSingleResult();
		return count;
    }
    
    public void reserve(Reservation reservation) {
		em.persist(reservation);
	}
    
}