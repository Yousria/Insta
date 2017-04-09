package com.example.post.social.friend;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author timotheearnauld
 */

@Repository
public interface FriendRepository extends GraphRepository<Friends> {
    @Query("MATCH (n:Friends) RETURN n ORDER BY n.user")
    Set<Friends> getAllUsers();

    @Query("MATCH (:Friends {user: {id_user}})-[r]-(p:Friends) RETURN p")
    List<Long> findAllFriends(@Param("id_user")Integer id_user);

    @Query("MATCH (m:Friends{user:{id_user}}) MATCH (p:Friends{user:{id_friend}}) CREATE UNIQUE (m)-[:IS_FRIEND_WITH]->(p)")
    void addNewFriend(@Param("id_user")Long id_user, @Param("id_friend")Long id_friend);

    @Query("MATCH (m:Friends{user:{id_user}}) MATCH (p:Friends{user:{id_friend}}) MATCH (m)-[r:IS_FRIEND_WITH]->(p) DELETE r")
    void deleteFriend(@Param("id_user")Long id_user, @Param("id_friend") Long id_friend);

    @Query("CREATE (m:Friends{user:{id_user}})")
    void addNewUser(@Param("id_user")Long id_user);

    @Query("MATCH (m:Friends{user:{id_user}}) DETACH DELETE m")
    void deleteUser(@Param("id_user")Long id_user);

    @Query("MATCH (m) DETACH DELETE m")
    void emptyDB();

    @Query("CREATE CONSTRAINT ON (friends:Friends) ASSERT friends.user IS UNIQUE")
    void createDataBase();
}
