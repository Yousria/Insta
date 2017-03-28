package com.example.post;

import com.example.loginAPI.Token;
import lombok.*;

/**
 * Created by kokoghlanian on 06/03/2017.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private Token token;
    private String comment;
    private ImageEntity image;
}
