package com.example.sb.examTest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @GetMapping("/member")
    public String memberForm(Model model) {
        Member m1 = new Member(1,"제리", 17, "미국");
        Member m2 = new Member(2,"톰", 18, "미국");
        Member m3 = new Member(3,"고길동", 48, "한국");
        Member m4 = new Member(4,"또치", 23, "영국");
        Member m5 = new Member(5,"도우너", 17, "한국");

        List<Member> mList = new ArrayList<>();
        mList.add(m1); mList.add(m2); mList.add(m3); mList.add(m4); mList.add(m5);
        model.addAttribute("memberList", mList);

        return "exam/Q1/memberList";
    }
}
