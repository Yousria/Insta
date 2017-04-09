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
    FriendRepository friendRepository;

    @Autowired
    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<FriendDTO> getAllUsers(){
        return Streams.stream(friendRepository.getAllUsers()).map(FriendAdapter::NodeToDto).collect(toList());
    }

    public void addUser(Long id_user) {
        friendRepository.addNewUser(id_user);
    }
}
