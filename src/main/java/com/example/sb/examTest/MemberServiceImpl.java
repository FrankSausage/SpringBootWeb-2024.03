package com.example.sb.examTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao mDao;


    @Override
    public Member getMemberInfo(int mid) {
        return mDao.getMemberInfo(mid);
    }

    @Override
    public List<Member> getMemberList() {
        return mDao.getMemberList();
    }

    @Override
    public void insertMember(Member member) {
        mDao.insertMember(member);
    }
}
