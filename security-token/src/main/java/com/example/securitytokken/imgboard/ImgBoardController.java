package com.example.securitytokken.imgboard;

import com.example.securitytokken.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class ImgBoardController {

    @Autowired
    private ImgBoardService imgBoardService;

    @Value("${spring.servlet.multipart.location}")
    private String path;

    @PostMapping("/board")
    public Map addImg(ImgBoardDto imgBoardDto) {
        String fileName = imgBoardDto.getMultipartFile().getOriginalFilename();
        File newFile = new File(path + fileName);
        boolean flag = false;

        try {
            imgBoardDto.getMultipartFile().transferTo(newFile);
            imgBoardDto.setImg(fileName);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = new User(authentication.getName(), "", "");
            imgBoardDto.setWriter(user);
            imgBoardService.save(imgBoardDto);
            flag = true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("flag", flag);
        return map;
    }

    @GetMapping("/board")
    public Map list() {
        Map map = new HashMap();
        map.put("list", imgBoardService.getAll());
        return map;
    }

    @GetMapping("/read-img/{fileName}")
    public ResponseEntity<byte[]> readImg(@PathVariable("fileName") String fileName) {
        ResponseEntity<byte[]> result = null;
        File file = new File(path + fileName);

        //응답 헤더정보 저장 객체
        HttpHeaders headers = new HttpHeaders();
        try {
            //전송하는 데이터의 마임 타입 설정
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            result = new ResponseEntity<byte[]>(
                    FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
