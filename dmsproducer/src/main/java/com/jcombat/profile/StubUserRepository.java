package com.jcombat.profile;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StubUserRepository implements UserRepository{

    private Map<String, User> profileData = new HashMap<String, User>();

    public StubUserRepository()
    {
        User user = new User(1000, "Hakim","hakim@gmail.com");
        profileData.put("1000", user);
        user = new User(2000, "Prashant","prashant.deshmukh@gmail.com");
        profileData.put("2000", user);
        user = new User(3000, "Dinesh","sagar@gmail.com");
        profileData.put("3000", user);
    }

    @Override
    public List<User> getUserList() {
        return new ArrayList<User>(profileData.values());
    }

    public User getUserById(String userId)
    {
        return profileData.get(userId);
    }
}
