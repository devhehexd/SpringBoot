package com.example.securitytokken.imgboard;

import com.example.securitytokken.user.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImgBoardDto {

    private int num;
    private User writer;
    private String title;
    private String img;
    private MultipartFile multipartFile;
}
