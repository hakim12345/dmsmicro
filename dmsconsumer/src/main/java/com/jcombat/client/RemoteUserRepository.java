package com.jcombat.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RemoteUserRepository implements UserRepository {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    public RemoteUserRepository(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }

    @Override
    public List<User> getUserList() {
        User[] users = restTemplate.getForObject(serviceUrl + "/profilesUser", User[].class);
        return Arrays.asList(users);
    }
}
