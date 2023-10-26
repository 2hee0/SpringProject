package com.onehundredmillion.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RESRVATION_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationBook> reservationBooks = new ArrayList<>();

    private ReservationStatus status;

    private String cancel;

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getReservation().add(this);
    }

    public void addReservation(ReservationBook reservationBook) {
        reservationBooks.add(reservationBook);
        reservationBook.setReservation(this);
    }


    //==생성 메서드==//
    public static Reservation createReservation(Member member, ReservationBook... reservationBooks) {
        Reservation reservation = new Reservation();
        reservation.setMember(member);
        for (ReservationBook reservationBook : reservationBooks) {
            reservation.addReservation(reservationBook);
        }
        reservation.setStatus(ReservationStatus.RESERVATION);
        return reservation;
    }
    //==비즈니스 로직==//

    /**
     * 주문 취소
     */
    public void cancel() {
        this.setStatus(ReservationStatus.CANCEL);
        for (ReservationBook reservationBook : reservationBooks) {
            reservationBook.cancel();
        }
    }
}