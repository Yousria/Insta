package com.example.post.image;

import com.example.loginAPI.Token;
import lombok.*;

import javax.persistence.Column;

/**
 * Created by kokoghlanian on 06/03/2017.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ImageDTO {

    private Long id;

    private String name;

    private Token token;

    private String title;

    private Integer likescore;

    private Integer dislikescore;

}


