package com.example.jpatest.down;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

@Controller
@RequestMapping("/down")
public class DownController {

    private String path = "C:\\down\\";

    @GetMapping("")
    public String fileList(ModelMap modelMap) {
        File dir = new File(path);
        modelMap.addAttribute("list", dir.list());
        return "/down/list";
    }

    @GetMapping("down")
    public ResponseEntity<byte[]> down(String fname) {
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
        return result;
    }
}
