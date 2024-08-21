package com.example.jdbctemplate.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestBookController {

    @Autowired
    private GuestBookService gbService;

    @GetMapping("/")
    public String home(Model m) {
        m.addAttribute("list", gbService.getAll());
        return "index";
    }

    //글 작성 폼
    @GetMapping("/book/add")
    public void addForm() {

    }

    //작성 완료
    @PostMapping("/book/add")
    public String add(GuestBook gb) {
        gbService.addGuestBook(gb);
        return "redirect:/"; //redirect로 이동. 사용자가 새 요청.
    }

    @GetMapping("/book/detail")
    public void detail(int num, Model m) {
        m.addAttribute("gb", gbService.getGuestBook(num));
    }

    @GetMapping("/book/edit")
    public String edit(GuestBook gb) {
        gbService.editGuestBook(gb);
        return "redirect:/";
    }

    @GetMapping("/book/delete")
    public String del(int num) {
        gbService.deleteGuestBook(num);
        return "redirect:/";
    }
}
