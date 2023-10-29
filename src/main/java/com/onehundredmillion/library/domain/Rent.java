package com.onehundredmillion.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RENT_ID", nullable = false)
    private Long id;

    private String returnBook;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    
    @OneToMany(mappedBy = "rent", cascade = CascadeType.ALL)
    private List<RentBook> rentBookList = new ArrayList<>();

    private LocalDateTime rentDate;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    public void setMember(Member member) {
        this.member = member;
        member.getRent().add(this);
    }

    public void addRentBook(RentBook rentBook) {
    	rentBookList.add(rentBook);
        rentBook.setRent(this);
    }

    public static Rent createRent(Member member, RentBook... rentBooks) {
        Rent rent = new Rent();
        rent.setMember(member);
        for (RentBook rentBook : rentBooks) {
            rent.addRentBook(rentBook);
        }
        rent.setStatus(BookStatus.RENT);
        rent.setRentDate(LocalDateTime.now());
        return rent;
    }

    //==비즈니스 로직==//
//    * 책 반납
    public void returnBook() {
        this.setStatus(BookStatus.ReturnBook);
        for (RentBook rentBook : rentBookList) {
            rentBook.returnBook();
        }
    }

}