package com.example.itaminbackend.domain.scrap.entity;

import com.example.itaminbackend.domain.board.entity.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;

    private boolean isDeleted;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    /**
     * 연관관계 메서드
     */

//    public void setUser(User user) {
//        this.user = user;
//        user.getScraps().add(this);
//    }

    public void setBoard(Board board) {
        this.board = board;
        board.getScraps().add(this);
    }

//    public static Scrap toEntity(User user, Diary diary) {
//        Scrap scrap = Scrap.builder()
//                .isDeleted(false)
//                .build();
//        scrap.setUser(user);
//        scrap.setDiary(diary);
//        return scrap;
//    }

}
