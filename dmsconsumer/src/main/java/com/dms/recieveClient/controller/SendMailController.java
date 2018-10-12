package com.dms.recieveClient.controller;


import com.dms.recieveClient.myService.CarService;
import com.dms.recieveClient.service.MailService;
import com.dms.recieveClient.service.impl.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class SendMailController  {

    @Autowired
    private MailService mailService;

    @Autowired
   private CarService carService;


    @RequestMapping(value = "/sendMailToTravelOperator", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> sendMailToTravelOperator(final HttpServletRequest req, final HttpSession session,
                                           @RequestParam(value = "tenantId", required = false) String tenantId) {

        MailService mailService=new MailServiceImpl();
        carService.getById(1);
        Map<String, Object> resMap1= mailService.sendMail(tenantId,session);
        return resMap1;
    }



}