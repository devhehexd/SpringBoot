package com.example.task1.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String detail(int num, Model m) {
        GuestBook gb = gbService.getGuestBook(num);
        if (gb == null) {
            m.addAttribute("msg", "없는 번호입니다.");
        }
        else {
            m.addAttribute("gb", gb);
        }
        return "guestbook/detail";
    }

    @GetMapping("/password")
    public String passwordForm(int num, Model m) {
        GuestBook gb = gbService.getGuestBook(num);
        m.addAttribute("gb", gb);
        return "guestbook/password";
    }

    @PostMapping("/password")
    public String password(int num, String password, @RequestParam(value = "action", required = false) String action, Model m) {
        GuestBook gb = gbService.getGuestBook(num);
        if (gb.getPassword().equals(password)) {
            if ("delete".equals(action)) {
                gbService.deleteGuestBook(num);
                return "redirect:/index";
            }
            m.addAttribute("gb", gb);
            return "redirect:/guestbook/edit?num=" + num;
        }
        else {
            m.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 입력해주세요.");
            m.addAttribute("gb", gb);
            return "guestbook/password";
        }
    }

    @GetMapping("/edit")
    public String editForm(int num, Model m) {
        GuestBook gb = gbService.getGuestBook(num);
        m.addAttribute("gb", gb);
        return "/guestbook/edit";
    }

    @PostMapping("/edit")
    public String edit(GuestBook gb) {
        gbService.editGuestBook(gb);
        return "index";
    }

    @GetMapping("/delete")
    public String del(int num, Model m) {
        GuestBook gb = gbService.getGuestBook(num);
        m.addAttribute("gb", gb);
        return "guestbook/password";
    }
}
