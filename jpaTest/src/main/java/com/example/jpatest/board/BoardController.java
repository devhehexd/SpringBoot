package com.example.jpatest.board;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    public String add(BoardDto boardDto) {
        bdService.saveBoard(boardDto);
        return "redirect:/board/list";
    }

    @ResponseBody
    @GetMapping("/getAjax")
    public Map getAjax(int num) {
        BoardDto board = bdService.getBoard(num);
        Map map = new HashMap();
        map.put("num", board.getNum());
        map.put("writer", board.getWriter());
        map.put("wdate", board.getWdate() + ""); //String형태로 바꿔줘야 javascript에서 인식함
        map.put("title", board.getTitle());
        map.put("content", board.getContent());
        return map;
    }

    @GetMapping("/detail")
    public String detail(int num, Model model, HttpServletRequest req, HttpServletResponse resp) {
        model.addAttribute("board", bdService.getBoard(num));
        String val = "";
        Cookie[] cookies = req.getCookies();
        for (Cookie cc : cookies) {
            if (cc.getName().equals("readPosts")) {
                val = cc.getValue();
                if (!val.contains(String.valueOf(num))) {
                    val += "/" + num;
                }
            }
        }
        if (val.equals("")) {
            val = num + "";
        }
        resp.addCookie(new Cookie("readPosts", val));
        return "board/detail";
    }

    @GetMapping("/readlist")
    public String readlist(Model model, HttpServletRequest request) {

        String val = "";
        Cookie[] cookies = request.getCookies();

        for (Cookie cc : cookies) {
            if (cc.getName().equals("readPosts")) {
                val = cc.getValue();
            }
        }

        List<BoardDto> list = new ArrayList<>();
        if (!val.equals("")) {
            String[] strArr = val.split("/");
            for (String s : strArr) {
                int bdNum = Integer.parseInt(s);
                list.add(bdService.getBoard(bdNum));
            }
        }
        Collections.sort(list, Comparator.comparing(BoardDto::getNum));
        model.addAttribute("list", list);
        return "/board/list";
    }

    @PostMapping("/edit")
    public String edit(BoardDto boardDto) {
        //수정하기 전 원본 글 정보검색. save()는 모든 컬럼을 수정하기 때문에 누락된 값은 null로 수정됨
        BoardDto oldBoard = bdService.getBoard(boardDto.getNum());
        oldBoard.setTitle(boardDto.getTitle()); //원본 글 제목
        oldBoard.setContent(boardDto.getContent()); //원본 글 내용
        bdService.saveBoard(oldBoard);
        return "redirect:/board/list";
    }

    @GetMapping("/delete")
    public String delete(int num) {
        bdService.deleteBoard(num);
        return "redirect:/board/list";
    }

    @GetMapping("/getbytitle")
    public String getbytitle(String title, Model model) {
        model.addAttribute("list", bdService.getByTitle(title));
        return "/board/list";
    }

    @GetMapping("/getbywriter")
    public String getbywriter(String writer, Model model) {
        model.addAttribute("list", bdService.getByWriter(writer));
        return "/board/list";
    }

}

