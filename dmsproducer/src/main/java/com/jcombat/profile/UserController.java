package com.jcombat.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/profilesUser")
    public User[] getAll() {
        List<User> users = userRepository.getUserList();
        return users.toArray(new User[users.size()]);
    }
}