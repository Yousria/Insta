package com.example;

import com.example.post.social.friend.FriendRepository;
import com.example.post.social.friend.FriendService;
import com.sun.istack.internal.Nullable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author timotheearnauld
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendsNodeTest {
    @Autowired
    FriendService friendService;

    @Autowired
    FriendRepository friendRepository;

    @Before
    public void should_configure_test(){
        try {
            friendRepository.emptyDB();
            friendRepository.deleteAll();
            friendRepository.createDataBase();
            friendService.addUser(1L);
            friendService.addUser(2L);
            friendService.addUser(3L);
            friendService.addUser(4L);
            friendService.addUser(5L);
        }catch(Exception e) {
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
    public void should_user_exist(){
        assertThat(friendService.doesUserExist(1L), is(true));
        assertThat(friendService.doesUserExist(2L), is(true));
        assertThat(friendService.doesUserExist(3L), is(true));
        assertThat(friendService.doesUserExist(4L), is(true));
        assertThat(friendService.doesUserExist(5L), is(true));
        assertThat(friendService.doesUserExist(6L), is(false));
    }

    @Test
    public void should_add_new_friend(){
        try{
            friendService.addFriendForUser(1L, 2L);
            friendService.addFriendForUser(2L, 3L);
            friendService.addFriendForUser(3L, 4L);
            friendService.addFriendForUser(1L, 4L);
            friendService.addFriendForUser(1L, 3L);
            friendService.addFriendForUser(2L, 4L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void should_delete_friend(){
        try{
            friendService.removeFriendForUser(1L, 4L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}