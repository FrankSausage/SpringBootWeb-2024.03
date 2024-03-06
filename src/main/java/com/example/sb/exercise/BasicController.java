package com.example.sb.exercise;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
@Controller
@RequestMapping("/ex")
public class BasicController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello")       // localhost:8090/sb/ex/hello
    public String hello() {
        return "exercise/hello";    // hello.html
    }

    @ResponseBody       // html 파일을 찾지 않고 데이터를 직접 전송
    @GetMapping("/noHtml")
    public String noHtml() {
        return "<h1>Hello Spring Boot!!</h1>";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:/ex/hello";    // Redirection
    }

    @GetMapping("/params")
    public String params(Model model) {
        model.addAttribute("name", "james");
        return "exercise/params";
    }

    @GetMapping("/params2")
    public String params2(Model model, HttpServletRequest req) {
        String name = req.getParameter("name");
        model.addAttribute("name", name);
        return "exercise/params";
    }

    @GetMapping("/params3")
    public String params3(Model model, String name, int count) {
        model.addAttribute("name", name + count);
        return "exercise/params";
    }

    @GetMapping("/memberForm")
    public String memberForm() {
       return "exercise/memberForm";
    }
    @PostMapping("/memberProc")
    public String memberProc(Model model, Member member) {
        log.info(member.toString());
        model.addAttribute("name", member.getName());
        return "exercise/params";
    }

    @GetMapping("/login")
    public String login(){
        return "exercise/login";
    }

    @PostMapping("/login")
    public String loginProc(String uid, String pwd, HttpSession session, Model model){
        String hashedPwd = "$2a$10$.y5Fa4jLAyyJXILa3AuMDeiN96pcGuARaLdX0Utdyz6bVipO6Yk9m";
        if (uid.equals("james") && BCrypt.checkpw(pwd, hashedPwd)){
            model.addAttribute("msg", uid + "님 환영합니다.");
            session.setAttribute("sessUid", uid);
            session.setAttribute("sessUname", "제임스");
            return "exercise/loginResult";
        } else {
            model.addAttribute("msg", "uid, 비밀번호를 확인하세요.");
            return "exercise/loginResult";
        }
    }

    @ResponseBody
    @GetMapping(value={"/path/{uid}/{bid}", "/path/{uid}"})
    public String path(@PathVariable String uid, @PathVariable(required = false) Integer bid){
        bid = (bid == null) ? 0 : bid;
        return "<h1>uid="+ uid +", bid="+ bid + "</h1>";
    }
}
