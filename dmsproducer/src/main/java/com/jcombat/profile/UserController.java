package com.jcombat.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/userDetail")
    public User getUserById(@RequestParam("id") String userId)
    {
        User user = userRepository.getUserById(userId);
        return user;
    }
}