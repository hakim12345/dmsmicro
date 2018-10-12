package com.dms.recieveClient.service;

import com.dms.recieveClient.common.MyOperation;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface MailService extends MyOperation {

    Map<String, Object> sendMail(String tenantId, final HttpSession session);
}