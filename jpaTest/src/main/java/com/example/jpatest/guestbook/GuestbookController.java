package com.example.jpatest.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

    @Autowired
    private GuestbookService gbService;

    @RequestMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", gbService.getAll());
    }

    @GetMapping("/add")
    public void addForm() {

    }

    @PostMapping("/add")
    public String add(GuestbookDto gbDto) {
        gbService.saveBook(gbDto);
        return "redirect:/guestbook/list";
    }

    @RequestMapping("/getbywriter")
    public String getByWriter(String writer, Model model) {
        model.addAttribute("list", gbService.getByWriter(writer));
        return "guestbook/list";
    }
}
