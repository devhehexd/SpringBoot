package com.example.repositorydemo.article;

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
import java.net.URLEncoder;
import java.nio.file.Files;

@Controller
@RequestMapping("/data")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Value("${spring.servlet.multipart.location}")
    private String path;

    @RequestMapping("list")
    public void list(Model model) {
        model.addAttribute("list", articleService.getAllArticles());
    }

    @GetMapping("/add")
    public void addForm() {

    }

    @PostMapping("/add")
    public String add(ArticleDto articleDto) {
        ArticleDto dto2 = articleService.saveArticle(articleDto);
        String fileName = dto2.getNum() + articleDto.getMultipartFile().getOriginalFilename();
        File newFile = new File(path + fileName);

        try {
            articleDto.getMultipartFile().transferTo(newFile);
            dto2.setData(fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        articleService.saveArticle(dto2);
        return "redirect:/data/list";
    }

    @GetMapping("/detail")
    public void detail(int num, Model model) {
        model.addAttribute("data", articleService.getArticle(num));
    }

    @GetMapping("/down")
    public ResponseEntity<byte[]> down(String fname, int num) {
        ResponseEntity<byte[]> result = null;
        File file = new File(path + fname);
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("Content-Type", Files.probeContentType(file.toPath()));
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fname, "UTF-8") + "\"");
            result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
        }catch (IOException e) {
            e.printStackTrace();
        }
        articleService.updateDownloadCount(num);

        return result;
    }
}
