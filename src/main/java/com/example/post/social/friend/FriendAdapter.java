package com.example.post.social.friend;

/**
 * @author timotheearnauld
 */
public class FriendAdapter {
    public static FriendDTO toFriendDTO(FriendEntity friendEntity){
        return FriendDTO.builder()
                .id(friendEntity.getId())
                .id_user(friendEntity.getId_user())
                .id_friend(friendEntity.getId_friend())
                .build();
    }

    public static FriendEntity toFriend(FriendDTO friendDTO){
        return FriendEntity.builder()
                .id(friendDTO.getId())
                .id_user(friendDTO.getId_user())
                .id_friend(friendDTO.getId_friend())
                .build();
    }
}
