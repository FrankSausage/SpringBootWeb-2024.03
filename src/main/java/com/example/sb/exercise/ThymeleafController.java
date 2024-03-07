package com.example.sb.exercise;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tl")
public class ThymeleafController {

    @GetMapping("/tag")
    public String tag(Model model){
        model.addAttribute("name", "제임스");
        model.addAttribute("data", "<b>Hello Spring</b>");
        return "thymeleaf/tag";
    }

    @GetMapping("/el")
    public String el(Model model, HttpSession session){
        Member m1 = new Member(101, "제임스", 25);
        model.addAttribute("member", m1);

        Member m2 = new Member(102, "마리아", 17);
        List<Member> mList = new ArrayList<>();
        mList.add(m1); mList.add(m2);
        model.addAttribute("memberList", mList);

        session.setAttribute("sessUname", "제임스");
        session.setAttribute("sessAge", 25);
        LocalDateTime now = LocalDateTime.now();
        model.addAttribute("now", now);
        return "thymeleaf/el";
    }

    @GetMapping("/url")
    public String url(Model model){
        model.addAttribute("uid", "james");
        model.addAttribute("page", 1);
        model.addAttribute("data", "Spring");
        return "thymeleaf/url";
    }

    @GetMapping("/params")
    @ResponseBody
    public String params(String uid, int page) {
        return "<h1>uid=" + uid + ", page=" + page + "</h1>";
    }

    @GetMapping("/iter")
    public String iter (Model model){
        List<Member> mList = new ArrayList<>();
        mList.add(new Member(101,"제임스", 25));
        mList.add(new Member(102,"마리아", 15));
        mList.add(new Member(103,"사라", 35));
        mList.add(new Member(104,"브라이언", 45));

        model.addAttribute("mList", mList);
        return "thymeleaf/iter";
    }

    @GetMapping("/cond")
    public String cond (Model model){
        List<Member> mList = new ArrayList<>();
        mList.add(new Member(101,"제임스", 25));
        mList.add(new Member(102,"마리아", 15));
        mList.add(new Member(103,"사라", 35));
        mList.add(new Member(104,"브라이언", 45));
        model.addAttribute("mList", mList);
        model.addAttribute("num1", 3);
        model.addAttribute("num2", 4);
        return "thymeleaf/cond";
    }

}
