package com.example.restapitest.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    //글 작성
    @PostMapping("")
    public Map add(BoardDto boardDto) {
        Map map = new HashMap();
        boolean flag = true;

        try {
            boardService.saveBoard(boardDto);
        } catch (Exception e) {
            System.out.println(e);
            flag = false;
        }
        map.put("flag", flag);

        return map;
    }

    //번호로 검색
    @GetMapping("/{num}")
    public Map get(@PathVariable("num") int num) {
        Map map = new HashMap();
        BoardDto boardDto = boardService.getBoard(num);
        map.put("boardDto", boardDto);
        return map;
    }

    //전체 검색
    @GetMapping("")
    public Map getAll() {
        Map map = new HashMap();
        List<BoardDto> list = boardService.getAllBoards();
        map.put("list", list);
        return map;
    }

    //제목으로 검색
    @GetMapping("/title/{title}")
    public Map getByTitle(@PathVariable("title") String title) {
        Map map = new HashMap();
        List<BoardDto> list = boardService.getByTitle(title);
        map.put("list", list);
        return map;
    }

    //작성자로 검색
    @GetMapping("/writer/{writer}")
    public Map getByWriter(@PathVariable("writer") String writer) {
        Map map = new HashMap();
        List<BoardDto> list = boardService.getByWriter(writer);
        map.put("list", list);
        return map;
    }

    //글 수정
    @PutMapping("")
    public Map edit(BoardDto boardDto) { //title, content 수정
        Map map = new HashMap();
        BoardDto origin = boardService.getBoard(boardDto.getNum());
        origin.setTitle(boardDto.getTitle());
        origin.setContent(boardDto.getContent());
        boolean flag = true;

        try {
            boardService.saveBoard(origin);
        } catch (Exception e) {
            System.out.println(e);
            flag = false;
        }
        map.put("flag", flag);

        return map;
    }

    //글 삭제
    @DeleteMapping("/{num}")
    public Map delete(@PathVariable("num") int num) {
        Map map = new HashMap();
        boolean flag = true;

        try {
            boardService.deleteBoard(num);
        }catch (Exception e) {
            System.out.println(e);
            flag = false;
        }
        map.put("flag", flag);
        return map;
    }

}
