package com.onehundredmillion.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
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

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationBook> reservationBooks = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    private BookStatus status;
=======
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "reservation")
    private List<ReservationBook> reservationBooks = new ArrayList<>();

    private ReservationStatus status;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

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
<<<<<<< HEAD
        reservation.setStatus(BookStatus.RESERVATION);
        return reservation;
    }
=======
        reservation.setStatus(ReservationStatus.RESERVATION);
        return reservation;
    }
    //==비즈니스 로직==//
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

    /**
     * 주문 취소
     */
    public void cancel() {
<<<<<<< HEAD
        this.setStatus(BookStatus.CANCEL);
=======
        this.setStatus(ReservationStatus.CANCEL);
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
        for (ReservationBook reservationBook : reservationBooks) {
            reservationBook.cancel();
        }
    }
}