package com.example.itaminbackend.domain.user.entity;

import com.example.itaminbackend.domain.user.constant.UserConstants;
import com.example.itaminbackend.domain.user.constant.UserConstants.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
