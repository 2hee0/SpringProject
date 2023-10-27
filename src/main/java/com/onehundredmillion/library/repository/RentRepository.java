package com.onehundredmillion.library.repository;

import com.onehundredmillion.library.domain.Rent;
import com.onehundredmillion.library.domain.Reservation;
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

    public List<Rent> findAll(){
        return em.createQuery("select r from Rent r", Rent.class).getResultList();
    }
}