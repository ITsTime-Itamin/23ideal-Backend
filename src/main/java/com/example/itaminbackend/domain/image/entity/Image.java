package com.example.itaminbackend.domain.image.entity;

import com.example.itaminbackend.domain.board.entity.Board;
import com.example.itaminbackend.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseTimeEntity {

    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;
    private String imageKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private boolean isDeleted;

    public Image(String imageKey) {
        this.imageKey = imageKey;
    }

    public static Image from(String key) {
        return Image.builder()
                .imageKey(key)
                .build();
    }
}
