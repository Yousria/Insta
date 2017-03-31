package com.example.post.social.friend;

import lombok.*;

/**
 * @author timotheearnauld
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendDTO {
    private Long id;
    private Long id_user;
    private Long id_friend;

}
