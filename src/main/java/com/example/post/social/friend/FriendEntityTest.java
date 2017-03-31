package com.example.post.social.friend;

import static org.springframework.test.util.AssertionErrors.assertEquals;

/**
 * @author timotheearnauld
 */
public class FriendEntityTest {
    public void should_create_a_new_friend_entity(){
        FriendEntity friendEntity = FriendEntity.builder()
                .id(1)
                .id_user(1)
                .id_friend(1)
                .build();

        assertEquals(friendEntity.getId(), 1);
        assertEquals(friendEntity.getId_user(), 1);
        assertEquals(friendEntity.getId_friend(), 1);
    }
}