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
import org.springframework.web.multipart.MultipartFile;

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
    private String path; //path = C:\shopimg\

    @GetMapping("/add")
    public void addForm() {

    }

    @PostMapping("/add")
    public String add(ProductDto productDto) {
        //insert (num, name, price, amount, seller)
        ProductDto newData = productService.save(productDto);
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
        File file = new File(path + fname);

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

    @GetMapping("/detail")
    public void detail(int num, Model model) {
        model.addAttribute("product", productService.getProduct(num));
    }

    @PostMapping("/edit")
    public String edit(ProductDto productDto) {
        ProductDto oldProductInfo = productService.getProduct(productDto.getNum());
        oldProductInfo.setName(productDto.getName());
        oldProductInfo.setPrice(productDto.getPrice());
        oldProductInfo.setAmount(productDto.getAmount());
        productService.save(oldProductInfo);
        return "redirect:/product/list";
    }

    @PostMapping("/edit-img")
    public String editImg(MultipartFile f, int num) {
        //이미지 변경할 상품을 검색해서 전체 정보를 불러옴
        ProductDto product = productService.getProduct(num);

        //삭제할 원본 이미지 경로 변수에 저장
        String fileNameForDelete = path + product.getImg();
        System.out.println("deleteFileName: " + fileNameForDelete);

        //삭제할 파일의 File 객체를 생성
        File deleteFile = new File(fileNameForDelete);

        //delete(): 파일 삭제 메서드
        //원본 파일 삭제
        deleteFile.delete();

        //새로 올라온 파일의 원파일명 저장
        String fileName = f.getOriginalFilename();

        //중복을 막기위해 원본 파일명 앞에 상품 번호 붙임
        fileName = product.getNum() + fileName;

        //서버에 복사할 새 파일을 생성
        File newFile = new File(path + fileName);
        try {
            //올라온 파일의 내용(f)을 생성한 새 파일(newFile)에 복사
            f.transferTo(newFile);
            //변경된 이미지 경로를 수정 객체에 저장
            product.setImg(fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //save()로 db에서 수정
        productService.save(product);

        return "redirect:/product/detail?num=" + num;
    }
}
