package com.example.sessiontest.board;

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
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService bdService;

    @RequestMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", bdService.getAllBoards());
    }

    @GetMapping("/add")
    public void addForm() {

    }

    @PostMapping("/add")
    public String add(Board board) {
        bdService.addBoard(board);
        return "redirect:/board/list";
    }

    @ResponseBody
    @GetMapping("/getAjax")
    public Map getAjax(int num) {
        Board board = bdService.getBoard(num);
        Map map = new HashMap();
        map.put("num", board.getNum());
        map.put("writer", board.getWriter());
        map.put("wdate", board.getWdate() + ""); //String형태로 바꿔줘야 javascript에서 인식함
        map.put("title", board.getTitle());
        map.put("content", board.getContent());
        return map;
    }

    @GetMapping("/detail")
    public void detail(int num, Model model) {
        model.addAttribute("board", bdService.getBoard(num));
    }

    @PostMapping("/edit")
    public String edit(Board board) {
        System.out.println(board);
        bdService.editBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String delete(int num) {
        bdService.deleteBoard(num);
        return "redirect:/board/list";
    }
}
