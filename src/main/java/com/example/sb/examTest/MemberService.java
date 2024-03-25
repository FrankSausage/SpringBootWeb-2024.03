package com.example.sb.examTest;

import java.util.List;

public interface MemberService {

    Member getMemberInfo(int mid);

    List<Member>  getMemberList();

    void insertMember(Member member);
}
