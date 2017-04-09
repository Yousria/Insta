package com.example;

import com.example.post.social.friend.FriendRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author timotheearnauld
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsNodeTest {
    @Autowired
    FriendRepository friendRepository;

    @Before
    public void should_configure_test(){
        try {
            friendRepository.emptyDB();
            friendRepository.deleteAll();
            friendRepository.createDataBase();
            friendRepository.addNewUser(1L);
            friendRepository.addNewUser(2L);
            friendRepository.addNewUser(3L);
            friendRepository.addNewUser(4L);
            friendRepository.addNewUser(5L);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void should_delete_user(){
        try{
            friendRepository.deleteUser(5L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void should_add_new_friend(){
        try{
            friendRepository.addNewFriend(1L, 2L);
            friendRepository.addNewFriend(2L, 3L);
            friendRepository.addNewFriend(3L, 4L);
            friendRepository.addNewFriend(1L, 4L);
            friendRepository.addNewFriend(1L, 3L);
            friendRepository.addNewFriend(2L, 4L);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void should_delete_friend(){
        try{
            friendRepository.deleteFriend(1L, 4L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}