package com.onehundredmillion.library.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private String addr;
    private String addr_detail;
    private String addr_etc;
    private String zipcode;

    public Address(String addr, String addr_detail, String addr_etc, String zipcode) {
        this.addr = addr;
        this.addr_detail = addr_detail;
        this.addr_etc = addr_etc;
        this.zipcode = zipcode;
    }

    protected Address() {

    }
}