package com.example.itaminbackend.domain.user.entity;

import com.example.itaminbackend.domain.user.constant.UserConstants;
import com.example.itaminbackend.domain.user.constant.UserConstants.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
}
