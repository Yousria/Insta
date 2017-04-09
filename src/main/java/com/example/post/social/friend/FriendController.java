package com.example.post.social.friend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author timotheearnauld
 */

@RestController
@RequestMapping("/friends/")
public class FriendController {
    final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping
    public List<FriendDTO> getAllUsers(){
        return friendService.getAllUsers();
    }

    @RequestMapping(value="friends", method = RequestMethod.GET)
    public List<FriendDTO> getFriendsForUser(@RequestParam("id_user") Long id_user){
        return friendService.findFriendsForUser(id_user);
    }

    @RequestMapping(value="addFriend", method = RequestMethod.GET)
    public boolean addFriendForUser(@RequestParam("id_user") Long id_user, @RequestParam("id_friend") Long id_friend){
        friendService.addFriendForUser(id_user, id_friend);

        return friendService.areFriends(id_user, id_friend) != null;
    }

    @RequestMapping(value="removeFriend", method = RequestMethod.GET)
    public boolean removeFriendForUser(@RequestParam("id_user") Long id_user, @RequestParam("id_friend") Long id_friend){
        friendService.removeFriendForUser(id_user, id_friend);

        return friendService.areFriends(id_user, id_friend) != null;
    }

    @RequestMapping(value="addUser", method = RequestMethod.GET)
    public void addUser(@RequestParam("id_user")Long id_user){
        friendService.addUser(id_user);
    }
}
