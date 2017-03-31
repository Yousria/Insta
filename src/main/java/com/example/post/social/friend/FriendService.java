package com.example.post.social.friend;

/**
 * @author timotheearnauld
 */
public interface FriendService {
    FriendEntity addNewFriend(Long id_user, Long id_friend);
    FriendEntity removeFriend(Long id_user, Long id_friend);
}
