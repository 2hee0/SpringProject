package com.onehundredmillion.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MEMBER_ID", nullable = false)
    private Long id;

    private  String name;
    private int password;
    private String RRN; //주민번호
    private String phoneNo;

    @Embedded
    Address address;

}