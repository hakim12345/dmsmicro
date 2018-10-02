package com.jcombat.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/profilesUser")
    public String getUserList(Model model) {
        model.addAttribute("profilesUser", userRepository.getUserList());
        return "profilesUser";
    }


        @GetMapping(value ="/userDetail")
    public String getUser(@RequestParam("id") String userId, Model model)
    {
        model.addAttribute("user",userRepository.getUser(userId));
        return "user";
    }
}