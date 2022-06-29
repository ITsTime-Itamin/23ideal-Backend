package com.example.itaminbackend.domain.board.entity;

import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.domain.image.entity.Image;
import com.example.itaminbackend.domain.scrap.entity.Scrap;
import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private String title;
    private String content;
    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private EBoardType boardType;

    @OneToMany(mappedBy = "board")
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Scrap> scraps = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }

    /**
     * 연관관계 매핑
     */
    public void setImages(List<Image> images) {
        this.images = images;
        for (Image image : images) {
            image.setBoard(this);}
    }

}
