package com.dms.recieveClient.myService.impl;

import com.dms.recieveClient.model.TravelOperator;
import com.dms.recieveClient.myService.CarService;
import com.dms.recieveClient.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class   CarServiceImpl implements CarService {

    @Autowired
    TenantService tenantService;

    @Override
    public Integer sendMail(String tenantId, HttpSession session) {
        return 2;
    }


    @Override
    public String sendMail1(String tenantId, final HttpSession session) {
        Map<String, Object> resMap = new HashMap<>();
        TravelOperator travelOperator = tenantService.getById(Long.valueOf(tenantId));
        resMap.put("travelOperator" ,travelOperator);
        resMap.put("mailStatus" , "true");
        return "vhdbhye";
    }
}
