package com.example.restapitest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") //요청받을 ip주소(*: 모든 ip)
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //추가
    @PostMapping("")
    public Map add(MemberDto memberDto) {
        Map map = new HashMap();
        MemberDto memberDto2 = memberService.saveMember(memberDto);
        map.put("memDto", memberDto2);
        return map;
    }

    //수정
    @PutMapping("")
    public Map edit(MemberDto memberDto) { //id, 새 password
        Map map = new HashMap();
        MemberDto origin = memberService.getMember(memberDto.getId());
        //password만 수정
        origin.setPassword(memberDto.getPassword());
        MemberDto memDtoForEdit = memberService.saveMember(origin);
        map.put("memDto", memDtoForEdit);
        return map;
    }

    //pk로 검색
    @GetMapping("/{id}") //{id}: PathVariable /members/aaa
    public Map get(@PathVariable("id") String id) {
        Map map = new HashMap();
        MemberDto memberDto = memberService.getMember(id);
        map.put("memDto", memberDto);

        return map;
    }

    //삭제
    @DeleteMapping("/{id}")
    public Map delete(@PathVariable("id") String id) {
        Map map = new HashMap();
        boolean flag = true;
        try {
            memberService.deleteMember(id);
        } catch (Exception e) {
            System.out.println(e);
            flag = false;
        }
        map.put("flag", flag);
        return map;
    }

    //로그인
    @GetMapping("/login")
    public Map login(String id, String password) {
        Map map = new HashMap();
        boolean flag = false;
        MemberDto memberDto = memberService.getMember(id);
        if (memberDto != null && memberDto.getPassword().equals(password)) {
            flag = true;
            map.put("loginId", memberDto.getId());
            map.put("type", memberDto.getType());
        }
        map.put("flag", flag);

        return map;
    }

}
