package com.dms.recieveClient.service.impl;

import com.dms.recieveClient.common.MyAbstractOperation;
import com.dms.recieveClient.constants.Constants;
import com.dms.recieveClient.model.TravelOperator;
import com.dms.recieveClient.service.MailService;
import com.dms.recieveClient.service.TenantService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
@Transactional
public class MailServiceImpl extends MyAbstractOperation implements MailService {

    @Inject
    private TenantService tenantService;

    @Override
    public Map<String, Object> sendMail(String tenantId, final HttpSession session) {
        Map<String, Object> resMap = new HashMap<>();
        TravelOperator travelOperator = tenantService.getById(Long.valueOf(tenantId));
        resMap.put("travelOperator" ,travelOperator);
        resMap.put("mailStatus" , "true");
        return resMap;
    }
}
