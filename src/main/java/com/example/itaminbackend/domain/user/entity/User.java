package com.example.itaminbackend.domain.user.entity;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.domain.board.entity.BoardConnection;
import com.example.itaminbackend.domain.comment.entity.Comment;
import com.example.itaminbackend.domain.scrap.entity.Scrap;
import com.example.itaminbackend.domain.user.constant.UserConstants.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String imageUrl;

    @Column
    private Boolean emailVerified;


    @Builder
    public User(String name, String email, String imageUrl, Boolean emailVerified) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.emailVerified = emailVerified;;
    }

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }

    public User toEntity() {
        return User.builder()
                .emailVerified(emailVerified)
                .name(name)
                .email(email)
                .imageUrl(imageUrl)
                .build();
    }

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Scrap> scraps = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<BoardConnection> boardConnections = new ArrayList<>();

    public void setBoards(Board board){
        this.boards.add(board);
        board.setUser(this);
    }
}
