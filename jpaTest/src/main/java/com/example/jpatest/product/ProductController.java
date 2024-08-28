package com.example.jpatest.product;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${spring.servlet.multipart.location}")
    private String path; //path = C:\shopimg

    @GetMapping("/add")
    public void addForm() {

    }

    @PostMapping("/add")
    public String add(ProductDto productDto) {

        ProductDto newData = productService.save(productDto); //insert (num, name, price, amount)
        String fileName = productDto.getMultipartFile().getOriginalFilename(); //getOriginalFilename(): 원본 파일명 반환
        fileName = newData.getNum() + fileName;
        File newFile = new File(path + "\\" + fileName);

        try {
            productDto.getMultipartFile().transferTo(newFile); //업로드 파일을 newFile에 복사
            newData.setImg(fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        productService.save(newData); //update
        return "redirect:/product/list";
    }

    @RequestMapping("/list")
    public void list(Model model, HttpSession session) {
        String type = session.getAttribute("type").toString();
        String loginId = session.getAttribute("loginId").toString();
        List<ProductDto> list = null;
        if (type.equals("판매자")) {
            list = productService.getBySeller(loginId);
        } else {
            list = productService.getAllProducts();
        }
        model.addAttribute("list", list);
    }

    @GetMapping("/read-img")
    public ResponseEntity<byte[]> readImg(String fname) {
        ResponseEntity<byte[]> result = null;
        File file = new File(path + "\\" + fname);

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
