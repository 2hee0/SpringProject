package com.onehundredmillion.library.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @NotEmpty
    private String userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private String passwordConfirm;
    private String Rrn1; //주민번호

    private String Rrn2; //주민번호
    private String phoneNo;
    @Embedded
    Address address;

    @OneToMany(mappedBy = "member")
    private List<Rent> rent = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservation = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> like = new ArrayList<>();


}