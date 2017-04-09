package com.example.post.social.friend;

import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * @author timotheearnauld
 */

@Service
public class FriendService {
    private FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public boolean areFriends(Long id_user, Long id_friend){
        if((friendRepository.areFriends(id_user, id_friend)) == null){
            System.out.println("Are not friends");
            return false;
        }
        return true;
    }

    List<FriendDTO> getAllUsers(){
        return Streams.stream(friendRepository.getAllUsers()).map(FriendAdapter::NodeToDto).collect(toList());
    }

    List<FriendDTO> findFriendsForUser(Long iduser){
        return Streams.stream(friendRepository.findAllFriends(iduser)).map(FriendAdapter::NodeToDto).collect(toList());
    }

    public boolean doesUserExist(Long id_user){
        return friendRepository.getUser(id_user) != null;
    }

    public void addFriendForUser(Long iduser, Long idfriend){
        friendRepository.addNewFriend(iduser, idfriend);
    }

    public void removeFriendForUser(Long iduser, Long idfriend){
        friendRepository.deleteFriend(iduser, idfriend);
    }

    public void addUser(Long id_user) {
        friendRepository.addNewUser(id_user);
    }
}
