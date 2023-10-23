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

//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "member_id", sequenceName = "member_id", allocationSize = 1)
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @NotEmpty
    private String userid;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    private String passwordConfirm;
    private String Rrn1; //주민번호
    private String Rrn2;
    private String phoneNo;
    @Embedded
    Address address;

    @OneToMany(mappedBy = "member")
    private List<Rent> rent = new ArrayList<>();

}