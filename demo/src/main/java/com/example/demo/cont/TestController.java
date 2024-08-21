package com.example.demo.cont;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/param1")
    public String modelTest(ModelMap m) {

        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        m.addAttribute("list", list);
        return "test/param1";
    }

    @GetMapping("/param2")
    public ModelAndView param2(int num, String name) {
        ModelAndView mav = new ModelAndView("/test/param2");
        mav.addObject("num", num);
        mav.addObject("name", name);
        return mav;
    }

    @GetMapping("/voidTest")
    public void voidTest(Model m) {
        m.addAttribute("data1", "val1");
        m.addAttribute("data2", "val2");
    }

    @GetMapping("/sessionTest")
    public void sessionTest(HttpSession session) {
        session.setAttribute("loginId", "aaa"); //어플리케이션(서버) 저장이기 때문에 로그아웃 전 까지 모든 페이지에서 사용가능한 값
        //session.invalidate(); //로그아웃 처리
    }
}
