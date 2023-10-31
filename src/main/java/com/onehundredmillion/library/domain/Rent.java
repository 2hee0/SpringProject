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

<<<<<<< HEAD
    
=======
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
    @OneToMany(mappedBy = "rent", cascade = CascadeType.ALL)
    private List<RentBook> rentBookList = new ArrayList<>();

    private LocalDateTime rentDate;

    @Enumerated(EnumType.STRING)
<<<<<<< HEAD
    private BookStatus status;
=======
    private RentStatus status;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f

    public void setMember(Member member) {
        this.member = member;
        member.getRent().add(this);
    }

    public void addRentBook(RentBook rentBook) {
<<<<<<< HEAD
    	rentBookList.add(rentBook);
        rentBook.setRent(this);
    }

=======
        rentBook.add(rentBook);
        rentBook.setRent(this);
    }



>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
    public static Rent createRent(Member member, RentBook... rentBooks) {
        Rent rent = new Rent();
        rent.setMember(member);
        for (RentBook rentBook : rentBooks) {
            rent.addRentBook(rentBook);
        }
<<<<<<< HEAD
        rent.setStatus(BookStatus.RENT);
=======
        rent.setStatus(RentStatus.RENT);
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
        rent.setRentDate(LocalDateTime.now());
        return rent;
    }

    //==비즈니스 로직==//
//    * 책 반납
    public void returnBook() {
<<<<<<< HEAD
        this.setStatus(BookStatus.ReturnBook);
=======
             this.setStatus(RentStatus.ReturnBook);
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
        for (RentBook rentBook : rentBookList) {
            rentBook.returnBook();
        }
    }

}