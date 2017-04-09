package com.example.post.social.friend;

import lombok.*;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;
/**
 * @author timotheearnauld
 */

@NodeEntity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Friends {
    @GraphId Long id_node;
    public Long user;

    public Friends(Long id){
        this.user = id;
    }

    @Relationship(type="IS_FRIEND_WITH", direction = Relationship.UNDIRECTED)
    List<Long> idusers;
}
