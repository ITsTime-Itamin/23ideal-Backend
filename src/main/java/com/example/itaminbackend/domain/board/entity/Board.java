package com.example.itaminbackend.domain.board.entity;

import com.example.itaminbackend.domain.board.constant.BoardConstants.EBoardType;
import com.example.itaminbackend.domain.myfile.entity.MyFile;
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

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private EBoardType boardType;

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<MyFile> myFiles = new ArrayList<>();

    @Builder
    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }

    /**
     * 연관관계 매핑
     */
    public void setMyFiles(List<MyFile> myFiles) {
        this.myFiles = myFiles;
        for (MyFile myFile : myFiles) {
            myFile.setBoard(this);}
    }
}
