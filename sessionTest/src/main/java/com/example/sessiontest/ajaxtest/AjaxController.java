package com.example.sessiontest.ajaxtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

    @ResponseBody //웹 페이지가 아니라 데이터를 응답으로 전달(return)
    @GetMapping("/test1")
    public String test1(int num, String name) {
        return "hello ajax / num: " + num + " / name: " + name;
    }

    @GetMapping("/view1")
    public String view1() {
        return "ajax/test1";
    }

    @ResponseBody //웹 페이지가 아니라 데이터를 응답(response)으로 전달(return)
    @PostMapping("/test2")
    public String test2(int num, String name) {
        return "ajax post 요청 / num: " + num + " / name: " + name;
    }

    @ResponseBody //웹 페이지가 아니라 데이터를 응답(response)으로 전달(return)
    @GetMapping("/test3")
    public Map test3(int num, String name) {
        Map map = new HashMap();
        map.put("num", num);
        map.put("name", name);
        return map;
    }
}
