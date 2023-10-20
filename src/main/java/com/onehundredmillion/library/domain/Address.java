package com.onehundredmillion.library.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private String zipcode;
    private String addr;
    private String addr_etc;
    private String addr_detail;

    public Address(String zipcode, String addr, String addr_etc, String addr_detail) {
        this.zipcode = zipcode;
        this.addr = addr;
        this.addr_etc = addr_etc;
        this.addr_detail = addr_detail;
    }

    protected Address() {

    }
}