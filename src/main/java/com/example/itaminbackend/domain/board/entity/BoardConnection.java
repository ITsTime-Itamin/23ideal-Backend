package com.example.itaminbackend.domain.board.entity;

import com.example.itaminbackend.domain.user.entity.User;
import com.example.itaminbackend.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardConnection extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardConnectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 엔티티 성성 메서드
     */
    public static BoardConnection toEntity(Board board, User user) {
        BoardConnection boardConnection = BoardConnection.builder()
                .build();
        boardConnection.setBoard(board);
        boardConnection.setUser(user);
        return boardConnection;
    }

    /**
     * 양방향 연관관계
     */
    public void setBoard(Board board){
        this.board = board;
        board.getBoardConnections().add(this);
    }

    public void setUser(User user){
        this.user = user;
        user.getBoardConnections().add(this);
    }



}
