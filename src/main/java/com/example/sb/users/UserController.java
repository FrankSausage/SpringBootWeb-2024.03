package com.example.sb.users;

import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired private UserService uSvc;

    @GetMapping("/list/{page}")
    public String list(@PathVariable int page, Model model) {
        List<User> uList = uSvc.getUserList(page);
        model.addAttribute("uList", uList);
        return "user/list";
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }
    @PostMapping("/register")
    public String registerProc(String uid, String pwd, String pwd2, String uname, String email) {
        if(uSvc.getUserByUid(uid) == null && pwd.equals(pwd2)){
            String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            User u = new User(uid, hashedPwd, uname, email);
            uSvc.registerUser(u);
        }
        return "redirect:/user/list/1";
    }
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    @PostMapping("/login")
    public String loginProc(String uid, String pwd, Model model, HttpSession session) {
        int result = uSvc.login(uid, pwd);
        String msg = null, url = null;
        if(result == uSvc.CORRECT_LOGIN){
            session.setAttribute("sessUid", uid);
            session.setAttribute("sessUname", uSvc.getUserByUid(uid).getUname());
            msg = uSvc.getUserByUid(uid).getUname() + "님 환영합니다.";
            url = "/sb/user/list/1";
        } else {
            msg = "계정이 존재하지 않거나 잘못 입력됐습니다.";
            url = "/sb/user/login";
        }
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        return "user/alertMsg";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/user/list/1";
    }

    @GetMapping("/update/{uid}")
    public String update(@PathVariable String uid, Model model) {
        User u = uSvc.getUserByUid(uid);
        model.addAttribute("user", u);
        return "user/update";
    }
    @PostMapping("/update")
    public String updateProc(String uid, String pwd, String pwd2, String uname, String email) {
        User u = uSvc.getUserByUid(uid);
        if(pwd != null && pwd.equals(pwd2)) {
            String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
            u.setPwd(hashedPwd);
            u.setUname(uname);
            u.setEmail(email);
            uSvc.updateUser(u);
        } else {
            System.out.println("에러" + uid + pwd + pwd2 + uname + email );
        }
        return "redirect:/user/list/1";
    }

    @GetMapping("/delete/{uid}")
    public String delete(@PathVariable String uid){
        uSvc.deleteUser(uid);
        return "redirect:/user/list/1";
    }
}
