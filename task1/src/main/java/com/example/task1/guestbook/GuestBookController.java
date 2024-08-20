package com.example.task1.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

    @Autowired
    private GuestBookService gbService;

    @GetMapping("/add")
    public String addForm(Model m) {
        m.addAttribute("gb", new GuestBook());
        return "guestbook/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute GuestBook gb) {
        gbService.addGuestBook(gb);
        return "index";
    }

    @RequestMapping("/list")
    public String list(Model m) {
        m.addAttribute("list", gbService.getAll());
        return "guestbook/list";
    }

    @GetMapping("/detail")
    public String detail(Integer num, Model m) {
        GuestBook gb = gbService.getGuestBook(num);
        if (gb == null) {
            m.addAttribute("msg", "없는 번호입니다.");
        }
        else {
            m.addAttribute("gb", gb);
        }
        return "guestbook/detail";
    }

    @GetMapping("/edit")
    public String editForm(Model m) {
        return "/guestbook/password";
    }

    @PostMapping("/edit")
    public String edit(GuestBook gb) {
        gbService.editGuestBook(gb);
        return "index";
    }
}
