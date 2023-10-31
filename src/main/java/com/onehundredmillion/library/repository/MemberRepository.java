package com.onehundredmillion.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.onehundredmillion.library.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	// @Autowired
	private final EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member update(Member member) {
		return em.merge(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name)
				.getResultList();
	}

	public Optional<Member> findByLoginId(String userId) {
		return findAll().stream().filter(m -> m.getUserId().equals(userId)).findFirst();
	}

	public boolean existsByuserId(String userId) {
<<<<<<< HEAD
		return findByLoginId(userId).isPresent();
	}
=======
		if (userId == null) {

			return false;
		} else {
			return true;
		}
	}

>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
}