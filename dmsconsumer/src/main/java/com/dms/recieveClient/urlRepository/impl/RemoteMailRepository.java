package com.dms.recieveClient.urlRepository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RemoteMailRepository {

    @Autowired
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    public RemoteMailRepository(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }
}