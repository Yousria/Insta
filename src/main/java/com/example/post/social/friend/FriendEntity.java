package com.example.post.social.friend;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author timotheearnauld
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friend")
class FriendEntity {
    @Id
    @Column(name="friend_id")
    @GeneratedValue
    private Long id;

    @Column(name="id_user")
    private Long id_user;

    @Column(name="id_friend")
    private Long id_friend;
}
