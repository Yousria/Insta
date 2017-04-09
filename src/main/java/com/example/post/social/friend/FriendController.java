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

    @RequestMapping(value="addUser", method = RequestMethod.GET)
    public void addUser(@RequestParam("id_user")Long id_user){
        friendService.addUser(id_user);
    }
}
