package com.jcombat.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/profilesUser")
    public String getUserList(Model model) {
        model.addAttribute("profilesUser", userRepository.getUserList());
        return "profilesUser";
    }
}