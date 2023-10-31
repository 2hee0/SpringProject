package com.onehundredmillion.library.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.onehundredmillion.library.domain.BookStatus;
import com.onehundredmillion.library.domain.Like;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
@Repository
@RequiredArgsConstructor
public class LikeRepository {
	private final EntityManager em;

    public void save(Like like) {
        em.persist(like);
    }

    public Like findOne(Long id) {
        return em.find(Like.class, id);
    }

    public List<Like> findAll(Long id, BookStatus status) {
        return em.createQuery("select l from Like l where l.member.id = :memberId and l.status = :status", Like.class)
        		.setParameter("memberId", id)
        		.setParameter("status", status)
        		.getResultList();
    }
    
	public Long likeCheck(Long memberId, Long bookId) {
	    Long count = em.createQuery("SELECT COUNT(l) FROM Like l " +
                "JOIN l.likeBooks LikeBook " +
                "WHERE l.member.id = :memberId " +
                "AND LikeBook.book.id = :bookId " +
                "AND l.status = :status", Long.class)
		.setParameter("memberId", memberId)
		.setParameter("bookId", bookId)
		.setParameter("status", BookStatus.LIKE)
		.getSingleResult();
		return count;
    }
    
    public void like(Like like) {
		em.persist(like);
	}
}
