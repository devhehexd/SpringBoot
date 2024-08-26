package com.example.thymeleaftest.member;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memService;

    @GetMapping("/")
    String home() {
        return "boardIndex";
    }

    @GetMapping("/join")
    public void joinForm() {

    }

    @PostMapping("/join")
    public String join(Member member) {
        memService.addMember(member);
        return "boardIndex";
    }

    @ResponseBody
    @GetMapping("/idcheckAjax")
    public Map idCheckAjax(String id) {
        Map map = new HashMap();
        boolean flag = false;
        if (memService.getMember(id) == null) {
            flag = true;
        }
        map.put("flag", flag);
        return map;
    }

    @GetMapping("/login")
    public void logInForm() {

    }

    @PostMapping("/login")
    public String logIn(Member member, HttpSession session, Model m) {
        Member member2 = memService.getMember(member.getId());
        if (member2 != null && member2.getPassword().equals(member.getPassword())) {
            session.setAttribute("loginId", member2.getId());
            session.setAttribute("type", member2.getType());
        } else {
            m.addAttribute("msg", "로그인 실패");
        }
        return "boardIndex";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "boardIndex";
    }

    @GetMapping("/memberInfo")
    public void memberInfoForm(Model m, HttpSession session) {
        String loggedId = (String) session.getAttribute("loginId");
        Member member2 = memService.getMember(loggedId);
        m.addAttribute("member", member2);
    }

    @PostMapping("/memberInfo")
    public void memberInfo(Model model, HttpSession session) {
        model.addAttribute("member", memService.getMember((String) session.getAttribute("loginId")));
    }

    @PostMapping("/edit")
    public String edit(Member member, HttpSession session) {
        memService.editMember(member);
        Member member2 = memService.getMember(member.getId());
        session.setAttribute("type", member2.getType());
        return "redirect:/member/";
    }

    @GetMapping("/out")
    public String out(HttpSession session) {
        String loggedId = (String) session.getAttribute("loginId");
        memService.deleteMember(loggedId);
        return "redirect:/member/logout";
    }
    //forward: 서버 내에서 이동. url 안바뀜. /member/join => index
    //redirect: 새 요청 시킴. 새 request 객체. /member/join => index
}
