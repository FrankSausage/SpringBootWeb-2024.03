package com.example.sb.examTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired MemberService mSvc;

    @GetMapping("/insert")
    public String memberInsertForm(){
        return "exam/memberInsert";
    }

    @PostMapping("/insert")
    public String memberInsertProc(String name, int age, String country){
        mSvc.insertMember(new Member(name, age, country));
        return "redirect:list";
    }

    @GetMapping("/list")
    public String memberListForm(Model model) {
        List<Member> mList = mSvc.getMemberList();
        model.addAttribute("memberList", mList);
        return "exam/memberList";
    }
}
