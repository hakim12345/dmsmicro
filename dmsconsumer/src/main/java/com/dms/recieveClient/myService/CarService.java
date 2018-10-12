package com.dms.recieveClient.myService;

import javax.servlet.http.HttpSession;

public interface CarService {
    Integer sendMail(String tenantId, HttpSession session);

    String sendMail1(String tenantId, final HttpSession session);
}
