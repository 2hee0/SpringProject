package com.onehundredmillion.library.dto;

import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NaverResult {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Bookresult> items;
}