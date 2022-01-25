package com.spring.captchalearn.controller;

import cn.apiclub.captcha.Captcha;
import com.spring.captchalearn.model.User;
import com.spring.captchalearn.service.UserCaptchaService;
import com.spring.captchalearn.util.CaptchaUtil;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    @Autowired
    UserCaptchaService userCaptchaService;

    @GetMapping("/register")
    public String registerUser(Model model){
        User user=new User();
        getCaptcha(user);
        model.addAttribute("user",user);
        return "registerUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user,Model model){
        if (user.getCaptcha().equals(user.getHiddenCaptcha())){
            userCaptchaService.createUser(user);
            model.addAttribute("message","User is Registering");
            return "redirect:allUsers";
        }else {
            model.addAttribute("message","Invalid Captcha");
            getCaptcha(user);
            model.addAttribute("user",user);
        }
        return "registerUser";
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model){
        List<User> userList=userCaptchaService.getUsers();
        model.addAttribute("userList",userList);
        return "listUsers";
    }

    private void getCaptcha(User user) {
        Captcha captcha= CaptchaUtil.create(240,70);
        user.setHiddenCaptcha(captcha.getAnswer());
        user.setCaptcha("");
        user.setRealCaptcha(CaptchaUtil.encode(captcha));

    }
}
