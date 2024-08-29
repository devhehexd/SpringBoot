package com.example.repositorydemo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public MemberDto saveMember(MemberDto memberDto) {
        Member entity = memberDao.save(new Member(memberDto.getId(), memberDto.getPassword(), memberDto.getName(),
                memberDto.getEmail(), memberDto.getType()));
        return new MemberDto(entity.getId(), entity.getPassword(), entity.getName(),
                entity.getEmail(), entity.getType());
    }

    public MemberDto getMember(String id) {
        Member entity = memberDao.findById(id).orElse(null);
        if (entity != null) {
            return new MemberDto(entity.getId(), entity.getPassword(), entity.getName(), entity.getEmail(), entity.getType());
        }
        return null;
    }

    public void deleteMember(String id) {
        memberDao.deleteById(id);
    }
}
