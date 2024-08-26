package com.example.thymeleaftest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberDao memDao;

    public void addMember(Member member) {
        memDao.insert(member);
    }

    public Member getMember(String id) {
        return memDao.select(id);
    }

    public void editMember(Member member) {
        memDao.update(member);
    }

    public void deleteMember(String id) {
        memDao.delete(id);
    }
}
