package com.onehundredmillion.library.repository;

import com.onehundredmillion.library.domain.Reservation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public Reservation findOne(Long id) {
        return em.find(Reservation.class, id);
    }

    public List<Reservation> findAll() {
        return em.createQuery("select rb from Reservation rb", Reservation.class).getResultList();
    }
}