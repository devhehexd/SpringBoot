package com.example.jpatest.updown;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/upload")
public class UpController {

    @GetMapping("/form")
    public void uploadForm() {

    }

    @PostMapping("/upload")
    public String upload(MultipartFile f, String title) {
        //MultipartFile: 파일 업로드 api
        //getOriginalFilename(): 원본 파일명 반환
        String fileName = f.getOriginalFilename();
        File newFile = new File("C:\\img\\" + fileName);

        try {
            f.transferTo(newFile); //업로드 파일을 newFile에 복사
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("fileName: " + fileName);
        System.out.println("title: " + title);
        return "redirect:/upload/list";
    }

    @GetMapping("/list")
    public void list(Model model) {
        //C:\\img\\ 폴더의 파일 목록을 읽어서 model에 담아 list.html 파일명 목록 출력
        File dir = new File("C:\\img\\");
        String[] files = dir.list(); //파일 목록
        model.addAttribute("files", files);
    }

    @GetMapping("/read-img")
    public ResponseEntity<byte[]> readImg(String fname) {
        ResponseEntity<byte[]> result = null;
        File file = new File("C:\\img\\" + fname);

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
