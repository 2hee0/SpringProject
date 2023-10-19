package com.onehundredmillion.library.repository;

import com.onehundredmillion.library.domain.Rent;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}