package com.onehundredmillion.library.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Liking")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "LIKE_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "like", cascade = CascadeType.ALL)
    private List<LikeBook> likeBooks = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private String cancel;

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getLike().add(this);
    }

    public void addReservation(LikeBook likeBook) {
        likeBooks.add(likeBook);
        likeBook.setLike(this);
    }


    //==생성 메서드==//
    public static Like createLike(Member member, LikeBook... likeBooks) {
        Like like = new Like();
        like.setMember(member);
        for (LikeBook likeBook : likeBooks) {
            like.addReservation(likeBook);
        }
        like.setStatus(BookStatus.LIKE);
        return like;
    }

    /**
     * 주문 취소
     */
    public void dislike() {
        this.setStatus(BookStatus.DISLIKE);
        for (LikeBook likeBook : likeBooks) {
            likeBook.dislike();
        }
    }
}