package com.example.sb.examTest;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberDao {

    @Select("select * from examuser where mid=#{mid}")
    Member getMemberInfo(int mid);

    @Select("select * from examuser")
    List<Member> getMemberList();

    @Insert("insert into examuser values(DEFAULT, #{name}, #{age}, #{country})")
    void insertMember(Member member);

}
