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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MEMBER_ID", nullable = false)
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    private String RRN; //주민번호
    private String phoneNo;

    @Embedded
    Address address;

    @OneToMany(mappedBy = "member")
    private List<Rent> rent = new ArrayList<>();

}